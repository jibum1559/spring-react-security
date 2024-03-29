CREATE TABLE Members (
    MemberID NUMBER PRIMARY KEY,
    Username VARCHAR2(50) UNIQUE NOT NULL,
    Password VARCHAR2(100) NOT NULL,
    FullName VARCHAR2(50) NOT NULL,
    Email VARCHAR2(100) UNIQUE NOT NULL,
    RegistrationDate DATE DEFAULT SYSDATE
);

create sequence members_seq
increment by 1
start with 1
minvalue 1
maxvalue 9999
nocycle
nocache
noorder;