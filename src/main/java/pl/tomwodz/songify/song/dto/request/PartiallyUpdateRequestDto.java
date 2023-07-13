package pl.tomwodz.songify.song.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PartiallyUpdateRequestDto(
        String songName,
        String artist) {
}
