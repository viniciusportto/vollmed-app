package med.voll.api.domain.consult.validation.cancellation;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultRepository;
import med.voll.api.domain.consult.DatasConsultCancel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorTimeAdvanceCancellation")
public class ValidatorAdvanceTime implements ValidatorCancellationConsult {

    @Autowired
    private ConsultRepository repository;

    @Override
    public void validate(DatasConsultCancel datas){
        var consult = repository.getReferenceById(datas.idConsult());
        var now = LocalDateTime.now();
        var hourDifference = Duration.between(now, consult.getDate()).toHours();

        if(hourDifference < 24){
            throw new IdValidationException("Consult can only be canceled at least 24 hours in advance!");
        }
    }

}
