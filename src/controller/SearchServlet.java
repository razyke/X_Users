package controller;

import dao.UserDao;
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
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao dao;

    public SearchServlet() {
        super();
        dao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("query").trim();

        if (query.equals("")||query.isEmpty()){
            RequestDispatcher view = request.getRequestDispatcher(Constants.LIST_USER_PAGE);
            request.setAttribute("users", dao.getAllUsersandAdress());
            view.forward(request, response);
        }else {
            RequestDispatcher view = request.getRequestDispatcher(Constants.LIST_USER_PAGE);
            request.setAttribute("users", dao.getUserByParams(query,false));
            view.forward(request, response);
        }




    }
}
