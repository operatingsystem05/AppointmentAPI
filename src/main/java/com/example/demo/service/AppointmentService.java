package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService
{
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {this.appointmentRepository = appointmentRepository;}

    public List<Appointment> getAll()
    {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findOverlapping(LocalDateTime startTime, LocalDateTime endTime)
    {
        return appointmentRepository.findOverlapping(startTime, endTime);
    }

    public Appointment addAppointment(Appointment appointment)
    {
        List<Appointment> overlaps = findOverlapping(appointment.getStartTime(), appointment.getEndTime());
        if (!overlaps.isEmpty()) {
            throw new RuntimeException("Time slot overlaps with another appointment");
        }
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id)
    {
        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment)
    {
        Optional<Appointment> existing = appointmentRepository.findById(id);
        if (existing.isEmpty()) {
            throw new RuntimeException("Appointment not found");
        }

        LocalDateTime newStart = updatedAppointment.getStartTime();
        LocalDateTime newEnd = updatedAppointment.getEndTime();

        List<Appointment> overlaps = findOverlapping(newStart, newEnd)
                .stream()
                .filter(a -> !a.getId().equals(id))
                .collect(Collectors.toList());

        if (!overlaps.isEmpty())
        {
            throw new RuntimeException("Time slot overlaps with another appointment");
        }
        Appointment appointment = existing.get();
        appointment.setTitle(updatedAppointment.getTitle());
        appointment.setClientName(updatedAppointment.getClientName());
        appointment.setStartTime(newStart);
        appointment.setEndTime(newEnd);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsBetween(LocalDateTime start, LocalDateTime end)
    {
        return appointmentRepository.findByStartTimeBetween(start, end);
    }

    public Duration getDuration(Appointment appointment)
    {
        return Duration.between(appointment.getStartTime(), appointment.getEndTime());
    }
}
