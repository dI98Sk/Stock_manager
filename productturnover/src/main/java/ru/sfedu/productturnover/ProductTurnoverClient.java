/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover;

import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static ru.sfedu.productturnover.Constants.*;
import ru.sfedu.productturnover.api.DataProviderCsv;
import ru.sfedu.productturnover.api.DataProviderDB;
import ru.sfedu.productturnover.api.DataProviderXml;
import ru.sfedu.productturnover.api.IDataProvider;
import ru.sfedu.productturnover.constant.Result;
import static ru.sfedu.productturnover.constant.StatusType.ERROR;
import ru.sfedu.productturnover.model.Type;
import ru.sfedu.productturnover.model.Provider;
import ru.sfedu.productturnover.model.Item;
import ru.sfedu.productturnover.model.Client;
import ru.sfedu.productturnover.model.Delivery;
import ru.sfedu.productturnover.model.Selling;

/**
 *
 * @author
 */
public class ProductTurnoverClient {
    // Подключение логгера
    private static Logger log = LogManager.getLogger(ProductTurnoverClient.class);
    public static String CONFIG_PATH = System.getProperty(CONFIG_KEY, DEFAULT_CONFIG_PATH);
    
    ProductTurnoverClient(){
        log.debug("ProductTurnoverClient[0]: starting application.........");
    }
    
    public static void main(String []args) throws IOException, Exception{
        cli(args);
    }
    
    public static void cli(String []query) throws IOException, Exception{
        if(query.length<4){
            log.error("incorrect query");
        }else{
            IDataProvider provider=initDataProvider(query[0]);
            assert provider != null;
            log.info(getAnswer(provider,query));
        }
    }
    
    public static IDataProvider initDataProvider(String param) throws IOException{
        switch(param.toLowerCase()){
            case CLI_CSV: return new DataProviderCsv();
            case CLI_XML: return new DataProviderXml();
            case CLI_DB: return new DataProviderDB();
            default: return null;
        }
    }
    
    private static String getAnswer(IDataProvider provider, String[] query) throws Exception{
        switch(query[1].toUpperCase()){
            case BEAN_CLIENT: return client(provider,query);
            case BEAN_DELIVERY: return delivery(provider,query);
            case BEAN_ITEM: return item(provider,query);
            case BEAN_TYPE: return type(provider, query);
            case BEAN_PROVIDER: return provider(provider, query);
            case BEAN_SELLING: return selling(provider, query);
            default: return new Result(ERROR,"incorrect query in getAnswer!").toString();
        }
    }
    
    private static String client(IDataProvider provider, String[] query) throws InterruptedException, Exception{
        switch(query[2].toLowerCase()){
            case CLI_SELECT :
                return provider.getClientById(Long.parseLong(query[3])).toString();
            case CLI_INSERT :
                if(query.length<8) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.insertClient(new Client(Long.decode(query[3]),query[4],query[5],query[6],Short.decode(query[7]))).toString();
            case CLI_UPDATE :
                if(query.length<8) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.updateClient(new Client(Long.decode(query[3]),query[4],query[5],query[6],Short.decode(query[7]))).toString();
            case CLI_DELETE :
                return provider.deleteClient(Long.decode(query[3])).toString();
            default :        
                return new Result(ERROR,"incorrect query for Client!").toString();
        }
    }
    
    private static String delivery(IDataProvider provider, String[] query) throws InterruptedException, Exception{
        switch(query[2].toLowerCase()){
            case CLI_SELECT :
                return provider.getDeliveryById(Long.parseLong(query[3])).toString();
            case CLI_INSERT :
                if(query.length<11) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.insertDelivery(new Delivery(Long.decode(query[3]),provider.getItemById(Long.decode(query[4])),provider.getProviderById(Long.decode(query[5])),new SimpleDateFormat("dd.MM.yyyy").parse(query[6]),new SimpleDateFormat("dd.MM.yyyy").parse(query[7]),Long.decode(query[8]),Short.decode(query[9]),Long.decode(query[10]))).toString();
            case CLI_UPDATE :
                if(query.length<11) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.updateDelivery(new Delivery(Long.decode(query[3]),provider.getItemById(Long.decode(query[4])),provider.getProviderById(Long.decode(query[5])),new SimpleDateFormat("dd.MM.yyyy").parse(query[6]),new SimpleDateFormat("dd.MM.yyyy").parse(query[7]),Long.decode(query[8]),Short.decode(query[9]),Long.decode(query[10]))).toString();
            case CLI_DELETE :
                return provider.deleteDelivery(Long.decode(query[3])).toString();
            default :        
                return new Result(ERROR,"incorrect query for Delivery!").toString();
        }
    }
    
    private static String item(IDataProvider provider, String[] query) throws InterruptedException, Exception{
        switch(query[2].toLowerCase()){
            case CLI_SELECT :
                return provider.getItemById(Long.parseLong(query[3])).toString();
            case CLI_INSERT :
                if(query.length<7) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.insertItem(new Item(Long.decode(query[3]),query[4],query[5],provider.getTypeById(Long.decode(query[6])),Long.decode(query[7]))).toString();
            case CLI_UPDATE :
                if(query.length<7) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.updateItem(new Item(Long.decode(query[3]),query[4],query[5],provider.getTypeById(Long.decode(query[6])),Long.decode(query[7]))).toString();
            case CLI_DELETE :
                return provider.deleteItem(Long.decode(query[3])).toString();
            default :        
                return new Result(ERROR,"incorrect query for Item!").toString();
        }
    }
    
    private static String type(IDataProvider provider, String[] query) throws InterruptedException, Exception{
        switch(query[2].toLowerCase()){
            case CLI_SELECT :
                return provider.getTypeById(Long.parseLong(query[3])).toString();
            case CLI_INSERT :
                if(query.length<6) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.insertType(new Type(Long.decode(query[3]),query[4],query[5])).toString();
            case CLI_UPDATE :
                if(query.length<6) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.updateType(new Type(Long.decode(query[3]),query[4],query[5])).toString();
            case CLI_DELETE :
                return provider.deleteType(Long.decode(query[3])).toString();
            default :        
                return new Result(ERROR,"incorrect query for Type!").toString();
        }
    }
    
    private static String provider(IDataProvider provider, String[] query) throws InterruptedException, Exception{
        switch(query[2].toLowerCase()){
            case CLI_SELECT :
                return provider.getProviderById(Long.parseLong(query[3])).toString();
            case CLI_INSERT :
                if(query.length<5) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.insertProvider(new Provider(Long.decode(query[3]),query[4])).toString();
            case CLI_UPDATE :
                if(query.length<5) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.updateProvider(new Provider(Long.decode(query[3]),query[4])).toString();
            case CLI_DELETE :
                return provider.deleteProvider(Long.decode(query[3])).toString();
            default :        
                return new Result(ERROR,"incorrect query for Provider!").toString();
        }
    }
    
    private static String selling(IDataProvider provider, String[] query) throws InterruptedException, Exception{
        switch(query[2].toLowerCase()){
            case CLI_SELECT :
                return provider.getSellingById(Long.parseLong(query[3])).toString();
            case CLI_INSERT :
                if(query.length<10) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.insertSelling(new Selling(Long.decode(query[3]),provider.getItemById(Long.decode(query[4])),provider.getClientById(Long.decode(query[5])),Long.decode(query[6]),new SimpleDateFormat("dd.MM.yyyy").parse(query[7]),Long.decode(query[8]),Short.decode(query[9]))).toString();
            case CLI_UPDATE :
                if(query.length<10) return new Result(ERROR,"TOO SMALL ARGUMENTS").toString();
                return provider.updateSelling(new Selling(Long.decode(query[3]),provider.getItemById(Long.decode(query[4])),provider.getClientById(Long.decode(query[5])),Long.decode(query[6]),new SimpleDateFormat("dd.MM.yyyy").parse(query[7]),Long.decode(query[8]),Short.decode(query[9]))).toString();
            case CLI_DELETE :
                return provider.deleteSelling(Long.decode(query[3])).toString();
            default :        
                return new Result(ERROR,"incorrect query for Selling!").toString();
        }
    }
}
