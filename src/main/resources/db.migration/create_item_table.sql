create table item
(
    id         bigint generated by default as identity,
    count      bigint,
    level      bigint,
    resourceId bigint,
    primary key (id)
)