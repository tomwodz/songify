package pl.tomwodz.songify.song.domain.repository;

import org.springframework.stereotype.Repository;
import pl.tomwodz.songify.song.domain.model.Song;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SongRepository {

    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("shawnmendes song1", "Shawn Mendes"),
            2, new Song("ariana grande song2","Ariana Grande"),
            3, new Song("test","Shawn Mendes"),
            4, new Song("test 2", "Shawn Mendes")
    ));
   public Song saveToDatabase(Song song) {
        database.put(database.size() + 1, song);
        return song;
    }
    public Map<Integer,Song> findAll() {
        return database;
    }


}
