package ru.sfedu.productturnover.api;
import ru.sfedu.productturnover.constant.ClassType;
import ru.sfedu.productturnover.constant.Result;
import java.util.List;
import java.util.Optional;
import ru.sfedu.productturnover.model.Client;
import ru.sfedu.productturnover.model.Delivery;
import ru.sfedu.productturnover.model.Item;
import ru.sfedu.productturnover.model.Provider;
import ru.sfedu.productturnover.model.Selling;
import ru.sfedu.productturnover.model.Type;
/**
 * Интерфейс, реализуемый DataProviders
 * @author Дмитрий
 */
public interface IDataProvider{
    
    //INSERT METHODS
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result insertType(Type bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result insertProvider(Provider bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result insertItem(Item bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result insertClient(Client bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result insertSelling(Selling bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result insertDelivery(Delivery bean) throws Exception;
    
    // UPDATE METHODS
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result updateType(Type bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result updateProvider(Provider bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result updateItem(Item bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result updateClient(Client bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result updateSelling(Selling bean) throws Exception;
    
    /**
     * 
     * @param bean
     * @return
     * @throws Exception 
     */
    public Result updateDelivery(Delivery bean) throws Exception;
    
    //SELECT METHODS
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Type getTypeById(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Provider getProviderById(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Item getItemById(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Client getClientById(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Selling getSellingById(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Delivery getDeliveryById(long id) throws Exception;
    
    public List<Type> getAllTypes() throws Exception;
    
    public List<Provider> getAllProviders() throws Exception;
    
    public List<Item> getAllItems() throws Exception;
    
    public List<Client> getAllClients() throws Exception;
    
    public List<Selling> getAllSelling() throws Exception;
    
    public List<Delivery> getAllDeliveries() throws Exception;
    
    public List<Selling> getSellingForClient(long client) throws Exception;
    
    public Result updateSellingStatus(long id, short status) throws Exception;
    
    //Delete methods
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Result deleteType(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Result deleteProvider(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Result deleteItem(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Result deleteClient(long id) throws Exception;
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Result deleteSelling(long id) throws Exception;

    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Result deleteDelivery(long id) throws Exception;
    
    
    /**
     * FOR DIPLOMA
     */
    
    /**
     * 
     * @param login
     * @return
     * @throws Exception 
     */
    public Client getClientByLogin(String login) throws Exception;
    
    /**
     *
     * @param cl
     * @param where
     * @return
     * @throws Exception
     */
//    private boolean isExist(Class cl, String where) throws Exception;
    
    /**
     * 
     * @param login
     * @param pass
     * @return
     * @throws Exception 
     */
    public Client signIn(String login, String pass) throws Exception;
}