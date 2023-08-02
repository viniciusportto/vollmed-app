package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.DataListingDoctor;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.RegisterDoctorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    //created get method and list of doctors
    @GetMapping
    public Page<DataListingDoctor> lister(@PageableDefault(size = 10, page = 0, sort = {"name"}) Pageable pagination){
        return repository.findAll(pagination).map(DataListingDoctor::new);
    }



}
