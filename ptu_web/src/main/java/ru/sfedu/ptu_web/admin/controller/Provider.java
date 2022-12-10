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
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER;
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER_CREATE;
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER_CREATE_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER_DELETE;
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER_EDIT;
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER_EDIT_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER_INDEX_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_PROVIDER_VIEW_PATH;
import static ru.sfedu.ptu_web.Constants.INDEX;

/**
 *
 * @author Skakun
 */
public class Provider extends HttpServlet {

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

        if(Pattern.matches(ADMIN_PROVIDER+"/\\d*",request.getRequestURL()))
            this.onView(request, response);
        else if(Pattern.matches(ADMIN_PROVIDER_CREATE+".*",request.getRequestURL()))
            this.onCreate(request, response);
        else if(Pattern.matches(ADMIN_PROVIDER_EDIT+"/\\d*",request.getRequestURL()))
            this.onEdit(request, response);
        else if(Pattern.matches(ADMIN_PROVIDER_DELETE+"/\\d*",request.getRequestURL()))
            this.onDelete(request, response);
        else
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session=request.getSession();
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_PROVIDER);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try {
            //Если не залогинен, то выкидываем на главную страницу
            if(request.getSession().getAttribute("client")==null){
                response.sendRedirect(INDEX);
                return;
            }
            String name=request.getParameter("name");
            String description=request.getParameter("description");
            String submit=request.getParameter("submit");
            String end=request.getRequestURI().replaceAll(".*/", "");
            long id=0;
            //Если передан id, то сохраняем его
            if(!end.isEmpty())
                id=Long.decode(end);
            //Проверка, была ли нажата кнопка на форме
            if(!submit.isEmpty()){
                IDataProvider provider=new DataProviderDB();
                String redirect="";
                ru.sfedu.productturnover.model.Provider prov=new ru.sfedu.productturnover.model.Provider();
                Result result=new Result();
                //проверка на редактирование или добавление записи
                if(id!=0){
                    prov=provider.getProviderById(id);
                    if(prov==null){
                        session.setAttribute("error","Provider not found, id="+id);
                        response.sendRedirect(ADMIN_PROVIDER);
                        return;
                    }
                    //в случае ошибки обратно перенаправим на страницу редактирования
                    redirect=ADMIN_PROVIDER_EDIT+"/"+id;
                }else{
                    //в случае ошибки обратно перенаправим на страницу добавления новой записи
                    redirect=ADMIN_PROVIDER_CREATE;
                }
                //Если имя пустое, то не сохраняем и ругаемся
                if(name.isEmpty()){
                    session.setAttribute("error","Name can't be empty!");
                    response.sendRedirect(redirect);
                    return;
                }
                //Создаём новый экземпляр класса, чтобы сравнить со старым. Если равны, то не обновляем
                ru.sfedu.productturnover.model.Provider prov1=new ru.sfedu.productturnover.model.Provider(prov);
                prov.setName(name);
                if(id!=0){
                    //Сравниваем переданные поля с предыдущими. Если равны, то не сохраняем
                    if(prov.equals(prov1)){
                        session.setAttribute("info","You haven't made any changes.");
                        response.sendRedirect(redirect);
                        return;
                    }else{
                        //обновляем данные
                        result=provider.updateProvider(prov);
                    }
                }else
                    //Вставляем новые данные
                    result=provider.insertProvider(prov);
                //Если была произведена вставка новой записи, то в поле id класса Result хранится id Новой записи. Сохраняем её в переменную id
                if(result.getId()!=0)
                    id=result.getId();
                
                if(result.equals(new Result(OK))){
                    session.setAttribute("success", "Saving was success");
                    //Если всё удачно, то переходим на страницу просмотра записи
                    response.sendRedirect(ADMIN_PROVIDER+"/"+id);
                    return;
                }else{
                    //Если произошла ошибка, то переходим на туже страницу и возвращаем сообщение об ошибке
                    session.setAttribute("error", "ERROR! "+result.getErrorMsg());
                    response.sendRedirect(redirect);
                    return;
                }
            }else{
                //Если не нажималась кнопка сохранения на форме, то идём дальше
                processRequest(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_PROVIDER);
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

    protected void onCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ADMIN_PROVIDER_CREATE_PATH).forward(request, response);
    }
    
    protected void onEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        ru.sfedu.productturnover.model.Provider prov=new DataProviderDB().getProviderById(id);
        if(prov==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Provider not found by id="+id);
            response.sendRedirect(ADMIN_PROVIDER);
        }else{
            request.setAttribute("item", prov);
            request.getRequestDispatcher(ADMIN_PROVIDER_EDIT_PATH).forward(request, response);
        }
    }
    
    protected void onView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        ru.sfedu.productturnover.model.Provider prov=new DataProviderDB().getProviderById(id);
        if(prov==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Provider not found by id="+id);
            response.sendRedirect(ADMIN_PROVIDER);
        }else{
            request.setAttribute("item", prov);
            request.getRequestDispatcher(ADMIN_PROVIDER_VIEW_PATH).forward(request, response);
        }
    }
    
    protected void onDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        Result result;
        HttpSession session=request.getSession();
        IDataProvider provider = new DataProviderDB();
        result=provider.deleteProvider(id);
        if(result.equals(new Result(OK))){
            session.setAttribute("success", "Deleting was success");
            response.sendRedirect(ADMIN_PROVIDER);
        }else{
            session.setAttribute("error", "ERROR! "+result.getErrorMsg());
            response.sendRedirect(ADMIN_PROVIDER+"/"+id);
        }
    }
    
    protected void onList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        List<ru.sfedu.productturnover.model.Provider> itemList=new DataProviderDB().getAllProviders();
        PrintWriter out = response.getWriter();
        out.print(itemList);
        request.setAttribute("itemList", itemList);
        
        request.getRequestDispatcher(ADMIN_PROVIDER_INDEX_PATH).forward(request, response);
    }
}
