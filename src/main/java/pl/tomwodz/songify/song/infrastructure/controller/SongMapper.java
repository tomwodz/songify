package pl.tomwodz.songify.song.infrastructure.controller;

import org.springframework.http.HttpStatus;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.PartiallyUpdateRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.response.*;
import pl.tomwodz.songify.song.domain.model.Song;

import java.util.Map;

public class SongMapper {
    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

   public static Song mapFromUpDateSongRequestDtoToSong(UpdateSongRequestDto dto) {
        String newSongName =dto.songName();
        String newArtist = dto.artist();
        return new Song(newSongName, newArtist);
    }

    public static Song mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateRequestDto dto){
        return new Song(dto.songName(), dto.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateResponseDto(Song song) {
        return new CreateSongResponseDto(song);
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Integer id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongtoUpdateSongResponseDto(Song newSong) {
        return new UpdateSongResponseDto(newSong.name(), newSong.artist());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongResponseDto(Song updatedSong) {
        return new PartiallyUpdateSongResponseDto(updatedSong);
    }

    public static GetSongResponseDto mapFromSongToGetResponseDto(Song song) {
        return new GetSongResponseDto(song);
    }

    public static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(Map<Integer, Song> database) {
        return new GetAllSongsResponseDto(database);
    }

}
