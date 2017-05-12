package org.forumj.common.web;

/**
 * Created by Andrew on 12/05/2017.
 */
public enum HttpMethod {
    GET(1), POST(2), PUT(3), DELETE(4), HEAD(5), OPTIONS(6), TRACE(7);

    private int id;

    HttpMethod(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
