package org.forumj.dbextreme.db.entity;

import org.forumj.common.db.entity.Bot;
import org.forumj.common.db.entity.BotVendor;

/**
 * Created by Andrew on 12/05/2017.
 */
public class BotImpl implements Bot {

    private Long id;

    private String name;

    private BotVendor botVendor;

    @Override
    public BotVendor getBotVendor() {
        return botVendor;
    }

    @Override
    public void setBotVendor(BotVendor botVendor) {
        this.botVendor = botVendor;
    }

    public BotImpl(String name) {
        this.name = name;
    }

    public BotImpl() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
