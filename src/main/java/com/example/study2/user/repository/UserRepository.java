package com.example.study2.user.repository;

import com.example.study2.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getString("age"));
                return user;
            }
        });
    }

    public int createUser(User createUser){
        String sql = "INSERT INTO user ( name, age) VALUES (?, ?)";
        return jdbcTemplate.update(sql, createUser.getName(), createUser.getAge());
    }

    public int createUser2(User createUser){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id")
                .usingColumns("name","age");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "efeaaaaf");
        parameters.put("age", 8);

        SqlParameterSource sql = new MapSqlParameterSource(parameters);
        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(sql);

        return key.intValue();
    }


}
