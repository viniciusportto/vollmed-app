package med.voll.api.domain.consult;

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


        var patient = patientRepository.findById(datas.idPatient()).get();
        var doctor = doctorRepository.findById(datas.idDoctor()).get();
        var consult = new Consult(null, doctor, patient, datas.date());
        consultRepository.save(consult);
    }


}
