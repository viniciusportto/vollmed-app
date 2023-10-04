package med.voll.api.controller;

import med.voll.api.domain.consult.ConsultSchedule;
import med.voll.api.domain.consult.ConsultSchedulingData;
import med.voll.api.domain.consult.DataDetailedConsult;
import med.voll.api.domain.doctor.Specialty;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultSchedulingData> jsonEntry;

    @Autowired
    private JacksonTester<DataDetailedConsult> jsonReturn;

    @MockBean
    private ConsultSchedule consultSchedule;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    @WithMockUser
    void schedule_scenario1() throws Exception {
        var response = mvc
                .perform(post("/consults"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    @WithMockUser
    void schedule_scenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var dataDetailed = new DataDetailedConsult(null, 2L, 5L, date);

        when(consultSchedule.toSchedule(any())).thenReturn(dataDetailed);

        var response = mvc
                .perform(
                        post("/consults")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonEntry.write(
                                        new ConsultSchedulingData(2L, 5L, date, specialty)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = jsonReturn.write(
                dataDetailed
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

}