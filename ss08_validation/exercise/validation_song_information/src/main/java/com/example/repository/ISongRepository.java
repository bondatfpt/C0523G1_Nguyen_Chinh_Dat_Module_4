package com.example.repository;

import com.example.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends CrudRepository<Song,Integer> {
}
