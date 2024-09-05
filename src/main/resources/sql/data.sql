USE flight;

TRUNCATE TABLE destinations;
DELETE
FROM flights;

INSERT INTO destinations (name)
VALUES ('Ankara (Turkey)'),
       ('Amsterdam (Netherlands)'),
       ('Berlin (Germany)'),
       ('Brasília (Brazil)'),
       ('Buenos Aires (Argentina)'),
       ('Cairo (Egypt)'),
       ('Canberra (Australia)'),
       ('London (UK)'),
       ('Madrid (Spain)'),
       ('Moscow (Russia)'),
       ('New Delhi (India)'),
       ('Ottawa (Canada)'),
       ('Paris (France)'),
       ('Pretoria (South Africa)'),
       ('Riyadh (Saudi Arabia)'),
       ('Rome (Italy)'),
       ('Stockholm (Sweden)'),
       ('Tokyo (Japan)'),
       ('Washington (USA)'),
       ('Beijing (China)');

INSERT INTO flights (datetime, destination, empty_seats)
SELECT DATE_ADD(
               CURDATE() + INTERVAL FLOOR(RAND() * 100) DAY,
               INTERVAL FLOOR(RAND() * 24) HOUR
       ) + INTERVAL ELT(FLOOR(RAND() * 4) + 1, '00:00:00', '00:15:00', '00:30:00', '00:45:00') HOUR_SECOND AS datetime,
       id,
       FLOOR(RAND() * 11) AS empty_seats
FROM
    (SELECT id
     FROM destinations
     ORDER BY RAND()
     LIMIT 20) AS random_destinations
        CROSS JOIN information_schema.tables
LIMIT 100;

