package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
        return repository.findAllByActiveTrue(pagination).map(DataListingDoctor::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateDoctor datas){
        var doctor = repository.getReferenceById(datas.id());
        doctor.updateInformations(datas);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }



}
