package com.example.kursach.orm.dao;

import com.example.kursach.orm.model.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 1 on 02.06.2016.
 */
public class UserDAO extends BaseDaoImpl<User, Integer> {

    public UserDAO(ConnectionSource connectionSource,
                      Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<User> getAllUsers() throws SQLException{
        return this.queryForAll();
    }

    public User getUserById(int id) throws SQLException {
        return this.queryForId(id);
    }

    public void deleteUser(User user) throws SQLException {
        this.delete(user);
    }

    public void createUser(User user) throws SQLException {
        this.create(user);
    }

    public void updateUser(User user) throws SQLException {
        this.update(user);
    }

    public List<User> getUserByLogin(String login)  throws SQLException{
        QueryBuilder<User, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq("login", login);
        PreparedQuery<User> preparedQuery = queryBuilder.prepare();
        List<User> userList =query(preparedQuery);
        return userList;
    }
}