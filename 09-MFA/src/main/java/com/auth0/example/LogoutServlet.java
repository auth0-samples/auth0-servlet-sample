package com.auth0.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        response.sendRedirect(getServletConfig().getInitParameter("onLogoutRedirectTo"));
    }

}
