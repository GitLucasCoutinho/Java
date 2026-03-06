package org.example.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id ;
    private String name ;
    private java.time.LocalDate birthday ;
}
