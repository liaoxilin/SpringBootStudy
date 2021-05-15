create table USER
(
    id int auto_increment,
    NAME       VARCHAR(30) default NULL,
    ACCOUNT_ID VARCHAR(50) default NULL,
    TOKEN      VARCHAR(50) default NULL,
    GMT_CREATE VARCHAR(50) default NULL,
    GMT_MODIFY VARCHAR(50) default NULL,
    BIO        VARCHAR(256),
    constraint USER_PK
        primary key (id)
);