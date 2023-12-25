package com.example.cs4.booking.repository;


import com.example.cs4.booking.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT bookings.id,bookings.booking_date,bookings.deposit,bookings.is_deleted,bookings.customer_id,bookings.employee_id,bookings.time_id,bookings.yard_id FROM bookings " +
            "JOIN customers ON bookings.customer_id = customers.id " +
            "WHERE customers.phone_number LIKE :phoneNumber  order by bookings.booking_date desc",
            nativeQuery = true)
    Page<Booking> findAllByPhone(Pageable pageable,@Param("phoneNumber") String phoneNumber);
    @Query(value = "select * from bookings where is_deleted = 0",
            nativeQuery = true)
    List<Booking> findAllBooking();
//    @Query(value = "SELECT bookings.id,bookings.booking_date,bookings.deposit,bookings.is_deleted,bookings.customer_id,bookings.employee_id,bookings.time_id,bookings.yard_id FROM bookings " +
//            "JOIN customers ON bookings.customer_id = customers.id " +
//            "WHERE customers.phone_number LIKE :phoneNumber or bookings.booking_date =:bookingDate  order by bookings.booking_date desc",
//            nativeQuery = true)
//    Page<Booking> findAllByPhoneAndBookingDate(Pageable pageable, @Param("phoneNumber") String phoneNumber, @Param("bookingDate") Date bookingDate);
    @Query(value = "SELECT bookings.id,bookings.booking_date,bookings.deposit,bookings.is_deleted,bookings.customer_id,bookings.employee_id,bookings.time_id,bookings.yard_id FROM bookings " +
            "JOIN customers ON bookings.customer_id = customers.id " +
            "WHERE customers.phone_number LIKE :phoneNumber and bookings.booking_date LIKE:bookingDate  order by bookings.booking_date desc",
            nativeQuery = true)
    Page<Booking> findAllByPhoneAndBookingDate(Pageable pageable, @Param("phoneNumber") String phoneNumber, @Param("bookingDate")String bookingDate);
}
