package med.voll.api.domain.consult;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSchedule {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void toSchedule(AppointmentSchedulingData datas){
        if(!patientRepository.existsById(datas.idPatient())){
            throw new IdValidationException("Patient ID doesn't exist!");
        }

        if(datas.idDoctor() != null && !doctorRepository.existsById(datas.idDoctor())){
            throw new IdValidationException("Doctor ID doesn't exist!");
        }

        var patient = patientRepository.getReferenceById(datas.idPatient());
        var doctor = choiceDoctor(datas);
        var consult = new Consult(null, doctor, patient, datas.date());
        consultRepository.save(consult);
    }

    private Doctor choiceDoctor(AppointmentSchedulingData datas) {
        if(datas.idDoctor() != null){
            return doctorRepository.getReferenceById(datas.idDoctor());
        }

        if(datas.specialty() == null){
            throw new IdValidationException("Specialty is mandatory when a doctor is not chosen!");
        }
        return doctorRepository.ChooseRandomDoctorAvailable(datas.specialty(),datas.date());
    }


}
