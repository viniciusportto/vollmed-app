package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.DataListingDoctor;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.RegisterDoctorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDoctorData datas){
        repository.save(new Doctor(datas));

    }

    @GetMapping
    public List<DataListingDoctor> lister(){
        return repository.findAll().stream().map(DataListingDoctor::new).toList();
    }



}
