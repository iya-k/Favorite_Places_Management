package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.ForgetPassword;
import com.example.jetty_jersey.ws.requests.Login;
import com.example.jetty_jersey.ws.requests.ResetPassword;
import com.example.jetty_jersey.ws.requests.SignUp;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.UserDAO;
import com.irif.projet.genielogiciel.jetty_jersey.model.Map;
import com.irif.projet.genielogiciel.jetty_jersey.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/" + Constants.USERS)
public class LoginRessource {
    private User current_user;
    AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    DAO<User> userDao = daoFact.getUserDAO();

    User usr = new User("Jojo12345", "Jean", "CHIC", "jojo@free.com", "p1a2r3i4s5");//temporaire
    User usr1 = new User("Jo", "Joris", "Mahieu", "mahieu@free.com", "3210");//idem


    public void addMap(String userid,int n){
  Map map;
  DAO<Map> mapDao = daoFact.getMapDAO();
  for(int i = 0; i < n; i++){
    map = new Map(userid,"map"+i,"public","root"+i+".jpg");
    mapDao.add("mapdb","map",map);
  }
}


public void test(int status) {
	System.out.println("methode test");
    switch(status){
      case 1 ://create user
        userDao.add(Constants.uINDEX,Constants.uTYPE,usr1);
        break;
      case 2 ://connect
        current_user = ((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,usr.getUsername(),usr.getPassword());
        DAO<Map> mapDao5 = daoFact.getMapDAO();
        Map map6 = mapDao5.find("homemapdb","homemap",userDao.getId(Constants.uINDEX,Constants.uTYPE,current_user));
        System.out.println("Map trouve : "+map6);
        break;
      case 3 : //delete index
        userDao.deleteIndex(Constants.uINDEX);
        break;
      case 4 ://delete user
        userDao.delete(Constants.uINDEX,usr);
        break;
      case 5 ://update user
        current_user = ((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,usr.getUsername(),usr.getPassword());
        if(current_user != null) {
          //current_user.setUsername("Charles");
          current_user.setLastname("Charles");
          //current_user.setFirstname("Louis123");
          userDao.update(Constants.uINDEX, Constants.uTYPE,current_user);
        }
        break;
      case 6 ://get all users
        List<User> list = userDao.findAll(Constants.uINDEX,Constants.uTYPE,User.class);
          for(int i = 0; i < list.size();i++) {
            System.out.println("User : "+list.get(i));
          }
          break;
      case 7://create Map
        current_user = ((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,usr.getUsername(),usr.getPassword());
        Map map = new Map(userDao.getId(Constants.uINDEX,Constants.uTYPE,current_user),"map1","private","mapimg.jpg");
        DAO<Map> mapDao = daoFact.getMapDAO();
        mapDao.add("mapdb","map",map);
        break;
      case 8 ://delete map
        DAO<Map> mapDao1 = daoFact.getMapDAO();
        mapDao1.deleteIndex("mapdb");
        break;
      case 9 ://find map
        DAO<Map> mapDao2 = daoFact.getMapDAO();
        Map map1 = mapDao2.find("mapdb", "map",userDao.getId(Constants.uINDEX,Constants.uTYPE,current_user));
        System.out.println(map1);
        break;
      case 10://create homemap
        current_user = ((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,usr.getUsername(),usr.getPassword());
        DAO<Map> mapDao3 = daoFact.getMapDAO();
        Map map4 = new Map("root","rootmap","public","root.jpg");
        mapDao3.add("homemapdb","homemap",map4);
        break;
      case 11://find public map
        DAO<Map> mapDao4 = daoFact.getMapDAO();
        Map map5 = mapDao4.find("homemapdb","homemap","root");
        System.out.println("Test_Map : "+map5);
        break;
      case 12://findAllById
        DAO<Map> mapDao7 = daoFact.getMapDAO();
        current_user = ((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,usr.getUsername(),usr.getPassword());
        String userid = userDao.getId(Constants.uINDEX,Constants.uTYPE,current_user);
        addMap(userid,10);
        List<Map> list1 = mapDao7.findAllById("mapdb","map",userid+" public");
        for(int i = 0; i < list1.size();i++) {
          System.out.println("Test_findById : "+list1.get(i));
        }
        break;
    }

  }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public int signUp(SignUp signUpRequest) {
        int status = 0;
        User user;
        System.out.println(signUpRequest.toString());
        if (signUpRequest != null) {
            user = new User(signUpRequest);
            status = userDao.add(Constants.uINDEX, Constants.uTYPE, user);
        }
        switch (status) {
            case -1:
                System.out.println("Exception");
                break;
            case 0:
                System.out.println("Echec");
                break;
            case 1:
                System.out.println("Reussite");
                break;
        }
        return status;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public int signIn(Login loginRequest) {
        int status = 0;
        System.out.println("login");
    	if(loginRequest != null) {
    		current_user = ((UserDAO)userDao).connect(Constants.uINDEX,Constants.uTYPE,loginRequest.getUsername(),loginRequest.getPassword());
    	}
    	status = current_user == null?0:1;

    	switch(status) {
			case 0:System.out.println("Echec");break;
			case 1:System.out.println("Reussite");break;
    	}
        return status;
    }

    @DELETE
    @Path("/{id}")
    public int deleteUser(User user) {
        int status = 0;
        if (user != null) {
            status = userDao.delete(Constants.uINDEX, usr);
        }

        switch (status) {
            case 0:
                System.out.println("Echec");
                break;
            case 1:
                System.out.println("Reussite");
                break;
        }

        return status;
    }

    @POST
    @Path("/forget_password")
    @Consumes(MediaType.APPLICATION_JSON)
    public int forgetPassword(ForgetPassword forgetPasswordRequest) {
        int status = 0;

        System.out.println(forgetPasswordRequest.toString());

        return status;
    }

    @POST
    @Path("/reset_password")
    @Consumes(MediaType.APPLICATION_JSON)
    public int resetPassword(ResetPassword resetPasswordRequest) {
        int status = 0;

        System.out.println(resetPasswordRequest.toString());

        return status;
    }

}
