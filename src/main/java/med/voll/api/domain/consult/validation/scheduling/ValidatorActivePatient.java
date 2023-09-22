package med.voll.api.domain.consult.validation.scheduling;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultSchedulingData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActivePatient implements ValidatorScheduleConsult {

    @Autowired
    private PatientRepository repository;

    public void validate(ConsultSchedulingData datas){
        var patientIsActive = repository.findActiveById(datas.idPatient());
        if(!patientIsActive){
            throw new IdValidationException("The consult cannot be scheduled with an excluded patient.");
        }
    }

}