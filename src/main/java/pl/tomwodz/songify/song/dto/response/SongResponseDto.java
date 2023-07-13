package pl.tomwodz.songify.song.dto.response;


import pl.tomwodz.songify.song.controller.Song;

import java.util.Map;

public record SongResponseDto(Map<Integer, Song> songs) {
}
