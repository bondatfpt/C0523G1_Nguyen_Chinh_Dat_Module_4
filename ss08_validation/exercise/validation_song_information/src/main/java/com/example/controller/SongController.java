package com.example.controller;

import com.example.dto.SongDto;
import com.example.model.Song;
import com.example.service.ISongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SongController {
    @Autowired
    private ISongService iSongService;
    @GetMapping("")
    public String showList (Model model){
        List<Song> songList = iSongService.findAll();
        model.addAttribute("list",songList);
        return "list";
    }

    @GetMapping ("showFormCreate")
    public String showFormCreate (Model model){
        model.addAttribute("songDto",new SongDto());
        return "form-create";
    }

    @PostMapping ("save")
    public String save (@Validated @ModelAttribute("songDto") SongDto songDto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return "form-create";
        }else {
            Song song = new Song();
            BeanUtils.copyProperties(songDto,song);
            iSongService.save(song);
            return "redirect:/";
        }
    }
    @GetMapping("showFormUpdate")
    public String showFormUpdate (@RequestParam int id, Model model){
        Song song = iSongService.findById(id);
        SongDto songDto = new SongDto();
        BeanUtils.copyProperties(song,songDto);
        model.addAttribute("songUpdate",songDto);
        return "form-update";
    }

    @PostMapping ("update")
    public String update (@Validated @ModelAttribute("songUpdate") SongDto songDto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return "form-update";
        }else {
            Song song = new Song();
            BeanUtils.copyProperties(songDto,song);
            iSongService.save(song);
            return "redirect:/";
        }
    }
}
