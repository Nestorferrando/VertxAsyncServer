package com.nospoon.samplemultiplayer.messages.fromserver.hall;

import java.util.List;

/**
 * Created by Nestor on 22/08/2016.
 */
public class GetRoomsResponse {
    private List<String> roomIDs;
    private List<Integer> roomPlayers;
    private List<Integer> maxRoomSize;


    public GetRoomsResponse(List<String> roomIDs, List<Integer> roomPlayers, List<Integer> maxRoomSize) {
        this.roomIDs = roomIDs;
        this.roomPlayers = roomPlayers;
        this.maxRoomSize = maxRoomSize;
    }

    public List<String> getRoomIDs() {
        return roomIDs;
    }

    public List<Integer> getRoomPlayers() {
        return roomPlayers;
    }

    public List<Integer> getMaxRoomSize() {
        return maxRoomSize;
    }
}
