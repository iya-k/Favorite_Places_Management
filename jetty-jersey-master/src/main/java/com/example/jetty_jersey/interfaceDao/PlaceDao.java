package com.example.jetty_jersey.interfaceDao;

import java.util.List;
import com.example.jetty_jersey.dao.*;

public interface PlaceDao {

    /**
    * @return the list of places
    */

    List<Place> getPlaces();

    /**
    * @param username
    * @return the list of places added by a specific user
    */
    List<Place> getPlaces(String username);

}
