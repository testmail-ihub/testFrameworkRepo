import sqlite3
from datetime import datetime, timedelta
from typing import List, Dict

# 1. Get all employees who joined in the last 6 months
def get_employees_joined_last_6_months(db_path):
    """
    Retrieves all employees who joined in the last 6 months from the 'employees' table.
    """
    try:
        conn = sqlite3.connect(db_path)
        cursor = conn.cursor()
        six_months_ago = datetime.now() - timedelta(days=180)
        query = """
            SELECT *
            FROM employees
            WHERE hire_date >= ?
        """
        cursor.execute(query, (six_months_ago.strftime('%Y-%m-%d'),))
        results = cursor.fetchall()
        conn.close()
        return results
    except sqlite3.Error as e:
        print(f"Database error: {e}")
        return []

# 2. Find the average salary per department
class EmployeeQueries:
    def __init__(self, db_name: str):
        self.conn = sqlite3.connect(db_name)
        self.cursor = self.conn.cursor()

    def get_average_salary_per_department(self) -> List[Dict]:
        """
        Calculates the average salary per department from the 'employees' table.
        """
        query = """
            SELECT department_id, AVG(salary) AS average_salary
            FROM employees
            GROUP BY department_id
        """
        self.cursor.execute(query)
        results = self.cursor.fetchall()
        return [{"department_id": row[0], "average_salary": row[1]} for row in results]

    # 3. List employees earning more than the average salary of their department
    def get_employees_above_average_salary(self) -> List[Dict]:
        """
        Retrieves employees earning more than the average salary of their department.
        """
        query = """
            SELECT e.*
            FROM employees e
            JOIN (
                SELECT department_id, AVG(salary) as avg_salary
                FROM employees
                GROUP BY department_id
            ) a ON e.department_id = a.department_id
            WHERE e.salary > a.avg_salary
        """
        self.cursor.execute(query)
        rows = self.cursor.fetchall()
        columns = [description[0] for description in self.cursor.description]
        return [dict(zip(columns, row)) for row in rows]

    def close_connection(self):
        self.conn.close()

# 4. Get department names with more than 5 employees
def get_departments_with_more_than_5_employees(db_path: str) -> List[str]:
    """
    Retrieves the names of departments with more than 5 employees.
    """
    query = """
        SELECT D.dept_name
        FROM employees E
        JOIN departments D ON E.department_id = D.dept_id
        GROUP BY D.dept_name
        HAVING COUNT(E.emp_id) > 5
    """
    try:
        connection = sqlite3.connect(db_path)
        cursor = connection.cursor()
        cursor.execute(query)
        departments = [row[0] for row in cursor.fetchall()]
        return departments
    except sqlite3.Error as e:
        print(f"Database error: {e}")
        return []
    finally:
        if connection:
            connection.close()

# 5. Update salary by 10% for employees in "Engineering"
def update_engineering_salaries(db_path: str):
    """
    Updates salary by 10% for all employees in the 'Engineering' department.
    """
    try:
        conn = sqlite3.connect(db_path)
        cur = conn.cursor()
        sql_update_query = """
            UPDATE employees
            SET salary = salary * 1.1
            WHERE department_id IN (
                SELECT dept_id FROM departments WHERE dept_name = ?
            );
        """
        cur.execute(sql_update_query, ('Engineering',))
        conn.commit()
        conn.close()
    except sqlite3.Error as e:
        print(f"Database error: {e}")
