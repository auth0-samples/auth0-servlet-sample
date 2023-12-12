package com.auth0.example;

import com.auth0.AuthenticationController;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;

import javax.servlet.ServletConfig;
import java.io.UnsupportedEncodingException;

/**
 * A class that manages a singleton instance of a {@link JwkProvider} and {@link AuthenticationController} to be used
 * by Servlets to authenticate users with Auth0.
 * <p>
 * Note that each application instance should only create <strong>one</strong> instance of the {@linkplain AuthenticationController}
 * per domain and application to minimize unnecessary resource usage.
 */
class AuthenticationControllerProvider {

    private AuthenticationControllerProvider() {}

    private static AuthenticationController INSTANCE;

    // if multiple threads may call this, synchronize this method and consider double locking
    static AuthenticationController getInstance(ServletConfig config) throws UnsupportedEncodingException {
        if (INSTANCE == null) {
            String domain = config.getServletContext().getInitParameter("com.auth0.domain");
            String clientId = config.getServletContext().getInitParameter("com.auth0.clientId");
            String clientSecret = config.getServletContext().getInitParameter("com.auth0.clientSecret");

            if (domain == null || clientId == null || clientSecret == null) {
                throw new IllegalArgumentException("Missing domain, clientId, or clientSecret. Did you update src/main/webapp/WEB-INF/web.xml?");
            }

            // JwkProvider required for RS256 tokens. If using HS256, do not use.
            JwkProvider jwkProvider = new JwkProviderBuilder(domain).build();
            INSTANCE = AuthenticationController.newBuilder(domain, clientId, clientSecret)
                    .withJwkProvider(jwkProvider)
                    .build();
        }

        return INSTANCE;
    }
}
