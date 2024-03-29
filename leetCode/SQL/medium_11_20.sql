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


-- sql_medium_16 --------------------------------------------------------------- 585. Investments in 2016
-- 1.
with unique_loacation as(
    select *
    from Insurance
    group by lat, lon
    having count(pid) = 1
), same_2015 as(
    select * 
    from Insurance
    group by tiv_2015
    having count(tiv_2015) > 1
)

    select ROUND(SUM(ul.tiv_2016),2) as tiv_2016 
    from unique_loacation ul
    left join same_2015 s
    on ul.tiv_2015 = s.tiv_2015
    where s.tiv_2015 is not null


-- 2.
SELECT ROUND(SUM(tiv_2016), 2) tiv_2016
FROM insurance
WHERE (tiv_2015 IN (SELECT tiv_2015
                    FROM insurance
                    GROUP BY 1
                    HAVING COUNT(tiv_2015) > 1))
    AND ((lat, lon) IN (SELECT lat, lon
                        FROM insurance
                        GROUP BY 1, 2
                        HAVING COUNT(*) = 1))

-- sql_medium_17 --------------------------------------------------------------- 176. Second Highest Salary
-- 1. 내꺼;;
select (case when count(id) > 0 then salary else null end )as SecondHighestSalary 
from (
    select *
    , rank()over(order by salary desc) as rnk
    from Employee
) rank_table
where rnk != 1
limit 1

-- 2. limit, offset 사용
select (
    select distinct salary    
    from Employee 
    order by salary desc 
    limit 1 offset 1 
) as SecondHighestSalary

-- 3. 이것도 똑똑하고 간단한 느낌
select max(E.salary) as SecondHighestSalary
from Employee E
where E.salary < (
    select max(E.salary) 
    from Employee E
    )
limit 1;


-- sql_medium_18 --------------------------------------------------------------- 1158. Market Analysis I
-- 1.
with cnt as (
    select o.order_date, u.user_id, u.join_date
    from Orders o
    left join Users u
    on o.buyer_id = u.user_id
), cnt2 as (
    select cnt.user_id as buyer_id 
        ,  join_date
        , COUNT(order_date) as orders_in_2019 
    from cnt
    where cnt.order_date >= '2019-01-01'
    group by cnt.user_id
)

select u.user_id as buyer_id 
    ,   u.join_date
    ,   IFNULL(cnt2.orders_in_2019 ,0) as orders_in_2019 
from Users u
left join cnt2
on u.user_id = cnt2.buyer_id


-- 2.
-- left join on 조건1 and 조건2
-- 거의 where 처럼 사용했음
-- where과 다른 점은 where은 해당 값이 없으면 없애버리지만
-- 얘는 null처리함
    select u.user_id AS buyer_id
        ,   u.join_date
        ,   COUNT(o.order_id) AS orders_in_2019 
    from Users u
    left join Orders o
    on o.buyer_id = u.user_id
    and YEAR(o.order_date) = '2019'
    group by u.user_id


-- sql_medium_19 --------------------------------------------------------------- 184. Department Highest Salary
select d.name as Department
    ,   sub.name as Employee
    ,   sub.salary as Salary
from (
    select *
    , rank() over(partition by departmentId order by salary desc) as rnk
    from Employee 
) sub
left join Department d
on sub.departmentId = d.id
where sub.rnk = 1



-- sql_medium_20 --------------------------------------------------------------- 178. Rank Scores
select score
, dense_rank() over(order by score desc) as 'rank'
from Scores

