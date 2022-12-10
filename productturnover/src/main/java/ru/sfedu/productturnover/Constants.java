package ru.sfedu.productturnover;

/**
 *
 * @author Дмитрий
 * 
 * Константы. Использование констант позволяет избежать хардкода, что  повысит  стоимость как програмиста
 */
public class Constants {
    //user roles
    public static final int ROLE_GUEST=0;
    public static final int ROLE_USER=1;
    public static final int ROLE_SALER=2;
    public static final int ROLE_ACCEPTOR=3;
    public static final int ROLE_SALER_ACCEPTOR=4;
    public static final int ROLE_ADMIN=99;
    
    // Указатель на именованную переменную, записанную в enviroment.properties,
    // которая подтягивается с помощью ConfigurationUtils
    public static final String PATH_CSV_STORE="csv_store";
    public static final String PATH_XML_STORE="xml_store";
    // Используется для подключения к базе данных
    public static final String DB_DRIVER = "db_driver";
    public static final String DB_USER="db_user";
    public static final String DB_PASS="db_pass";
    public static final String DB_URL="db_url";
    // Данные конфига
    public static final String CONFIG_KEY="propertyValue";
    public static final String DEFAULT_CONFIG_PATH = "./src/main/resources/enviroment.properties";
    public static final String DEFAULT_LOG_PATH = "./src/main/resources/log4j2.properties";
    // Набор полей моделей
    public static final String[] COLUMNS_CLIENT = new String[] {"id", "name","login","password","role"};
    public static final String[] COLUMNS_ITEM = new String[] {"id", "name","description","type","balance"};
    public static final String[] COLUMNS_TYPE = new String[] {"id", "name","description"};
    public static final String[] COLUMNS_PROVIDER = new String[] {"id", "name"};
    public static final String[] COLUMNS_SELLING = new String[] {"id", "item","client","number","date","price","status"};
    public static final String[] COLUMNS_DELIVERY = new String[] {"id", "item","provider","datestart","dateend","number","status","price"};
    // константы CLI
    public static final String CLI_XML="xml";
    public static final String CLI_CSV="csv";
    public static final String CLI_DB="db";
    
    public static final String CLI_INSERT="insert";
    public static final String CLI_SELECT="select";
    public static final String CLI_UPDATE="update";
    public static final String CLI_DELETE="delete";
    
    public static final String BEAN_ITEM="ITEM";
    public static final String BEAN_CLIENT="CLIENT";
    public static final String BEAN_TYPE="TYPE";
    public static final String BEAN_PROVIDER="PROVIDER";
    public static final String BEAN_SELLING="SELLING";
    public static final String BEAN_DELIVERY="DELIVERY";    
    //запросы к базе данных
    public static final String DB_INSERT="INSERT INTO \"%s\"(%s) VALUES (%s) RETURNING id;";
    
    public static final String DB_UPDATE_ID="UPDATE \"%s\" SET %s WHERE id=%d;";
    public static final String DB_SELECT_ID="SELECT * FROM \"%s\" WHERE id=%d;";
    public static final String DB_DELETE_ID="DELETE FROM \"%s\" WHERE id=%d;";
    public static final String DB_COUNT_ID="SELECT count(*) FROM \"%s\" WHERE id=%d;";
    
    public static final String DB_UPDATE_STR="UPDATE \"%s\" SET %s WHERE %s;";
    public static final String DB_SELECT_STR="SELECT * FROM \"%s\" WHERE %s;";
    public static final String DB_DELETE_STR="DELETE FROM \"%s\" WHERE %s;";
    public static final String DB_COUNT_STR="SELECT count(*) FROM \"%s\" WHERE %s;";
    
    public static final String DB_SELECT_ALL="SELECT * FROM \"%s\"";
}