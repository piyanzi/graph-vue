create schema if not exists test collate utf8mb4_0900_ai_ci;

create table if not exists attribute_table
(
	attribute_id int not null
		primary key,
	element_id int not null,
	attribute_name varchar(32) null,
	attribute_type varchar(255) null,
	attribute_default_value varchar(45) null
);

INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (1, 1, '属性1', null, 'qwe');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (2, 1, '属性2', null, 'asd');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (3, 1, '属性3', null, 'zxc');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (4, 1, '属性4', null, 'qwe');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (5, 1, '属性5', null, 'zxc');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (6, 2, '属性2', null, 'hgj');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (7, 2, '属性3', null, '123');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (8, 2, '属性4', null, 'g35g2');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (9, 2, '属性5', null, '67hjtn');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (10, 2, '属性1', null, '45g4h');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (11, 3, '属性11', null, 'gtreg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (12, 3, '属性12', null, 'grge');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (13, 3, '属性13', null, '789');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (14, 3, '属性14', null, 'ryth');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (15, 3, '属性15', null, 'regv');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (16, 4, '属性16', null, 'trhrt');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (17, 4, '属性17', null, 'erg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (18, 4, '属性18', null, 'erger');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (19, 4, '属性19', null, 'etb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (20, 4, '属性20', null, 'nrg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (21, 5, '属性21', null, 'bebe');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (22, 5, '属性22', null, '132');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (23, 5, '属性23', null, '233');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (24, 5, '属性1', null, 'erg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (25, 5, '属性3', null, 'fdg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (26, 6, '属性2', null, '3g3h');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (27, 6, '属性4', null, '34gh');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (28, 6, '属性5', null, 'gerg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (29, 6, '属性7', null, 'srg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (30, 6, '属性8', null, 'erg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (31, 7, '属性9', null, 'afv');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (32, 7, '属性10', null, 'getg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (33, 7, '属性11', null, 'trb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (34, 7, '属性12', null, 'ebr');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (35, 7, '属性13', null, 'evb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (36, 8, '属性3', null, 'enbern');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (37, 8, '属性4', null, 'rge');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (38, 8, '属性5', null, 'reg');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (39, 8, '属性6', null, 'ehh');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (40, 8, '属性6', null, 'reb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (41, 9, '属性7', null, 'grebv');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (42, 9, '属性8', null, 'ebeb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (43, 9, '属性9', null, 'rbeb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (44, 9, '属性10', null, 'erb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (45, 9, '属性11', null, 'vrfv');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (46, 10, '属性2', null, 'rbe');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (47, 10, '属性5', null, 'dsv');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (48, 10, '属性4', null, 'dfb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (49, 10, '属性3', null, 'nee');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (50, 10, '属性1', null, 'erbr');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (51, 11, '属性1', null, 'tnrt');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (52, 11, '属性2', null, 'etn');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (53, 11, '属性3', null, '35y');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (54, 11, '属性5', null, 'etnet');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (55, 11, '属性4', null, 'rb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (56, 12, '属性4', null, 'df');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (57, 12, '属性3', null, 'ssdv');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (58, 12, '属性2', null, '3g43g');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (59, 12, '属性1', null, 'rberb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (60, 12, '属性5', null, 'reb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (61, 13, '属性1', null, 'regr');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (62, 13, '属性2', null, 'dfb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (63, 13, '属性3', null, 'fdb');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (64, 13, '属性4', null, 'wev');
INSERT INTO test.attribute_table (attribute_id, element_id, attribute_name, attribute_type, attribute_default_value) VALUES (65, 13, '属性5', null, 'web');

create table if not exists element_table
(
	element_id int not null
		primary key,
	element_type int null,
	element_name varchar(32) null,
	element_path varchar(255) null
);

INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (1, null, '分输站', '/elements/分输站.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (2, null, '单向阀', '/elements/单向阀.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (3, null, '截断阀', '/elements/截断阀.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (4, null, '油库', '/elements/油库.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (5, null, '注入站', '/elements/注入站.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (6, null, '理想调节阀', '/elements/理想调节阀.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (7, null, '离心压缩机', '/elements/离心压缩机.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (8, null, '空冷器', '/elements/空冷器.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (9, null, '立管', '/elements/立管.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (10, null, '站场', '/elements/站场.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (11, null, '水平管道', '/elements/水平管道.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (12, null, '竖直管道', '/elements/竖直管道.svg');
INSERT INTO test.element_table (element_id, element_type, element_name, element_path) VALUES (13, null, '阀室', '/elements/阀室.svg');


create table if not exists project_table
(
	project_id int not null
		primary key,
	user_id int not null,
	project_name varchar(32) null,
	project_file varchar(255) null
);

INSERT INTO test.project_table (project_id, user_id, project_name, project_file) VALUES (1, 111, '项目1', '/models/111/1.xml');
INSERT INTO test.project_table (project_id, user_id, project_name, project_file) VALUES (2, 111, '项目2', '/models/111/2.xml');
INSERT INTO test.project_table (project_id, user_id, project_name, project_file) VALUES (3, 333, 'test1', '/models/333/1.xml');


create table if not exists user_table
(
	user_id varchar(32) not null
		primary key,
	username varchar(32) not null,
	password varchar(32) not null,
	role int not null
);

INSERT INTO test.user_table (user_id, username, password, role) VALUES ('111', 'a', 'a', 1);
INSERT INTO test.user_table (user_id, username, password, role) VALUES ('222', 'bbb', 'b', 1);
INSERT INTO test.user_table (user_id, username, password, role) VALUES ('333', 'c', 'c', 0);




