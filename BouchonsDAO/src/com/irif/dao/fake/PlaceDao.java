package com.irif.dao.fake;

public class PlaceDao {

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
