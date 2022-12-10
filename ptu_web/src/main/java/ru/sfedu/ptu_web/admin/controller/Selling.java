package ru.sfedu.ptu_web.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.sfedu.productturnover.api.DataProviderDB;
import ru.sfedu.productturnover.api.IDataProvider;
import ru.sfedu.productturnover.constant.Result;
import static ru.sfedu.productturnover.constant.StatusType.OK;
import ru.sfedu.productturnover.model.Client;
import static ru.sfedu.ptu_web.Constants.ADMIN_SELLING;
import static ru.sfedu.ptu_web.Constants.ADMIN_SELLING_INDEX_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_SELLING_VIEW_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_USER;
import static ru.sfedu.ptu_web.Constants.INDEX;
import static ru.sfedu.ptu_web.Constants.STATUS_LIST;

/**
 *
 * @author Skakun
 */
public class Selling extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getSession().getAttribute("client")==null){
            response.sendRedirect(INDEX);
        }
        //Если переходим на страницу, но указан ещё id, то вызываем панель показа этого объекта
        if(Pattern.matches(ADMIN_SELLING+"/\\d*",request.getRequestURL()))
            this.onView(request, response);
        else//в остальных случаях вызываем список всех полей
            this.onList(request, response);
        
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
    @Override // обрабатывает GET запросы
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Selling.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session=request.getSession();
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_SELLING);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override //обрабатывает POST-запросы. Всё, что присылаем из формы - это POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try {
            if(request.getSession().getAttribute("client")==null/* || ((Client)request.getSession().getAttribute("client")).getRole()!=ROLE_ADMIN*/){
                response.sendRedirect(INDEX);
                return;
            }
            String submit=request.getParameter("submit");
            //Проверка, была ли нажата кнопка на форме
            if(!submit.isEmpty()){
                IDataProvider provider=new DataProviderDB();
                String end=request.getRequestURI().replaceAll(".*/", "");
                long id=0;
                //Если передан id, то сохраняем его
                if(!end.isEmpty())
                    id=Long.decode(end);
                else{
                    session.setAttribute("error","Selling is undefined!");
                    response.sendRedirect(ADMIN_SELLING);
                    return;
                }
                ru.sfedu.productturnover.model.Selling selling=provider.getSellingById(id);
                if(selling==null){
                    session.setAttribute("error","Selling is undefined!");
                    response.sendRedirect(ADMIN_SELLING);
                    return;
                }
                ru.sfedu.productturnover.model.Selling selling1=new ru.sfedu.productturnover.model.Selling(selling);
                if(request.getParameter("status")!=null)
                    selling.setStatus((short)Short.decode(request.getParameter("status")));
                
                if(request.getParameter("price")!=null)
                    selling.setPrice((long)(Float.parseFloat(request.getParameter("price"))*100));
                
                Result result=new Result();
                if(selling.equals(selling1)){
                    session.setAttribute("info","You haven't made any changes");
                    response.sendRedirect(ADMIN_SELLING+"/"+id);
                    return;
                }else
                    result=provider.updateSelling(selling);
                
                if(result.equals(new Result(OK))){
                    if(selling.getStatus()==2){
                        selling.getItem().setBalance(selling.getItem().getBalance()-selling.getNumber());
                        result=provider.updateItem(selling.getItem());
                        if(result.equals(new Result(OK))){
                            session.setAttribute("success", "Saving was success. Balance is updated");
                        }else{
                            session.setAttribute("warning", "Saving was success, but balance wasn't updated");
                        }
                    }else
                    session.setAttribute("success", "Saving was success");
                    //Если всё удачно, то переходим на страницу просмотра записи
                    response.sendRedirect(ADMIN_SELLING+"/"+id);
                    return;
                }else{
                    //Если произошла ошибка, то переходим на туже страницу и возвращаем сообщение об ошибке
                    session.setAttribute("error", "ERROR! "+result);
                    response.sendRedirect(ADMIN_SELLING+"/"+id);
                    return;
                }
                
            }else{
                processRequest(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(Selling.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_SELLING);
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
    
    //подгружает объект и вызывает страницу для просмотра данных объекта
    protected void onView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        if(id==0)
            response.sendRedirect(ADMIN_SELLING);
        ru.sfedu.productturnover.model.Selling selling=new DataProviderDB().getSellingById(id);
        if(selling==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Selling not found by id="+id);
            response.sendRedirect(ADMIN_SELLING);
        }else{
            request.setAttribute("item", selling);
            request.getRequestDispatcher(ADMIN_SELLING_VIEW_PATH).forward(request, response);
        }
    }
    
    //подгружает все объекты и подгружает страницу, где эти объекты подгружаются в списке
    protected void onList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        List<ru.sfedu.productturnover.model.Selling> itemList=new DataProviderDB().getAllSelling();
        PrintWriter out = response.getWriter();
        out.print(itemList);
        request.setAttribute("itemList", itemList);
        
        request.getRequestDispatcher(ADMIN_SELLING_INDEX_PATH).forward(request, response);
    }
}
