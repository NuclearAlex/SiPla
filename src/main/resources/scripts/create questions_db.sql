create table if not exists questions
(
    id                bigint  not null primary key,
    topics_id         bigint  not null constraint topics_id_fk references topics,
    answer_time       int not null,
    nominal           int not null,
    text              varchar not null,
    answer            varchar not null,
    additional_answer varchar,
    source            varchar not null
);



create table if not exists topics
(
    id         bigint  not null primary key,
    rounds_id  bigint  not null constraint rounds_id_fk references rounds,
    topic_name varchar not null
);



create table if not exists rounds
(
    id         bigint  not null primary key,
    round_name varchar not null,
    round_time integer not null
);