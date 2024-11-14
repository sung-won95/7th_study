### 🔹 **환경 설정**

- **의존성 추가** (`build.gradle`)

    ```groovy
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
        implementation 'mysql:mysql-connector-java:8.0.28'
    }
    
    ```

- **데이터베이스 연결 설정** (`application.properties`)

    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/mydb
    spring.datasource.username=root
    spring.datasource.password=1234
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    
    ```


### 🔹 **데이터 조회 예제**

- **학생 정보를 조회하는 DAO 클래스**

    ```java
    @Repository
    public class StudentDao {
    
        @Autowired
        private JdbcTemplate jdbcTemplate;
    
        public List<Student> getAllStudents() {
            String sql = "SELECT * FROM students";
            return jdbcTemplate.query(sql, new RowMapper<Student>() {
                @Override
                public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Student student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setMajor(rs.getString("major"));
                    return student;
                }
            });
        }
    }
    
    ```

- **JOIN을 활용한 학생별 수강 과목 조회**

    ```java
    public List<StudentCourse> getStudentCourses() {
        String sql = "SELECT s.name AS student_name, c.course_name AS course_name " +
                     "FROM students s " +
                     "INNER JOIN enrollment e ON s.student_id = e.student_id " +
                     "INNER JOIN courses c ON e.course_id = c.course_id";
        return jdbcTemplate.query(sql, new RowMapper<StudentCourse>() {
            @Override
            public StudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentCourse sc = new StudentCourse();
                sc.setStudentName(rs.getString("student_name"));
                sc.setCourseName(rs.getString("course_name"));
                return sc;
            }
        });
    }
    
    ```


### 🔹 **데이터 삽입 예제**

- **학생 정보 삽입**

    ```java
    public int addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, major) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, student.getStudentId(), student.getName(), student.getMajor());
    }
    
    ```

- 우리가 배웠던 InsertCode

    ```java
    @Override
    public MemoResponseDto saveMemo(Memo memo) {
        // INSERT Query를 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("memo").usingGeneratedKeyColumns("id");
    
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", memo.getTitle());
        parameters.put("contents", memo.getContents());
    
    		// 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    
        return new MemoResponseDto(key.longValue(), memo.getTitle(), memo.getContents());
    }
    ```


### 🔹 **데이터 수정 예제**

- **학생 정보 수정**

    ```java
    public int updateStudentMajor(int studentId, String newMajor) {
        String sql = "UPDATE students SET major = ? WHERE student_id = ?";
        return jdbcTemplate.update(sql, newMajor, studentId);
    }
    
    ```


### 🔹 **데이터 삭제 예제**

- **학생 정보 삭제**

    ```java
    public int deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        return jdbcTemplate.update(sql, studentId);
    }
    
    ```