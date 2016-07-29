package com.nospoon.vertxserver.core.model;

import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nestor on 7/26/2016.
 */
public class AttachedHandlers {

    private Map<Class,MessageHandler> handlers;

    public AttachedHandlers() {
        handlers = new HashMap<>();

    }

    public MessageHandler addHandler(MessageHandler handler)
    {
        return handlers.put(handler.getClass(),handler);
    }

    public MessageHandler removeHandler(Class handlerClass)
    {
        return handlers.remove(handlerClass);
    }

    public List<Class> getHandlerTypes() {
        return handlers.keySet().stream().collect(Collectors.toList());
    }

    public List<MessageHandler> getHandlers() {
        return handlers.values().stream().collect(Collectors.toList());
    }

}
