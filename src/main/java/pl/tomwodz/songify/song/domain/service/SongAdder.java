package pl.tomwodz.songify.song.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.songify.song.domain.model.Song;
import pl.tomwodz.songify.song.domain.repository.SongRepository;
import pl.tomwodz.songify.song.domain.repository.SongRepositoryInMemory;

@Log4j2
@Service
public class SongAdder {
private final SongRepository songRepository;

    SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song addSong(Song song) {
        log.info("adding new song: " + song);
        songRepository.save(song);
        return song;
    }

}
