CREATE TABLE episodes (
     id BIGINT NOT NULL AUTO_INCREMENT,
     show_id BIGINT,
     season_number BIGINT,
     episode_number BIGINT,
     PRIMARY KEY (id)
);