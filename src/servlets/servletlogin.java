package servlets;

import data.User;
import data.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/enter")
public class ServletLogIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/enter.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = UserDB.selectOne(login,password);

        if(nonNull(session) && nonNull(session.getAttribute("user"))){
            req.getRequestDispatcher("/welcome.jsp").forward(req,resp);
        } else{
            if(user != null){
                req.getSession().setAttribute("user", user);
                resp.addCookie(new Cookie("user",user.getLogin()));
                req.getRequestDispatcher("/welcome.jsp").forward(req,resp);
            } else{
                if(login == null){
                    req.setAttribute("message", "Wrong log or pass");
                }
                //req.getRequestDispatcher("/enter.jsp").forward(req, resp);
            }
        }
    }
}
