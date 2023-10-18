package com.auth0.example;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.JWKSourceBuilder;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;

@WebServlet(urlPatterns = {"/nimbus"})
public class NimbusVerifierServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        // For testing simplicity, just accept the jwt as a query param
        String jwt = req.getParameter("jwt");
        if (jwt == null) {
            throw new RuntimeException("missing jwt from query param");
        }
        verify(jwt);
    }

    private void verify(String jwt) {
        // Process the token
        SecurityContext ctx = null; // optional context parameter, not required here
        try {
            NimbusProvider.getInstance().process(jwt, ctx);
        } catch (ParseException | BadJOSEException e) {
            // Invalid token
            throw new RuntimeException(e.getMessage());
        } catch (JOSEException e) {
            // Key sourcing failed or another internal exception
            throw new RuntimeException(e.getMessage());
        }
    }

    static class NimbusProvider {

        private static ConfigurableJWTProcessor<SecurityContext> INSTANCE = null;

        static ConfigurableJWTProcessor<SecurityContext> getInstance() {
            if (INSTANCE == null) {
                ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();

                // The public RSA keys to validate the signatures will be sourced from the
                // OAuth 2.0 server's JWK set URL. The key source will cache the retrieved
                // keys for 5 minutes. 30 seconds prior to the cache's expiration the JWK
                // set will be refreshed from the URL on a separate dedicated thread.
                // Retrial is added to mitigate transient network errors.
                JWKSource<SecurityContext> keySource;
                try {
                    keySource = JWKSourceBuilder
                            .create(new URL("https://auth0-quickstarts-test.auth0.com/.well-known/jwks.json"))
                            .retrying(true)
                            .build();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                // The expected JWS algorithm of the access tokens (agreed out-of-band)
                JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;

                // Configure the JWT processor with a key selector to feed matching public
                // RSA keys sourced from the JWK set URL
                JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(
                        expectedJWSAlg,
                        keySource);
                jwtProcessor.setJWSKeySelector(keySelector);

                // Set the required JWT claims for access tokens
                jwtProcessor.setJWTClaimsSetVerifier(new DefaultJWTClaimsVerifier<>(
                        new JWTClaimsSet.Builder()
                                .issuer("https://auth0-quickstarts-test.auth0.com/")
                                .audience("https://quickstarts-api/")
                                .build(),
                        new HashSet<>(Arrays.asList(
                                JWTClaimNames.SUBJECT,
                                JWTClaimNames.ISSUED_AT,
                                JWTClaimNames.EXPIRATION_TIME))));

                INSTANCE = jwtProcessor;
            }
            return INSTANCE;
        }
    }
}
