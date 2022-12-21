create table player_currency
(
    player_id   bigint not null references player (id),
    currency_id bigint not null references currency (id)
)