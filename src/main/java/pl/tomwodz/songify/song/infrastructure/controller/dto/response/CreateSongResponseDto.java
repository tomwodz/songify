package pl.tomwodz.songify.song.infrastructure.controller.dto.response;

import pl.tomwodz.songify.song.domain.model.Song;

public record CreateSongResponseDto(Song song) {
}
