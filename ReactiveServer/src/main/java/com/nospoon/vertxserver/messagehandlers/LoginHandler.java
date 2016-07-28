package com.nospoon.vertxserver.messagehandlers;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.core.messagehandlers.MessageHandler;
import com.nospoon.vertxserver.core.model.Player;

/**
 * Created by Nestor on 7/26/2016.
 */
public class LoginHandler extends MessageHandler {


    public Promise<Void> handleLoginProcess(Object request, Player player) {


        return Promises.resolve(null);

    }

    @Override
    public void playerDetached(Player player) {

    }


    @Override
    public void playerAttached(Player player) {

    }

    @Override
    public void onStart() {

    }
}
