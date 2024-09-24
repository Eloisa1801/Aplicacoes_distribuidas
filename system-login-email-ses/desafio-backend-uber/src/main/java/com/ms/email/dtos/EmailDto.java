package com.ms.email.dtos;



public record EmailDto(
     
    String from, 
    String to, 
    String subject, 
    String body
) {
}
