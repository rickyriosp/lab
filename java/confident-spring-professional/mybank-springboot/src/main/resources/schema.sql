create table if not exists transaction
(
    id uuid default random_uuid() primary key,
    amount numeric not null,
    time_stamp timestamp not null,
    reference varchar(255) not null,
    slogan varchar(255),
    receiving_user varchar(255) not null
);