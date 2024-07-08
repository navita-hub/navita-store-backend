create table if not exists device (
    id bigserial primary key,
    app_id varchar not null unique,
    firebase_token varchar,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table if not exists term (
    id bigserial primary key,
    term varchar not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table if not exists device_term (
    device_id bigint references device (id),
    term_id bigint references term (id),
    accept_at timestamp not null default now(),
    primary key (device_id, term_id)
);
