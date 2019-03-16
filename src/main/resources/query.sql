DROP TABLE FILE_STRUCTURE_CHILDREN;
DROP TABLE FILE_STRUCTURE;
COMMIT;

CREATE TABLE FILE_STRUCTURE
(
	PATH	     VARCHAR2(255 CHAR) NOT NULL,
	EXT          VARCHAR2(255 CHAR),
	IS_DIRECTORY NUMBER(1,0)        NOT NULL,
	NAME         VARCHAR2(255 CHAR) NOT NULL,
	TIMESTAMP    VARCHAR2(255 CHAR) NOT NULL,
	PRIMARY KEY (PATH)
)
;
CREATE TABLE FILE_STRUCTURE_CHILDREN
(
	FILE_STRUCTURE_PATH VARCHAR2(255 char) NOT NULL,
	CHILDREN            VARCHAR2(255 CHAR) NOT NULL
)
;
ALTER TABLE FILE_STRUCTURE_CHILDREN
ADD CONSTRAINT FK_FILE_STRUCTURE_CHILDREN FOREIGN KEY
(
  FILE_STRUCTURE_PATH
)
REFERENCES FILE_STRUCTURE
(
  PATH
)
ENABLE
;
COMMIT;


INSERT INTO FILE_STRUCTURE (PATH, IS_DIRECTORY, NAME, TIMESTAMP) VALUES ('/Users/marco27/temp', '0', 'temp', TO_TIMESTAMP('1975-12-27 13:27:03.751000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO FILE_STRUCTURE (PATH, IS_DIRECTORY, NAME, TIMESTAMP) VALUES ('/Users/marco27/temp/docker.txt', '1', 'docker.txt', TO_TIMESTAMP('1975-12-27 13:27:03.751000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO FILE_STRUCTURE_CHILDREN (FILE_STRUCTURE_PATH, CHILDREN) VALUES ('/Users/marco27/temp', '/Users/marco27/temp/docker.txt');
COMMIT;

SELECT
    filestruct0_.path                AS path1_0_0_,
    filestruct0_.ext                 AS ext2_0_0_,
    filestruct0_.is_directory        AS is_directory3_0_0_,
    filestruct0_.name                AS name4_0_0_,
    filestruct0_.timestamp           AS timestamp5_0_0_,
    children1_.file_structure_path   AS file_structure_pat1_1_1_,
    filestruct2_.path                AS children2_1_1_,
    filestruct2_.path                AS path1_0_2_,
    filestruct2_.ext                 AS ext2_0_2_,
    filestruct2_.is_directory        AS is_directory3_0_2_,
    filestruct2_.name                AS name4_0_2_,
    filestruct2_.timestamp           AS timestamp5_0_2_
FROM
    file_structure            filestruct0_
    LEFT OUTER JOIN file_structure_children   children1_ ON filestruct0_.path = children1_.file_structure_path
    LEFT OUTER JOIN file_structure            filestruct2_ ON children1_.children = filestruct2_.path
WHERE
    filestruct0_.path = '/Users/marcoguastalli/temp'


HHH000400: Using dialect: org.hibernate.dialect.Oracle10gDialect
drop table file_structure cascade constraints
Hibernate: drop table file_structure cascade constraints
drop table file_structure_children cascade constraints
Hibernate: drop table file_structure_children cascade constraints
create table file_structure (path varchar2(255 char) not null, ext varchar2(255 char), is_directory number(1,0) not null, name varchar2(255 char) not null, timestamp varchar2(255 char), primary key (path))
Hibernate: create table file_structure (path varchar2(255 char) not null, ext varchar2(255 char), is_directory number(1,0) not null, name varchar2(255 char) not null, timestamp varchar2(255 char), primary key (path))
create table file_structure_children (file_structure_path varchar2(255 char) not null, children varchar2(255 char) not null)
Hibernate: create table file_structure_children (file_structure_path varchar2(255 char) not null, children varchar2(255 char) not null)
alter table file_structure_children add constraint UK_coiq8j0olmm8kpixdqgipnxmo unique (children)
Hibernate: alter table file_structure_children add constraint UK_coiq8j0olmm8kpixdqgipnxmo unique (children)
alter table file_structure_children add constraint FK79hkh1p23yma88n4sjodhnmy3 foreign key (children) references file_structure
Hibernate: alter table file_structure_children add constraint FK79hkh1p23yma88n4sjodhnmy3 foreign key (children) references file_structure
alter table file_structure_children add constraint FK5b9nrgnkp8sbkp0721ptd9ovl foreign key (file_structure_path) references file_structure
Hibernate: alter table file_structure_children add constraint FK5b9nrgnkp8sbkp0721ptd9ovl foreign key (file_structure_path) references file_structure
HHH000476: Executing import script 'ScriptSourceInputFromUrl(file:/Users/marcoguastalli/dev/repository/git/filesystemapi/target/classes/import.sql)'
