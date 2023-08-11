package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String zipcode;
    private String number;
    private String complement;
    private String city;
    private String uf;

    public Address(AddressData data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.uf = data.uf();
        this.city = data.city();
        this.number = data.number();
        this.complement = data.complement();
    }

    public void updateInformations(AddressData datas) {
        if( datas.street() != null){
            this.street = datas.street();
        }
        if( datas.neighborhood() != null){
            this.neighborhood = datas.neighborhood();
        }
        if( datas.zipcode() != null){
            this.zipcode = datas.zipcode();
        }
        if( datas.uf() != null){
            this.uf = datas.uf();
        }
        if( datas.city() != null){
            this.city = datas.city();
        }
        if( datas.number() != null){
            this.number = datas.number();
        }
        if( datas.complement() != null){
            this.complement = datas.complement();
        }
    }
}
