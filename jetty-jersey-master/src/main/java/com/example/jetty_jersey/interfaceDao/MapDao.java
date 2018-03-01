package com.example.jetty_jersey.interfaceDao;

import java.util.List;
import com.example.jetty_jersey.dao.*;

public interface MapDao {

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
