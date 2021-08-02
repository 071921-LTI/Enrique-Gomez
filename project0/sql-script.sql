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

insert into users(username, password, userType) values ('ezemog1996', 'password', 'employee');
insert into users(username, password, userType) values ('enrique', 'password', 'customer');

insert into items (itemId, itemName, minimumOffer, isPurchased) values (1, 'GX', 46, false);
insert into items (itemId, itemName, minimumOffer, isPurchased) values (2, 'Previa', 86, true);
insert into items (itemId, itemName, minimumOffer, isPurchased) values (3, 'Q', 88, true);
insert into items (itemId, itemName, minimumOffer, isPurchased) values (4, 'Silverado 2500', 2, false);
insert into items (itemId, itemName, minimumOffer, isPurchased) values (5, '1500', 58, true);

select * from offers join items on offers.itemId = items.itemId join users on offers.customerId = users.userId;
