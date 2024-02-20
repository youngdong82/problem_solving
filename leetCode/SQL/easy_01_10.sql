-- sql_easy_01 --------------------------------------------------------------- 1757. Recyclable and Low Fat Products
    select product_id 
    from Products 
    where low_fats = 'Y'
    and recyclable = 'Y'

-- sql_easy_02 --------------------------------------------------------------- 584. Find Customer Referee
    select name
    from Customer
    where referee_id != 2 
    OR referee_id is null

-- sql_easy_03 --------------------------------------------------------------- 595. Big Countries
    select name, population, area
    from World
    where area >= 3000000 or population >= 25000000

-- sql_easy_04 --------------------------------------------------------------- 1148. Article Views I
    select distinct author_id as id
    from Views
    where author_id = viewer_id
    order by author_id ASC;

-- sql_easy_05 --------------------------------------------------------------- 1683. Invalid Tweets
    select tweet_id
    from Tweets
    where LENGTH(content) > 15




-- sql_easy_06 --------------------------------------------------------------- 1378. Replace Employee ID With The Unique Identifier
    select emp.name, uni.unique_id
    from Employees as emp
    left join EmployeeUNI as uni
    on emp.id = uni.id

-- sql_easy_07 --------------------------------------------------------------- 1068. Product Sales Analysis I
    select b.product_name, a.year, a.price
    from Sales as a
    left join Product as b
    on a.product_id = b.product_id

-- sql_easy_08 --------------------------------------------------------------- 1581. Customer Who Visited but Did Not Make Any Transactions
    select a.customer_id, count(a.customer_id) as count_no_trans
    from Visits as a
    left join Transactions as b
    on a.visit_id = b.visit_id
    where transaction_id is null
    group by a.customer_id

-- sql_easy_09 --------------------------------------------------------------- 197. Rising Temperature
    select w1.id
    from Weather as w1,  Weather as w2
    where w1.temperature > w2.temperature 
    and DATEDIFF(w1.recordDate, w2.recordDate) = 1
    
-- sql_easy_10 --------------------------------------------------------------- 1661. Average Time of Process per Machine
    select a1.machine_id, ROUND(AVG(a2.timestamp - a1.timestamp), 3) as processing_time
    from Activity a1
    join Activity a2
    on a1.process_id=a2.process_id
    and a1.machine_id=a2.machine_id
    and a1.timestamp < a2.timestamp
    group by a1.machine_id
