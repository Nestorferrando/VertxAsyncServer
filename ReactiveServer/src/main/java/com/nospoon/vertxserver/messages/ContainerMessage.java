package com.nospoon.vertxserver.messages;

/**
 * Created by Nestor on 7/27/2016.
 */
public class ContainerMessage {

    private String fullyQualifiedMessageName;
    private String serializedMessage;

    public ContainerMessage(String fullyQualifiedMessageName, String serializedMessage) {
        this.fullyQualifiedMessageName = fullyQualifiedMessageName;
        this.serializedMessage = serializedMessage;
    }

    public String getFullyQualifiedMessageName() {
        return fullyQualifiedMessageName;
    }

    public String getSerializedMessage() {
        return serializedMessage;
    }
}
