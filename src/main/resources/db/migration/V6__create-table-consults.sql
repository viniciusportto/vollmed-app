create table consults(

    id bigint not null auto_increment,
    doctor_id bigint not null,
    paciente_id bigint not null,
    date datetime not null,

    primary key(id),
    constraint fk_consults_doctor_id foreign key(doctor_id) references doctors(id),
    constraint fk_consults_paciente_id foreign key(paciente_id) references pacientes(id)

);