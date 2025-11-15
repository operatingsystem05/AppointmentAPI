package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String clientName;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public String getTitle() { return title;}
    public void setTitle(String title) { this.title = title;}

    public String getClientName() { return clientName;}
    public void setClientName(String clientName) { this.clientName = clientName;}

    public LocalDateTime getStartTime() { return startTime;}
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime;}

    public LocalDateTime getEndTime() { return endTime;}
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime;}
}
