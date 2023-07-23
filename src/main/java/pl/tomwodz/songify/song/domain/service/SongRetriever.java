package pl.tomwodz.songify.song.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.songify.song.domain.model.Song;
import pl.tomwodz.songify.song.domain.repository.SongRepository;
import java.util.List;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;

    SongRetriever(SongRepository  songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll(){
        log.info("retrieving all songs: ");
        return songRepository.findAll();
    }

    public List<Song>  findAllLimitedBy(Integer limit){
        return songRepository.findAll()
                .stream()
                .limit(limit)
                .toList();
    }
}
