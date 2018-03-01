package com.irif.dao.fake;

public class EventDao {


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
