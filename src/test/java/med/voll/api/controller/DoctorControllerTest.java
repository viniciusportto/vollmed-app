package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RegisterDoctorData> registerDoctorDataJson;

    @Autowired
    private  JacksonTester<DatasDetailingDoctor> datasDetailingDoctorJson;

    @MockBean
    private DoctorRepository repository;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    @WithMockUser
    void register_scenario1() throws Exception{
        var response = mvc
                .perform(post("/doctors"))
                .andReturn().getResponse();
        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    @WithMockUser
    void register_scenario2()throws Exception{
        var datasRegister = new RegisterDoctorData(
                "Doctor",
                "doctor@voll.med",
                "12345678",
                "123456",
                Specialty.CARDIOLOGY,
                dataAddress());

        when(repository.save(any())).thenReturn(new Doctor(datasRegister));

        var response = mvc
                .perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerDoctorDataJson.write(datasRegister).getJson()))
                .andReturn().getResponse();

        var datasDetailed = new DatasDetailingDoctor(
                null,
                datasRegister.name(),
                datasRegister.email(),
                datasRegister.crm(),
                datasRegister.phone(),
                datasRegister.specialty(),
                new Address(datasRegister.address())
        );

        var expectedJson = datasDetailingDoctorJson.write(datasDetailed).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    private AddressData dataAddress(){
        return new AddressData(
                "Street abc",
                "neighborhood",
                "00000000",
                "Sao Paulo",
                "SP",
                null,
                null
        );
    }

}