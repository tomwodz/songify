package pl.tomwodz.songify.song.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SongViewController {

    Map<Integer, String> database = new HashMap<>(Map.of(
            1, "shawnmendes song1",
            2, "ariana grande song2",
            3, "test",
            4, "test 2"
    ));

    @GetMapping("/")
    public String home(){
        return  "home";
    }

    @GetMapping("/view/songs")
    public String songs(Model model){
        model.addAttribute("songMap", database);
        return  "songs";
    }
}
