package services;

import models.*;
import javax.inject.*;

@Singleton
public class UserManager {
    public String createUser(User user){
        if(User.find.where().eq("userName", user.userName).findRowCount() > 0){
            return "ユーザー名が重複しています。他のユーザー名を入力してください。";
        }

        try{
            user.save();
        } catch(Exception e){
        	    e.printStackTrace();
            return "想定しないエラーが発生しました。時間をおいて再度お試しください。";
        }

        return null;
    }
}