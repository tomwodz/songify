package pl.tomwodz.songify.song.domain.repository;

import org.springframework.data.repository.Repository;
import pl.tomwodz.songify.song.domain.model.Song;

import java.util.List;

public interface SongRepository extends Repository<Song,Long> {
    Song save(Song song);
    List<Song> findAll();
}
