--Структура таблицы `history`
CREATE TABLE "history" (
    id bigserial PRIMARY KEY,
    message text NOT NULL,
    user_id int NOT NULL
);

-- Структура таблицы `role`
CREATE TABLE "role" (
    id bigserial PRIMARY KEY,
    role text NOT NULL
);

-- Структура таблицы `user`
CREATE TABLE "user" (
    id bigserial PRIMARY KEY,
    first_name text NOT NULL,
    last_name text NOT NULL,
    email text NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    phone text,
    role int NOT NULL,
    CONSTRAINT fk_role
        FOREIGN KEY(role)
            REFERENCES role(id)
);

ALTER TABLE "history"
ADD CONSTRAINT fk_user
    FOREIGN KEY(user_id)
        REFERENCES "user"(id);

