package pl.tomwodz.songify.song.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.songify.song.domain.service.SongAdder;
import pl.tomwodz.songify.song.domain.service.SongRetriever;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.PartiallyUpdateRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.response.*;
import pl.tomwodz.songify.song.domain.model.SongNotFoundException;
import pl.tomwodz.songify.song.domain.model.Song;

import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {



    private final SongAdder songAdder;
    private final SongRetriever songRetriever;

    public SongRestController(SongAdder songAdder, SongRetriever songRetriever) {
        this.songAdder = songAdder;
        this.songRetriever = songRetriever;
    }

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (limit != null) {
            Map<Integer, Song> limitedMap = songRetriever.findAllLimitedBy(limit);
            GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }




    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getAllSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found.");
        }
       Song song = allSongs.get(id);
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
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found.");
        }
        allSongs.remove(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request){
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found.");
        }
        Song newSong = SongMapper.mapFromUpDateSongRequestDtoToSong(request);
        Song oldSong =  allSongs.put(id, newSong);
        log.info("Updated song with id: " + id +
                        " with song name: " + oldSong.name() + " to new SongName: " + newSong.name() +
                " old Artist: " + oldSong.artist() + " to new Artist: " + newSong.artist());
        UpdateSongResponseDto body = SongMapper.mapFromSongtoUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateRequestDto request) {
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs .containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found.");
        }
        Song songFromDatabase = allSongs .get(id);
        Song updatedSong = SongMapper.mapFromPartiallyUpdateSongRequestDtoToSong(request);
        Song.SongBuilder builder = Song.builder();
        if (request.songName() != null) {
            builder.name(updatedSong.name());
            log.info("partially updated song name");
        } else {
            builder.name(songFromDatabase.name());
        }
        if (request.artist() != null) {
            builder.artist(updatedSong.artist());
            log.info("partially updated song artist");
        } else {
            builder.artist(songFromDatabase.artist());
        }
        allSongs .put(id,updatedSong);
        PartiallyUpdateSongResponseDto body = SongMapper.mapFromSongToPartiallyUpdateSongResponseDto(updatedSong);
        return ResponseEntity.ok(body);
    }



}


