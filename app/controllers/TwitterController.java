package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import java.util.List;
import twitter4j.*;
import twitter4j.auth.*;
import java.io.*;
import play.cache.Cache;

public class TwitterController extends Controller {
	  public Result callback() {
	  	    try {
	  	        Twitter twitter = (Twitter) Cache.get("twitter");
            RequestToken requestToken = (RequestToken) Cache.get("requestToken");
            Cache.remove("requestToken");
  	  	        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, request().getQueryString("oauth_verifier"));
  	  	        Cache.set("accessToken", accessToken);
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
            Cache.set("twitter", twitter);
            return redirect(requestToken.getAuthorizationURL());
    	    } catch(TwitterException te) {
    	    	    te.printStackTrace();
    	    	    return redirect("/");
    	    }
    }

    public Result follow() {
    	    System.out.println(request().getQueryString("tweetText"));
    			return ok(protectedIndex.render());
    }
}
