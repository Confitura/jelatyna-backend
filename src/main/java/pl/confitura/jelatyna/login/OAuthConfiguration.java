package pl.confitura.jelatyna.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.confitura.jelatyna.login.facebook.FacebookService;
import pl.confitura.jelatyna.login.github.GithubService;
import pl.confitura.jelatyna.login.google.GoogleService;

import java.util.HashMap;
import java.util.Map;

import static pl.confitura.jelatyna.infrastructure.Profiles.FAKE_SECURITY;

@EnableConfigurationProperties(OAuthConfiguration.OAuthConfigurationProperties.class)
@Configuration
@Profile("!" + FAKE_SECURITY)
public class OAuthConfiguration {

    @Autowired
    private OAuthConfigurationProperties properties;

    @Autowired
    private OAuthUserService oauthUserService;

    @Autowired
    private ObjectMapper mapper;

    public OAuthConfiguration() {
        System.out.println();
    }

    private AbstractOAuth20Service github(OAuthProviderProperties properties) {
        return new GithubService(
                oauthUserService,
                properties,
                mapper);
    }

    private AbstractOAuth20Service facebook(OAuthProviderProperties properties) {
        return new FacebookService(
                oauthUserService,
                properties,
                mapper);
    }

    private AbstractOAuth20Service google(OAuthProviderProperties properties) {
        return new GoogleService(
                oauthUserService,
                properties,
                mapper);
    }

    @Bean("OAuthServices")
    public Map<String, AbstractOAuth20Service> services() {
        OAuthProviderProperties githubProperties = this.properties.oauth2.get(GithubService.SYSTEM);
        OAuthProviderProperties facebookProperties = this.properties.oauth2.get(FacebookService.SYSTEM);
        OAuthProviderProperties googleProperties = this.properties.oauth2.get(GoogleService.SYSTEM);
        HashMap<String, AbstractOAuth20Service> map = new HashMap<>();
        if (githubProperties != null) {
            map.put(GithubService.SYSTEM, github(githubProperties));
        }
        if (facebookProperties != null) {
            map.put(FacebookService.SYSTEM, facebook(facebookProperties));
        }
        if (googleProperties != null) {
            map.put(GoogleService.SYSTEM, google(googleProperties));
        }
        return map;
    }


    @Data
    @ConfigurationProperties(prefix = "app.oauth")
    public static class OAuthConfigurationProperties {
        private Map<String, OAuthProviderProperties> oauth2 = new HashMap<>();

    }


    @Data
    public static class OAuthProviderProperties {
        private String apiKey;
        private String apiSecret;
        private String callback;
    }

}
