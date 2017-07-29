package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import dao.UserDao;
import model.User;
import util.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Daniil Smirnov on 27.07.2017.
 * All copy registered MF.
 */

public class UserController extends HttpServlet {


    private UserDao dao;


    public UserController() {
        super();
        dao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            if (!dao.getUserById(userId).isAdmin()){
                dao.deleteUser(userId);
                forward = Constants.ADMIN_PAGE;
                request.setAttribute("added", "Has been deleted");
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
            int count = 0;
            List<User> testing = dao.getAllUsersandAdress();
            for (User u: testing){
                if (u.isAdmin()){
                    count++;
                }
            }
            if (count>1){
                dao.deleteUser(userId);
                forward = Constants.ADMIN_PAGE;
                request.setAttribute("added", "Has been deleted");
            }else {
                forward = Constants.ADMIN_PAGE;
                request.setAttribute("error", "There is need at least one Admin");
            }
        } else if (action.equalsIgnoreCase("edit")){
            forward = Constants.USER_PAGE;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = Constants.LIST_USER_PAGE;
            request.setAttribute("users", dao.getAllUsersandAdress());
        } else {
            forward = Constants.USER_PAGE;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        boolean error = false;

        int zip=0;
        String Country = request.getParameter("Country").trim();
        String City = request.getParameter("City").trim();
        String District =request.getParameter("District").trim();
        String Street = request.getParameter("Street").trim();
        String FirstName =request.getParameter("First_name").trim();
        String LastName =request.getParameter("Last_name").trim();
        String LoginName =request.getParameter("Login_name").trim();
        String Email =request.getParameter("email").trim();

        String[] parametrs = new String[]{Country,City,District,Street,FirstName,LastName,LoginName,Email};

        for (String s:parametrs){
            if (s.isEmpty()||s.equals("")){
                error=true;
            }
        }
        if (!Email.contains("@")){
            error=true;
        }

        try {
            zip = Integer.parseInt(request.getParameter("zip"));
        }catch (Exception e){
            e.printStackTrace();
            error=true;
        }

        user.groupClass.setIdGroup(Integer.parseInt(request.getParameter("groupId")));
        user.adressClass.setZip(zip);
        user.adressClass.setCountry(Country);
        user.adressClass.setCity(City);
        user.adressClass.setDistrict(District);
        user.adressClass.setStreet(Street);

        user.setFirstName(FirstName);
        user.setLastName(LastName);
        user.setLoginName(LoginName);
        user.setEmail(Email);

        if (request.getParameter("isActive")!=null) {
            if (request.getParameter("isActive").equals("true")) {
                user.setActive(true);
            } else {
                user.setActive(false);
            }
        }

        if (request.getParameter("isAdmin")!=null) {
            if (request.getParameter("isAdmin").equals("true")) {
                user.setAdmin(true);
            } else {
                user.setAdmin(false);
            }
        }

        try {
            Date birthday = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("Birthday"));
            if (birthday.getTime()>new Date().getTime()){
                error=true;
            }
            user.setBirthday(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            error=true;
        }

        String userid = request.getParameter("userId");
        if(userid == null || userid.isEmpty())
        {
            String password=request.getParameter("Password").trim();
            if (password.equals("")||password.isEmpty()){
                error=true;
            }
            user.setPassword(password);

            if (!error){
            dao.addUser(user);
            RequestDispatcher view = request.getRequestDispatcher(Constants.ADMIN_PAGE);
            request.setAttribute("added", "User has been created");
            view.forward(request, response);
            }else {
                RequestDispatcher view = request.getRequestDispatcher(Constants.ADMIN_PAGE);
                request.setAttribute("error","Error of adding user");
                view.forward(request,response);
            }
        }
        else
        {

            if (!error){
                user.setIdUser(Integer.parseInt(userid));

                int count = 0;
                List<User> testing = dao.getAllUsersandAdress();
                for (User u: testing){
                    if (u.isAdmin()){
                        count++;
                    }
                }

                if (!user.isAdmin()&&dao.getUserById(Integer.parseInt(userid)).isAdmin()){
                    count--;
                }

            if (count>0){
                dao.updateUser(user);
                RequestDispatcher view = request.getRequestDispatcher(Constants.ADMIN_PAGE);
                request.setAttribute("added", "User has been updated");
                view.forward(request, response);
            }else {
                RequestDispatcher view = request.getRequestDispatcher(Constants.ADMIN_PAGE);
                request.setAttribute("error", "There is need at least one Admin");
                view.forward(request, response);
            }

            }else{
                RequestDispatcher view = request.getRequestDispatcher(Constants.ADMIN_PAGE);
                request.setAttribute("error","Error of updating user");
                view.forward(request,response);
            }
        }

    }
}