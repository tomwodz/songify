package pl.tomwodz.songify.song.infrastructure.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.tomwodz.songify.song.domain.service.SongRetriever;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SongViewController {

    private  final SongRetriever songRetriever;

    public SongViewController(SongRetriever songRetriever) {
        this.songRetriever = songRetriever;
    }

    @GetMapping("/")
    public String home(){
        return  "home";
    }

    @GetMapping("/view/songs")
    public String songs(Model model, Pageable pageable){
        model.addAttribute("songMap", this.songRetriever.findAll(pageable));
        return  "songs";
    }
}
