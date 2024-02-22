-- sql_easy_11 --------------------------------------------------------------- 577. Employee Bonus
  select a.name,b.bonus
  from Employee a
  left join Bonus b
  on a.empId = b.empId
  where b.bonus is null or b.bonus < 1000


-- sql_easy_12 --------------------------------------------------------------- 1280. Students and Examinations
  select stu.student_id, 
        stu.student_name, 
        sub.subject_name, 
        COUNT(exam.student_id) as attended_exams
  from Students stu
  cross join Subjects sub
  left join Examinations exam
      on stu.student_id = exam.student_id
      and sub.subject_name = exam.subject_name
  group by stu.student_id, sub.subject_name
  order by stu.student_id, sub.subject_name;


-- sql_easy_13 --------------------------------------------------------------- 620. Not Boring Movies
  select *
  from Cinema
  where description != 'boring'
  and MOD(id, 2)
  order by rating desc


-- sql_easy_14 --------------------------------------------------------------- 1251. Average Selling Price
  select  p.product_id, 
          IFNULL(Round(SUM(u.units * p.price)/SUM(u.units),2),0)as average_price
  from Prices p 
  left join UnitsSold u
  on p.product_id = u.product_id
  and u.purchase_date BETWEEN start_date AND end_date
  group by p.product_id

-- sql_easy_15 --------------------------------------------------------------- 1075. Project Employees I
  select  p.project_id,
          ROUND(SUM(e.experience_years)/COUNT(e.name),2) average_years
  from Project p
  left join Employee e
  on p.employee_id = e.employee_id
  where e.experience_years is not null
  group by p.project_id


-- sql_easy_16 --------------------------------------------------------------- 1633. Percentage of Users Attended a Contest
  select  r.contest_id,
          ROUND(count(contest_id) * 100 / (select COUNT(user_id) from Users),2) as percentage
  from Register r
  group by contest_id
  order by percentage desc, contest_id


-- sql_easy_17 --------------------------------------------------------------- 1211. Queries Quality and Percentage
  select query_name,
          ROUND( avg(rating / position) ,2) as quality,
          ROUND( sum(case when rating < 3 then 1 else 0 end)*100 / COUNT(rating), 2 ) as poor_query_percentage
  from Queries
  where query_name is not null
  group by query_name


-- sql_easy_18 --------------------------------------------------------------- 2356. Number of Unique Subjects Taught by Each Teacher
  select teacher_id, COUNT(distinct subject_id) as cnt
  from Teacher
  group by teacher_id


-- sql_easy_19 --------------------------------------------------------------- 175. Combine Two Tables
  select p.firstName, p.lastName, a.city, a.state
  from Person p
  left join Address a
  on p.personId = a.personId

-- sql_easy_20 --------------------------------------------------------------- 181. Employees Earning More Than Their Managers
  select e1.name as Employee 
  from Employee e1
  left join Employee e2
  on e1.managerId = e2.id
  where e1.managerId is not null
  and e1.salary > e2.salary