package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.consult.Consult;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("should return null when the only registered doctor is not available on the date")
    void chooseRandomDoctorAvailableScenario1() {
        //given or arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("Patient", "patient@email.com", "12345678900");
        registerConsult(doctor, patient, nextMondayAt10);

        //when or act
        var freeDoctor = doctorRepository.chooseRandomDoctorAvailable(Specialty.CARDIOLOGY, nextMondayAt10 );

        //then or assert
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("should return the doctor when he is available on the date")
    void chooseRandomDoctorAvailableScenario2() {
        //given or arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456", Specialty.CARDIOLOGY);

        //when or act
        var freeDoctor = doctorRepository.chooseRandomDoctorAvailable(Specialty.CARDIOLOGY, nextMondayAt10 );

        //then or assert
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private void registerConsult(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Consult(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty){
        var doctor = new Doctor(doctorDatas(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientDatas(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private RegisterDoctorData doctorDatas(String name, String email, String crm, Specialty specialty) {
        return new RegisterDoctorData(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private PatientRegistrationData patientDatas(String name, String email, String cpf) {
        return new PatientRegistrationData(
                name,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "street xpto",
                "neighborhood",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}