-- sql_easy_33 --------------------------------------------------------------- 586. Customer Placing the Largest Number of Orders
  select customer_number
  from Orders
  group by customer_number
  order by COUNT(order_number) desc
  limit 1


-- sql_easy_34 --------------------------------------------------------------- 627. Swap Salary
  update Salary
  set sex = case sex when 'm' then 'f' else 'm' end;


-- sql_easy_35 --------------------------------------------------------------- 1083. Sales Analysis II
  select s.buyer_id
  from Sales s
  left join Product p
  on s.product_id = p.product_id
  group by s.buyer_id
  having SUM(case when p.product_name = 'S8' then 1 else 0 end) > 0
  and SUM(case when p.product_name = 'iPhone' then 1 else 0 end) = 0


-- sql_easy_36 --------------------------------------------------------------- 1050. Actors and Directors Who Cooperated At Least Three Times
  select ActorDirector.actor_id
      , ActorDirector.director_id
  from ActorDirector
  group by actor_id, director_id
  having COUNT(actor_id) >= 3


-- sql_easy_37 --------------------------------------------------------------- 1978. Employees Whose Manager Left the Company
  -- 1. left join 사용
  select e1.employee_id
  from Employees e1
  left join Employees e2
  on e1.manager_id = e2.employee_id
  where e1.salary < 30000
  and e1.manager_id is not null
  and e2.employee_id is null
  order by e1.employee_id


  -- 2. 서브쿼리 사용
  select employee_id
  from Employees
  where salary < 30000
  and manager_id not in (
          select employee_id from Employees
      )
  order by employee_id


-- sql_easy_38 --------------------------------------------------------------- 1741. Find Total Time Spent by Each Employee
  select event_day as day
      , emp_id
      , SUM((out_time - in_time)) as total_time
  from Employees
  group by emp_id, event_day


-- sql_easy_39 --------------------------------------------------------------- 603. Consecutive Available Seats
  -- 1. or 사용
  select distinct c1.seat_id
  from Cinema c1
  cross join Cinema c2
  where ( c1.seat_id = c2.seat_id - 1 or c1.seat_id = c2.seat_id + 1 )
  and c1.free = '1' and c2.free = '1'
  order by c1.seat_id


  -- 2. or 사용
  select distinct c1.seat_id
  from Cinema c1
  cross join Cinema c2
  where ABS( c2.seat_id - c1.seat_id ) = 1
  and c1.free = '1' and c2.free = '1'
  order by c1.seat_id


-- sql_easy_40 --------------------------------------------------------------- 1084. Sales Analysis III
-- 내꺼 => 2019년도가 아닌 값이 나오면 틀림
  select s.product_id, p.product_name
  from Sales s
  left join Product p
  on s.product_id = p.product_id
  where s.sale_date between '2019-01-01' and '2019-03-31'
  and s.product_id not in (
      select product_id
      from Sales
      where sale_date not between '2019-01-01' and '2019-03-31'
      )

-- 정답 => 다른 연도가 와도 상관없음
  SELECT s.product_id, product_name
  FROM Sales s
  LEFT JOIN Product p
  ON s.product_id = p.product_id
  GROUP BY s.product_id
  HAVING MIN(sale_date) >= '2019-01-01' AND
        MAX(sale_date) <= '2019-03-31'



