/*
 * delete from artist where art_name like '%''%';
 *  delete from album where alb_name like '%''%';
 * delete from song where s_name like '%''%';
 * delete from song where s_name in (select s_name from (select count(song_id),s_name from song group by s_name having count(song_id)>1));
 * delete from album where alb_name in (select alb_name from (select count(album_id),alb_name from album group by alb_name having count(album_id)>1));
 * delete from artist where art_name in (select art_name from (select count(artist_id),art_name from artist group by art_name having count(artist_id)>1));
*/
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
		on delete cascade
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
		on delete cascade,
	foreign key(genre_id) references genre(genre_id)
		on delete cascade,
	primary key(song_id)
);

create table purchased_item (
	p_id varchar(5),
	purchase_id varchar(5),
	song_id varchar(5),
	i_price number(7,3) check(i_price>0),	
	primary key(p_id),
	foreign key(song_id) references song(song_id) 
		on delete cascade,
	foreign key(purchase_id) references purchase(purchase_id)
		on delete cascade
);

/*
 delete from purchase;
 delete from purchased_item;
 drop sequence purchase_seq;
 drop sequence pitem_seq;
 create sequence purchase_seq start with 1;
 create sequence pitem_seq start with 1;
 CREATE OR REPLACE TRIGGER purchase_inc 
 BEFORE INSERT ON purchase
 FOR EACH ROW
 BEGIN
  SELECT purchase_seq.NEXTVAL
  INTO   :new.purchase_id
  FROM   dual;
 END;
 /
 CREATE OR REPLACE TRIGGER pitem_inc 
 BEFORE INSERT ON purchased_item
 FOR EACH ROW
 BEGIN
  SELECT pitem_seq.NEXTVAL
  INTO   :new.p_id
  FROM   dual;
 END;
 /
*/

