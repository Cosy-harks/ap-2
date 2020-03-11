package com.revaturelabs.ap2.batch.track;

import com.revaturelabs.ap2.batch.track.dto.TrackDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("*/track")
public class TrackController {
    private TrackService trackService;

    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<List<TrackDTO>> getAllTracks(){
        //ResponseEntity get control of response
        return this.trackService.findAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

    }

    @PostMapping
    public ResponseEntity<TrackDTO> createTrack(TrackDTO trackDTO){
        return this.trackService.save(trackDTO)
            .map(dto -> ResponseEntity.created(URI.create("/track/" + dto.getId())).body(dto))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
/*
        Optional<TrackDTO> savedTrack = this.trackService.save(trackDTO);

        if(savedTrack.isPresent()){
            return ResponseEntity.created(URI.create("/track/" + savedTrack.get().getId())).body(savedTrack.get());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();*/
    }
}
