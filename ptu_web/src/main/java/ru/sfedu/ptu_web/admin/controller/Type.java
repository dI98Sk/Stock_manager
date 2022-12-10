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
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE;
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE_CREATE;
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE_CREATE_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE_DELETE;
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE_EDIT;
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE_EDIT_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE_INDEX_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_TYPE_VIEW_PATH;
import static ru.sfedu.ptu_web.Constants.INDEX;

/**
 *
 * @author Skakun
 */
public class Type extends HttpServlet {

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
        if(Pattern.matches(ADMIN_TYPE+"/\\d*",request.getRequestURL()))
            this.onView(request, response);
        else if(Pattern.matches(ADMIN_TYPE_CREATE+".*",request.getRequestURL())) //Если после основного URL указан create, то вызываем форму создания
            this.onCreate(request, response);
        else if(Pattern.matches(ADMIN_TYPE_EDIT+"/\\d*",request.getRequestURL())) //Если после основного URL указан edit и указано id, то вызываем форму для редактирования этого объекта
            this.onEdit(request, response);
        else if(Pattern.matches(ADMIN_TYPE_DELETE+"/\\d*",request.getRequestURL())) //Если после основгого URL указан delete и указано id, то пытаемся удалить этот объект
            this.onDelete(request, response);
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
            Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session=request.getSession();
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_TYPE);
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
                ru.sfedu.productturnover.model.Type type=new ru.sfedu.productturnover.model.Type();
                Result result=new Result();
                //проверка на редактирование или добавление записи
                if(id!=0){
                    type=provider.getTypeById(id);
                    if(type==null){
                        session.setAttribute("error","Type not found, id="+id);
                        response.sendRedirect(ADMIN_TYPE);
                        return;
                    }
                    //в случае ошибки обратно перенаправим на страницу редактирования
                    redirect=ADMIN_TYPE_EDIT+"/"+id;
                }else{
                    //в случае ошибки обратно перенаправим на страницу добавления новой записи
                    redirect=ADMIN_TYPE_CREATE;
                }
                //Если имя пустое, то не сохраняем и ругаемся
                if(name.isEmpty()){
                    session.setAttribute("error","Name can't be empty!");
                    response.sendRedirect(redirect);
                    return;
                }
                //Создаём новый экземпляр класса, чтобы сравнить со старым. Если равны, то не обновляем
                ru.sfedu.productturnover.model.Type type1=new ru.sfedu.productturnover.model.Type(type);
                type.setName(name);
                type.setDescription(description);
                if(id!=0){
                    //Сравниваем переданные поля с предыдущими. Если равны, то не сохраняем
                    if(type.equals(type1)){
                        session.setAttribute("info","You haven't made any changes.");
                        response.sendRedirect(redirect);
                        return;
                    }else{
                        //обновляем данные
                        result=provider.updateType(type);
                    }
                }else
                    //Вставляем новые данные
                    result=provider.insertType(type);
                //Если была произведена вставка новой записи, то в поле id класса Result хранится id Новой записи. Сохраняем её в переменную id
                if(result.getId()!=0)
                    id=result.getId();
                
                if(result.equals(new Result(OK))){
                    session.setAttribute("success", "Saving was success");
                    //Если всё удачно, то переходим на страницу просмотра записи
                    response.sendRedirect(ADMIN_TYPE+"/"+id);
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
            Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_TYPE);
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

    //подгружает страницу с формой для создания нового объекта
    protected void onCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ADMIN_TYPE_CREATE_PATH).forward(request, response);
    }
    
    //подгружает объект и вызывает страницу с формой для редактирования этого объекта
    protected void onEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        ru.sfedu.productturnover.model.Type type=new DataProviderDB().getTypeById(id);
        if(type==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Type not found by id="+id);
            response.sendRedirect(ADMIN_TYPE);
        }else{
            request.setAttribute("item", type);
            request.getRequestDispatcher(ADMIN_TYPE_EDIT_PATH).forward(request, response);
        }
    }
    
    //подгружает объект и вызывает страницу для просмотра данных объекта
    protected void onView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        if(id==0)
            response.sendRedirect(ADMIN_TYPE);
        ru.sfedu.productturnover.model.Type type=new DataProviderDB().getTypeById(id);
        if(type==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Type not found by id="+id);
            response.sendRedirect(ADMIN_TYPE);
        }else{
            request.setAttribute("item", type);
            request.getRequestDispatcher(ADMIN_TYPE_VIEW_PATH).forward(request, response);
        }
    }
    
    //удаляет указанный объект и переходит на страницу со всеми объектами
    protected void onDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        Result result;
        HttpSession session=request.getSession();
        IDataProvider provider = new DataProviderDB();
        result=provider.deleteType(id);
        if(result.equals(new Result(OK))){
            session.setAttribute("success", "Deleting was success");
            response.sendRedirect(ADMIN_TYPE);
        }else{
            session.setAttribute("error", "ERROR! "+result.getErrorMsg());
            response.sendRedirect(ADMIN_TYPE+"/"+id);
        }
    }
    
    //подгружает все объекты и подгружает страницу, где эти объекты подгружаются в списке
    protected void onList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        List<ru.sfedu.productturnover.model.Type> itemList=new DataProviderDB().getAllTypes();
        PrintWriter out = response.getWriter();
        out.print(itemList);
        request.setAttribute("itemList", itemList);
        
        request.getRequestDispatcher(ADMIN_TYPE_INDEX_PATH).forward(request, response);
    }
}
