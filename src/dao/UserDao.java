package dao;

import model.User;
import util.DbUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Daniil Smirnov on 27.07.2017.
 * All copy registered MF.
 */
public class UserDao {


    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        int adressid = 0;
        try{
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO adress(zip,Country,City,District,Street)"+
                    " VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1,user.adressClass.getZip());
            ps.setString(2,user.adressClass.getCountry());
            ps.setString(3,user.adressClass.getCity());
            ps.setString(4,user.adressClass.getDistrict());
            ps.setString(5,user.adressClass.getStreet());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id FROM adress");
            while (rs.next()){
                adressid = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users(First_name,Last_name,Login_name,Password,Email,Birthday,isActive,isAdmin,createdTimeStamp,`Group`,Adress)" +
                            " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setDate(6, new java.sql.Date(user.getBirthday().getTime()));
            int admin = user.isAdmin()? 1:0;
            int active = user.isActive()? 1:0;
            preparedStatement.setInt(7, active);
            preparedStatement.setInt(8, admin);
            preparedStatement.setDate(9, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setInt(10, user.groupClass.getIdGroup());
            preparedStatement.setInt(11, adressid);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where id=?");

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement ps = connection.prepareStatement("delete from adress where id =?");
            ps.setInt(1,userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users,adress set First_name=?, Last_name=?," +
                            " Login_name=?," +
                            " Email=?, Birthday=?," +
                            "isActive=?,isAdmin=?," +
                            "`Group`=?,adress.zip=?," +
                            "adress.Country=?,adress.City=?," +
                            "adress.District=?,adress.Street=?" +

                            "where users.id=? and adress.id=?");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setDate(5,new java.sql.Date (user.getBirthday().getTime()));
            int admin = user.isAdmin()? 1:0;
            int active = user.isActive()? 1:0;
            preparedStatement.setInt(6, active);
            preparedStatement.setInt(7, admin);
            preparedStatement.setInt(8,user.groupClass.getIdGroup());
            preparedStatement.setInt(9,user.adressClass.getZip());
            preparedStatement.setString(10,user.adressClass.getCountry());
            preparedStatement.setString(11,user.adressClass.getCity());
            preparedStatement.setString(12,user.adressClass.getDistrict());
            preparedStatement.setString(13,user.adressClass.getStreet());

            preparedStatement.setInt(14, user.getIdUser());
            preparedStatement.setInt(15, user.getIdUser());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsersandAdress() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select users.id, First_name,Last_name,Login_name,Email,Birthday,createdTimeStamp,isActive,isAdmin,lastupdateTimeStamp,`Group`,zip,Country,City,District,Street from users JOIN adress on users.id = Adress.id");
            while (rs.next()) {
                users.add(setListofUsers(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void changeUserPass(int userId, String pass){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set Password=? where users.id=?");
            preparedStatement.setString(1, pass);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public User findUser(String loginName, String password){
        try {
            PreparedStatement pstm = connection.
                    prepareStatement("SELECT Login_name, Password, isAdmin,id FROM users WHERE Login_name = ? AND password= ?");
            pstm.setString(1, loginName);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id"));
                user.setLoginName(loginName);
                user.setPassword(password);
                boolean admin = rs.getInt("isAdmin")==1;
                user.setAdmin(admin);
                return user;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getUserByParams(String query, boolean managers) {
        int group = 0;
        try {
            group = Integer.parseInt(query);
        }catch (Exception e){

        }
        List<User> users = new ArrayList<>();
        String sqlQuerry="";
        java.util.Date birthday = null;
        int var = 0;


        if (query.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
            var=1;
            try {
                birthday = new SimpleDateFormat("yyyy-MM-dd").parse(query);
            }catch (ParseException e){
                e.printStackTrace();
            }

            sqlQuerry = "select users.id, First_name," +
                    "Last_name,Login_name," +
                    "Email,Birthday," +
                    "createdTimeStamp," +
                    "lastupdateTimeStamp,isActive,isAdmin,`Group`," +
                    "zip,Country,City,District,Street"+
                    " from users JOIN adress on users.id = Adress.id WHERE Birthday = ?";

        } else if (query.contains("@")) {
            sqlQuerry ="select users.id, First_name," +
                    "Last_name,Login_name," +
                    "Email,Birthday," +
                    "createdTimeStamp," +
                    "lastupdateTimeStamp,isActive,isAdmin,`Group`," +
                    "zip,Country,City,District,Street"+
                    " from users JOIN adress on users.id = Adress.id WHERE Email = ?";

        } else {
            query = "%"+query+"%";
            sqlQuerry ="select users.id, First_name," +
                    "Last_name,Login_name," +
                    "Email,Birthday," +
                    "createdTimeStamp," +
                    "lastupdateTimeStamp,isActive,isAdmin,`Group`," +
                    "zip,Country,City,District,Street"+
                    " from users JOIN adress on users.id = Adress.id WHERE First_name LIKE ?";
        }

        if (managers){
            sqlQuerry = "select users.id, First_name," +
                    "Last_name,Login_name," +
                    "Email,Birthday," +
                    "createdTimeStamp," +
                    "lastupdateTimeStamp,isActive,isAdmin,`Group`," +
                    "zip,Country,City,District,Street"+
                    " from users JOIN adress on users.id = Adress.id WHERE `Group` = ?";
        }
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuerry);
        if (var==1 && birthday!=null){
            preparedStatement.setDate(1, new java.sql.Date(birthday.getTime()));
        }else if (managers){
            preparedStatement.setInt(1,group);
        }else {
            preparedStatement.setString(1, query);

        }

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            users.add(setListofUsers(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select users.id, First_name," +
                            "Last_name,Login_name," +
                            "Email,Birthday," +
                            "createdTimeStamp," +
                            "lastupdateTimeStamp,isActive,isAdmin,`Group`," +
                            "zip,Country,City,District,Street"+
                            " from users JOIN adress on users.id = Adress.id WHERE users.id = ? and Adress.id=?");

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setIdUser(rs.getInt("id"));
                user.setFirstName(rs.getString("First_name"));
                user.setLastName(rs.getString("Last_name"));
                user.setLoginName(rs.getString("Login_name"));
                user.setEmail(rs.getString("Email"));
                user.setBirthday(rs.getDate("Birthday"));
                user.setCreatedTimeStamp(rs.getDate("createdTimeStamp"));
                user.setLastupdateTimeStamp(rs.getTimestamp("lastupdateTimeStamp"));
                boolean active = rs.getInt("isActive")==1;
                user.setActive(active);
                boolean admin = rs.getInt("isAdmin")==1;
                user.setAdmin(admin);
                user.groupClass.setIdGroup(rs.getInt("Group"));
                user.adressClass.setZip(rs.getInt("zip"));
                user.adressClass.setCountry(rs.getString("Country"));
                user.adressClass.setCity(rs.getString("City"));
                user.adressClass.setDistrict(rs.getString("District"));
                user.adressClass.setStreet(rs.getString("Street"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void activateUser(int userId){

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set isActive=? where users.id=?");

            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User setListofUsers(ResultSet rs){
        User user = new User();
        try {
            user.setIdUser(rs.getInt("id"));
            user.setFirstName(rs.getString("First_name"));
            user.setLastName(rs.getString("Last_name"));
            user.setLoginName(rs.getString("Login_name"));
            user.setEmail(rs.getString("Email"));
            user.setBirthday(rs.getDate("Birthday"));
            user.setCreatedTimeStamp(rs.getDate("createdTimeStamp"));
            boolean active = rs.getInt("isActive") == 1;
            user.setActive(active);
            boolean admin = rs.getInt("isAdmin") == 1;
            user.setAdmin(admin);
            user.setLastupdateTimeStamp(rs.getTimestamp("lastupdateTimeStamp"));
            user.groupClass.setIdGroup(rs.getInt("Group"));
            user.adressClass.setZip(rs.getInt("zip"));
            user.adressClass.setCountry(rs.getString("Country"));
            user.adressClass.setCity(rs.getString("City"));
            user.adressClass.setDistrict(rs.getString("District"));
            user.adressClass.setStreet(rs.getString("Street"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}

