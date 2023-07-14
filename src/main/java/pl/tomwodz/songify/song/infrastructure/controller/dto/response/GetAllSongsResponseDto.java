package pl.tomwodz.songify.song.infrastructure.controller.dto.response;


import pl.tomwodz.songify.song.domain.model.Song;

import java.util.Map;

public record GetAllSongsResponseDto(Map<Integer, Song> songs) {
}
