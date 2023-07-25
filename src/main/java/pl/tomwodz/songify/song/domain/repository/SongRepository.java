package pl.tomwodz.songify.song.domain.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.songify.song.domain.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends Repository<Song,Long> {
    Song save(Song song);

    @Query("SELECT s FROM Song s")
    List<Song> findAll();

    @Query("SELECT s FROM Song s WHERE  s.id =:id")
    Optional<Song> findById(Long id);

    @Modifying
    @Query("DELETE FROM Song s WHERE s.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Song s SET s.name = :#{#newSong.name}, s.artist = :#{#newSong.artist} WHERE s.id = :id")
    void updateById(Long id, Song newSong);

    boolean existsById(Long id);
}
