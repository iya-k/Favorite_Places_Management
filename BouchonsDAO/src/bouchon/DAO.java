package bouchon;

import java.awt.Event;

import com.irif.dao.fake.EventAction;
import com.irif.dao.fake.MapAction;
import com.irif.dao.fake.PlaceAction;
import com.irif.dao.fake.UserAction;

public class DAO {

	public static UserAction getUserAction(){
		return new UserAction();
	}
	
	public static MapAction getMapAction(){
		return new MapAction();
	}
	
	public static EventAction getEventAction(){
		return new EventAction();
	}
	
	public static PlaceAction getPlaceAction(){
		return new PlaceAction();
	}
}
