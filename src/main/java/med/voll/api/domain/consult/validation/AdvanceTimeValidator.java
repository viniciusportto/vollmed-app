package med.voll.api.domain.consult.validation;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultSchedulingData;

import java.time.Duration;
import java.time.LocalDateTime;

///validação consulta deve ser agendada com 30 minutos de antecedência
public class AdvanceTimeValidator {

    public void validate(ConsultSchedulingData datas){
        var dateConsult = datas.date();
        var now = LocalDateTime.now();
        var minuteDifference = Duration.between(now, dateConsult).toMinutes();

        if(minuteDifference < 30){
            throw new IdValidationException("Consultation must be scheduled at least 30 minutes in advance");
        }
    }

}
