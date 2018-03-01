package bouchon;

import java.awt.Event;

import com.irif.dao.fake.EventDao;
import com.irif.dao.fake.MapDao;
import com.irif.dao.fake.PlaceDao;
import com.irif.dao.fake.UserDao;

public class DAO {

	public static UserDao getUserDao(){
		return new UserDao();
	}

	public static MapDao getMapDao(){
		return new MapDao();
	}

	public static EventDao getEventDao(){
		return new EventDao();
	}

	public static PlaceDao getPlaceDao(){
		return new PlaceDao();
	}
}
