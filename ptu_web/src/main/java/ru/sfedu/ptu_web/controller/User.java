/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.ptu_web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.plexus.digest.Hex;
import ru.sfedu.productturnover.api.DataProviderDB;
import ru.sfedu.productturnover.constant.Result;
import ru.sfedu.productturnover.constant.StatusType;
import ru.sfedu.productturnover.model.Client;
import static ru.sfedu.ptu_web.Constants.INDEX;
import static ru.sfedu.ptu_web.Constants.USER;
import static ru.sfedu.ptu_web.Constants.WELCOME;

/**
 *
 * @author Skakun
 */
public class User extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        try {
            //        processRequest(request, response);
            if(request.getSession().getAttribute("client")==null){
                response.sendRedirect(INDEX);
            }
            
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            String newPassword=request.getParameter("new_password");
            String confirmPassword=request.getParameter("confirm_password");
            
            
            Client clientNew=new Client((Client)request.getSession().getAttribute("client"));
            
            if(password!=null && !password.equals("")){
                if(clientNew.getPassword().equals(Hex.encode(MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"))))){
                    if(newPassword==null || newPassword.equals("")){
                        session.setAttribute("error", "You didn't write a new password!");
                    }else if(!newPassword.equals(confirmPassword)){
                        session.setAttribute("error", "Password and confirmation do not match!");
                    }else{
                        clientNew.setPassword(Hex.encode(MessageDigest.getInstance("SHA-256").digest(newPassword.getBytes("UTF-8"))));
                    }
                }else{
                    session.setAttribute("error", "Password is incorrect!");
                }
            }
            
            if(name!=null && !name.equals(""))
                clientNew.setName(name);
            else{
                session.setAttribute("error", "You didn't write your name!");
            }
            
            if(clientNew.equals((Client)request.getSession().getAttribute("client")) && session.getAttribute("error")==null){
                session.setAttribute("info", "You haven't made any changes.");
            }else if(session.getAttribute("error")==null){
                Result res=new DataProviderDB().updateClient(clientNew);
                if(res.equals(new Result(StatusType.OK))){
                    session.setAttribute("success", "Data updated successfully.");
                    session.setAttribute("client",clientNew);
                }else{
                    session.setAttribute("error", "Error! "+res.getErrorMsg());
                }
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(USER);
    }

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
        this.processRequest(request,response);
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
        this.processRequest(request, response);
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
