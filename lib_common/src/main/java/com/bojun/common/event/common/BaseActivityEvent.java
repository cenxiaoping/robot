package com.bojun.common.event.common;

import com.bojun.common.event.BaseEvent;

/**
 * BaseActivityEvent
 */
public class BaseActivityEvent<T> extends BaseEvent<T> {
    public BaseActivityEvent(int code) {
        super(code);
    }
}
