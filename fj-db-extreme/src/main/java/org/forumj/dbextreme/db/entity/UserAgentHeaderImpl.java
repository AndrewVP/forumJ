package org.forumj.dbextreme.db.entity;

import org.forumj.common.db.entity.Device;
import org.forumj.common.db.entity.UserAgentHeader;

/**
 * Created by Andrew on 20/05/2017.
 */
public class UserAgentHeaderImpl implements UserAgentHeader {

    private Long id;

    private String value;

    private int botId;

    private int osId;

    private int browserId;

    private Device device;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getBotId() {
        return botId;
    }

    @Override
    public void setBotId(int botId) {
        this.botId = botId;
    }

    @Override
    public int getOsId() {
        return osId;
    }

    @Override
    public void setOsId(int osId) {
        this.osId = osId;
    }

    @Override
    public int getBrowserId() {
        return browserId;
    }

    @Override
    public void setBrowserId(int browserId) {
        this.browserId = browserId;
    }

    @Override
    public Device getDevice() {
        return device;
    }

    @Override
    public void setDevice(Device device) {
        this.device = device;
    }
}


