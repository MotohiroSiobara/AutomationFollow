package services;

import models.*;
import javax.persistence.*;
import javax.inject.*;
import dto.*;

@Singleton
public class Authenticator {
    public User login(LoginRequest req){
        return User.find.where().eq("userName",req.userName).eq("password",req.password).findUnique();
    }
}