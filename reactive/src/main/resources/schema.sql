CREATE TABLE IF  NOT EXISTS "product" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name varchar(255),
    owner varchar(255),
    description varchar(255)
);