package med.voll.api.domain.consult.validation;

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
        var patietHasAnotherConsultOnDay = repository.existByPatientIdAndDataBetween(datas.idPatient(), firstHour, lastHour);
    }

}
