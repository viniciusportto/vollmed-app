package med.voll.api.domain.consult.validation.scheduling;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultSchedulingData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

///validação consulta deve ser agendada com 30 minutos de antecedência
@Component("ValidatorAdvanceTimeSchedule")
public class ValidatorAdvanceTime implements ValidatorScheduleConsult {

    public void validate(ConsultSchedulingData datas){
        var dateConsult = datas.date();
        var now = LocalDateTime.now();
        var minuteDifference = Duration.between(now, dateConsult).toMinutes();

        if(minuteDifference < 30){
            throw new IdValidationException("Consultation must be scheduled at least 30 minutes in advance");
        }
    }

}