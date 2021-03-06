create table blocks (id int8 generated by default as identity, uuid varchar(255), title varchar(255), button_link varchar(255), button_text varchar(255), content text, image_id int8, page_id int8, primary key (id));
create table blogs (id int8 generated by default as identity, uuid varchar(255), title varchar(255), content text, slug varchar(255), image_id int8, seo_id int8, primary key (id));
create table experiences (id int8 generated by default as identity, uuid varchar(255), title varchar(255), company varchar(255), description text, end_date date, start_date date, primary key (id));
create table images (id int8 generated by default as identity, uuid varchar(255), title varchar(255), alt_text varchar(255), url varchar(255), primary key (id));
create table menu_items (id int8 generated by default as identity, uuid varchar(255), title varchar(255), is_external boolean, slug varchar(255), menu_id int8, primary key (id));
create table menus (id int8 generated by default as identity, uuid varchar(255), title varchar(255), primary key (id));
create table pages (id int8 generated by default as identity, uuid varchar(255), title varchar(255), slug varchar(255), image_id int8, seo_id int8, primary key (id));
create table projects (id int8 generated by default as identity, uuid varchar(255), title varchar(255), content text, live_link varchar(255), repo_link varchar(255), image_id int8, seo_id int8, primary key (id));
create table seos (id int8 generated by default as identity, uuid varchar(255), title varchar(255), description varchar(255), primary key (id));
create table settings (id int8 generated by default as identity, uuid varchar(255), name varchar(255), value varchar(255), primary key (id));
alter table if exists blocks add constraint FKjm72ja129ywhwnb3hqeap4lvb foreign key (image_id) references images;
alter table if exists blocks add constraint FKt25oy9al6y95kdkr3qorehvek foreign key (page_id) references pages;
alter table if exists blogs add constraint FKd3hupdqimepujqpq15cu9g6j8 foreign key (image_id) references images;
alter table if exists blogs add constraint FKhiqi4eafwsqku2fwy2sfk6028 foreign key (seo_id) references seos;
alter table if exists menu_items add constraint FK6fwmu1a0d0hysfd3c00jxyl2c foreign key (menu_id) references menus;
alter table if exists pages add constraint FKi0j04aiibciea31rbwukp7x3 foreign key (image_id) references images;
alter table if exists pages add constraint FKr83q6n0wenow8nh95ccw65wah foreign key (seo_id) references seos;
alter table if exists projects add constraint FK1olf86tl58sibkbmsjvn8xm9p foreign key (image_id) references images;
alter table if exists projects add constraint FKsm6kewfprs2uwkx453mk5h239 foreign key (seo_id) references seos;
