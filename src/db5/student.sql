drop table student purge;
select * from tab;

create table student(
num number primary key,
name varchar2(12) not null,
kor number,
eng number,
mat number);

create sequence n_sql increment by 1 start with 1 nocycle nocache;
drop sequence n_sql;

insert into student(num,name,kor,eng,mat) values(n_sql.nextval,'kim',75,65,88);
insert into student(num,name,kor,eng,mat) values(n_sql.nextval,'lee',100,75,98);
select * from student;
