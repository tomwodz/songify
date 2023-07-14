package pl.tomwodz.songify.song.infrastructure.controller.dto.request;

public record PartiallyUpdateRequestDto(
        String songName,
        String artist) {
}
