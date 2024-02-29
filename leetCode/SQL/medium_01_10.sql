-- sql_medium_01 --------------------------------------------------------------- 570. Managers with at Least 5 Direct Reports
    select e1.name
    from Employee e1
    left join Employee e2
    on e1.id = e2.managerId
    group by e1.id
    having COUNT(e2.id) >= 5



-- sql_medium_02 --------------------------------------------------------------- 1934. Confirmation Rate
    select s.user_id
        , IFNULL(ROUND(sum(case when c.action = 'confirmed' then 1 else 0 end) / COUNT(c.user_id),2),0) as confirmation_rate
    from Signups s
    left join Confirmations c
    on s.user_id = c.user_id
    group by s.user_id


-- sql_medium_03 --------------------------------------------------------------- 1193. Monthly Transactions I
    select  DATE_FORMAT(trans_date, '%Y-%m') as month
        ,   country
        ,   COUNT(id) as trans_count
        ,   SUM(case when state = 'approved' then 1 else 0 end) as approved_count 
        ,   SUM(amount) as trans_total_amount 
        ,   SUM(case when state = 'approved' then amount else 0 end) as approved_total_amount  
    from Transactions 
    group by month ,country

-- sql_medium_04 --------------------------------------------------------------- 1174. Immediate Food Delivery II
    select  ROUND(SUM(case when order_date = customer_pref_delivery_date then 1 else 0 end) * 100 / COUNT(*),2)  as immediate_percentage 
    from Delivery d
    where ( d.customer_id, d.order_date) in(
        select customer_id 
        ,   MIN(order_date)
        from Delivery 
        group by customer_id
    )
-- sql_medium_05 --------------------------------------------------------------- 550. Game Play Analysis IV
    SELECT 
        ROUND(
            COUNT( distinct player_id) / 
            (SELECT COUNT(DISTINCT player_id) FROM Activity)
        ,2) as fraction
    FROM   Activity
    WHERE (player_id, DATE_SUB(event_date, INTERVAL 1 DAY)) in(
        SELECT  player_id, MIN(event_date) AS first_login 
        FROM Activity 
        GROUP BY player_id
    )

-- sql_medium_06 --------------------------------------------------------------- 070. Product Sales Analysis III
    select product_id
        ,   year as first_year
        ,   quantity
        ,   price
    from Sales
    where (product_id, year) in(
        select product_id, min(year)
        from Sales
        group by product_id
    )


-- sql_medium_07 --------------------------------------------------------------- 
-- sql_medium_08 --------------------------------------------------------------- 
-- sql_medium_09 --------------------------------------------------------------- 
-- sql_medium_10 --------------------------------------------------------------- 
