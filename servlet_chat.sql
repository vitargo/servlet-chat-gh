# Создание базы даных `servlet_chat`
CREATE DATABASE `servlet_chat`;

# Структура таблицы `history`
CREATE TABLE `history` (
                           `id` bigint PRIMARY KEY AUTO_INCREMENT,
                           `message` int NOT NULL,
                           `user_id` int NOT NULL
);

# Структура таблицы `role`
CREATE TABLE `role` (
                        `Id` bigint PRIMARY KEY AUTO_INCREMENT,
                        `role` text NOT NULL
);

# Структура таблицы `user`
CREATE TABLE `user` (
                        `Id` bigint PRIMARY KEY AUTO_INCREMENT,
                        `first_name` text NOT NULL,
                        `last_name` text NOT NULL,
                        `email` text NOT NULL,
                        `login` text NOT NULL,
                        `password` text NOT NULL,
                        `phone` text,
                        `role` int NOT NULL
);

