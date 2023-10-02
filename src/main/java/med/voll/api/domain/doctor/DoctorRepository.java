package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor>findAllByActiveTrue(Pageable pagination);

    @Query("""
            select d from Doctors d
            where
            d.active = true
            and
            d.specialty = :specialty
            and
            d.id not in(
                select c.doctor.id from Consult c
                where
                c.date = :date
                and
                c.cancelMotive is null
            )
            order by rand()
            limit 1
            """)
    Doctor chooseRandomDoctorAvailable(Specialty specialty, LocalDateTime date);

    @Query("""
           select d.active
           from Doctors d
           where
           d.id = :id
            """)
    Boolean findActiveById(Long id);

}
