drop table if exists users;
drop table if exists roles;
drop table if exists statuses;
drop table if exists types;
drop table if exists reimbursements;

create table roles (
    role_id SERIAL primary key,
    role VARCHAR(20) not null
)

create table users (
    user_id SERIAL primary key,
    username VARCHAR(50) unique not null,
    password VARCHAR(50) not null,
    first_name VARCHAR(100) not null,
    last_name VARCHAR(100) not null,
    email VARCHAR(150) unique not null,
    role_id INTEGER references roles(role_id)
)

create table statuses (
    status_id SERIAL primary key,
    status VARCHAR(10) not null
)

create table types (
    type_id SERIAL primary key,
    type VARCHAR(10) not null
)

create table reimbursements (
    reimb_id SERIAL primary key,
    amount NUMERIC(8, 2) not null,
    submitted DATE not null,
    resolved DATE,
    description VARCHAR(250) not null,
    receipt VARCHAR(500),
    author INTEGER not null references users(user_id),
    resolver INTEGER references users(user_id),
    status_id INTEGER not null references statuses(status_id),
    type_id INTEGER not null references types(type_id)
)

insert into roles (role) values ('Manager');
insert into roles (role) values ('Employee');

insert into users (username, password, first_name, last_name, email, role_id) values ('jash0', 'NReZEQp6u', 'Jacquenette', 'Ash', 'jash0@is.gd', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('rnolton1', 'zSVTvMRQ', 'Riane', 'Nolton', 'rnolton1@boston.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('mstanwix2', '7gd3h8p', 'Myrtice', 'Stanwix', 'mstanwix2@cargocollective.com', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('ktowsey3', 'Z7S8FcSoRI', 'Kathi', 'Towsey', 'ktowsey3@msu.edu', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('xfoggo4', 'nkdEiZd7r', 'Ximenez', 'Foggo', 'xfoggo4@hatena.ne.jp', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('lsawers5', '4FVyfDtKlhb', 'Leandra', 'Sawers', 'lsawers5@wiley.com', 2);

insert into statuses (status) values ('pending');
insert into statuses (status) values ('approved');
insert into statuses (status) values ('denied');

insert into types (type) values ('LODGING');
insert into types (type) values ('TRAVEL');
insert into types (type) values ('FOOD');
insert into types (type) values ('OTHER');

insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (1694.61, '2/3/2021', '2021-02-12 19:37:46', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.', '', 6, 1, 2);
insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (5872.84, '3/17/2021', '2020-10-18 10:06:35', 'Phasellus in felis.', '', 4, 3, 1);
insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (2157.09, '5/7/2021', '2021-05-10 13:25:35', 'Proin risus.', '', 5, 2, 4);
insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (4768.1, '1/29/2021', '2020-11-13 23:47:03', 'Curabitur at ipsum ac tellus semper interdum.', '', 3, 3, 1);
insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (1349.24, '1/30/2021', '2021-07-24 08:47:25', 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', '', 3, 3, 3);
insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (8558.29, '7/12/2021', '2020-12-03 00:21:17', 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', '', 5, 2, 4);
insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (280.23, '3/29/2021', '2021-04-30 21:05:43', 'Sed sagittis.', '', 6, 2, 2);
insert into reimbursements (amount, submitted, resolved, description, receipt, author, status_id, type_id) values (713.88, '6/27/2021', '2021-06-30 06:18:44', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', '', 5, 3, 4);