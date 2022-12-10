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
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_CREATE;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_CREATE_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_DELETE;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_EDIT;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_EDIT_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_INDEX_PATH;
import static ru.sfedu.ptu_web.Constants.ADMIN_DELIVERY_VIEW_PATH;
import static ru.sfedu.ptu_web.Constants.INDEX;
import static ru.sfedu.ptu_web.Constants.STATUS_LIST;

/**
 *
 * @author Skakun
 */
public class Delivery extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getSession().getAttribute("client")==null){
            response.sendRedirect(INDEX);
        }
        //Если переходим на страницу, но указан ещё id, то вызываем панель показа этого объекта
        if(Pattern.matches(ADMIN_DELIVERY+"/\\d*",request.getRequestURL()))
            this.onView(request, response);
        else if(Pattern.matches(ADMIN_DELIVERY_CREATE+".*",request.getRequestURL())) //Если после основного URL указан create, то вызываем форму создания
            this.onCreate(request, response);
        else if(Pattern.matches(ADMIN_DELIVERY_EDIT+"/\\d*",request.getRequestURL())) //Если после основного URL указан edit и указано id, то вызываем форму для редактирования этого объекта
            this.onEdit(request, response);
        else if(Pattern.matches(ADMIN_DELIVERY_DELETE+"/\\d*",request.getRequestURL())) //Если после основгого URL указан delete и указано id, то пытаемся удалить этот объект
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
            Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session=request.getSession();
            session.setAttribute("error", ex.toString());
        }
        response.sendRedirect(ADMIN_DELIVERY);
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
                Date dateStart=new Date();
                if(!request.getParameter("dateStart").isEmpty())
                    dateStart=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateStart"));
                Date dateEnd=new Date();
                if(!request.getParameter("dateEnd").isEmpty())
                    dateEnd=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateEnd"));
                ru.sfedu.productturnover.model.Item item=null;
                if(!request.getParameter("item").isEmpty())
                    item=provider.getItemById(Long.parseLong(request.getParameter("item")));
                ru.sfedu.productturnover.model.Provider prov=null;
                if(!request.getParameter("provider").isEmpty())
                    prov=provider.getProviderById(Long.parseLong(request.getParameter("provider")));
                String end=request.getRequestURI().replaceAll(".*/", "");
                long id=0;
                //Если передан id, то сохраняем его
                if(!end.isEmpty())
                    id=Long.decode(end);
                long number=0;
                if(!request.getParameter("number").isEmpty())
                    number=(long)(Float.parseFloat(request.getParameter("number"))*1000);
                short status=0;
                if(!request.getParameter("status").isEmpty())
                    status=Short.decode(request.getParameter("status"));
                long price=0;
                if(!request.getParameter("price").isEmpty())
                    price=(long)(Float.parseFloat(request.getParameter("price"))*100);
                String redirect="";
                ru.sfedu.productturnover.model.Delivery delivery=new ru.sfedu.productturnover.model.Delivery();
                Result result=new Result();
                //проверка на редактирование или добавление записи
                if(id!=0){
                    delivery=provider.getDeliveryById(id);
                    if(delivery==null){
                        session.setAttribute("error","Delivery not found, id="+id);
                        response.sendRedirect(ADMIN_DELIVERY);
                        return;
                    }
                    //в случае ошибки обратно перенаправим на страницу редактирования
                    redirect=ADMIN_DELIVERY_EDIT+"/"+id;
                }else{
                    //в случае ошибки обратно перенаправим на страницу добавления новой записи
                    redirect=ADMIN_DELIVERY_CREATE;
                }
                //Если имя пустое, то не сохраняем и ругаемся
                if(item==null){
                    session.setAttribute("error","Item can't be empty!");
                    response.sendRedirect(redirect);
                    return;
                }
                if(prov==null){
                    session.setAttribute("error","Provider can't be empty!");
                    response.sendRedirect(redirect);
                    return;
                }
                //Создаём новый экземпляр класса, чтобы сравнить со старым. Если равны, то не обновляем
                ru.sfedu.productturnover.model.Delivery delivery1=new ru.sfedu.productturnover.model.Delivery(delivery);
                delivery.setItem(item);
                delivery.setProvider(prov);
                delivery.setDatestart(dateStart);
                delivery.setDateend(dateEnd);
                delivery.setNumber(number);
                delivery.setStatus(status);
                delivery.setPrice(price);
                if(id!=0){
                    //Сравниваем переданные поля с предыдущими. Если равны, то не сохраняем
                    if(delivery.equals(delivery1)){
                        session.setAttribute("info","You haven't made any changes.");
                        response.sendRedirect(redirect);
                        return;
                    }else{
                        //обновляем данные
                        result=provider.updateDelivery(delivery);
                    }
                }else
                    //Вставляем новые данные
                    result=provider.insertDelivery(delivery);
                //Если была произведена вставка новой записи, то в поле id класса Result хранится id Новой записи. Сохраняем её в переменную id
                if(result.getId()!=0)
                    id=result.getId();
                if(result.equals(new Result(OK))){
                    if(delivery.getStatus()==5){
                        delivery.getItem().setBalance(item.getBalance()+delivery.getNumber());
                        result=provider.updateItem(delivery.getItem());
                        if(result.equals(new Result(OK))){
                            session.setAttribute("success", "Saving was success. Balance is updated");
                        }else{
                            session.setAttribute("warning", "Saving was success, but balance wasn't updated");
                        }
                    }else
                        session.setAttribute("success", "Saving was success");
                    //Если всё удачно, то переходим на страницу просмотра записи
                    response.sendRedirect(ADMIN_DELIVERY+"/"+id);
                    return;
                }else{
                    //Если произошла ошибка, то переходим на туже страницу и возвращаем сообщение об ошибке
                    session.setAttribute("error", "ERROR! "+result);
                    response.sendRedirect(redirect);
                    return;
                }
            }else{
                //Если не нажималась кнопка сохранения на форме, то идём дальше
                processRequest(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("error", ex.toString());            
        }
        response.sendRedirect(ADMIN_DELIVERY);
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
        List<ru.sfedu.productturnover.model.Provider> providerList=new DataProviderDB().getAllProviders();
        if(!providerList.isEmpty())
            request.setAttribute("providerList",providerList);
        request.setAttribute("statusList",STATUS_LIST);
        request.getRequestDispatcher(ADMIN_DELIVERY_CREATE_PATH).forward(request, response);
    }
    
    //подгружает объект и вызывает страницу с формой для редактирования этого объекта
    protected void onEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        ru.sfedu.productturnover.model.Delivery delivery=new DataProviderDB().getDeliveryById(id);
        HttpSession session=request.getSession();
        if(delivery==null){
            session.setAttribute("error","Error! Delivery not found by id="+id);
            response.sendRedirect(ADMIN_DELIVERY);
        }else if(delivery.getStatus()==5){
            session.setAttribute("error","Error! You can't edit received delivery id="+id);
            response.sendRedirect(ADMIN_DELIVERY+"/"+id);
        }else{
            List<ru.sfedu.productturnover.model.Item> itemList=new DataProviderDB().getAllItems();
            if(!itemList.isEmpty())
                request.setAttribute("itemList",itemList);
            
            List<ru.sfedu.productturnover.model.Provider> providerList=new DataProviderDB().getAllProviders();
            if(!providerList.isEmpty())
                request.setAttribute("providerList",providerList);
            
            request.setAttribute("statusList",STATUS_LIST);
            
            request.setAttribute("item", delivery);
            request.getRequestDispatcher(ADMIN_DELIVERY_EDIT_PATH).forward(request, response);
        }
    }
    
    //подгружает объект и вызывает страницу для просмотра данных объекта
    protected void onView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        if(id==0)
            response.sendRedirect(ADMIN_DELIVERY);
        ru.sfedu.productturnover.model.Delivery delivery=new DataProviderDB().getDeliveryById(id);
        if(delivery==null){
            HttpSession session=request.getSession();
            session.setAttribute("error","Error! Delivery not found by id="+id);
            response.sendRedirect(ADMIN_DELIVERY);
        }else{
            request.setAttribute("item", delivery);
            request.getRequestDispatcher(ADMIN_DELIVERY_VIEW_PATH).forward(request, response);
        }
    }
    
    //удаляет указанный объект и переходит на страницу со всеми объектами
    protected void onDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long id=Long.decode(request.getRequestURI().replaceAll(".*/", ""));
        Result result;
        HttpSession session=request.getSession();
        IDataProvider provider = new DataProviderDB();
        ru.sfedu.productturnover.model.Delivery delivery=provider.getDeliveryById(id);
        if(delivery==null || delivery.getStatus()==5){
            session.setAttribute("error", "ERROR! You can't delete this record! ");
            response.sendRedirect(ADMIN_DELIVERY);
            return;
        }
        result=provider.deleteDelivery(id);
        if(result.equals(new Result(OK))){
            session.setAttribute("success", "Deleting was success");
            response.sendRedirect(ADMIN_DELIVERY);
        }else{
            session.setAttribute("error", "ERROR! "+result.getErrorMsg());
            response.sendRedirect(ADMIN_DELIVERY+"/"+id);
        }
    }
    
    //подгружает все объекты и подгружает страницу, где эти объекты подгружаются в списке
    protected void onList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        List<ru.sfedu.productturnover.model.Delivery> itemList=new DataProviderDB().getAllDeliveries();
        PrintWriter out = response.getWriter();
        out.print(itemList);
        request.setAttribute("itemList", itemList);
        
        request.getRequestDispatcher(ADMIN_DELIVERY_INDEX_PATH).forward(request, response);
    }
}
