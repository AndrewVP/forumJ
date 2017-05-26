package org.forumj.common.db.entity;

import java.util.Arrays;

/**
 * Created by Andrew on 19/05/2017.
 */
public enum Device {
    NO_DEVICE(0)
    , UNKNOWN_DEVICE(10)
    , DESKTOP(20)
    , TABLET(30)
    , PHONE(40)
    , GAME_CONSOLE(50)
    , SMART_TV(60)
    , WEAREBLE(70) // Google Glass
    ;



    Device(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public static Device fromId(int id){
        return Arrays.stream(values()).filter(
                device -> device.getId() == id
        ).findAny().orElseThrow(
                () -> new RuntimeException("Wrong Device id value")
        );
    }
}
