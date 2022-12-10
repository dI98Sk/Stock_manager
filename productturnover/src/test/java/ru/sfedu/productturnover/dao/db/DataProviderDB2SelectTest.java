/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover.dao.db;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.productturnover.api.DataProviderDB;
import ru.sfedu.productturnover.constant.Result;
import ru.sfedu.productturnover.constant.StatusType;

/**
 *
 * @author Дмитрий
 */
public class DataProviderDB2SelectTest {
    //@Test
    public void testGetClientById() throws Exception{
        System.out.println("Get Client By Id");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.getClientById(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
//    @Test
    public void testGetItemById() throws Exception{
        System.out.println("Get Item By Id");
        DataProviderDB instance = new DataProviderDB();
        
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.getItemById(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
//    @Test
    public void testGetTypeById() throws Exception{
        System.out.println("Get Type By Id");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.getTypeById(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    //@Test
    public void testGetProviderById() throws Exception{
        System.out.println("Get Provider By Id");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.getProviderById(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    //@Test
    public void testGetDeliveryById() throws Exception{
        System.out.println("Get Delivery By Id");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.getDeliveryById(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
    
    //@Test
    public void testGetSellingById() throws Exception{
        System.out.println("Get Selling By Id");
        DataProviderDB instance = new DataProviderDB();
        Result expResult=new Result(StatusType.OK);
        Result result=new Result(StatusType.OK);
        if(instance.getSellingById(500)==null)
            result.setStatus(StatusType.ERROR);
        assertEquals(expResult, result);
    }
}
