package ru.sfedu.productturnover.api;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static ru.sfedu.productturnover.Constants.BEAN_CLIENT;
import static ru.sfedu.productturnover.Constants.BEAN_ITEM;
import static ru.sfedu.productturnover.Constants.BEAN_PROVIDER;
import static ru.sfedu.productturnover.Constants.BEAN_TYPE;
import static ru.sfedu.productturnover.Constants.CLI_CSV;
import static ru.sfedu.productturnover.Constants.PATH_CSV_STORE;

import static ru.sfedu.productturnover.constant.StatusType.ERROR;
import static ru.sfedu.productturnover.constant.StatusType.OK;
import static ru.sfedu.productturnover.constant.StatusType.WARNING;
import ru.sfedu.productturnover.constant.Result;
import ru.sfedu.productturnover.model.Selling;
import ru.sfedu.productturnover.model.Delivery;
import ru.sfedu.productturnover.model.Client;
import ru.sfedu.productturnover.model.Item;
import ru.sfedu.productturnover.model.Provider;
import ru.sfedu.productturnover.model.Type;
import static ru.sfedu.productturnover.utils.ConfigurationUtil.getConfigurationEntry;
import static ru.sfedu.productturnover.Constants.BEAN_DELIVERY;
import static ru.sfedu.productturnover.Constants.BEAN_SELLING;




/**
 *
 * @author Дмитрий
 * Класс, предназначенный для работы с csv-файлами
 */
public class DataProviderCsv implements IDataProvider{
    private static final Logger log = LogManager.getLogger(DataProviderCsv.class);
    /**
     * заглушка на конструктор по-умолчанию
     */
    public DataProviderCsv(){}
    
    @Override
    public Result insertType(Type bean) throws Exception {
        try {
            if (getTypeById(bean.getId()) == null) {
                return insert(bean, BEAN_TYPE);
            }else{
                log.error("ERROR: record already added");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result insertProvider(Provider bean) throws Exception {
        try {
            if (getProviderById(bean.getId()) == null) {
                return insert(bean, BEAN_PROVIDER);
            }else{
                log.error("ERROR: record already added");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result insertItem(Item bean) throws Exception {
        try {
            if (getItemById(bean.getId()) == null) {
                return insert(bean, BEAN_ITEM);
            }else{
                log.error("ERROR: record already added");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result insertClient(Client bean) throws Exception {
        try {
            if (getClientById(bean.getId()) == null) {
                return insert(bean, BEAN_CLIENT);
            }else{
                log.error("ERROR: record already added");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }
    
    // продать товар покупателю
    @Override
    public Result insertSelling(Selling bean){
        try {
            if (getSellingById(bean.getId()) == null) {
                List<Client> lc=this.select(Client.class, BEAN_CLIENT);
                List<Item> li=this.select(Item.class, BEAN_ITEM);
                if(!lc.isEmpty() && !li.isEmpty() && lc.stream().anyMatch(el -> el.getId()==bean.getClient().getId()) && li.stream().anyMatch(el -> el.getId()==bean.getItem().getId())){
                    return insert(bean, BEAN_SELLING);
                }else{
                    log.error("ERROR: Client OR Item NOT FOUND!");
                }
            }else{
                log.error("ERROR: record already added");
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
            if (getDeliveryById(bean.getId()) == null) {
                List<Provider> lp=this.select(Provider.class, BEAN_PROVIDER);
                List<Item> li=this.select(Item.class, BEAN_ITEM);
                if(!lp.isEmpty() && !li.isEmpty() && lp.stream().anyMatch(el -> el.getId()==bean.getProvider().getId()) && li.stream().anyMatch(el -> el.getId()==bean.getItem().getId())){
                    return insert(bean, BEAN_DELIVERY);
                }else{
                    log.error("ERROR: Provider OR Item NOT FOUND!");
                }
            }else{
                log.error("ERROR: record already added");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateType(Type bean) throws Exception {
        List<Type> bl = select(Type.class, BEAN_TYPE);
        try {
            if (getTypeById(bean.getId()) != null) {
                bl.removeIf(el -> el.getId() == bean.getId());
                bl.add(bean);
                return update(bl,BEAN_TYPE);
            }else{
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
                return new Result(ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateProvider(Provider bean) throws Exception {
        List<Provider> bl = select(Provider.class, BEAN_PROVIDER);
        try {
            if (getProviderById(bean.getId()) != null) {
                bl.removeIf(el -> el.getId() == bean.getId());
                bl.add(bean);
                return update(bl,BEAN_PROVIDER);
            }else{
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
                return new Result(ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateItem(Item bean) throws Exception {
        List<Item> bl = select(Item.class, BEAN_ITEM);
        try {
            if (getItemById(bean.getId()) != null) {
                bl.removeIf(el -> el.getId() == bean.getId());
                bl.add(bean);
                return update(bl,BEAN_ITEM);
            }else{
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
                return new Result(ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result updateClient(Client bean) throws Exception {
        List<Client> bl = select(Client.class, BEAN_CLIENT);
        try {
            if (getClientById(bean.getId()) != null) {
                bl.removeIf(el -> el.getId() == bean.getId());
                bl.add(bean);
                return update(bl,BEAN_CLIENT);
            }else{
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
                return new Result(ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }
    
    // изменить информацию о покупке
    @Override
    public Result updateSelling(Selling bean) throws Exception {
        List<Selling> bl = select(Selling.class, BEAN_SELLING);
        try {
            if (getSellingById(bean.getId()) != null) {
                List<Client> lc=this.select(Client.class, BEAN_CLIENT);
                List<Item> li=this.select(Item.class, BEAN_ITEM);
                if(!lc.isEmpty() && !li.isEmpty() && lc.stream().anyMatch(el -> el.getId()==bean.getClient().getId()) && li.stream().anyMatch(el -> el.getId()==bean.getItem().getId())){
                    bl.removeIf(el -> el.getId() == bean.getId());
                    bl.add(bean);
                    return update(bl,BEAN_SELLING);
                }else{
                    log.error("ERROR: Client OR Item NOT FOUND!");
                }
            }else{
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
                return new Result(ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }
    
    // изменить информацию о доставке
    @Override
    public Result updateDelivery(Delivery bean) throws Exception {
        List<Delivery> bl = select(Delivery.class, BEAN_DELIVERY);
        try {
            if (getDeliveryById(bean.getId()) != null) {
                List<Provider> lp=this.select(Provider.class, BEAN_PROVIDER);
                List<Item> li=this.select(Item.class, BEAN_ITEM);
                if(!lp.isEmpty() && !li.isEmpty() && lp.stream().anyMatch(el -> el.getId()==bean.getProvider().getId()) && li.stream().anyMatch(el -> el.getId()==bean.getItem().getId())){
                    bl.removeIf(el -> el.getId() == bean.getId());
                    bl.add(bean);
                    return update(bl,BEAN_DELIVERY);
                }else{
                    log.error("ERROR: Provider OR Item NOT FOUND!");
                }
            }else{
                log.error("ERROR: RECORD FOR UPDATE NOR FOUND!");
                return new Result(ERROR);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new Result(ERROR);
    }

    @Override
    public Result deleteType(long id) throws Exception {
        List<Type> bl = select(Type.class, BEAN_TYPE);
        try {
            if (getTypeById(id) != null) {
                bl.removeIf(el -> el.getId() == id);
                return update(bl,BEAN_TYPE);
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
        List<Provider> bl = select(Provider.class, BEAN_PROVIDER);
        try {
            if (getProviderById(id) != null) {
                bl.removeIf(el -> el.getId() == id);
                return update(bl,BEAN_PROVIDER);
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
        List<Item> bl = select(Item.class, BEAN_ITEM);
        try {
            if (getItemById(id) != null) {
                bl.removeIf(el -> el.getId() == id);
                return update(bl,BEAN_ITEM);
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
        List<Client> bl = select(Client.class, BEAN_CLIENT);
        try {
            if (getClientById(id) != null) {
                bl.removeIf(el -> el.getId() == id);
                return update(bl,BEAN_CLIENT);
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
        List<Selling> bl = select(Selling.class, BEAN_SELLING);
        try {
            if (getSellingById(id) != null) {
                bl.removeIf(el -> el.getId() == id);
                return update(bl,BEAN_SELLING);
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
        List<Delivery> bl = select(Delivery.class, BEAN_DELIVERY);
        try {
            if (getDeliveryById(id) != null) {
                bl.removeIf(el -> el.getId() == id);
                return update(bl,BEAN_DELIVERY);
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
        try{
            List<Client> lg=this.select(Client.class, BEAN_CLIENT);
            return lg.stream()
                    .filter(el->el.getId()==id)
                    .limit(1)
                    .findFirst().get();
        }catch(Exception e){
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
        try{
            List<Type> lg=this.select(Type.class, BEAN_TYPE);
            return lg.stream()
                    .filter(el->el.getId()==id)
                    .limit(1)
                    .findFirst().get();
        }catch(Exception e){
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
        try{
            List<Provider> lg=this.select(Provider.class, BEAN_PROVIDER);
            return lg.stream()
                    .filter(el->el.getId()==id)
                    .limit(1)
                    .findFirst().get();
        }catch(Exception e){
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
        try{
            List<Item> lg=this.select(Item.class, BEAN_ITEM);
            return lg.stream()
                    .filter(el->el.getId()==id)
                    .limit(1)
                    .findFirst().get();
        }catch(Exception e){
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
        try{
            List<Selling> lg=this.select(Selling.class, BEAN_SELLING);
            return lg.stream()
                    .filter(el->el.getId()==id)
                    .limit(1)
                    .findFirst().get();
        }catch(Exception e){
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
        try{
            List<Delivery> lg=this.select(Delivery.class, BEAN_DELIVERY);
            return lg.stream()
                    .filter(el->el.getId()==id)
                    .limit(1)
                    .findFirst().get();
        }catch(Exception e){
            log.error(e);
        }
        return null;
    }

    /**
     * Получение необходимого файла для требуемой модели
     * @param type
     * 
     * @throws Exception 
     */
    private String getFile(String type) throws Exception{
        String file=getConfigurationEntry(PATH_CSV_STORE);
        file+=type+"."+CLI_CSV.toLowerCase();
        return file;
    }
    
    public <T> List<T> select(Class cl, String key) throws Exception {
        try {
            FileReader fileReader = new FileReader(this.getFile(key));
            CSVReader csvReader = new CSVReader(fileReader);
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                    .withType(cl)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<T> list = csvToBean.parse();
            return (List<T>) list;
        }catch(Exception e){
            log.error(e);
            return Collections.emptyList();
        }
    }
    
    public <T> Result insert(T bean, String key) throws Exception{
        List<T> list = select(bean.getClass(),key);
        try{
            list.add(bean);
            return update(list,key);
            
        }catch(Exception e){
            log.error(e);
            return new Result(ERROR);
        }
        
    }
    
    private <T> Result update(List<T> beans, String key){
        try {
            FileWriter file_path = new FileWriter(this.getFile(key), false);
            CSVWriter writer = new CSVWriter(file_path);
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(beans);
            writer.close();
            return new Result(OK);
        } catch (Exception error) {
            log.error(error);
            return new Result(ERROR);
        }
    }

    @Override
    public Client getClientByLogin(String login) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public boolean isExist(Class cl, String where) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public Client signIn(String login, String pass) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Type> getAllTypes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Provider> getAllProviders() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Client> getAllClients() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Selling> getAllSelling() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Delivery> getAllDeliveries() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Selling> getSellingForClient(long user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result updateSellingStatus(long id, short status) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}