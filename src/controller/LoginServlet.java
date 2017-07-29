package controller;

import dao.UserDao;
import model.User;
import util.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Daniil Smirnov on 29.07.2017.
 * All copy registered MF.
 */
public class LoginServlet extends HttpServlet {

    private UserDao dao;

    public LoginServlet() {
        super();
        dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(Constants.LOGIN_PAGE);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null
                || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            try {
                user = dao.findUser(userName, password);
                if (user == null) {
                    hasError = true;
                    errorString = "User Login or password invalid";
                }
            } catch (Exception e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (hasError) {
            user = new User();
            user.setLoginName(userName);
            user.setPassword(password);
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher view = request.getRequestDispatcher(Constants.LOGIN_PAGE);
            view.forward(request, response);
        }
        else if (user.isAdmin()){
            RequestDispatcher view = request.getRequestDispatcher(Constants.ADMIN_PAGE);
            view.forward(request, response);
        }else if (!user.isAdmin()){
            RequestDispatcher view = request.getRequestDispatcher(Constants.SIMPLE_INFO_PAGE);
            request.setAttribute("user",dao.getUserById(user.getIdUser()));
            view.forward(request, response);
        }
    }

}


