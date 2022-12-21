create table progress
(
    id         bigint generated by default as identity,
    maxScore   bigint,
    resourceId bigint,
    score      bigint,
    player     bigint references player (id),
    primary key (id)
)