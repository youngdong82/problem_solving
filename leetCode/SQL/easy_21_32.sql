-- sql_easy_21 --------------------------------------------------------------- 610. Triangle Judgement
  select *,
      case when (
          x + y + z - GREATEST(x, y, z) <= GREATEST(x, y, z)
      ) then 'No' else 'Yes' end as triangle
  from Triangle 


-- sql_easy_22 --------------------------------------------------------------- 182. Duplicate Emails
  select email as Email
  from Person 
  group by email  
  HAVING count(email) > 1

-- sql_easy_23 --------------------------------------------------------------- 196. Delete Duplicate Emails
  delete p1
  from Person p1, Person p2
  where p1.email = p2.email and p1.id > p2.id


-- sql_easy_24 --------------------------------------------------------------- 1517. Find Users With Valid E-Mails 
  select *
  from Users
  where mail regexp '^[A-Za-z][A-Za-z0-9_\.\-]*@leetcode[.]com$'


-- sql_easy_25 --------------------------------------------------------------- 1731. The Number of Employees Which Report to Each Employee
  select  e1.employee_id ,
          e1.name ,
          COUNT(e2.employee_id) as reports_count,
          ROUND(AVG(e2.age),0) as average_age
  from Employees e1
  left join Employees e2
  on e1.employee_id = e2.reports_to
  where e2.employee_id is not null
  group by e1.employee_id
  order by e1.employee_id


-- sql_easy_26 --------------------------------------------------------------- 1667. Fix Names in a Table 
  select user_id,
      CONCAT(UPPER(LEFT(name,1)), LOWER(SUBSTRING(name,2)))    as name
  from Users
  order by user_id


-- sql_easy_27 --------------------------------------------------------------- 607. Sales Person 
  select  s.name
  from SalesPerson s
  left join Orders o
  on s.sales_id = o.sales_id
  left join Company c
  on o.com_id = c.com_id
  group by s.name
  having SUM(case when c.name = 'red' then 1 else 0 end) < 1


-- sql_easy_28 --------------------------------------------------------------- 1141. User Activity for the Past 30 Days I
  select activity_date as day
      , COUNT( distinct user_id) as active_users
  from Activity
  where activity_date > '2019-06-27'
      and activity_date <= '2019-07-27'
  group by activity_date
  order by activity_date


-- sql_easy_29 --------------------------------------------------------------- 596. Classes More Than 5 Students 
  select class
  from Courses
  group by class
  having COUNT(student) >= 5


-- sql_easy_30 --------------------------------------------------------------- 1729. Find Followers Count 
  select user_id
      , COUNT(follower_id )as followers_count 
  from Followers 
  group by user_id
  order by user_id


-- sql_easy_31 --------------------------------------------------------------- 1789. Primary Department for Each Employee
  (
      select employee_id 
          , department_id 
      from Employee 
      where primary_flag = 'Y'
  ) 
  UNION
  (
      select employee_id 
          , department_id 
      from Employee 
      group by employee_id
      having count(employee_id) = 1
  )



  SELECT employee_id
      , department_id
  FROM Employee
  WHERE primary_flag = 'Y' 
    OR employee_id in (
            SELECT employee_id
            FROM Employee
            GROUP BY employee_id
            HAVING COUNT(*) = 1
        )


-- sql_easy_32 --------------------------------------------------------------- 1484. Group Sold Products By The Date
  select sell_date
      , COUNT(distinct product) as num_sold
      , GROUP_CONCAT( DISTINCT product order by product ASC separator ',' ) as products
  from Activities 
  group by sell_date 
  order by sell_date

