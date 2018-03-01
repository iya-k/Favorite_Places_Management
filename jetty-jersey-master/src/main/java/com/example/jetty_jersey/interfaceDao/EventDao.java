package com.example.jetty_jersey.interfaceDao;


import java.util.List;
import com.example.jetty_jersey.dao.*;

public interface EventDao {


  /**
  * @return the list of events
  */

  List<Event> getEvents();

  /**
  * @param username
  * @return the list of events added by a specific user
  */
  List<Event> getEvents(String username);

  

}
