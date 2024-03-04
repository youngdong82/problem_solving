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

-- sql_medium_13 --------------------------------------------------------------- 
-- sql_medium_14 --------------------------------------------------------------- 
-- sql_medium_15 --------------------------------------------------------------- 
-- sql_medium_16 --------------------------------------------------------------- 
-- sql_medium_17 --------------------------------------------------------------- 
-- sql_medium_18 --------------------------------------------------------------- 
-- sql_medium_19 --------------------------------------------------------------- 
-- sql_medium_20 --------------------------------------------------------------- 
