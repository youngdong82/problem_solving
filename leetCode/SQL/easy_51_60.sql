-- sql_easy_51 --------------------------------------------------------------- 512. Game Play Analysis II
select player_id, device_id 
from Activity 
where (player_id, event_date) in (select player_id
    ,   MIN(event_date)  as event_date
from Activity
group by player_id) 



-- sql_easy_52 --------------------------------------------------------------- 1076. Project Employees II
select project_id
from Project
group by project_id
having COUNT(employee_id) = (
    select COUNT(employee_id) cnt
    from Project 
    group by project_id
    order by cnt desc
    limit 1)


-- sql_easy_53 --------------------------------------------------------------- 1173. Immediate Food Delivery I
-- 1.
select round(
    sum(case when order_date = customer_pref_delivery_date then 1 else 0 end) 
        *100 
        / count(*)
    ,2) as immediate_percentage 
from Delivery

-- 2. AVG 활용
SELECT ROUND(
    100 * AVG(order_date = customer_pref_delivery_date)
    ,2) AS immediate_percentage
FROM Delivery;



-- sql_easy_54 --------------------------------------------------------------- 241. Number of Comments per Post
-- 1. use cte
with posts as (
    select distinct sub_id as post_id
    from Submissions 
    where parent_id is null
    order by post_id
)

select post_id
    , count(distinct s.sub_id) number_of_comments 
from posts p
left join Submissions s 
on p.post_id = s.parent_id
group by p.post_id


-- 2. cte 습관되는것 같은데 되도록 이렇게 짜도록 하자!!
select s1.sub_id as post_id 
    , count(distinct s2.sub_id) number_of_comments 
from Submissions s1 
left join Submissions s2 
on s1.sub_id = s2.parent_id
where s1.parent_id is null
group by s1.sub_id


-- sql_easy_55 --------------------------------------------------------------- 
-- sql_easy_56 --------------------------------------------------------------- 
-- sql_easy_57 --------------------------------------------------------------- 
-- sql_easy_58 --------------------------------------------------------------- 
-- sql_easy_59 --------------------------------------------------------------- 
-- sql_easy_60 --------------------------------------------------------------- 
