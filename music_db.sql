create table users (
	user_id varchar(5),
	u_name varchar(20) not null,
	email varchar(30) unique,
	password varchar(20) not null,	
	primary key(user_id)
);

create table purchase (
	purchase_id varchar(5),
	user_id varchar(20),
	p_date date,
	total_cost number(8,3) not null,	
	primary key(purchase_id),
	foreign key(user_id) references users(user_id)
		on delete set null
);

create table artist (
	artist_id varchar(5),
	art_name varchar(150) not null,
	bio varchar(200),	
	primary key(artist_id)
);

create table album (
	album_id varchar(5),
	alb_name varchar(150) not null,
	artist_id varchar(5),
	release_date date not null,	
	primary key(album_id),
	foreign key(artist_id) references artist(artist_id)
		on delete set null
);
 create table genre (
	genre_id varchar(5),
	g_name varchar(20) not null,	
	primary key(genre_id)
);
create table song (
	song_id varchar(5),
	s_name varchar(200) not null,
	album_id varchar(5),
	genre_id varchar(5),
	s_price number(8,3) check (s_price>0),
	foreign key(album_id) references album(album_id)
		on delete set null,
	foreign key(genre_id) references genre(genre_id)
		on delete set null,
	primary key(song_id)
);

create table purchased_item (
	p_id varchar(5),
	purchase_id varchar(5),
	song_id varchar(5),
	i_price number(7,3) check(i_price>0),	
	primary key(p_id),
	foreign key(song_id) references song(song_id) 
		on delete set null,
	foreign key(purchase_id) references purchase(purchase_id)
		on delete cascade
);

