package com.example.cs4.yard.repository;

import com.example.cs4.yard.model.Yard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IYardRepository extends JpaRepository<Yard, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update yards set is_deleted = 1 where id =:id", nativeQuery = true)
    void deleteYard(@Param("id") int idDelete);

    @Query(value = "select * from yards where (yards.name like:nameSearch or yards.address like:nameSearch) and is_deleted =0", nativeQuery = true)
    Page<Yard> getAllYard(Pageable pageable, @Param("nameSearch") String nameSearch);

    //Liên code
//    @Query(value = "SELECT * from yards where is_deleted =0 and id not in (SELECT yard_id FROM bookings WHERE booking_date =:bookingDate GROUP BY yard_id, time_id\n" +
//            "HAVING COUNT(*) = (22 - 4 + 1))", nativeQuery = true)
//    Page<Yard> getYardByDate(Pageable pageable, @Param("bookingDate") String bookingDate);
//    end
    @Query(value = "select * from yards where (yards.name like:nameSearch or yards.address like:nameSearch) and is_deleted =0 and id not in (SELECT yard_id FROM bookings WHERE booking_date LIKE:bookingDate GROUP BY yard_id, time_id\n" +
            "HAVING COUNT(*) = (22 - 4 + 1))", nativeQuery = true)
    Page<Yard> getAllYardDate(Pageable pageable, @Param("nameSearch") String nameSearch, @Param("bookingDate") String bookingDate);
}

