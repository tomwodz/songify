package pl.tomwodz.songify.song.infrastructure.controller;

import org.springframework.http.HttpStatus;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.PartiallyUpdateRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import pl.tomwodz.songify.song.infrastructure.controller.dto.response.*;
import pl.tomwodz.songify.song.domain.model.Song;

import java.util.List;

public class SongMapper {

    public  static SongDto mapFromSongToSongDto(Song song){
        return new SongDto(song.getId(), song.getName(), song.getArtist());
    }
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
        SongDto songDto = SongMapper.mapFromSongToSongDto(song);
        return new CreateSongResponseDto(songDto);
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongtoUpdateSongResponseDto(Song newSong) {
        return new UpdateSongResponseDto(newSong.getName(), newSong.getArtist());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongResponseDto(Song song) {
        SongDto songDto = SongMapper.mapFromSongToSongDto(song);
        return new PartiallyUpdateSongResponseDto(songDto);
    }

    public static GetSongResponseDto mapFromSongToGetResponseDto(Song song) {
        SongDto songDto = SongMapper.mapFromSongToSongDto(song);
        return new GetSongResponseDto(songDto);
    }

    public static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(List<Song> songs) {
        List<SongDto> songDtos = songs.stream()
                .map(SongMapper::mapFromSongToSongDto)
                .toList();
        return new GetAllSongsResponseDto(songDtos);
    }

}
