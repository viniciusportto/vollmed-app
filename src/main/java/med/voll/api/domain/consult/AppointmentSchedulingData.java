package med.voll.api.domain.consult;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentSchedulingData(
        Long idDoctor,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime date,

        Specialty specialty){
}
