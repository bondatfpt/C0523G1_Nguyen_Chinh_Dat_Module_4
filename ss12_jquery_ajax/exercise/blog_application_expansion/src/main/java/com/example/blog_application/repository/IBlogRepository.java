package com.example.blog_application.repository;

import com.example.blog_application.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends CrudRepository<Blog,Integer> {
////    @Query(value = "select blog.id blog.tittle,blog.content,blog.date_of_writing, category.name from blog join category on blog.category_id = category.id",nativeQuery = true)
////    List<Blog> getBlogsWithCategoryName();
//@Query(value = "SELECT blog.id, blog.title, blog.content, blog.date_of_writing, category.name FROM blog JOIN category ON blog.category_id = category.id", nativeQuery = true)
//List<Blog> getBlogsWithCategoryName();

    @Query (value = "select * from blog where category_id =:categoryId",nativeQuery = true)
    List<Blog> showBlogByCategory(@Param("categoryId") int categoryId);

    @Query(value = "select * from blog\n" +
            "where tittle like:tittle ",nativeQuery = true)
    List<Blog> searchByTittle (@Param("tittle") String tittle);

    Page<Blog> findAll (Pageable pageable);
}
