package com.nospoon.samplemultiplayer.api;

import com.nospoon.jpromises.Promise;
import com.nospoon.jpromises.Promises;
import com.nospoon.vertxserver.core.dbapi.DBApi;
import com.nospoon.vertxserver.core.model.Player;

/**
 * Created by Nestor on 8/1/2016.
 */
public class FakeMultiplayerDBApi implements DBApi {

    public Promise<Boolean> loginRequest(String userID, String session)
    {
        return Promises.resolve(Boolean.TRUE);
    }

    public String getMultiplayerID(String sessionID)
    {
        return sessionID.substring(0,4);
    }

}
