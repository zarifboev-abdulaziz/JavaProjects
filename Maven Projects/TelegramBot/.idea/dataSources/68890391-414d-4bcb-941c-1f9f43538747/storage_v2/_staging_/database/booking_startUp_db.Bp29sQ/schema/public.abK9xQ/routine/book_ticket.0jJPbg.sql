create function book_ticket(i_passenger_id integer, i_flight_id integer,
                            i_flight_class character varying) returns character varying
    language plpgsql
as
$$
declare
    generated_serial_number integer;
    find_flight_id          integer;
    flight_price            double precision;
    airline_id              int;
    from_city_id            int;
    to_city_id              int;
    define_flight_class_id  int;
    flight_class_rate       double precision;

begin
    select id, rate
    into define_flight_class_id, flight_class_rate
    from flight_classes
    where upper(i_flight_class) = name;

    select f.id, f.flight_price, f.airline_id, f.city_from_id, f.city_to_id
    into find_flight_id,
        flight_price, airline_id, from_city_id, to_city_id
    from flights f
    where f.id = i_flight_id;

    if find_flight_id is null then
        return 'Flight not found! Please try again';
    end if;

    INSERT INTO tickets(flight_class_id, price, flight_id, passenger_id)
    values (define_flight_class_id,
            flight_price *
            flight_class_rate,
            find_flight_id,
            i_passenger_id);

    select serial_number
    into generated_serial_number
    from tickets
    where tickets.passenger_id = i_passenger_id
      AND tickets.flight_id = find_flight_id
      AND tickets.price = flight_price * flight_class_rate
      AND tickets.flight_class_id =
          define_flight_class_id;

    INSERT INTO order_history(passenger_id, airline_id, city_from, city_to, price,
                              ticket_serialnum)
    values (i_passenger_id, airline_id,
            from_city_id, to_city_id,
            flight_price * flight_class_rate,
            generated_serial_number);

    call transaction_procedure(i_passenger_id, airline_id, flight_price * flight_class_rate);

    return 'Successful purchase! serial number - ' || generated_serial_number;
end;
$$;

alter function book_ticket(integer, integer, varchar) owner to postgres;

