package org.example.kitchensink.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

   @Id
     private String id;

    @NotBlank(message = "Name must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name Must not contain numbers")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Must be a well-formed email address")
    private String email;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "^\\d{10,12}$", message = "Phone number size must be between 10 and 12")
    private String phoneNumber;
}
