INSERT INTO member (name, email, password, role)
VALUES ('어드민', 'test@email.com', 'password', 'ADMIN'),
       ('유저', 'user@email.com', 'password', 'USER');

INSERT INTO reservation_time (start_at)
VALUES ('13:00'),
       ('14:00'),
       ('15:00');

INSERT INTO theme (name, description, thumbnail)
VALUES ('테마1', '테마1 설명', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('테마2', '테마2 설명', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('테마3', '테마3 설명', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('테마4', '테마4 설명', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg'),
       ('테마5', '테마5 설명', 'https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg');

INSERT INTO payment(payment_key, order_id, amount)
VALUES ('paymentkey_1849139t7a0sfac973', 'roomescape_1133125', 1000),
       ('paymentkey_3osvd93p38vdae39br', 'roomescape_1133126', 2000);

INSERT INTO reservation (date, member_id, time_id, theme_id, payment_status, payment_id)
VALUES (NOW() - 4, 1, 1, 1, 'PENDING', null),
       (NOW() - 3, 1, 2, 1, 'PENDING', null),
       (NOW() - 2, 1, 3, 1, 'PENDING', null),
       (NOW() - 1, 1, 1, 2, 'WEB_PAID', 1),
       (NOW() + 0, 1, 2, 2, 'WEB_PAID', 2),
       (NOW() + 1, 2, 1, 3, 'PENDING', null);

INSERT INTO waiting(date, member_id, time_id, theme_id)
VALUES (NOW() + 1, 1, 1, 3);