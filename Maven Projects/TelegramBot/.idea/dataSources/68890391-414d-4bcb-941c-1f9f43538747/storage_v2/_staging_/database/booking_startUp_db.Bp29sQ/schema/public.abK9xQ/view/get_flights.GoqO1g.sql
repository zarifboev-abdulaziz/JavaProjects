create view get_flights1
            (depart_time, arrival_time, airline, depart_airport, arrival_deport, depart_city,
             arrive_city, price, timestamp)
as
SELECT f.depart_date_time::timestamp without time zone  AS depart_time,
       f.arrival_date_time::timestamp without time zone AS arrival_time,
       a.name                                           AS airline,
       air1.name                                        AS depart_airport,
       air2.name                                        AS arrival_deport,
       city1.name                                       AS depart_city,
       city2.name                                       AS arrive_city,
       f.flight_price                                   AS price,
       (f.depart_date_time - f.arrival_date_time) AS duration
FROM flights f
         JOIN airlines a ON a.id = f.airline_id

         JOIN airports air1 ON air1.id = f.depart_airport_id
         JOIN airports air2 ON air2.id = f.arrival_airport_id

         JOIN cities city1 ON city1.id = air1.city_id
         JOIN cities city2 ON city2.id = air2.city_id;

alter table get_flights
    owner to postgres;

