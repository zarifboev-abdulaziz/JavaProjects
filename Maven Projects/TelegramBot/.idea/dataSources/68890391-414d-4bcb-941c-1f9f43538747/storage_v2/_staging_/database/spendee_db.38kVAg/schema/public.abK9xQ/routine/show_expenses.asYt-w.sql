create function show_expenses(i_user_id integer, date_from date, date_to date)
    returns TABLE(expense character varying, percent text)
    language plpgsql
as
$$
Begin
    return query
        select sc.name               AS Expense,
               concat(round(100 * sum(transactions.amount)::decimal / (select sum(amount)
                                                                       from transactions
                                                                       where user_id = i_user_id
                                                                         AND category_id = 1
                                                                         AND date > date_from
                                                                         AND date < date_to)::decimal,
                            2), '%') AS
                                        Percentage
        from transactions
                 join sub_categories sc on transactions.subcategory_id = sc.id
        where user_id = i_user_id
          AND transactions.category_id = 1
          AND date > date_from
          AND date < date_to
        Group By sc.name;
End;
$$;

alter function show_expenses(integer, date, date) owner to postgres;

