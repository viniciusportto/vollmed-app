package med.voll.api.domain.consult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Boolean existsByDoctorIdAndData(Long idDoctor, LocalDateTime date);

    Boolean existByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);
}
