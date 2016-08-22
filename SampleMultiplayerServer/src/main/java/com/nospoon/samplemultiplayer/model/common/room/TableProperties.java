package com.nospoon.samplemultiplayer.model.common.room;

/**
 * Created by Nestor on 8/16/2016.
 */
public interface TableProperties<T,Q,S> {

    Class<T> getGameType();

    Q getGameConfig(S gameSelections);


}