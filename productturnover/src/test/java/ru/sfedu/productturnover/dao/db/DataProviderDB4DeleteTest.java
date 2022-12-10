/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover.dao.db;

import static org.junit.Assert.*;
import org.junit.Test;
import ru.sfedu.productturnover.api.DataProviderDB;
import ru.sfedu.productturnover.constant.Result;
import ru.sfedu.productturnover.constant.StatusType;

/**
 *
 * @author Дмитрий
 */
public class DataProviderDB4DeleteTest {
    /**
     * Test of insert method, of class DataProviderDB.
     */
    //@Test
    public void test1DeleteClient() throws Exception{
        System.out.println("Delete Client");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteClient(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
//    @Test
    public void test2DeleteType() throws Exception{
        System.out.println("Delete Type");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteType(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    //@Test
    public void test3DeleteItem() throws Exception{
        System.out.println("Delete Item");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteItem(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    //@Test
    public void test4DeleteProvider() throws Exception{
        System.out.println("Delete Provider");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteProvider(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    //@Test
    public void test5DeleteDelivery() throws Exception{
        System.out.println("Delete Delivery");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteDelivery(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    //@Test
    public void test6DeleteSelling() throws Exception{
        System.out.println("Delete Selling");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.deleteSelling(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
}
