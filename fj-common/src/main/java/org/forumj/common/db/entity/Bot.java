package org.forumj.common.db.entity;

/**
 * Created by Andrew on 12/05/2017.
 */
public interface Bot extends NamedEntity {

    int NOT_BOT = 0;
    int UNKNOWN_BOT = 1;

    BotVendor getBotVendor();

    void setBotVendor(BotVendor botVendor);
}
