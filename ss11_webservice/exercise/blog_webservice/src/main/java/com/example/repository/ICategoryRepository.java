package com.example.repository;

import com.example.model.Blog;
import com.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {

}
