create table PUBLIC.notification
(
    id bigint auto_increment,
    notifier bigint not null,
    receiver bigint not null,
    outerid BIGINT not null,
    type int not null,
    gmt_create bigint not null,
    status int default 0 not null,
    constraint NOTIFICATION_PK
        primary key (id)
);

