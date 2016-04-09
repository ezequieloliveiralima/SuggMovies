create table account (
	id_account INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
	, email varchar(100) not null
	, password varchar(32) not null
);

create table profile_account (
	id_account INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
	, name varchar(50) not null
	, birth_date date not null
	, signup_date date not null
);

alter table profile_account add foreign key (id_account) references account(id_account);

create table book (
	id_book INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
	, isbn varchar(255) not null
	, id_account int not null
);

alter table book add foreign key (id_account) references account(id_account);

create table movie (
	id_movie INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
	, title varchar(255) not null
);

create table indication (
	id_indication INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
	, id_book int not null
	, id_movie int not null
);

alter table indication add foreign key (id_book) references book(id_book);
alter table indication add foreign key (id_movie) references movie(id_movie);

create table comment (
	id_comment INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
	, comment varchar(140) not null
	, id_indication int not null
	, id_account int not null
);

alter table comment add foreign key (id_account) references account(id_account);
alter table comment add foreign key (id_indication) references indication(id_indication);

create table category_book (
	id_category_book INT not null primary key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1)
	, category_book varchar(50) not null
);

create table category_movie (
	category_book varchar(50) not null
	, id_category_book int not null references category_book(id_category_book)
);

alter table category_movie add foreign key (id_category_book) references category_book(id_category_book);
