package com.jasper.demo.spring.mybatis.dao;

import com.jasper.demo.spring.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserJdbcDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserById(int id) {
        return jdbcTemplate.queryForObject("select * from user where id = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setBirthday(rs.getDate("birthday"));
                user.setName(rs.getString("name"));
                return user;
            }
        }, id);
    }

    /**
     *
     * @param user
     * @return  返回自增主键ID
     */
    public int addUser(final User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into user (name, birthday) values (?, ?)", new String[]{"id"});
                ps.setString(1, user.getName());
                ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
