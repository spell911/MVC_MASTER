/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBean.UserAccount;

import utils.DBUtils;
import utils.MyUtils;

import conn.ConnectionUtils;

@WebServlet(urlPatterns = {"/doLogin"})
public class DoLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DoLoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            if (conn != null) {
                try {
                    user = DBUtils.findUser(conn, userName, password);
                    if (user == null) {
                        hasError = true;
                        errorString = "User Name or password invalid";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    hasError = true;
                    errorString = e.getMessage();
                }
            } else {
                System.out.println(conn + " / " + userName + " / " + password + " / " + requestURL + " / " + queryString + " / " + request + " / ");
            }
        }

        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        } // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            } // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }

            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
