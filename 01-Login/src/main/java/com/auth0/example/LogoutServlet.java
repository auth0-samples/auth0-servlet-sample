package com.auth0.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    private String domain;
    private String clientId;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        domain = config.getServletContext().getInitParameter("com.auth0.domain");
        clientId = config.getServletContext().getInitParameter("com.auth0.clientId");
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        String returnTo = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/portal/home";
        String redirectLogout = String.format("https://%s/v2/logout?client_id=%s&returnTo=%s", domain, clientId, returnTo);
        response.sendRedirect(redirectLogout);
    }

}
