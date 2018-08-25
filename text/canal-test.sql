-- auto-generated definition
create table tp_knowledge
(
  ID               int auto_increment
    primary key,
  KNOWLEDGE_NAME   varchar(200) null,
  KNOWLEDGE_IMG    text         null,
  KNOWLEDGE_STATUS char         null,
  DATE_CREATED     datetime     null,
  LAST_UPDATED     datetime     null
)
  charset = utf8;

INSERT INTO emp.tp_knowledge
  (KNOWLEDGE_NAME, KNOWLEDGE_IMG, KNOWLEDGE_STATUS, DATE_CREATED, LAST_UPDATED)
VALUES
  ('66', '66', '3', now(), now());


  