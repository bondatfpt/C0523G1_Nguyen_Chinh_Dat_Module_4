package com.example.cs4.time.repository;


import com.example.cs4.time.model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ITimeRepository extends JpaRepository<Time,Integer> {
//    Page<Time> findAllByDeleteIs(Pageable pageable, boolean isDelete);

    @Query(value = "select * from times where is_deleted = 0 order by time",
            nativeQuery = true)
    Page<Time> findAllByDeleteIs(Pageable pageable);
    @Query(value = "select * from times where is_deleted = 0",
            nativeQuery = true)
    List<Time> getAll();
    @Query(value = "select * from times where id not in (select time_id from bookings where booking_date =:dateSearch)",
            nativeQuery = true)
    List<Time> findByDate(@Param("dateSearch") Date dateSearch);
}
