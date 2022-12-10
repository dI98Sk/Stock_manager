package ru.sfedu.productturnover.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;
import java.util.StringJoiner;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.productturnover.model.converter.ClientConverter;
import ru.sfedu.productturnover.model.converter.ItemConverter;

/**
 * Class Selling
 */
/**
 * Связывающий класс клиентов и товаров со всеми геттерами и сеттерами
 * @author Дмитрий
 */
@Root
public class Selling{

  //
  // Fields
  //
  @Attribute // - аннотация, дающая команду записывать данное поле в аттрибут Root'а
  @CsvBindByName
  long id; 
    
  @Element
  @CsvCustomBindByName(converter=ItemConverter.class)
  private Item item;
  
  @Element
  @CsvCustomBindByName(converter=ClientConverter.class)
  private Client client;
  
  @Element
  @CsvBindByName
  private long number;
  
  @Element
  @CsvBindByName
  @CsvDate("dd.MM.yyyy")
  private Date date;
  
  @Element
  @CsvBindByName
  private long price;
  
  @Element
  @CsvBindByName
  private short status;
  //
  // Constructors
  //
  
  public Selling(){}
  
  /**
   * 
   * @param id
   * @param user
   * @param sport
   * @param date
   * @param result
   * @throws InterruptedException 
   */
  public Selling(long id, Item item, Client client, long number, Date date, long price, short status) throws InterruptedException {
      this.id=id;
      this.item=item;
      this.client=client;
      this.number=number;
      this.date=date;
      this.price=price;
      this.status=status;
  }
  
  public Selling(Item item, Client client, long number, Date date, long price, short status) throws InterruptedException {
      this.item=item;
      this.client=client;
      this.number=number;
      this.date=date;
      this.price=price;
      this.status=status;
  }
  
  public Selling(Selling obj) throws InterruptedException {
      this.id=obj.getId();
      this.item=obj.getItem();
      this.client=obj.getClient();
      this.number=obj.getNumber();
      this.date=obj.getDate();
      this.price=obj.getPrice();
      this.status=obj.getStatus();
  }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public boolean equals(Selling obj){
        if(obj.getItem()==null || obj.getClient()==null) return false;
        return obj.getItem().equals(this.item)&&obj.getClient().equals(this.client)&&(obj.getNumber()==this.number)&&obj.getDate().equals(this.date)&&(obj.getPrice()==this.price);
    }
    
    public String stringifyStatus(){
        switch(this.status){
            case 1:
                return "Waiting";
            case 2:
                return "Received";
            default:
                return "Canceled";
        }
    }
    
  //
  // Other methods
  //
  /**
   * 
   *  
   */
  public String toString(){
      return new StringJoiner(", ", Selling.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("item=" + this.item)
                .add("client=" + this.client)
                .add("number=" + this.number)
                .add("date=" + this.date)
                .add("price=" + this.price)
                .add("status=" + this.status)
                .toString();
  }
  
  public String getAllFields(){
      return new StringJoiner(", ")
                .add("id")
                .add("item")
                .add("client")
                .add("number")
                .add("date")
                .add("price")
                .add("status")
                .toString();
  }
  
  public String getAllValues(){
      return new StringJoiner(", ")
                .add("'" + this.id+"'")
                .add("'" + this.item.getId()+"'")
                .add("'" + this.client.getId()+"'")
                .add("'" + this.number+"'")
                .add("'" + this.date+"'")
                .add("'" + this.price+"'")
                .add("'" + this.status+"'")
                .toString();
  }
  
  public String getAllFieldsNew(){
      return new StringJoiner(", ")
                .add("item")
                .add("client")
                .add("number")
                .add("date")
                .add("price")
                .add("status")
                .toString();
  }
  
  public String getAllValuesNew(){
      return new StringJoiner(", ")
                .add("'" + this.item.getId()+"'")
                .add("'" + this.client.getId()+"'")
                .add("'" + this.number+"'")
                .add("'" + this.date+"'")
                .add("'" + this.price+"'")
                .add("'" + this.status+"'")
                .toString();
  }
  
  public String getValuesForUpdate(){
      return new StringJoiner(", ")
                .add("\"item\"='" + this.item.getId()+"'")
                .add("\"client\"='" + this.client.getId()+"'")
                .add("\"number\"='" + this.number+"'")
                .add("\"date\"='" + this.date+"'")
                .add("\"price\"='" + this.price+"'")
                .add("\"status\"='" + this.status+"'")
                .toString();
  }
}
