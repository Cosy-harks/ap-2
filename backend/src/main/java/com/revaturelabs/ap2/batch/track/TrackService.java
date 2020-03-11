package com.revaturelabs.ap2.batch.track;

import com.revaturelabs.ap2.batch.track.dto.TrackDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackService {

	private TrackRepository trackRepository;

	public TrackService(TrackRepository trackRepository){
		this.trackRepository = trackRepository;
	}

	public Optional<List<TrackDTO>> findAll(){
		try {
			//streams are good for changing data
			return Optional.of(this.trackRepository.findAll().stream()
					.map(TrackAdapter::adapt)
					.collect(Collectors.toList()));
		}catch(Exception e){
			return Optional.empty();
		}
	}

	//Don't pass Track object to save
	//company grows audit fields [created by, updated when]
	//DTO Data Transfer Object designed to conform to frontend
	//DtoEntityAdapter from DTO to Entity
	public Optional<TrackDTO> save(TrackDTO trackDTO){
		Track track = TrackAdapter.adapt(trackDTO);
		try {
			track = this.trackRepository.save(track);
		} catch (Exception e){
			//broken business rule Throw exception
			//If server issue give them nothing
			return Optional.empty();
		}
		return Optional.of(TrackAdapter.adapt(track));
	}
}
