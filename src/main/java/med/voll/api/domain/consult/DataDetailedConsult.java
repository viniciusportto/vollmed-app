package med.voll.api.domain.consult;

import java.time.LocalDateTime;

public record DataDetailedConsult(Long id, Long idDoctor, Long idPaciente, LocalDateTime date) {
}
