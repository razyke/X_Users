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
 * Created by Daniil Smirnov on 28.07.2017.
 * All copy registered MF.
 */
public class UserActive extends HttpServlet {

    private UserDao dao;

    public UserActive() {
        super();
        dao = new UserDao();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("Activate")!=null) {
            if (request.getParameter("Activate").equals("true")) {
                int id = Integer.parseInt(request.getParameter("ID"));

                dao.activateUser(id);

                RequestDispatcher view = request.getRequestDispatcher(Constants.ADMIN_PAGE);
                view.forward(request, response);
            }
        }

        if (request.getParameter("passtochange")!=null){
            int id = Integer.parseInt(request.getParameter("passtochange"));
            String pass = request.getParameter("password");
            boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
            System.out.println(admin);

            if (pass.trim().equals("") || pass.trim().isEmpty()){
                String message = "Password can't be epmty !";
                if (admin){
                    RequestDispatcher view = request.getRequestDispatcher(Constants.USER_PAGE);
                    request.setAttribute("user", dao.getUserById(id));
                    request.setAttribute("notchanged",message);
                    view.forward(request, response);
                }else {
                    RequestDispatcher view = request.getRequestDispatcher(Constants.SIMPLE_INFO_PAGE);
                    request.setAttribute("user",dao.getUserById(id));
                    request.setAttribute("notchanged",message);
                    view.forward(request, response);
                }
            }else {
                String message = "Password has been changed";
                dao.changeUserPass(id,pass);
                if (admin){
                    RequestDispatcher view = request.getRequestDispatcher(Constants.USER_PAGE);
                    request.setAttribute("user", dao.getUserById(id));
                    request.setAttribute("changed",message);
                    view.forward(request, response);
                }else {
                    RequestDispatcher view = request.getRequestDispatcher(Constants.SIMPLE_INFO_PAGE);
                    request.setAttribute("user",dao.getUserById(id));
                    request.setAttribute("changed",message);
                    view.forward(request, response);
                }
            }
        }

        if (request.getParameter("changePass")!=null){
            User user = new User();
            int userId = Integer.parseInt(request.getParameter("userId"));

            if (request.getParameter("userAdmin")!=null) {
                if (request.getParameter("userAdmin").equals("true")) {
                    user.setAdmin(true);
                } else {
                    user.setAdmin(false);
                }
            }
            user.setIdUser(userId);
            request.setAttribute("user", user);
            RequestDispatcher view = request.getRequestDispatcher(Constants.CHANGE_PASS);
            view.forward(request, response);
        }
        if (request.getParameter("groupID")!=null){
            RequestDispatcher view = request.getRequestDispatcher(Constants.LIST_USER_PAGE);
            request.setAttribute("users",dao.getUserByParams(request.getParameter("groupID"),true));
            view.forward(request, response);
        }
    }
}
