package controllers;

import play.mvc.*;
import play.data.*;
import javax.inject.*;

import views.html.*;

import models.*;
import dto.*;
import services.*;

public class LoginController extends Controller {
    public Result login() {
        return ok(login.render());
    }

    @Inject Authenticator auth;

    public Result doLogin() {
        LoginRequest request = Form.form(LoginRequest.class).bindFromRequest().get();
        User user = auth.login(request);
        if(user == null){
            flash("message","login failed.");
            return redirect(routes.LoginController.login());
        }

        setSession(user);
        return redirect(routes.HomeController.index());
    }

    public Result logout() {
        clearSession();
        return redirect(routes.HomeController.index());
    }

    private void setSession(User user) {
        session("fullName",user.fullName);
        session("id",user.id.toString());
    }

    private void clearSession() {
        session().clear();
    }
}