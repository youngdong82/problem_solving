-- sql_medium_11 --------------------------------------------------------------- 1907. Count Salary Categories
SELECT "Low Salary" AS category,
    sum(income < 20000) AS accounts_count
FROM Accounts

UNION

SELECT "Average Salary" AS category,
    sum(income BETWEEN 20000 AND 50000) AS accounts_count
FROM Accounts

UNION

SELECT "High Salary" AS category,
    sum(income > 50000) AS accounts_count
FROM Accounts;


-- sql_medium_12 --------------------------------------------------------------- 626. Exchange Seats
-- 내꺼
with swap_table as(
    select s1.id as asis_id
        ,   s1.student as asis_name
        ,   s2.id as tobe_id
        ,   s2.student as tobe_name
    from Seat s1
    join Seat s2
    where s2.id - s1.id = 1
    and s2.id % 2 = 0
)


    select s.id as id
        ,   st.tobe_name as student
    from Seat s
    left join swap_table st
    on s.id = st.asis_id 
    where st.asis_id is not null

    union

    select s.id as id
        ,   st.asis_name as student
    from Seat s
    left join swap_table st
    on s.id = st.tobe_id 
    where st.asis_id is not null

    union
    (
        select *
        from Seat
        where MOD(id,2) =1
        and id in (
            select MAX(id) id
            from Seat
        )
    )

order by id

-- 좀더 똑똑한 union
(
    select (id+1) as id,student
    FROM Seat
    WHERE MOD(id,2)=1 AND id != (SELECT max(id) FROM Seat)
)
UNION
(
    SELECT (id-1) as id,student
    FROM Seat 
    WHERE MOD(id,2) = 0
)
UNION
(
    SELECT *
    FROM Seat
    WHERE id = (SELECT max(id) FROM Seat) AND MOD(id,2) = 1
)
ORDER BY id

-- 가장 깔끔
select
    case
        -- 가장 큰 숫자인데 홀수라면 그대로
        when id = (select max(id) from seat) and id%2=1 then id
        -- 홀수라면 id +1
        when id%2=1 then id+1
        -- 짝수라면 id -1
        else id-1
    end as id,
    student
from seat
order by id;


-- sql_medium_13 --------------------------------------------------------------- 1341. Movie Rating
with all_join as (
    select  u.name
        ,   m.title
        ,   mr.rating
        ,   mr.created_at
    from MovieRating mr
    left join Users u
    on mr.user_id = u.user_id
    left join Movies m
    on mr.movie_id = m.movie_id
)

(select name as results
from all_join
group by name
order by COUNT(title) desc, name
limit 1)

union all

(select title as results
from all_join
where created_at >= '2020-02-01'
and created_at < '2020-03-01'
group by title
order by AVG(rating) desc, title
limit 1)


-- sql_medium_14 --------------------------------------------------------------- 1321. Restaurant Growth
with cumulative_table as(
    select customer_id 
        , visited_on
        , SUM(1) over(order by visited_on) as idx
        , sum(amount) as sum_amount
        , SUM(sum(amount)) over(ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) as cumulative_sum
    from Customer 
    group by visited_on
    order by visited_on 
)

select visited_on
    ,   cumulative_sum as amount
    , ROUND(cumulative_sum/7,2) as average_amount 
from cumulative_table
where idx >= 7


-- sql_medium_15 --------------------------------------------------------------- 602. Friend Requests II: Who Has the Most Friends
with id_table as(
    select requester_id as id
    from RequestAccepted
    union all
    select accepter_id as id
    from RequestAccepted
)

select id
    ,   count(id) as num
from id_table
group by id
order by num desc
limit 1


-- sql_medium_16 --------------------------------------------------------------- 
-- sql_medium_17 --------------------------------------------------------------- 
-- sql_medium_18 --------------------------------------------------------------- 
-- sql_medium_19 --------------------------------------------------------------- 
-- sql_medium_20 --------------------------------------------------------------- 
