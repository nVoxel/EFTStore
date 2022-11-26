create table if not exists users
(
    id serial primary key,
    email varchar(320) not null,
    password_hash varchar not null,
    nickname varchar(32) not null,
    avatar_id int
);

create table if not exists items
(
    id serial primary key,
    name_ru varchar not null,
    name_en varchar not null,
    image_name varchar not null,
    image_name_det varchar not null,
    description_ru varchar not null,
    description_en varchar not null,
    wiki_link varchar,
    price_rub int not null
);

create table if not exists cart_items
(
    id serial primary key,
    user_id int not null references users(id) ON DELETE CASCADE,
    item_id int not null references items(id) ON DELETE CASCADE
);

create table if not exists avatars
(
    id serial primary key,
    file_name varchar not null,
    content_type varchar not null,
    size int not null,
    original_file_name varchar not null,
    uploader_id int not null references users(id) ON DELETE CASCADE
);

create table if not exists sessions
(
    user_id int not null references users(id) ON DELETE CASCADE,
    session_key varchar not null
);