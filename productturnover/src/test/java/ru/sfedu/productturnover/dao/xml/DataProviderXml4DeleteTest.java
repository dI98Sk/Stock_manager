/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover.dao.xml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sfedu.productturnover.api.DataProviderXml;
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
public class DataProviderXml4DeleteTest {
    /**
     * Test of insert method, of class DataProviderXml.
     */
    @Test
    public void test1DeleteClient() throws Exception{
        System.out.println("Delete Client");
        DataProviderXml instance = new DataProviderXml();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteClient(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2DeleteType() throws Exception{
        System.out.println("Delete Type");
        DataProviderXml instance = new DataProviderXml();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteType(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test3DeleteItem() throws Exception{
        System.out.println("Delete Item");
        DataProviderXml instance = new DataProviderXml();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteItem(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test4DeleteProvider() throws Exception{
        System.out.println("Delete Provider");
        DataProviderXml instance = new DataProviderXml();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteProvider(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test5DeleteDelivery() throws Exception{
        System.out.println("Delete Delivery");
        DataProviderXml instance = new DataProviderXml();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteDelivery(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test6DeleteSelling() throws Exception{
        System.out.println("Delete Selling");
        DataProviderXml instance = new DataProviderXml();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteSelling(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
}
