package pl.tomwodz.songify;


import java.util.Map;

public record SongResponseDto(Map<Integer, String> songs) {
}
