package med.voll.api.domain.consult;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultSchedule {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void toSchedule(ConsultSchedulingData datas){
        if(!patientRepository.existsById(datas.idPatient())){
            throw new IdValidationException("Patient ID doesn't exist!");
        }

        if(datas.idDoctor() != null && !doctorRepository.existsById(datas.idDoctor())){
            throw new IdValidationException("Doctor ID doesn't exist!");
        }

        var patient = patientRepository.getReferenceById(datas.idPatient());
        var doctor = choiceDoctor(datas);
        var consult = new Consult(null, doctor, patient, datas.date(), null);
        consultRepository.save(consult);
    }

    private Doctor choiceDoctor(ConsultSchedulingData datas) {
        if(datas.idDoctor() != null){
            return doctorRepository.getReferenceById(datas.idDoctor());
        }

        if(datas.specialty() == null){
            throw new IdValidationException("Specialty is mandatory when a doctor is not chosen!");
        }
        return doctorRepository.ChooseRandomDoctorAvailable(datas.specialty(),datas.date());
    }

    public void cancel(DatasConsultCancel datas){
        if(!consultRepository.existsById(datas.idConsult())){
            throw new IdValidationException("Consult ID entered does not exist!");
        }

        var consult = consultRepository.getReferenceById(datas.idConsult());
        consult.cancel(datas.motive());
    }

}
