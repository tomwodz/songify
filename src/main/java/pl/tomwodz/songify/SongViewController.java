package pl.tomwodz.songify;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SongViewController {

    private Map<Integer, String> database =new HashMap<>();

    @GetMapping("/")
    public String home(){
        return  "home";
    }

    @GetMapping("/view/songs")
    public String songs(Model model){
        database.put(1, "shawnmendes song1");
        database.put(2, "ariana grande song2");
        database.put(3, "test");
        database.put(4, "test 2");
        model.addAttribute("songMap", database);
        return  "songs";
    }
}
