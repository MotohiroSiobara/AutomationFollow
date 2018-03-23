package controllers;

import play.mvc.*;
import models.*;
import java.util.*;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
//    	    User user = new User();
//    	    user.userName = "username";
//    	    user.password = "password";
//    	    user.save();
//
//    	    List<User> userList = User.find.all();
//
//    	    userList.forEach(u->System.out.println(u.userName + " : " + u.password));

        return ok(index.render("Your new application is ready."));
    }

}
