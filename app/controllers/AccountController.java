package controllers;

import play.mvc.*;
import play.data.*;
import javax.inject.*;

import views.html.*;

import models.*;
import services.*;

public class AccountController extends Controller {
	 public Result register() {
     	 return ok(register.render(Form.form(User.class)));
   }

	 @Inject UserManager userManager;
	 public Result doRegister() {
       User user = Form.form(User.class).bindFromRequest().get();

       String errorMessage = userManager.createUser(user);

       if(errorMessage != null){
      	 		flash("errormsg",errorMessage);
      	 		System.out.println(errorMessage);
      	 		return redirect(routes.AccountController.register());
       }

       return redirect(routes.HomeController.index());
   }
}