-- sql_hard_01 --------------------------------------------------------------- 185. Department Top Three Salaries
SELECT d.name AS Department,
        sub.name AS Employee,
        sub.salary
FROM (
    SELECT name,
        departmentId,
        salary,
        DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS salary_rank
    FROM Employee
) AS sub
LEFT JOIN Department AS d
ON sub.departmentId = d.id
WHERE salary_rank <= 3;


-- sql_hard_02 --------------------------------------------------------------- 
-- sql_hard_03 --------------------------------------------------------------- 
-- sql_hard_04 --------------------------------------------------------------- 
-- sql_hard_05 --------------------------------------------------------------- 
-- sql_hard_06 --------------------------------------------------------------- 
-- sql_hard_07 --------------------------------------------------------------- 
-- sql_hard_08 --------------------------------------------------------------- 
-- sql_hard_09 --------------------------------------------------------------- 
-- sql_hard_10 --------------------------------------------------------------- 
