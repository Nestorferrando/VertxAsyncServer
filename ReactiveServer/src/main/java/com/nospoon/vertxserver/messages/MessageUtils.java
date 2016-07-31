package com.nospoon.vertxserver.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nestor on 7/31/2016.
 */
public class MessageUtils {

    private static Gson gson = new GsonBuilder().create();

    public static List<Object> deserialize(String serializedData) {
        List<Object> data = new ArrayList<>();
        splitBufferIntoJSONS(serializedData).forEach(serializedMsg -> {
            try {
                ContainerMessage container = gson.fromJson(serializedMsg, ContainerMessage.class);
                Class c = Class.forName(container.getFullyQualifiedMessageName());
                data.add(gson.fromJson(container.getSerializedMessage(), c));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return data;
    }

    public static String serialize(Object msg) {
        String name = msg.getClass().getCanonicalName();
        return gson.toJson(new ContainerMessage(name, gson.toJson(msg)));
    }

    private static List<String> splitBufferIntoJSONS(String buffer) {
        List<String> splitted = new ArrayList<>();
        String bufferTemp = buffer;
        int tokenIndex = bufferTemp.indexOf("}{");
        while (tokenIndex > -1) {
            splitted.add(bufferTemp.substring(0, tokenIndex + 1));
            bufferTemp = bufferTemp.substring(tokenIndex + 1, bufferTemp.length());
            tokenIndex = bufferTemp.indexOf("}{");
        }
        splitted.add(bufferTemp);
        return splitted;
    }


}

