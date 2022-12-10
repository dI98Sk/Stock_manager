/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.ptu_web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.codehaus.plexus.digest.Hex;
import ru.sfedu.productturnover.api.DataProviderDB;
import ru.sfedu.productturnover.api.IDataProvider;
import ru.sfedu.productturnover.constant.Result;
import ru.sfedu.productturnover.constant.StatusType;
import ru.sfedu.productturnover.model.Client;
import static ru.sfedu.ptu_web.Constants.INDEX;
import static ru.sfedu.ptu_web.Constants.URL;
import static ru.sfedu.ptu_web.Constants.WELCOME;

/**
 *
 * @author Skakun
 */
public class Registration extends HttpServlet {
    
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Registration.class);
    
    public Registration(){
        super();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Registration</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Registration at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        doPost(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
        if(request.getSession().getAttribute("client")!=null){
            response.sendRedirect(INDEX);
        }
        String name=request.getParameter("name");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        String conf_pass=request.getParameter("conf_pass");
        HttpSession session=request.getSession();
        if(name==null || login==null || password==null || conf_pass==null){
            session.setAttribute("error","Error! Not all fields are filled");
            response.sendRedirect(URL);
        }
        if(!password.equals(conf_pass)){
            session.setAttribute("error","Error! Confirm password and Password not match");
        }
        try {
            Client client=new Client(name,login,Hex.encode(MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"))));
            IDataProvider provider=new DataProviderDB();
            Result res=provider.insertClient(client);
            log.info(res);
            if(res.equals(new Result(StatusType.OK)))
                session.setAttribute("success", "Registration was successful. "+res.getErrorMsg());
            else if(res.equals(new Result(StatusType.WARNING)))
                session.setAttribute("warning", "Warning! "+res.getErrorMsg());
            else
                session.setAttribute("error", "Error! "+res.getErrorMsg());
        } catch (InterruptedException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "Error! "+ex.toString());
        } catch (Exception ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", "Error! "+ex.toString());
        }
        response.sendRedirect(URL);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
