package med.voll.api.domain.consult;

import java.time.LocalDateTime;

public record DataDetailedConsult(Long id, Long idDoctor, Long idPaciente, LocalDateTime date) {
    public DataDetailedConsult(Consult consult) {
        this(consult.getId(), consult.getDoctor().getId(),consult.getPatient().getId(),consult.getDate());

    }
}
