package pl.tomwodz.songify.song.domain.service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.songify.song.domain.model.Song;
import pl.tomwodz.songify.song.domain.repository.SongRepository;

@Service
@Log4j2
@Transactional
public class SongUpdater {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;
    SongUpdater(SongRepository  songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    public void updateById(Long id, Song newSong) {
        songRetriever.existsById(id);
        songRepository.updateById(id, newSong);
    }

    public Song updatePartiallyById(Long id, Song songFromRequest) {
        Song songFromDatabase = songRetriever.findSongById(id);
        Song.SongBuilder builder = Song.builder();
        if (songFromRequest.getName() != null) {
            builder.name(songFromRequest.getName());
            log.info("partially updated song name " + id);
        } else {
            builder.name(songFromDatabase.getName());
        }
        if (songFromRequest.getArtist() != null) {
            builder.artist(songFromRequest.getArtist());
            log.info("partially updated song artist " + id);
        } else {
            builder.artist(songFromDatabase.getArtist());
        }
        Song toSave = builder.build();
        updateById(id, toSave);
        return toSave;
    }

    //dirty checking
/*    public void updateById(Long id, Song newSong) {
        Song songById = songRetriever.findSongById(id);
        songById.setName(newSong.getName());
        songById.setArtist(newSong.getArtist());
    }

    public Song updatePartiallyById(Long id, Song songFromRequest) {
        Song songFromDatabase = songRetriever.findSongById(id);
        if (songFromRequest.getName() != null) {
            songFromDatabase.setName(songFromRequest.getName());
            log.info("partially updated song name " + id);
        }
        if (songFromRequest.getArtist() != null) {
            songFromDatabase.setArtist(songFromRequest.getArtist());
            log.info("partially updated song artist " + id);
        }
        return songFromDatabase;
    }*/
}
