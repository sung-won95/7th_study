### 🔹 **테이블 생성하기**
- 
- **예시**: 학생 정보를 저장하는 `students` 테이블 생성

    ```sql
    CREATE TABLE students (
        student_id INT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        age INT,
        major VARCHAR(50)
    );
    
    ```
  ### 🔹 **테이블 수정하기**

- 컬럼 추가

    ```sql
    ALTER TABLE students ADD COLUMN email VARCHAR(100);
    
    ```

- 컬럼 삭제

    ```sql
    ALTER TABLE students DROP COLUMN email;
    
    ```


### 🔹 **테이블 삭제하기**

- **주의**: 테이블을 삭제하면 데이터도 함께 삭제됩니다.

    ```sql
    DROP TABLE students;
    
    ```


### 🔹 **데이터 삽입하기**

- **문법**

    ```sql
    INSERT INTO 테이블명 (컬럼1, 컬럼2, ...) VALUES (값1, 값2, ...);
    
    ```

- **예시**

    ```sql
    INSERT INTO students (student_id, name, age, major)
    VALUES (1, '김철수', 20, '컴퓨터공학');
    
    ```

### 🔹 **데이터 조회하기**

- **문법**

    ```sql
    SELECT 컬럼명1, 컬럼명2, ... FROM 테이블명 WHERE 조건;
    
    ```

- **예시**

    ```sql
    SELECT * FROM students;
    SELECT name, major FROM students WHERE age >= 20;
    
    ```


### 🔹 **데이터 수정하기**

- **문법**

    ```sql
    UPDATE 테이블명 SET 컬럼명 = 값, ... WHERE 조건;
    
    ```

- **예시**

    ```sql
    UPDATE students SET age = 21 WHERE student_id = 1;
    
    ```


### 🔹 **데이터 삭제하기**

- **문법**

    ```sql
    DELETE FROM 테이블명 WHERE 조건;
    
    ```

- **예시**

    ```sql
    DELETE FROM students WHERE student_id = 1;
    
    ```

## 13. JOIN을 활용한 데이터 조회 실습

### 🔹 **실습 시나리오**

- 학생과 수강 과목 정보를 관리하는 시스템입니다.

### 🔹 **테이블 생성**

- **학생 테이블**

    ```sql
    CREATE TABLE students (
        student_id INT PRIMARY KEY,
        name VARCHAR(50),
        major VARCHAR(50)
    );
    
    ```

- **과목 테이블**

    ```sql
    CREATE TABLE courses (
        course_id INT PRIMARY KEY,
        course_name VARCHAR(100),
        instructor VARCHAR(50)
    );
    
    ```

- **수강 테이블**

    ```sql
    CREATE TABLE enrollment (
        student_id INT,
        course_id INT,
        PRIMARY KEY (student_id, course_id),
        FOREIGN KEY (student_id) REFERENCES students(student_id),
        FOREIGN KEY (course_id) REFERENCES courses(course_id)
    );
    
    ```


### 🔹 **데이터 삽입**

- **학생 데이터**

    ```sql
    INSERT INTO students (student_id, name, major)
    VALUES
    (1, '김철수', '컴퓨터공학'),
    (2, '이영희', '경영학'),
    (3, '박민수', '전자공학');
    
    ```

- **과목 데이터**

    ```sql
    INSERT INTO courses (course_id, course_name, instructor)
    VALUES
    (101, '데이터베이스', '최교수'),
    (102, '운영체제', '박교수'),
    (103, 'Spring(JAVA)', '김교수');
    
    ```

- **수강 데이터**

    ```sql
    INSERT INTO enrollment (student_id, course_id)
    VALUES
    (1, 101),
    (1, 102),
    (2, 103),
    (3, 101),
    (3, 103);
    
    ```


### 🔹 **INNER JOIN 예제**

- **학생별 수강 과목 조회**

    ```sql
    SELECT s.name AS 학생이름, c.course_name AS 과목명
    FROM students s
    INNER JOIN enrollment e ON s.student_id = e.student_id
    INNER JOIN courses c ON e.course_id = c.course_id;
    
    ```

- **결과**


    | 학생이름 | 과목명 |
    | --- | --- |
    | 김철수 | 데이터베이스 |
    | 김철수 | 운영체제 |
    | 이영희 | 마케팅원론 |
    | 박민수 | 데이터베이스 |
    | 박민수 | 마케팅원론 |

### 🔹 **LEFT JOIN 예제**

- **모든 학생의 수강 과목 조회 (수강하지 않은 학생도 포함)**

    ```sql
    SELECT s.name AS 학생이름, c.course_name AS 과목명
    FROM students s
    LEFT JOIN enrollment e ON s.student_id = e.student_id
    LEFT JOIN courses c ON e.course_id = c.course_id;
    
    ```

- **결과**: 수강하지 않은 학생은 `과목명`이 `NULL`로 표시됩니다.

### 🔹 **RIGHT JOIN 예제**

- **모든 과목의 수강 학생 조회 (수강한 학생이 없는 과목도 포함)**

    ```sql
    SELECT s.name AS 학생이름, c.course_name AS 과목명
    FROM students s
    RIGHT JOIN enrollment e ON s.student_id = e.student_id
    RIGHT JOIN courses c ON e.course_id = c.course_id;
    
    ```

- **결과**: 수강생이 없는 과목은 `학생이름`이 `NULL`로 표시됩니다.

### 🔹 **FULL OUTER JOIN 예제**

- **모든 학생과 모든 과목의 조합 조회**

    ```sql
    SELECT s.name AS 학생이름, c.course_name AS 과목명
    FROM students s
    LEFT JOIN enrollment e ON s.student_id = e.student_id
    LEFT JOIN courses c ON e.course_id = c.course_id
    UNION
    SELECT s.name AS 학생이름, c.course_name AS 과목명
    FROM students s
    RIGHT JOIN enrollment e ON s.student_id = e.student_id
    RIGHT JOIN courses c ON e.course_id = c.course_id;
    
    ```