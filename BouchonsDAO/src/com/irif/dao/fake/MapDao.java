package com.irif.dao.fake;

public class MapDao {

    /**
    * @return the list of maps
    */
    List<Map> getMaps();

    /**
    * @param username
    * @return the list of maps of a specific user
    */
    List<Map> getMaps(String username);

}
