package com.example.jetty_jersey.interfaceDao;

import java.util.List;
import com.example.jetty_jersey.dao.*;

public interface UserDao {

    /**
    * @return the list of events
    */
    List<User> getUsers();

    /**
    * @param username
    * @return the list of users friends with username
    */
    List<User> getUsers(String username);


}
