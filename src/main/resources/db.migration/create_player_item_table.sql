create table player_item
(
    player_id bigint not null references player (id),
    item_id   bigint not null references item (id)
)