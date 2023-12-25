package com.example.service;

import com.example.model.Post;
import com.example.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository iPostRepository;
    @Override
    public Page<Post> findAll(Pageable pageable) {
        return iPostRepository.findAll(pageable);
    }
    @Override
    public void save(Post post) {
        iPostRepository.save(post);
    }

    @Override
    public void delete(Integer id) {
        iPostRepository.deleteById(id);
    }

    @Override
    public Post findById(Integer id) {
        return iPostRepository.findById(id).orElse(null);
    }
}
