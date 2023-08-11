package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record datasDetailingDoctor(Long id, String name, String email, String crm, String phone, Specialty specialty, Address address) {

    public  datasDetailingDoctor(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }
}