package com.auth0.example;

import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@WebServlet(urlPatterns = {"/javajwt"})
public class JavaJwtVerifierServlet extends HttpServlet {

    private final static JwkProvider jwkProvider = new JwkProviderBuilder("auth0-quickstarts-test.auth0.com").build();

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
        RSAKeyProvider keyProvider = new RSAKeyProvider() {
            @Override
            public RSAPublicKey getPublicKeyById(String kid) {
                try {
                    return (RSAPublicKey) jwkProvider.get(kid).getPublicKey();
                } catch (JwkException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public RSAPrivateKey getPrivateKey() {
                return null;
            }

            @Override
            public String getPrivateKeyId() {
                return null;
            }
        };

        Algorithm algorithm = Algorithm.RSA256(keyProvider);

        JWT.require(algorithm)
                .withIssuer("https://auth0-quickstarts-test.auth0.com/")
                .withAudience("https://quickstarts-api/")
                .build().verify(jwt);
    }
}
