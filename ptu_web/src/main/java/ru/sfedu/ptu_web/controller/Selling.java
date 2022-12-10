package ru.sfedu.ptu_web.controller;

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
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_CREATE;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_EDIT;
import static ru.sfedu.ptu_web.Constants.INDEX;
import static ru.sfedu.ptu_web.Constants.SELLING;
import static ru.sfedu.ptu_web.Constants.SELLING_INDEX_PATH;
import static ru.sfedu.ptu_web.Constants.SELLING_VIEW_PATH;
import static ru.sfedu.ptu_web.Constants.SELLING_CREATE;
import static ru.sfedu.ptu_web.Constants.SELLING_CREATE_PATH;
import static ru.sfedu.ptu_web.Constants.SELLING_DELETE;
import static ru.sfedu.ptu_web.Constants.SELLING_EDIT;
import static ru.sfedu.ptu_web.Constants.SELLING_EDIT_PATH;

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
        if(Pattern.matches(SELLING+"/\\d*",request.getRequestURL()))
            this.onView(request, response);
        else if(Pattern.matches(SELLING_CREATE+".*",request.getRequestURL())) //Если после основного URL указан create, то вызываем форму создания
            this.onCreate(request, response);
        else if(Pattern.matches(SELLING_EDIT+"/\\d*",request.getRequestURL())) //Если после основного URL указан edit и указано id, то вызываем форму для редактирования этого объекта
            this.onEdit(request, response);
        else if(Pattern.matches(SELLING_DELETE+"/\\d*",request.getRequestURL())) //Если после основгого URL указан delete и указано id, то пытаемся удалить этот объект
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
            Logger.getLogger(Selling.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session=request.getSession();
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(SELLING);
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
            String submit=request.getParameter("submit");
            //Проверка, была ли нажата кнопка на форме
            if(!submit.isEmpty()){
                IDataProvider provider=new DataProviderDB();
                Date date=new Date();
                if(!request.getParameter("date").isEmpty())
                    date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
                ru.sfedu.productturnover.model.Item item=null;
                if(!request.getParameter("item").isEmpty())
                    item=provider.getItemById(Long.parseLong(request.getParameter("item")));
                String end=request.getRequestURI().replaceAll(".*/", "");
                long id=0;
                //Если передан id, то сохраняем его
                if(!end.isEmpty())
                    id=Long.decode(end);
                long number=0;
                if(!request.getParameter("number").isEmpty())
                    number=(long)(Float.parseFloat(request.getParameter("number"))*1000);
                short status=1;
                long price=0;
                String redirect="";
                ru.sfedu.productturnover.model.Selling selling=new ru.sfedu.productturnover.model.Selling();
                Result result=new Result();
                //проверка на редактирование или добавление записи
                if(id!=0){
                    selling=provider.getSellingById(id);
                    if(selling==null){
                        session.setAttribute("error","Selling not found, id="+id);
                        response.sendRedirect(SELLING);
                        return;
                    }
                    //в случае ошибки обратно перенаправим на страницу редактирования
                    redirect=SELLING_EDIT+"/"+id;
                }else{
                    //в случае ошибки обратно перенаправим на страницу добавления новой записи
                    redirect=SELLING_CREATE;
                }
                //Если имя пустое, то не сохраняем и ругаемся
                if(item==null){
                    session.setAttribute("error","Item can't be empty!");
                    response.sendRedirect(redirect);
                    return;
                }
                //Создаём новый экземпляр класса, чтобы сравнить со старым. Если равны, то не обновляем
                ru.sfedu.productturnover.model.Selling selling1=new ru.sfedu.productturnover.model.Selling(selling);
                selling.setItem(item);
                selling.setDate(date);
                selling.setNumber(number);
                selling.setStatus(status);
                selling.setPrice(price);
                selling.setClient((Client)request.getSession().getAttribute("client"));
                if(id!=0){
                    //Сравниваем переданные поля с предыдущими. Если равны, то не сохраняем
                    if(selling.equals(selling1)){
                        session.setAttribute("info","You haven't made any changes.");
                        response.sendRedirect(redirect);
                        return;
                    }else{
                        //обновляем данные
                        result=provider.updateSelling(selling);
                    }
                }else
                    //Вставляем новые данные
                    result=provider.insertSelling(selling);
                //Если была произведена вставка новой записи, то в поле id класса Result хранится id Новой записи. Сохраняем её в переменную id
                if(result.getId()!=0)
                    id=result.getId();
                if(result.equals(new Result(OK))){
                    session.setAttribute("success", "Saving was success");
                    //Если всё удачно, то переходим на страницу просмотра записи
                    response.sendRedirect(SELLING+"/"+id);
                    return;
                }else{
                    //Если произошла ошибка, то переходим на туже страницу и возвращаем сообщение об ошибке
                    session.setAttribute("error", "ERROR! "+result+"|"+selling+"|"+selling1);
                    response.sendRedirect(redirect);
                    return;
                }
            }else{
                //Если не нажималась кнопка сохранения на форме, то идём дальше
                processRequest(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(Selling.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(SELLING);
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
            throws ServletException, IOException, Exception {
        List<ru.sfedu.productturnover.model.Item> itemList=new DataProviderDB().getAllItems();
        if(!itemList.isEmpty())
            request.setAttribute("itemList",itemList);
        request.getRequestDispatcher(SELLING_CREATE_PATH).forward(request, response);
    }
    
    //подгружает объект и вызывает страницу с формой для редактирования этого объекта
    protected void onEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        ru.sfedu.productturnover.model.Selling selling=new DataProviderDB().getSellingById(id);
        if(selling==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Selling not found by id="+id);
            response.sendRedirect(SELLING);
        }else if(selling.getStatus()==0){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! You can't edit cancelled item");
            response.sendRedirect(SELLING+"/"+id);
        }else if(selling.getStatus()==2){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! You can't edit received item");
            response.sendRedirect(SELLING+"/"+id);
        }else{
            List<ru.sfedu.productturnover.model.Item> itemList=new DataProviderDB().getAllItems();
            if(!itemList.isEmpty())
                request.setAttribute("itemList",itemList);
            
            request.setAttribute("item", selling);
            request.getRequestDispatcher(SELLING_EDIT_PATH).forward(request, response);
        }
    }
    
    //подгружает объект и вызывает страницу для просмотра данных объекта
    protected void onView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        if(id==0)
            response.sendRedirect(SELLING);
        ru.sfedu.productturnover.model.Selling selling=new DataProviderDB().getSellingById(id);
        if(selling==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Selling not found by id="+id);
            response.sendRedirect(SELLING);
        }else{
            request.setAttribute("item", selling);
            request.getRequestDispatcher(SELLING_VIEW_PATH).forward(request, response);
        }
    }
    
    //удаляет указанный объект и переходит на страницу со всеми объектами
    protected void onDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        Result result;
        HttpSession session=request.getSession();
        IDataProvider provider = new DataProviderDB();
        result=provider.updateSellingStatus(id,(short)0);
        if(result.equals(new Result(OK))){
            session.setAttribute("success", "Cancelling was success");
            response.sendRedirect(SELLING);
        }else{
            session.setAttribute("error", "ERROR! "+result.getErrorMsg());
            response.sendRedirect(SELLING+"/"+id);
        }
    }
    
    //подгружает все объекты и подгружает страницу, где эти объекты подгружаются в списке
    protected void onList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        List<ru.sfedu.productturnover.model.Selling> itemList=new DataProviderDB().getSellingForClient(((Client)request.getSession().getAttribute("client")).getId());
        PrintWriter out = response.getWriter();
        out.print(itemList);
        request.setAttribute("itemList", itemList);
        
        request.getRequestDispatcher(SELLING_INDEX_PATH).forward(request, response);
    }
}
