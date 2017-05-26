package org.forumj.common.db.entity;

/**
 * Created by Andrew on 20/05/2017.
 */
public interface NamedEntity extends Entity {

    String getName();

    void setName(String name);
}
