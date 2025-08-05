create table if not exists questions_pg
(
    id                bigint  not null primary key,
    topics_id         bigint  not null constraint topics_id_fk references topics_pg,
    nominal           int not null,
    text              varchar not null,
    answer            varchar not null,
    additional_answer varchar,
    source            varchar not null
);



create table if not exists topics_pg
(
    id         bigint  not null primary key,
    rounds_id  bigint  not null constraint rounds_id_fk references rounds_pg,
    topic_name varchar not null
);



create table if not exists rounds_pg
(
    id         bigint  not null primary key,
    round_name varchar not null,
    round_time integer not null
);


create table if not exists doc_names_pg
(
    id       bigint  not null primary key,
    doc_name varchar not null,
    topics_id bigint  not null constraint topics_id_pk references topics_pg
);