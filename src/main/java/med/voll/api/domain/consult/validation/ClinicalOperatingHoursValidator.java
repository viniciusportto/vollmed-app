package med.voll.api.domain.consult.validation;

import med.voll.api.domain.IdValidationException;
import med.voll.api.domain.consult.ConsultSchedulingData;

import java.time.DayOfWeek;

//validação de consulta apenas poderá ser agendada entre 7 e 18 horas e não pode ser agendada no domingo
public class ClinicalOperatingHoursValidator {

    public void validate(ConsultSchedulingData datas){
        var dataConsult = datas.date();

        var sunday = dataConsult.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeTheClinicOpens = dataConsult.getHour() < 7;
        var afterTheClinicCloses = dataConsult.getHour() > 18;

        if(sunday || beforeTheClinicOpens || afterTheClinicCloses){
            throw new IdValidationException("Consultation outside clinic opening hours");
        }

    }


}
