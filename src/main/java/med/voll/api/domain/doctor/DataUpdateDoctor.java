package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record DataUpdateDoctor(
//dto
        @NotNull
        Long id,
        String name,
        String phone,
        String email,
        AddressData address) {
}
