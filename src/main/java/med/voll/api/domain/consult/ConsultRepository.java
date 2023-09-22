package med.voll.api.domain.consult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);

    boolean existsByDoctorIdAndDateAndCancelMotiveIsNull(Long idMedico, LocalDateTime data);

}
