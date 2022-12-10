package ru.sfedu.ptu_web;

/**
 *
 * @author Skakun
 */
public class Constants {
    //основной URL для всего сайта. !ОСТОРОЖНО! на нём строятся все URL-константы сайта
    public static final String URL="http://localhost:8080/ptu_web-1.0-PTU/";
    //Путь, где хранятся страницы jsp
    public static final String PATH="/";
    //Путь, где хранятся административные страницы jsp
    public static final String ADMIN_PATH=PATH+"admin/";
    
    public static final String IMAGES=URL+"images/";
    
    //user roles
    public static final int ROLE_GUEST=0;
    public static final int ROLE_USER=1;
    public static final int ROLE_SALER=2;
    public static final int ROLE_ACCEPTOR=3;
    public static final int ROLE_SALER_ACCEPTOR=4;
    public static final int ROLE_ADMIN=99;
    
    //part of pages
    public static final String HEADER="/fragments/header.jsp";
    public static final String FOOTER="/fragments/footer.jsp";
    
    //error pages
    public static final String HTTP_NOT_FOUND="/error/404.jsp";
    public static final String HTTP_FORBIDDEN="/error/403.jsp";    
    
    //pages
    public static final String INDEX=URL/*+"index.jsp"*/;
    public static final String LOGOUT=URL+"jsp/logout.jsp";
    public static final String LOGIN=URL+"jsp/login.jsp";
    public static final String REGISTER=URL+"jsp/register.jsp";
    public static final String WELCOME=URL+"jsp/welcome.jsp";
    public static final String ABOUT=URL+"jsp/about.jsp";
    public static final String CONTACT=URL+"jsp/contact.jsp";
    public static final String USER=URL+"user/"/*+"index.jsp"*/;
    
        //item
        public static final String ITEM=URL+"item";
//        public static final String ITEM_EDIT=ITEM+"/edit";
//        public static final String ITEM_CREATE=ITEM+"/create";
//        public static final String ITEM_DELETE=ITEM+"/delete";
        //path
        public static final String ITEM_PATH=PATH+"itemPath/";
        public static final String ITEM_INDEX_PATH=ITEM_PATH+"index.jsp";
        public static final String ITEM_VIEW_PATH=ITEM_PATH+"view.jsp";
//        public static final String ITEM_EDIT_PATH=ITEM_PATH+"edit.jsp";
//        public static final String ITEM_CREATE_PATH=ITEM_PATH+"create.jsp";
        
        //selling
        public static final String SELLING=URL+"buying";
        public static final String SELLING_EDIT=SELLING+"/edit";
        public static final String SELLING_CREATE=SELLING+"/create";
        public static final String SELLING_DELETE=SELLING+"/delete";
        //path
        public static final String SELLING_PATH=PATH+"sellingPath/";
        public static final String SELLING_INDEX_PATH=SELLING_PATH+"index.jsp";
        public static final String SELLING_VIEW_PATH=SELLING_PATH+"view.jsp";
        public static final String SELLING_EDIT_PATH=SELLING_PATH+"edit.jsp";
        public static final String SELLING_CREATE_PATH=SELLING_PATH+"create.jsp";
    
    //admin_pages
    public static final String ADMINISTRATION=URL+"admin/"/*+"index.jsp"*/;
        //type
        public static final String ADMIN_TYPE=ADMINISTRATION+"type";
        public static final String ADMIN_TYPE_EDIT=ADMIN_TYPE+"/edit";
        public static final String ADMIN_TYPE_CREATE=ADMIN_TYPE+"/create";
        public static final String ADMIN_TYPE_DELETE=ADMIN_TYPE+"/delete";
        //path
        public static final String ADMIN_TYPE_PATH=ADMIN_PATH+"typePath/";
        public static final String ADMIN_TYPE_INDEX_PATH=ADMIN_TYPE_PATH+"index.jsp";
        public static final String ADMIN_TYPE_VIEW_PATH=ADMIN_TYPE_PATH+"view.jsp";
        public static final String ADMIN_TYPE_EDIT_PATH=ADMIN_TYPE_PATH+"edit.jsp";
        public static final String ADMIN_TYPE_CREATE_PATH=ADMIN_TYPE_PATH+"create.jsp";
        
        //provider
        public static final String ADMIN_PROVIDER=ADMINISTRATION+"provider";
        public static final String ADMIN_PROVIDER_EDIT=ADMIN_PROVIDER+"/edit";
        public static final String ADMIN_PROVIDER_CREATE=ADMIN_PROVIDER+"/create";
        public static final String ADMIN_PROVIDER_DELETE=ADMIN_PROVIDER+"/delete";
        //path
        public static final String ADMIN_PROVIDER_PATH=ADMIN_PATH+"providerPath/";
        public static final String ADMIN_PROVIDER_INDEX_PATH=ADMIN_PROVIDER_PATH+"index.jsp";
        public static final String ADMIN_PROVIDER_VIEW_PATH=ADMIN_PROVIDER_PATH+"view.jsp";
        public static final String ADMIN_PROVIDER_EDIT_PATH=ADMIN_PROVIDER_PATH+"edit.jsp";
        public static final String ADMIN_PROVIDER_CREATE_PATH=ADMIN_PROVIDER_PATH+"create.jsp";
        
        //user
        public static final String ADMIN_USER=ADMINISTRATION+"user";
        //path
        public static final String ADMIN_USER_PATH=ADMIN_PATH+"userPath/";
        public static final String ADMIN_USER_INDEX_PATH=ADMIN_USER_PATH+"index.jsp";
        public static final String ADMIN_USER_VIEW_PATH=ADMIN_USER_PATH+"view.jsp";
        
        //item
        public static final String ADMIN_ITEM=ADMINISTRATION+"item";
        public static final String ADMIN_ITEM_EDIT=ADMIN_ITEM+"/edit";
        public static final String ADMIN_ITEM_CREATE=ADMIN_ITEM+"/create";
        public static final String ADMIN_ITEM_DELETE=ADMIN_ITEM+"/delete";
        //path
        public static final String ADMIN_ITEM_PATH=ADMIN_PATH+"itemPath/";
        public static final String ADMIN_ITEM_INDEX_PATH=ADMIN_ITEM_PATH+"index.jsp";
        public static final String ADMIN_ITEM_VIEW_PATH=ADMIN_ITEM_PATH+"view.jsp";
        public static final String ADMIN_ITEM_EDIT_PATH=ADMIN_ITEM_PATH+"edit.jsp";
        public static final String ADMIN_ITEM_CREATE_PATH=ADMIN_ITEM_PATH+"create.jsp";
        
        //delivery
        public static final String ADMIN_DELIVERY=ADMINISTRATION+"delivery";
        public static final String ADMIN_DELIVERY_EDIT=ADMIN_DELIVERY+"/edit";
        public static final String ADMIN_DELIVERY_CREATE=ADMIN_DELIVERY+"/create";
        public static final String ADMIN_DELIVERY_DELETE=ADMIN_DELIVERY+"/delete";
        //path
        public static final String ADMIN_DELIVERY_PATH=ADMIN_PATH+"deliveryPath/";
        public static final String ADMIN_DELIVERY_INDEX_PATH=ADMIN_DELIVERY_PATH+"index.jsp";
        public static final String ADMIN_DELIVERY_VIEW_PATH=ADMIN_DELIVERY_PATH+"view.jsp";
        public static final String ADMIN_DELIVERY_EDIT_PATH=ADMIN_DELIVERY_PATH+"edit.jsp";
        public static final String ADMIN_DELIVERY_CREATE_PATH=ADMIN_DELIVERY_PATH+"create.jsp";
        
        //selling
        public static final String ADMIN_SELLING=ADMINISTRATION+"selling";
        //path
        public static final String ADMIN_SELLING_PATH=ADMIN_PATH+"sellingPath/";
        public static final String ADMIN_SELLING_INDEX_PATH=ADMIN_SELLING_PATH+"index.jsp";
        public static final String ADMIN_SELLING_VIEW_PATH=ADMIN_SELLING_PATH+"view.jsp";

    
    //servlets
    public static final String SR_REGISTRATION=URL+"Registration";
    public static final String SR_LOGIN=URL+"Login";
    public static final String SR_USER=URL+"User";
    public static final String SR_SELLING=URL+"buying/";
    
    public static final String SR_ADMIN=URL+"admin/";
        public static final String SR_ADMIN_TYPE=SR_ADMIN+"type/";
        public static final String SR_ADMIN_PROVIDER=SR_ADMIN+"provider/";
        public static final String SR_ADMIN_ITEM=SR_ADMIN+"item/";
        public static final String SR_ADMIN_DELIVERY=SR_ADMIN+"delivery/";
        public static final String SR_ADMIN_USER=SR_ADMIN+"user/";
        public static final String SR_ADMIN_SELLING=SR_ADMIN+"selling/";
        
        
    //other
        
    public static final String[] STATUS_LIST=new String[] {"Is being formed","Waiting","Accepted. Departure expected","Sent","Arrived","Received"};
}
