package med.voll.api.domain.consult;

import jakarta.validation.constraints.NotNull;

public record DatasConsultCancel(

    @NotNull
    Long idConsult,

    @NotNull
    CancelMotive motive) {

}
