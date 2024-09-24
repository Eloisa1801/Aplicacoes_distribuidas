package com.kipper.eventsmicroservice.dtos;

public record EmailRequestDTO(String from, String to, String subject, String body) {
}
