package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import java.util.List;
import twitter4j.*;
import twitter4j.auth.*;
import java.io.*;
import play.cache.Cache;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.*;

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
	  	    return redirect("/twitter/protectedIndex");
	  }

	  public Result protectedIndex() {
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
    	    String tweetText = request().getQueryString("tweetText");
    	    System.out.println(tweetText);
    	    try {
    	    			Twitter twitter = (Twitter) Cache.get("twitter");
    	    			if (twitter == null) {
    	    				return redirect("/login");
    	    			}
    	    			System.out.println(twitter);
    	    			Query query = new Query(tweetText);
    	    			System.out.println(query);
    	        QueryResult result = twitter.search(query);
    	        Stream<String> screenNames = result.getTweets().stream().map(new Function<Status, String>() {
    	            @Override
    	            public String apply(Status status) {
    	                return status.getUser().getScreenName();
    	            }
    	        });
    	        screenNames.forEach(new Consumer<String>() {
    	            @Override
    	            public void accept(String name) {
    	            	    try {
    	            	        twitter.createFriendship(name);
    	            	    } catch(TwitterException te) {
    	            	    	    te.printStackTrace();
    	            	    }
    	            }
    	        });
    	    } catch(TwitterException te) {
    	    			te.printStackTrace();
    	    }
    	    Cache.remove("twitter");
    			return redirect("/twitter/protectedIndex");
    }
}
