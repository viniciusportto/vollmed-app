package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;


@Table(name = "doctors")
@Entity(name = "Doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private boolean active;

    public Doctor(RegisterDoctorData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }


    public void updateInformations(DataUpdateDoctor datas) {
        if(datas.name() != null) {
            this.name = datas.name();
        }
        if(datas.phone() != null){
            this.phone = datas.phone();
        }
        if(datas.email() != null) {
            this.email = datas.email();
        }
        if (datas.address() != null){
            this.address.updateInformations(datas.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
