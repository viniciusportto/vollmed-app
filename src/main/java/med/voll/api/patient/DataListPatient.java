package med.voll.api.patient;

public record DataListPatient(Long id, String name, String email, String cpf) {

    public DataListPatient(Pacient pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getCpf());
    }

}
