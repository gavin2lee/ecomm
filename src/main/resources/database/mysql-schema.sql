create table pesudo_domain 
(oid varchar(255), create_at datetime, update_at datetime, name varchar(50), identity_id bigint);

alter table pesudo_domain add constraint pk_oid primary key(oid);

create index pesudo_domain_identity_id on pesudo_domain (identity_id);