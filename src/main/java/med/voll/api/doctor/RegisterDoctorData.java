package med.voll.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.address.AddressData;

//"Class with only the fields to be sent or received from the API, standard DTO."
public record RegisterDoctorData(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Specialty specialty,

        @NotNull
        @Valid
        AddressData address,

        String lastname)
{

}