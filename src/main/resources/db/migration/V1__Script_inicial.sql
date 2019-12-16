create table url(
	uid bigserial primary key,
	url varchar(255) not null,
	new_url varchar(255) not null,
	type_expiration varchar(15) not null,
	time_expiration bigint not null,
	date_create timestamp not null
);
