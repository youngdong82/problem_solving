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


-- sql_medium_07 --------------------------------------------------------------- 1045. Customers Who Bought All Products
-- 1.
with subQuery as (
    select COUNT(product_key) as cnt
    from Product
)

select c.customer_id
from Customer c
join subQuery 
group by c.customer_id
HAVING COUNT(distinct c.product_key) = (select cnt from subQuery)

-- 2.
select c.customer_id
from Customer c
group by c.customer_id
HAVING COUNT(distinct c.product_key) = (select COUNT(product_key) from Product)


-- sql_medium_08 --------------------------------------------------------------- 180. Consecutive Numbers
select distinct l1.num as ConsecutiveNums
from Logs l1
join Logs l2
join Logs l3
where   (l3.num = l2.num and (l3.id-l2.id) = 1)
    and (l2.num = l1.num and (l2.id-l1.id) = 1)


-- sql_medium_09 --------------------------------------------------------------- 1164. Product Price at a Given Date
select product_id
    ,  new_price as price 
from Products 
where (product_id,change_date) in (
    select product_id , max(change_date) as date 
    from Products
    where change_date <='2019-08-16' 
    group by product_id
)
union all

select distinct product_id
    ,  10 as price 
from Products 
where product_id not in(
    select distinct product_id 
    from Products 
    where change_date <='2019-08-16' 
    )


-- sql_medium_10 --------------------------------------------------------------- 1204. Last Person to Fit in the Bus
select person_name 
from (  
    select person_name
        ,  sum(weight) over(order by turn) as cum_sum
    from queue
) as increase
where cum_sum <= 1000
order by cum_sum desc
limit 1
