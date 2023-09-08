package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consult.AppointmentSchedulingData;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consults")
public class ConsultController {

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentSchedulingData datas){
        System.out.println(datas);
        return ResponseEntity.ok(new AppointmentSchedulingData(null, null, null, null));
    }

}
