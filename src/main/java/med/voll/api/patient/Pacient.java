package med.voll.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "pacients")
@Entity(name = "Pacient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pacient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Pacient(PatientRegistrationData datas) {
        this.active = true;
        this.name = datas.name();
        this.email = datas.email();
        this.telefone = datas.phone();
        this.cpf = datas.cpf();
        this.address = new Address(datas.address());
    }

    public void informationUpdate(PatientUpdateData datas) {
        if (datas.name() != null) {
            this.name = datas.name();
        }
        if (datas.phone() != null) {
            this.telefone = datas.phone();
        }
        if (datas.address() != null) {
            this.address.updateInformations(datas.address());
        }

    }

    public void delete() {
        this.active = false;
    }
}
