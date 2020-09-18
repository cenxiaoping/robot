package com.bojun.common.event.common;

import com.bojun.common.event.BaseEvent;

/**
 * BaseFragmentEvent
 */
public class BaseFragmentEvent<T> extends BaseEvent<T> {
    public BaseFragmentEvent(int code) {
        super(code);
    }
}
