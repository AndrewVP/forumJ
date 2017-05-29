package org.forumj.dbextreme.db.service;

import org.forumj.common.db.entity.*;
import org.forumj.common.db.service.FJServiceHolder;
import org.forumj.common.db.service.IpAddressService;
import org.forumj.common.db.service.RequestService;
import org.forumj.common.web.HttpMethod;
import org.forumj.dbextreme.db.dao.HttpCookieDao;
import org.forumj.dbextreme.db.dao.HttpHeaderDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by Andrew on 12/05/2017.
 */
public class RequestServiceImpl extends FJService implements RequestService {

    @Override
    public Request getObject() {
        return getRequestDao().getObject();
    }

    @Override
    public void create(Request request) throws Exception {
        if (request.getIp().getId() == null){
            String ip = request.getIp().getIp();
            Long ipId = getIpAddressDao().find(ip);
            if (ipId == null){
                getIpAddressDao().create(request.getIp());
            }
            request.getIp().setId(ipId);
        }
        if(request.getUas() != null){
            if (request.getUas().getId() == null){
                String uasValue = request.getUas().getValue();
                UserAgentHeader uas = getUserAgentHeaderDao().readByValue(uasValue);
                if (uas != null){
                    request.setUas(uas);
                    request.setBotId(uas.getBotId());
                }else{
                    uas = getUserAgentHeaderDao().getObject();
                    request.setBotId(Bot.NOT_BOT);
                    uas.setBotId(Bot.NOT_BOT);
                    uas.setValue(uasValue);
                    uas.setBrowserId(ClientBrowser.UNKNOWN_BROWSER_ID);
                    uas.setOsId(ClientOS.UNKNOWN_OS_ID);
                    uas.setDevice(Device.UNKNOWN_DEVICE);
                    getUserAgentHeaderDao().create(uas);
                }
            }
        }
        getRequestDao().create(request);
        request.getCookies().stream().forEach(
                httpCookie -> {
                    httpCookie.setRequestId(request.getId());
                    try {
                        Long cookieNameId = getHttpCookieNameDao().find(httpCookie.getName().getName());
                        if (cookieNameId == null){
                            getHttpCookieNameDao().create(httpCookie.getName());
                        }else{
                            httpCookie.getName().setId(cookieNameId);
                        }
                        getHttpCookieDao().create(httpCookie);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        request.getHeaders().stream().forEach(
                httpHeader -> {
                    httpHeader.setRequestId(request.getId());
                    try {
                        Long headerNameId = getHttpHeaderNameDao().find(httpHeader.getName().getName());
                        if (headerNameId == null){
                            getHttpHeaderNameDao().create(httpHeader.getName());
                        }else{
                            httpHeader.getName().setId(headerNameId);
                        }
                        getHttpHeaderDao().create(httpHeader);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public void create(HttpServletRequest httpServletRequest, IUser user) throws Exception {
        IpAddressService ipAddressService = FJServiceHolder.getIpAddressService();
        HttpHeaderDao httpHeaderDao = getHttpHeaderDao();
        HttpCookieDao httpCookieDao = getHttpCookieDao();
        IpAddress ipAddress = ipAddressService.getObject();
        ipAddress.setIp(httpServletRequest.getRemoteAddr());
        Request request = getObject();
        request.setTime(System.currentTimeMillis());
        String method = httpServletRequest.getMethod();
        request.setMethod(HttpMethod.valueOf(method));
        request.setIp(ipAddress);
        if (user != null){
            request.setUserId(user.getId());
        }else{
            request.setUserId(-1l);
        }
        request.setUrl(httpServletRequest.getRequestURI());
        Enumeration<String> headers = httpServletRequest.getHeaderNames();
        while (headers.hasMoreElements()){
            String headerName = headers.nextElement();
            String headerValue = httpServletRequest.getHeader(headerName);
            if (headerName.equals(HttpHeaderName.UAS)){
                UserAgentHeader userAgentHeader = getUserAgentHeaderDao().getObject();
                userAgentHeader.setValue(headerValue);
                request.setUas(userAgentHeader);
            }else{
                HttpHeader httpHeader = httpHeaderDao.getObject();
                httpHeader.setValue(headerValue);
                httpHeader.setName(headerName);
                request.addHeader(httpHeader);
            }
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length > 0){
            Arrays.stream(cookies).forEach(cookie -> request.addCookie(httpCookieDao.getObject().withCookie(cookie)));
        }
        create(request);
    }
}
