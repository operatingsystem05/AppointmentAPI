package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
    List<Appointment> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT a FROM Appointment a WHERE a.startTime < :endTime AND a.endTime > :startTime")
    List<Appointment> findOverlapping(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
