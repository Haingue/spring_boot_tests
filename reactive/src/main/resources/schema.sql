CREATE TABLE IF  NOT EXISTS "product" (
    id UUID,
    name varchar(255),
    owner varchar(255),
    description varchar(255),
    PRIMARY KEY (id)
);