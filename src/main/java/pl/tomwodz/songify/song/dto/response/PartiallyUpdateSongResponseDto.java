package pl.tomwodz.songify.song.dto.response;

import pl.tomwodz.songify.song.controller.Song;

public record PartiallyUpdateSongResponseDto(Song updatedSong){}
