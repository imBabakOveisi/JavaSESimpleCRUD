create table persons (
    person_id number primary key ,
    first_name nvarchar2(30) not null ,
    last_name nvarchar2(30) not null ,
    birth_date date not null ,
    role nvarchar2(20) not null ,
    active number(1) default 1 not null
);

create sequence person_seq start with 1 increment by 1;

--

create table educations (
    education_id number primary key ,
    person_id number references persons,
    university nvarchar2(50) not null ,
    education_grade nvarchar2(30) not null ,
    average number(4, 2) not null ,
    start_date date not null ,
    end_date date not null
);

create sequence education_seq start with 1 increment by 1;