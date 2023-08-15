create function get_product_names(price_from integer, price_to integer)
    returns TABLE
            (
                product_name  character varying,
                product_price integer
            )
    language plpgsql
as
$$
Begin
    return query
        select name, price
        from products
        where price between Price_from and Price_to;
End;
$$;

alter function get_product_names(integer, integer) owner to postgres;

