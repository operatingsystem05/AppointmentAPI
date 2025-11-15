package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController
{
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {this.appointmentService = appointmentService;}

    @GetMapping
    public List<Appointment> getAppointments(@RequestParam(required = false) String category)
    {
        return appointmentService.getAll();
    }

    @GetMapping("/between")
    public List<Appointment> getAppointmentsBetween(
            @RequestParam String start,
            @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return appointmentService.getAppointmentsBetween(startTime, endTime);
    }

    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment)
    {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment)
    {
        return appointmentService.updateAppointment(id, updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id)
    {
        appointmentService.deleteAppointment(id);
    }
}