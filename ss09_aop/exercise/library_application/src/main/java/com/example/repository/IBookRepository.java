package com.example.repository;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface IBookRepository extends JpaRepository<Book,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update books set amount = :amount where id = :id",nativeQuery = true)
    void reduceTheNumber (@Param("amount") int amount, @Param("id") int id);
}
