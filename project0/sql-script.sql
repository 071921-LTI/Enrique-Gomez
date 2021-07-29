create table if not exists users(
	userId SERIAL primary key,
	username VARCHAR(20) unique not null,
	password VARCHAR(20) not null,
	userType VARCHAR(10) not null
);

create table if not exists items(
	itemId SERIAL primary key,
	itemName VARCHAR(20) not null,
	minimumOffer NUMERIC(6, 2) not null,
	isPurchased BOOLEAN not null default false
);

create table if not exists offers(
	offerId SERIAL primary key,
	customerId INTEGER references users(userId),
	itemId INTEGER references items(itemId),
	offerAmount NUMERIC(6, 2) not null,
	isAccepted BOOLEAN not null default false
);

insert into users(username, password, userType) values ('enrique', 'password', 'customer');