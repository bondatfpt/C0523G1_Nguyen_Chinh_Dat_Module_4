package com.example.service;

import com.example.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {
    Page<Post> findAll (Pageable pageable);
    void save (Post post);

    void delete (Integer id);

    Post findById (Integer id);
}
