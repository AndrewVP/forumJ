package org.forumj.common.db.entity;

/**
 * Created by Andrew on 20/05/2017.
 */
public interface UserAgentHeader extends Entity{
    String getValue();

    void setValue(String value);

    int getBotId();

    void setBotId(int botId);

    int getOsId();

    void setOsId(int osId);

    int getBrowserId();

    void setBrowserId(int browserId);

    Device getDevice();

    void setDevice(Device device);
}
