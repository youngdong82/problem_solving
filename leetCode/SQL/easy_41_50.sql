-- sql_easy_41 --------------------------------------------------------------- 1179. Reformat Department Table
    select id,
        sum(case when month='Jan' then revenue end) AS Jan_Revenue,
        sum(case when month='Feb' then revenue end) AS Feb_Revenue,
        sum(case when month='Mar' then revenue end) AS Mar_Revenue,
        sum(case when month='Apr' then revenue end) AS Apr_Revenue,
        sum(case when month='May' then revenue end) AS May_Revenue,
        sum(case when month='Jun' then revenue end) AS Jun_Revenue,
        sum(case when month='Jul' then revenue end) AS Jul_Revenue,
        sum(case when month='Aug' then revenue end) AS Aug_Revenue,
        sum(case when month='Sep' then revenue end) AS Sep_Revenue,
        sum(case when month='Oct' then revenue end) AS Oct_Revenue,
        sum(case when month='Nov' then revenue end) AS Nov_Revenue,
        sum(case when month='Dec' then revenue end) AS Dec_Revenue 
    from Department
    group by id


    SELECT id,
        SUM(if(month = 'Jan', revenue, null)) AS Jan_Revenue,
        SUM(if(month = 'Feb', revenue, null)) AS Feb_Revenue,
        SUM(if(month = 'Mar', revenue, null)) AS Mar_Revenue,
        SUM(if(month = 'Apr', revenue, null)) AS Apr_Revenue,
        SUM(if(month = 'May', revenue, null)) AS May_Revenue,
        SUM(if(month = 'Jun', revenue, null)) AS Jun_Revenue,
        SUM(if(month = 'Jul', revenue, null)) AS Jul_Revenue,
        SUM(if(month = 'Aug', revenue, null)) AS Aug_Revenue,
        SUM(if(month = 'Sep', revenue, null)) AS Sep_Revenue,
        SUM(if(month = 'Oct', revenue, null)) AS Oct_Revenue,
        SUM(if(month = 'Nov', revenue, null)) AS Nov_Revenue,
        SUM(if(month = 'Dec', revenue, null)) AS Dec_Revenue
    FROM Department
    GROUP BY id


-- sql_easy_42 --------------------------------------------------------------- 1890. The Latest Login in 2020
    select  user_id
        ,   MAX(time_stamp) as last_stamp          
    from Logins 
    where year(time_stamp) = 2020
    group by user_id


-- sql_easy_43 --------------------------------------------------------------- 1407. Top Travellers
    select  name
        ,   IFNULL(SUM(distance),0) as travelled_distance 
    from Users u
    left join Rides r
    on u.id = r.user_id
    group by u.id
    order by travelled_distance desc, name asc 


-- sql_easy_44 --------------------------------------------------------------- 1327. List the Products Ordered in a Period
    select p.product_name
        , sum(o.unit) as unit
    from Products p
    left join Orders o
    on p.product_id = o.product_id
    where o.order_date between '2020-02-01' and '2020-02-29'
    group by p.product_id
    having unit >= 100


-- sql_easy_45 --------------------------------------------------------------- 
-- sql_easy_46 --------------------------------------------------------------- 
-- sql_easy_47 --------------------------------------------------------------- 
-- sql_easy_48 --------------------------------------------------------------- 
-- sql_easy_49 --------------------------------------------------------------- 
-- sql_easy_50 --------------------------------------------------------------- 
