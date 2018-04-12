package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.constant.Constants;
import com.example.jetty_jersey.ws.requests.ForgetPassword;
import com.example.jetty_jersey.ws.requests.Login;
import com.example.jetty_jersey.ws.requests.ResetPassword;
import com.example.jetty_jersey.ws.requests.SignUp;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.AbstractDAOFactory;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAO;
import com.irif.projet.genielogiciel.jetty_jersey.DAO.implement.UserDAO;
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
    private static final String INDEX = "userdb";
    private static final String TYPE = "user";
    private User current_user;
    AbstractDAOFactory daoFact = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    DAO<User> userDao = daoFact.getUserDAO();

    User usr = new User("Jojo12345", "Jean", "CHIC", "jojo@free.com", "p1a2r3i4s5");//temporaire
    User usr1 = new User("Jo", "Joris", "Mahieu", "mahieu@free.com", "3210");//idem

    public void test(int status) {
        switch (status) {
            case 1://create user
                userDao.add(INDEX, TYPE, usr);
                break;
            case 2://connect
                current_user = ((UserDAO) userDao).connect(INDEX, TYPE, usr.getUsername(), usr.getPassword());
                break;
            case 3: //delete index
                userDao.deleteIndex(INDEX);
                break;
            case 4://delete user
                userDao.delete(INDEX, usr);
                break;
            case 5://update user
                current_user = ((UserDAO) userDao).connect(INDEX, TYPE, usr.getUsername(), usr.getPassword());
                if (current_user != null) {
                    //current_user.setUsername("Charles");
                    current_user.setLastname("Charles");
                    //current_user.setFirstname("Louis123");
                    userDao.update(INDEX, TYPE, current_user);
                }
                break;
            case 6://get all users
                List<User> list = userDao.findAll(INDEX, TYPE, User.class);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("User : " + list.get(i));
                }

        }

    }


    private static void registerException(Exception e) {
        Logger.getLogger(LoginRessource.class.getName()).log(Level.SEVERE, null, e);
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
            status = userDao.add(INDEX, TYPE, user);
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
        test(1);
    	/*if(loginRequest != null) {
    		current_user = ((UserDAO)userDao).connect(INDEX,TYPE,usr.getUsername(),usr.getPassword());
    	}
    	status = current_user == null?0:1;
    	
    	switch(status) {
			case 0:System.out.println("Echec");break;
			case 1:System.out.println("Reussite");break;
    	}*/
        return status;
    }

    @DELETE
    @Path("/{id}")
    public int deleteUser(User user) {
        int status = 0;
        if (user != null) {
            status = userDao.delete(INDEX, usr);
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
