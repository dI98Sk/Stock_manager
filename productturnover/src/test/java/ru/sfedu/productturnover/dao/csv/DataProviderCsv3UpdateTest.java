/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover.dao.csv;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import ru.sfedu.productturnover.api.DataProviderCsv;
import static org.junit.Assert.*;
import ru.sfedu.productturnover.constant.Result;
import ru.sfedu.productturnover.constant.StatusType;
import ru.sfedu.productturnover.model.Type;
import ru.sfedu.productturnover.model.Provider;
import ru.sfedu.productturnover.model.Item;
import ru.sfedu.productturnover.model.Client;
import ru.sfedu.productturnover.model.Delivery;
import ru.sfedu.productturnover.model.Selling;

/**
 *
 * @author Дмитрий
 */
public class DataProviderCsv3UpdateTest {
    @Test
    public void testUpdate1Client() throws Exception {
        System.out.println("NEWpdate Client");
        List<Client> list = new ArrayList<>();
        DataProviderCsv instance = new DataProviderCsv();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.updateClient(new Client(500,"NEWlientName","Log","Pass",(short)1));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdate2Type() throws Exception {
        System.out.println("NEWpdate Type");
        DataProviderCsv instance = new DataProviderCsv();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.updateType(new Type(500,"NEWype ","NEWypeDesc "));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdate3Item() throws Exception {
        System.out.println("NEWpdate Item");
        DataProviderCsv instance = new DataProviderCsv();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.updateItem(new Item(500,"NEWtemName ","NEWesc",new Type(500,"NEW","NEWT"),0));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdate4Provider() throws Exception {
        System.out.println("NEWpdate Provider");
        DataProviderCsv instance = new DataProviderCsv();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.updateProvider(new Provider(500,"NEWroviderName"));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdate5Delivery() throws Exception {
        System.out.println("NEWpdate Delivery");
        DataProviderCsv instance = new DataProviderCsv();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.updateDelivery(new Delivery(500,new Item(500,"NEWtemName ","NEWesc",new Type(500,"NEW","NEWT"),0),new Provider(500,"NEWroviderName "),new Date(),new Date(),11,(short)1,11*13));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdate6Selling() throws Exception {
        System.out.println("NEWpdate Selling");
        DataProviderCsv instance = new DataProviderCsv();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.updateSelling(new Selling(500,new Item(500,"NEWtemName ","NEWesc",new Type(500,"NEW","NEWT"),0),new Client(500,"NEWlientName","Log","Pass",(short)1),1*3,new Date(),3+41,(short)0));
        assertEquals(expResult, result);
    }
}