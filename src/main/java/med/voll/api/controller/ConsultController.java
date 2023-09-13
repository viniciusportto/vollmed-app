package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consult.ConsultSchedule;
import med.voll.api.domain.consult.ConsultSchedulingData;
import med.voll.api.domain.consult.DatasConsultCancel;
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
    private ConsultSchedule schedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ConsultSchedulingData datas){
        schedule.toSchedule(datas);
        return ResponseEntity.ok(new ConsultSchedulingData(null, null, null, null));
    }

    public ResponseEntity cancel(@RequestBody @Valid DatasConsultCancel datas){
        schedule.cancel(datas);
        return ResponseEntity.noContent().build();
    }

}
