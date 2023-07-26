package med.voll.api.doctor;

import med.voll.api.address.AddressData;

//"Class with only the fields to be sent or received from the API, standard DTO."
public record RegisterDoctorData(String name, String email, String crm, Specialty specialty, AddressData address) {
}
