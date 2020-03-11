package com.revaturelabs.ap2.batch.track;

import com.revaturelabs.ap2.batch.track.dto.TrackDTO;

public class TrackAdapter {
    private TrackAdapter() {}

    public static TrackDTO adapt(Track track){
        return new TrackDTO(track.getId(), track.getName());
    }

    public static Track adapt(TrackDTO track){
        return new Track(track.getId(), track.getName());
    }
}
