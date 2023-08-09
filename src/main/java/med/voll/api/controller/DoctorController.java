package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDoctorData datas, UriComponentsBuilder uriBuilder){
        var doctor = new Doctor(datas);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new datasDetailingDoctor(doctor));

    }
    //created get method and list of doctors
    @GetMapping
    public ResponseEntity <Page<DataListingDoctor>>lister(@PageableDefault(size = 10, page = 0, sort = {"name"}) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(DataListingDoctor::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateDoctor datas){
        var doctor = repository.getReferenceById(datas.id());
        doctor.updateInformations(datas);

        return ResponseEntity.ok(new datasDetailingDoctor(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }



}
