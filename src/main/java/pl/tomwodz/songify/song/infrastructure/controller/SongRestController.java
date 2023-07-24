package pl.tomwodz.songify.song.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.songify.song.domain.service.SongAdder;
import pl.tomwodz.songify.song.domain.service.SongDeleter;
import pl.tomwodz.songify.song.domain.service.SongRetriever;
import pl.tomwodz.songify.song.domain.service.SongUpdater;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.PartiallyUpdateRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.response.*;
import pl.tomwodz.songify.song.domain.model.SongNotFoundException;
import pl.tomwodz.songify.song.domain.model.Song;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/songs")
public class SongRestController {

    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final SongDeleter songDeleter;
    private final SongUpdater songUpdater;

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        List<Song> allSongs = songRetriever.findAll();
        if (limit != null) {
            List<Song>  limitedMap = songRetriever.findAllLimitedBy(limit);
            GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getAllSongById(@PathVariable Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        Song song = songRetriever.findSongById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " not found."));
        GetSongResponseDto response = SongMapper.mapFromSongToGetResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        Song song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        songAdder.addSong(song);
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateResponseDto(song);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songRetriever.existsById(id);
        songDeleter.deletebyId(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateSongRequestDto request){
        songRetriever.existsById(id);
        Song newSong = SongMapper.mapFromUpDateSongRequestDtoToSong(request);
        songUpdater.updateById(id, newSong);
        log.info("Updated song with id: " + id);
        UpdateSongResponseDto body = SongMapper.mapFromSongtoUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateRequestDto request) {
        List<Song>  allSongs = songRetriever.findAll();
        if (!allSongs.contains(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found.");
        }
        log.info("song exist");
        Song songFromDatabase = allSongs .get(id);
        Song updatedSong = SongMapper.mapFromPartiallyUpdateSongRequestDtoToSong(request);
        Song.SongBuilder builder = Song.builder();
        if (request.songName() != null) {
            builder.name(updatedSong.getName());
            log.info("partially updated song name");
        } else {
            builder.name(songFromDatabase.getName());
        }
        if (request.artist() != null) {
            builder.artist(updatedSong.getArtist());
            log.info("partially updated song artist");
        } else {
            builder.artist(songFromDatabase.getArtist());
        }
        songAdder.addSong(updatedSong);
        PartiallyUpdateSongResponseDto body = SongMapper.mapFromSongToPartiallyUpdateSongResponseDto(updatedSong);
        return ResponseEntity.ok(body);
    }

}


