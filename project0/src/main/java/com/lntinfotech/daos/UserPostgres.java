package com.lntinfotech.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;
import com.lntinfotech.util.ConnectionUtil;

public class UserPostgres implements UserDao {

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        String sql = "select * from users where userId' = ?";
        User user = null;
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                int userId = result.getInt("userId");
                String username = result.getString("username");
                String password = result.getString("password");
                String userType = result.getString("userType");
                user = new User(userId, username, password, userType);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByUsername(String usernameInput) throws UserNotFoundException {
        String sql = "select * from users where username = ?";
        User user = null;
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usernameInput);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                int userId = result.getInt("userId");
                String username = result.getString("username");
                String password = result.getString("password");
                String userType = result.getString("userType");
                user = new User(userId, username, password, userType);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean addUser(User user) {
        String sql = "insert into users (username, password, userType) values (?, ?, 'customer') returning userId";
        
        try (Connection connection = ConnectionUtil.getConnection()) {
        	PreparedStatement statement = connection.prepareStatement(sql);
        	statement.setString(1, user.getUsername());
        	statement.setString(2, user.getPassword());
        	
        	statement.executeQuery();
        	
        	return true;
        } catch (SQLException e) {
        	return false;
        }
    }

    @Override
    public int updateUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteUser(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
