package pl.tomwodz.songify.song.domain.repository;

import pl.tomwodz.songify.song.domain.model.Song;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongRepositoryInMemory implements SongRepository {

    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("shawnmendes song1", "Shawn Mendes"),
            2, new Song("ariana grande song2","Ariana Grande"),
            3, new Song("test","Shawn Mendes"),
            4, new Song("test 2", "Shawn Mendes")
    ));
    @Override
   public Song save(Song song) {
        database.put(database.size() + 1, song);
        return song;
    }
    @Override
    public List<Song> findAll() {
        return database.values()
                .stream()
                .toList();
    }


}
