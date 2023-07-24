package pl.tomwodz.songify.song.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.songify.song.domain.repository.SongRepository;

@Service
@Log4j2
public class SongDeleter {
    private final SongRepository songRepository;
    SongDeleter(SongRepository  songRepository) {
        this.songRepository = songRepository;
    }


    public void deletebyId(Long id) {
        log.info("deleting song by id: " + id);
        songRepository.deleteById(id);
    }
}
