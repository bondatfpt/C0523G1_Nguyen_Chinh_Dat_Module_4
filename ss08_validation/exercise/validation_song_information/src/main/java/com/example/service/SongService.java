package com.example.service;

import com.example.model.Song;
import com.example.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements ISongService {
    @Autowired
    private ISongRepository iSongRepository;

    @Override
    public List<Song> findAll() {
        return (List<Song>) iSongRepository.findAll();
    }

    @Override
    public boolean save(Song song) {
        try {
            iSongRepository.save(song);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Song findById(int id) {
      return  iSongRepository.findById(id).get();
    }
}
