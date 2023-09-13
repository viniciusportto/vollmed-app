package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consult.AppointmentSchedule;
import med.voll.api.domain.consult.AppointmentSchedulingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consults")
public class ConsultController {

    @Autowired
    private AppointmentSchedule schedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentSchedulingData datas){
        schedule.toSchedule(datas);
        return ResponseEntity.ok(new AppointmentSchedulingData(null, null, null, null));
    }

}
