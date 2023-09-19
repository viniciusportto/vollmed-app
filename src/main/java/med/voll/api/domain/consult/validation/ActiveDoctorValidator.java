package med.voll.api.domain.consult.validation;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultSchedulingData;
import med.voll.api.domain.doctor.DoctorRepository;

public class ActiveDoctorValidator {

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
