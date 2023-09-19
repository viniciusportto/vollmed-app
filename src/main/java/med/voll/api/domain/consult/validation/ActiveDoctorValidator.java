package med.voll.api.domain.consult.validation;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultSchedulingData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements ValidatorScheduleConsult {

    @Autowired
    private DoctorRepository repository;

    public void validate(ConsultSchedulingData datas){
        //escolha do médico opcional
        if(datas.idDoctor() == null){
            return;
        }

        var doctorIsActive = repository.findActiveById(datas.idDoctor());
        if(!doctorIsActive){
            throw new IdValidationException("Consultation cannot be scheduled with a doctor excluded");
        }

    }

}
