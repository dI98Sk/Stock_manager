/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover.api;

import ru.sfedu.productturnover.model.Provider;
import ru.sfedu.productturnover.model.Selling;
import ru.sfedu.productturnover.model.Item;
import ru.sfedu.productturnover.model.Client;
import ru.sfedu.productturnover.model.Type;
import ru.sfedu.productturnover.model.Delivery;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static ru.sfedu.productturnover.Constants.BEAN_CLIENT;
import static ru.sfedu.productturnover.Constants.BEAN_DELIVERY;
import static ru.sfedu.productturnover.Constants.BEAN_ITEM;
import static ru.sfedu.productturnover.Constants.BEAN_PROVIDER;
import static ru.sfedu.productturnover.Constants.BEAN_SELLING;
import static ru.sfedu.productturnover.Constants.BEAN_TYPE;
import static ru.sfedu.productturnover.Constants.COLUMNS_CLIENT;
import static ru.sfedu.productturnover.Constants.COLUMNS_DELIVERY;
import static ru.sfedu.productturnover.Constants.COLUMNS_ITEM;
import static ru.sfedu.productturnover.Constants.COLUMNS_PROVIDER;
import static ru.sfedu.productturnover.Constants.COLUMNS_SELLING;
import static ru.sfedu.productturnover.Constants.COLUMNS_TYPE;
import static ru.sfedu.productturnover.Constants.DB_COUNT_STR;
import static ru.sfedu.productturnover.Constants.DB_DRIVER;
import static ru.sfedu.productturnover.Constants.DB_PASS;
import static ru.sfedu.productturnover.Constants.DB_URL;
import static ru.sfedu.productturnover.Constants.DB_USER;
import ru.sfedu.productturnover.constant.Result;
import static ru.sfedu.productturnover.constant.StatusType.ERROR;
import static ru.sfedu.productturnover.constant.StatusType.OK;
import static ru.sfedu.productturnover.constant.StatusType.WARNING;
import static ru.sfedu.productturnover.utils.ConfigurationUtil.getConfigurationEntry;
import static ru.sfedu.productturnover.Constants.DB_SELECT_ID;
import static ru.sfedu.productturnover.Constants.DB_UPDATE_ID;
import static ru.sfedu.productturnover.Constants.DB_DELETE_ID;
import static ru.sfedu.productturnover.Constants.DB_INSERT;
import static ru.sfedu.productturnover.Constants.DB_SELECT_ALL;
import static ru.sfedu.productturnover.Constants.DB_SELECT_STR;

/**
 *
 * @author Дмитрий
 */
public class DataProviderDB implements IDataProvider{
    private static final Logger log = LogManager.getLogger(DataProviderDB.class);
    /**
     *
     * @throws IOException
     */
    public DataProviderDB() throws IOException{}
    
    @Override
    public Result insertType(Type bean) throws Exception {
        try {
            if(bean.getId()==0)
                return this.insert(String.format(DB_INSERT, BEAN_TYPE,
                    bean.getAllFieldsNew(), bean.getAllValuesNew()));
            else
                return this.insert(String.format(DB_INSERT, BEAN_TYPE,
                        bean.getAllFields(), bean.getAllValues()));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result insertProvider(Provider bean) throws Exception {
        try {
            if(bean.getId()==0)
                return this.insert(String.format(DB_INSERT, BEAN_PROVIDER,
                    bean.getAllFieldsNew(), bean.getAllValuesNew()));
            else
                return this.insert(String.format(DB_INSERT, BEAN_PROVIDER,
                        bean.getAllFields(), bean.getAllValues()));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result insertItem(Item bean) throws Exception {
        try {
            if(bean.getId()==0)
                return this.insert(String.format(DB_INSERT, BEAN_ITEM,
                    bean.getAllFieldsNew(), bean.getAllValuesNew()));
            else
                return this.insert(String.format(DB_INSERT, BEAN_ITEM,
                        bean.getAllFields(), bean.getAllValues()));
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result insertClient(Client bean) throws Exception {
        try {
            if(bean.getLogin().equals(""))
                return new Result(ERROR,"Login not specified!");
            if(isExist(bean.getClass(), "login='"+bean.getLogin()+"'"))
                return new Result(ERROR,"Login already exist!");
            if(bean.getId()==0)
                return this.insert(String.format(DB_INSERT, BEAN_CLIENT,
                    bean.getAllFieldsNew(), bean.getAllValuesNew()));
            else
                return this.insert(String.format(DB_INSERT, BEAN_CLIENT,
                        bean.getAllFields(), bean.getAllValues()));
        } catch (Exception e) {
            log.error(e);
            return new Result(ERROR,e.toString());
        }
    }
    
    // продать товар покупателю
    @Override
    public Result insertSelling(Selling bean) throws SQLException{
        try {
            ResultSet setC=(select(String.format(DB_SELECT_ID, BEAN_CLIENT, bean.getClient().getId())));
            ResultSet setI=(select(String.format(DB_SELECT_ID, BEAN_ITEM, bean.getItem().getId())));
            if (setC != null && setC.next() && setI != null && setI.next()) {
                if(bean.getId()==0)
                return this.insert(String.format(DB_INSERT, BEAN_SELLING,
                    bean.getAllFieldsNew(), bean.getAllValuesNew()));
                else
                    return this.insert(String.format(DB_INSERT, BEAN_SELLING,
                            bean.getAllFields(), bean.getAllValues()));
            }else{
                log.error("ERROR: Client OR Item NOT FOUND!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }
    
    // заказать товар от поставщика
    @Override
    public Result insertDelivery(Delivery bean){
        try {
            ResultSet setP=(select(String.format(DB_SELECT_ID, BEAN_PROVIDER, bean.getProvider().getId())));
            ResultSet setI=(select(String.format(DB_SELECT_ID, BEAN_ITEM, bean.getItem().getId())));
            if (setP != null && setP.next() && setI != null && setI.next()) {
                if(bean.getId()==0)
                return this.insert(String.format(DB_INSERT, BEAN_DELIVERY,
                    bean.getAllFieldsNew(), bean.getAllValuesNew()));
                else
                    return this.insert(String.format(DB_INSERT, BEAN_DELIVERY,
                            bean.getAllFields(), bean.getAllValues()));
            }else{
                log.error("ERROR: Provider OR Item NOT FOUND!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateType(Type bean) throws Exception {
        try {
            if (getTypeById(bean.getId()) != null) {
                return execute(String.format(DB_UPDATE_ID, BEAN_TYPE, bean.getValuesForUpdate(),bean.getId()));
            } else {
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateProvider(Provider bean) throws Exception {
        try {
            if (getProviderById(bean.getId()) != null) {
                return execute(String.format(DB_UPDATE_ID, BEAN_PROVIDER, bean.getValuesForUpdate(),bean.getId()));
            } else {
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateItem(Item bean) throws Exception {
        try {
            if (getItemById(bean.getId()) != null) {
                return execute(String.format(DB_UPDATE_ID, BEAN_ITEM, bean.getValuesForUpdate(),bean.getId()));
            } else {
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateClient(Client bean) throws Exception {
        try {
            if (getClientById(bean.getId()) != null) {
                return execute(String.format(DB_UPDATE_ID, BEAN_CLIENT, bean.getValuesForUpdate(),bean.getId()));
            } else {
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }
    
    // изменить информацию о покупке
    @Override
    public Result updateSelling(Selling bean) throws Exception {
        try {
            ResultSet setC=(select(String.format(DB_SELECT_ID, BEAN_CLIENT, bean.getClient().getId())));
            ResultSet setI=(select(String.format(DB_SELECT_ID, BEAN_ITEM, bean.getItem().getId())));
            if (setC != null && setC.next() && setI != null && setI.next()) {
                if(getSellingById(bean.getId()) != null)
                    return execute(String.format(DB_UPDATE_ID, BEAN_SELLING, bean.getValuesForUpdate(),bean.getId()));
                else
                    return new Result(ERROR,"Record For Update not found!");
            } else {
                return new Result(ERROR,"Item or Client not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR,"Update Selling is error!");
    }
    
    // изменить информацию о доставке
    @Override
    public Result updateDelivery(Delivery bean) throws Exception {
        try {
            ResultSet setP=(select(String.format(DB_SELECT_ID, BEAN_PROVIDER, bean.getProvider().getId())));
            ResultSet setI=(select(String.format(DB_SELECT_ID, BEAN_ITEM, bean.getItem().getId())));
            if (setP != null && setP.next() && setI != null && setI.next()) {
                if(getDeliveryById(bean.getId()) != null)
                    return execute(String.format(DB_UPDATE_ID, BEAN_DELIVERY, bean.getValuesForUpdate(),bean.getId()));
                else
                    return new Result(ERROR,"Record For Update not found!");
            } else {
                return new Result(ERROR,"Item or Provider not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR,"Update Delivery is error!");
    }

    @Override
    public Result deleteType(long id) throws Exception {
        try {
            if (getTypeById(id)!=null) {
                return execute(String.format(DB_DELETE_ID, BEAN_TYPE, id));
            }else{
                log.error("ERROR: RECORD FOR DELETE NOR FOUND!");
                return new Result(WARNING);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result deleteProvider(long id) throws Exception {
        try {
            if (getProviderById(id)!=null) {
                return execute(String.format(DB_DELETE_ID, BEAN_PROVIDER, id));
            }else{
                log.error("ERROR: RECORD FOR DELETE NOR FOUND!");
                return new Result(WARNING);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result deleteItem(long id) throws Exception {
        try {
            if (getItemById(id)!=null) {
                return execute(String.format(DB_DELETE_ID, BEAN_ITEM, id));
            }else{
                log.error("ERROR: RECORD FOR DELETE NOR FOUND!");
                return new Result(WARNING);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result deleteClient(long id) throws Exception {
        try {
            if (getClientById(id)!=null) {
                return execute(String.format(DB_DELETE_ID, BEAN_CLIENT, id));
            }else{
                log.error("ERROR: RECORD FOR DELETE NOR FOUND!");
                return new Result(WARNING);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }
    
    // удалить покупку
    @Override
    public Result deleteSelling(long id) throws Exception {
        try {
            if (getSellingById(id)!=null) {
                return execute(String.format(DB_DELETE_ID, BEAN_SELLING, id));
            }else{
                log.error("ERROR: RECORD FOR DELETE NOR FOUND!");
                return new Result(WARNING);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }
    
    // удалить доставку
    @Override
    public Result deleteDelivery(long id) throws Exception {
        try {
            if (getDeliveryById(id)!=null) {
                return execute(String.format(DB_DELETE_ID, BEAN_DELIVERY, id));
            }else{
                log.error("ERROR: RECORD FOR DELETE NOR FOUND!");
                return new Result(WARNING);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    /**
     * Метод получения объекта Client по переданному id
     * @param id
     * 
     * @throws Exception
     */
    @Override
    public Client getClientById(long id) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ID, BEAN_CLIENT, id));
            if (set != null && set.next()) {
                return new Client(set.getLong(COLUMNS_CLIENT[0]),set.getString(COLUMNS_CLIENT[1]),set.getString(COLUMNS_CLIENT[2]),set.getString(COLUMNS_CLIENT[3]),set.getShort(COLUMNS_CLIENT[4]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    /**
     * Метод получения объекта Type по переданному id
     * @param id
     * 
     * @throws Exception 
     */
    @Override
    public Type getTypeById(long id) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ID, BEAN_TYPE, id));
            if (set != null && set.next()) {
                return new Type(set.getLong(COLUMNS_TYPE[0]),set.getString(COLUMNS_TYPE[1]),set.getString(COLUMNS_TYPE[2]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     * Метод получения объекта Provider по переданному id
     * @param id
     * 
     * @throws Exception 
     */
    @Override
    public Provider getProviderById(long id) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ID, BEAN_PROVIDER, id));
            if (set != null && set.next()) {
                return new Provider(set.getLong(COLUMNS_PROVIDER[0]),set.getString(COLUMNS_PROVIDER[1]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     * Метод получения объекта Item по переданному id
     * @param id
     * 
     * @throws Exception 
     */
    @Override
    public Item getItemById(long id) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ID, BEAN_ITEM, id));
            if (set != null && set.next()) {
                return new Item(set.getLong(COLUMNS_ITEM[0]),set.getString(COLUMNS_ITEM[1]),set.getString(COLUMNS_ITEM[2]),getTypeById(set.getLong(COLUMNS_ITEM[3])),set.getLong(COLUMNS_ITEM[4]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    
    /**
     * Метод получения объекта Selling по переданному id
     * получить информацию о покупке
     * @param id
     * 
     * @throws Exception 
     */
    @Override
    public Selling getSellingById(long id) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ID, BEAN_SELLING, id));
            if (set != null && set.next()) {
                return new Selling(set.getLong(COLUMNS_SELLING[0]),getItemById(set.getLong(COLUMNS_SELLING[1])),getClientById(set.getLong(COLUMNS_SELLING[2])),set.getLong(COLUMNS_SELLING[3]),set.getDate(COLUMNS_SELLING[4]),set.getLong(COLUMNS_SELLING[5]),set.getShort(COLUMNS_SELLING[6]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    
    /**
     * Метод получения объекта Delivery по переданному id
     * получить информацию о доставке
     * @param id
     * 
     * @throws Exception 
     */
    @Override
    public Delivery getDeliveryById(long id) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ID, BEAN_DELIVERY, id));
            if (set != null && set.next()) {
                return new Delivery(set.getLong(COLUMNS_DELIVERY[0]),getItemById(set.getLong(COLUMNS_DELIVERY[1])),getProviderById(set.getLong(COLUMNS_DELIVERY[2])),set.getDate(COLUMNS_DELIVERY[3]),set.getDate(COLUMNS_DELIVERY[4]),set.getLong(COLUMNS_DELIVERY[5]),set.getShort(COLUMNS_DELIVERY[6]),set.getLong(COLUMNS_DELIVERY[7]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    
    @Override
    public List<Type> getAllTypes() throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ALL, BEAN_TYPE));
            if (set != null && set.next()) {
                List<Type> lt=new ArrayList<>();
                do{
                    lt.add(new Type(set.getLong(COLUMNS_TYPE[0]),set.getString(COLUMNS_TYPE[1]),set.getString(COLUMNS_TYPE[2])));
                }while(set.next());
                return lt;
            }else{
                log.error("Records not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Provider> getAllProviders() throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ALL, BEAN_PROVIDER));
            if (set != null && set.next()) {
                List<Provider> lt=new ArrayList<>();
                do{
                    lt.add(new Provider(set.getLong(COLUMNS_PROVIDER[0]),set.getString(COLUMNS_PROVIDER[1])));
                }while(set.next());
                return lt;
            }else{
                log.error("Records not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Item> getAllItems() throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ALL, BEAN_ITEM));
            if (set != null && set.next()) {
                List<Item> lt=new ArrayList<>();
                do{
                    lt.add(new Item(set.getLong(COLUMNS_ITEM[0]),set.getString(COLUMNS_ITEM[1]),set.getString(COLUMNS_ITEM[2]),getTypeById(set.getLong(COLUMNS_ITEM[3])),set.getLong(COLUMNS_ITEM[4])));
                }while(set.next());
                return lt;
            }else{
                log.error("Records not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Client> getAllClients() throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ALL, BEAN_CLIENT));
            if (set != null && set.next()) {
                List<Client> lt=new ArrayList<>();
                do{
                    lt.add(new Client(set.getLong(COLUMNS_CLIENT[0]),set.getString(COLUMNS_CLIENT[1]),set.getString(COLUMNS_CLIENT[2]),set.getString(COLUMNS_CLIENT[3]),set.getShort(COLUMNS_CLIENT[4])));
                }while(set.next());
                return lt;
            }else{
                log.error("Records not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Selling> getAllSelling() throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ALL, BEAN_SELLING));
            if (set != null && set.next()) {
                List<Selling> lt=new ArrayList<>();
                do{
                    lt.add(new Selling(set.getLong(COLUMNS_SELLING[0]),getItemById(set.getLong(COLUMNS_SELLING[1])),getClientById(set.getLong(COLUMNS_SELLING[2])),set.getLong(COLUMNS_SELLING[3]),set.getDate(COLUMNS_SELLING[4]),set.getLong(COLUMNS_SELLING[5]),set.getShort(COLUMNS_SELLING[6])));
                }while(set.next());
                return lt;
            }else{
                log.error("Records not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    
    @Override
    public List<Selling> getSellingForClient(long client) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_STR, BEAN_SELLING, "\"client\"="+client));
            if (set != null && set.next()) {
                List<Selling> lt=new ArrayList<>();
                do{
                    lt.add(new Selling(set.getLong(COLUMNS_SELLING[0]),getItemById(set.getLong(COLUMNS_SELLING[1])),getClientById(set.getLong(COLUMNS_SELLING[2])),set.getLong(COLUMNS_SELLING[3]),set.getDate(COLUMNS_SELLING[4]),set.getLong(COLUMNS_SELLING[5]),set.getShort(COLUMNS_SELLING[6])));
                }while(set.next());
                return lt;
            }else{
                log.error("Records not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    
    @Override
    public List<Delivery> getAllDeliveries() throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_ALL, BEAN_DELIVERY));
            if (set != null && set.next()) {
                List<Delivery> lt=new ArrayList<>();
                do{
                    lt.add(new Delivery(set.getLong(COLUMNS_DELIVERY[0]),getItemById(set.getLong(COLUMNS_DELIVERY[1])),getProviderById(set.getLong(COLUMNS_DELIVERY[2])),set.getDate(COLUMNS_DELIVERY[3]),set.getDate(COLUMNS_DELIVERY[4]),set.getLong(COLUMNS_DELIVERY[5]),set.getShort(COLUMNS_DELIVERY[6]),set.getLong(COLUMNS_DELIVERY[7])));
                }while(set.next());
                return lt;
            }else{
                log.error("Records not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    
    @Override
    public Client getClientByLogin(String login) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_STR, BEAN_CLIENT, "login="+login));
            if (set != null && set.next()) {
                return new Client(set.getLong(COLUMNS_CLIENT[0]),set.getString(COLUMNS_CLIENT[1]),set.getString(COLUMNS_CLIENT[2]),set.getString(COLUMNS_CLIENT[3]),set.getShort(COLUMNS_CLIENT[4]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
    
    @Override
    public Client signIn(String login, String pass) throws Exception {
        try {
            ResultSet set = select(String.format(DB_SELECT_STR, BEAN_CLIENT, "login='"+login+"' and password='"+pass+"'"));
            if (set != null && set.next()) {
                return new Client(set.getLong(COLUMNS_CLIENT[0]),set.getString(COLUMNS_CLIENT[1]),set.getString(COLUMNS_CLIENT[2]),set.getString(COLUMNS_CLIENT[3]),set.getShort(COLUMNS_CLIENT[4]));
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

//    @Override
    private boolean isExist(Class cl, String where) throws Exception {
        try {
            ResultSet set = select(String.format(DB_COUNT_STR, cl.getSimpleName().toUpperCase(), where));
            if (set != null && set.next()) {
                log.info("Record was found!");
                if(set.getInt(1)>=1){
                    return true;
                }else{
                    return false;
                }
            }else{
                log.error("Record not found!");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }
    
    @Override
    public Result updateSellingStatus(long id, short status) throws Exception {
        try {
            if(getSellingById(id) != null)
                return execute(String.format(DB_UPDATE_ID, BEAN_SELLING, "status="+status,id));
            else
                return new Result(ERROR,"Record For Update not found!");
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR,"Update Selling is error!");
    }

    private Connection connection() throws IOException, ClassNotFoundException, SQLException {
        Class.forName(getConfigurationEntry(DB_DRIVER));
        return DriverManager.getConnection(
                getConfigurationEntry(DB_URL),
                getConfigurationEntry(DB_USER),
                getConfigurationEntry(DB_PASS));
    }
    
    private Result execute(String sql) {
        try {
            log.info(sql);
            PreparedStatement statement = connection().prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            return new Result(OK,"All success"+sql);
        } catch (Exception e) {
            log.error(e);
            return new Result(ERROR,e.toString()+" "+sql);
        }
    }
    
    private Result insert(String sql) {
        try {
            log.info(sql);
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            long id=0;
            if (set != null && set.next()) {
                if(set.getLong(1)>=1){
                    id=set.getLong(1);
                }
            }
            statement.close();
            return new Result(OK,"All success",id);
        } catch (Exception e) {
            log.error(e);
            return new Result(ERROR,e.toString()+" "+sql);
        }
    }

    public ResultSet select(String sql){
        try {
            log.info(sql);
            PreparedStatement statement = connection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            connection().close();
            return set;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            log.error(e);
        }
        return null;
    }
}
