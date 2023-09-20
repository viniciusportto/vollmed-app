package med.voll.api.domain.consult.validation;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultRepository;
import med.voll.api.domain.consult.ConsultSchedulingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithOtherConsultValidator implements ValidatorScheduleConsult {

    @Autowired
    private ConsultRepository repository;

    public void validate(ConsultSchedulingData datas){
        var doctorHasAnotherConsultAtTheSameTime = repository.existsByDoctorIdAndDate(datas.idDoctor(),datas.date());
        if(doctorHasAnotherConsultAtTheSameTime){
            throw new IdValidationException("The doctor already has another consult scheduled at the same time.");
        }
    }
}
