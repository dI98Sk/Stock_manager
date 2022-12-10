package ru.sfedu.ptu_web.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY;
import static ru.sfedu.ptu_web.Constants.ADMIN_SELLING;
import static ru.sfedu.ptu_web.Constants.ADMIN_USER;
import static ru.sfedu.ptu_web.Constants.ADMIN_USER_INDEX_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_USER_VIEW_PATH;
import static ru.sfedu.ptu_web.Constants.INDEX;
import static ru.sfedu.ptu_web.Constants.ROLE_ADMIN;
import static ru.sfedu.ptu_web.Constants.ROLE_GUEST;
import static ru.sfedu.ptu_web.Constants.ROLE_USER;

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getSession().getAttribute("client")==null){
            response.sendRedirect(INDEX);
        }
        //Если переходим на страницу, но указан ещё id, то вызываем панель показа этого объекта
        if(Pattern.matches(ADMIN_USER+"/\\d*",request.getRequestURL()))
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
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session=request.getSession();
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_USER);
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
                    session.setAttribute("error","User is undefined!");
                    response.sendRedirect(ADMIN_USER);
                    return;
                }
                Client client=provider.getClientById(id);
                if(client==null){
                    session.setAttribute("error","User is undefined!");
                    response.sendRedirect(ADMIN_USER);
                    return;
                }
                Client client1=new Client(client);
                short role=1;
                if(!request.getParameter("role").isEmpty())
                    role=(short)Short.decode(request.getParameter("role"));
                else{
                    session.setAttribute("error","Role is undefined!");
                    response.sendRedirect(ADMIN_USER+"/"+id);
                    return;
                }
                
                client.setRole(role);
                
                Result result=new Result();
                if(client.equals(client1)){
                    session.setAttribute("info","You haven't made any changes");
                    response.sendRedirect(ADMIN_USER+"/"+id);
                    return;
                }else
                    result=provider.updateClient(client);
                
                if(result.equals(new Result(OK))){
                    session.setAttribute("success", "Saving was success");
                    //Если всё удачно, то переходим на страницу просмотра записи
                    response.sendRedirect(ADMIN_USER+"/"+id);
                    return;
                }else{
                    //Если произошла ошибка, то переходим на туже страницу и возвращаем сообщение об ошибке
                    session.setAttribute("error", "ERROR! "+result);
                    response.sendRedirect(ADMIN_USER+"/"+id);
                    return;
                }
                
            }else{
                processRequest(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_USER);
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
            response.sendRedirect(ADMIN_USER);
        ru.sfedu.productturnover.model.Client client=new DataProviderDB().getClientById(id);
        if(client==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Client not found by id="+id);
            response.sendRedirect(ADMIN_USER);
        }else{
            request.setAttribute("item", client);
            request.getRequestDispatcher(ADMIN_USER_VIEW_PATH).forward(request, response);
        }
    }
    
    //подгружает все объекты и подгружает страницу, где эти объекты подгружаются в списке
    protected void onList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        List<ru.sfedu.productturnover.model.Client> itemList=new DataProviderDB().getAllClients();
        PrintWriter out = response.getWriter();
        out.print(itemList);
        request.setAttribute("itemList", itemList);
        
        request.getRequestDispatcher(ADMIN_USER_INDEX_PATH).forward(request, response);
    }
}
