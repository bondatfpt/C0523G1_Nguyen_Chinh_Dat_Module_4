package com.example.service;

import com.example.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll ();
    boolean save (Song song);

    Song findById (int id);
}
