package ru.itis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private Integer passpNumb;
    private Integer age;
    private String dateOfPassp;
}