package com.example.repository;

import com.example.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBLogRepository extends JpaRepository<Blog,Integer> {
    @Query(value = "select * from blog where category_id =:categoryId",nativeQuery = true)
    List<Blog> showBlogByCategory(@Param("categoryId") int categoryId);

}
