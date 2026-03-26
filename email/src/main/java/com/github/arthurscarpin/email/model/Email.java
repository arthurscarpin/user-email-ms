package com.github.arthurscarpin.email.model;

import com.github.arthurscarpin.email.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_email")
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userId;

    private String emailFrom;

    private String emailTo;

    private String subject;

    @Column(columnDefinition = "BODY")
    private String body;

    private LocalDateTime sendDate;

    private EmailStatus status;
}
