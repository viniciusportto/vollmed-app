package med.voll.api.domain.consult.validation.scheduling;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultRepository;
import med.voll.api.domain.consult.ConsultSchedulingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientWithoutAnotherConsultOnDay implements ValidatorScheduleConsult {

    @Autowired
    private ConsultRepository repository;

    public void validate(ConsultSchedulingData datas){
        var firstHour = datas.date().withHour(7);
        var lastHour = datas.date().withHour(18);
        var patientHasAnotherConsultOnDay = repository.existsByPatientIdAndDateBetween(datas.idPatient(), firstHour, lastHour);
        if (patientHasAnotherConsultOnDay){
            throw new IdValidationException("Patient already has an consult scheduled that day");
        }
    }

}