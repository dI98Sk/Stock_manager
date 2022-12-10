/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover.dao.xml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ru.sfedu.productturnover.api.DataProviderXml;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataProviderXml1InsertTest {
    
    /**
     * Test of insert method, of class DataProviderXml.
     */
    @Test
    public void testInsert1Client() throws Exception {
        System.out.println("insert Client");
        List<Client> list = new ArrayList<>();
        DataProviderXml instance = new DataProviderXml();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.insertClient(new Client(500,"ClientName","Log","Pass",(short)1));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsert2Type() throws Exception {
        System.out.println("insert Type");
        DataProviderXml instance = new DataProviderXml();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.insertType(new Type(500,"Type ","TypeDesc "));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsert3Item() throws Exception {
        System.out.println("insert Item");
        DataProviderXml instance = new DataProviderXml();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.insertItem(new Item(500,"ItemName ","Desc",new Type(500,"T","TT"),0));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsert4Provider() throws Exception {
        System.out.println("insert Provider");
        DataProviderXml instance = new DataProviderXml();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.insertProvider(new Provider(500,"ProviderName"));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsert5Delivery() throws Exception {
        System.out.println("insert Delivery");
        DataProviderXml instance = new DataProviderXml();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.insertDelivery(new Delivery(500,new Item(500,"ItemName ","Desc",new Type(500,"T","TT"),0),new Provider(500,"ProviderName "),new Date(),new Date(),11,(short)1,11*13));
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsert6Selling() throws Exception {
        System.out.println("insert Selling");
        DataProviderXml instance = new DataProviderXml();
        Result expResult = new Result(StatusType.OK);
        Result result = instance.insertSelling(new Selling(500,new Item(500,"ItemName ","Desc",new Type(500,"T","TT"),0),new Client(500,"ClientName","Log","Pass",(short)1),1*3,new Date(),3+41,(short)0));
        assertEquals(expResult, result);
    }
}
