package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.protectedIndex;
import java.util.List;
import twitter4j.*;
import twitter4j.auth.*;
import java.io.*;
import play.cache.Cache;


public class TwitterController extends Controller {
	  public Result callback() {
	  	    try {
	  	        Twitter twitter = TwitterFactory.getSingleton();
            RequestToken requestToken = (RequestToken) Cache.get("requestToken");
  	  	        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, request().getQueryString("oauth_verifier"));
  	  	        twitter.updateStatus("こんにちは");
	  	    } catch (TwitterException te) {
	  	        te.printStackTrace();
	      }
	  	    return ok(protectedIndex.render());
	  }

    public Result login() {
    	    try {
    	    	    Twitter twitter = new TwitterFactory().getInstance();
            RequestToken requestToken = twitter.getOAuthRequestToken();
            Cache.set("requestToken", requestToken);
            return redirect(requestToken.getAuthorizationURL());
    	    } catch(TwitterException te) {
    	    	    te.printStackTrace();
    	    	    return redirect("/");
    	    }
    }
}
