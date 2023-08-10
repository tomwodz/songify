package pl.tomwodz.songify.song.infrastructure.controller.dto.response;
import pl.tomwodz.songify.song.domain.model.Song;
import java.util.List;

public record GetAllSongsResponseDto(List<Song> songs) {
}
