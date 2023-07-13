package pl.tomwodz.songify.song.controller;

import lombok.Builder;

@Builder
public record Song(String name, String artist) {
}
