package com.irif.dao.fake;

public class UserDao {

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
