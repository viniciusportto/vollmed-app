package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consult.ConsultSchedule;
import med.voll.api.domain.consult.ConsultSchedulingData;
import med.voll.api.domain.consult.DatasConsultCancel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consults")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {

    @Autowired
    private ConsultSchedule schedule;

    @PostMapping
    @Transactional
        public ResponseEntity schedule(@RequestBody @Valid ConsultSchedulingData datas){
        var dto = schedule.toSchedule(datas);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid DatasConsultCancel datas){
        schedule.cancel(datas);
        return ResponseEntity.noContent().build();
    }

}
