//package modules;
//
//import be.objectify.deadbolt.java.cache.HandlerCache;
//import com.google.inject.AbstractModule;
//import com.google.inject.Provides;
//
//import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer;
//import org.pac4j.core.client.Clients;
//import org.pac4j.core.config.Config;
//import org.pac4j.oauth.client.TwitterClient;
//import org.pac4j.play.LogoutController;
//import org.pac4j.play.CallbackController;
//import org.pac4j.play.store.PlayCacheStore;
//import org.pac4j.play.store.PlaySessionStore;
//import org.pac4j.play.deadbolt2.*;
//
//import play.Configuration;
//import play.Environment;
//import play.Play;
//
//public class SecurityModule extends AbstractModule {
//
//    private final Configuration configuration;
//
//    public SecurityModule(final Environment environment, final Configuration configuration) {
//        this.configuration = configuration;
//    }
//
//    @Override
//    protected void configure() {
//    	    bind(HandlerCache.class).to(Pac4jHandlerCache.class);
//    	    bind(Pac4jRoleHandler.class).to(MyPac4jRoleHandler.class);
//
//    	    final PlayCacheSessionStore playCacheSessionStore = new PlayCacheSessionStore(getProvider(SyncCacheApi.class));
//        bind(PlaySessionStore.class).to(PlayCacheSessionStore.class);
//        // TwitterのAPI KeyとAPI Secretを設定
//        TwitterClient twitterClient = new TwitterClient(Play.application().configuration().getString("consumerKey"), Play.application().configuration().getString("consumerSecret"));
//
//        // コールバックURLとクライアントを設定、今回はtwitterのみ
//        Clients clients = new Clients("http://localhost:9000/callback", twitterClient);
//
//        Config config = new Config(clients);
//
//        // Authorizer：認証ロジック　複数のAuthorizerをadd可能
//        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer<>("ROLE_ADMIN"));
//
//        // ActionAdapterの設定
//        config.setHttpActionAdapter(new DemoHttpActionAdapter());
//
//        bind(Config.class).toInstance(config);
//
//        // callback
//        CallbackController callbackController = new CallbackController();
//        callbackController.setDefaultUrl("/");
//        bind(CallbackController.class).toInstance(callbackController);
//
//        // logout
//        ApplicationLogoutController logoutController = new ApplicationLogoutController();
//        logoutController.setDefaultUrl("/");
//        bind(ApplicationLogoutController.class).toInstance(logoutController);
//    }
//}