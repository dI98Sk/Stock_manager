package ru.sfedu.productturnover.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;
import java.util.StringJoiner;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.productturnover.model.converter.ItemConverter;
import ru.sfedu.productturnover.model.converter.ProviderConverter;

/**
 * Class Delivery
 */
/**
 * Связывающий класс поставщиков и товаров со всеми геттерами и сеттерами
 * @author Дмитрий
 */
@Root
public class Delivery{

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
  @CsvCustomBindByName(converter=ProviderConverter.class)
  private Provider provider;
  
  @Element
  @CsvBindByName
  @CsvDate("dd.MM.yyyy") //-формат даты, в котором будет записывать элемент в csv-файл
  private Date datestart;
  
  @Element
  @CsvBindByName
  @CsvDate("dd.MM.yyyy")
  private Date dateend;
  
  @Element
  @CsvBindByName
  private long number;
  
  @Element
  @CsvBindByName
  private short status;
  
  @Element
  @CsvBindByName
  private long price;
  //
  // Constructors
  //
  
  public Delivery(){}
  
  /**
   * 
   * @param id
   * @param user
   * @param city
   * @param datestart
   * @param dateend
   * @param result
   * @throws InterruptedException 
   */
  public Delivery(long id, Item item, Provider provider, Date datestart, Date dateend, long number, short status, long price) throws InterruptedException {
      this.id=id;
      this.item=item;
      this.provider=provider;
      this.datestart=datestart;
      this.dateend=dateend;
      this.number=number;
      this.status=status;
      this.price=price;
  }
  
  public Delivery(Item item, Provider provider, Date datestart, Date dateend, long number, short status, long price) throws InterruptedException {
      this.item=item;
      this.provider=provider;
      this.datestart=datestart;
      this.dateend=dateend;
      this.number=number;
      this.status=status;
      this.price=price;
  }
  
  public Delivery(Delivery obj) throws InterruptedException {
      this.id=obj.getId();
      this.item=obj.getItem();
      this.provider=obj.getProvider();
      this.datestart=obj.getDatestart();
      this.dateend=obj.getDateend();
      this.number=obj.getNumber();
      this.status=obj.getStatus();
      this.price=obj.getPrice();
  }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    public boolean equals(Delivery obj){
        if(obj.getItem()==null || obj.getProvider()==null) return false;
        return obj.getItem().equals(this.item)&&obj.getProvider().equals(this.provider)&&obj.getDatestart().equals(this.datestart)&&obj.getDateend().equals(this.dateend)&&(obj.getNumber()==this.number)&&(obj.getStatus()==this.status)&&(obj.getPrice()==this.price);
    }
    
    public String stringifyStatus(){
        switch(this.status){
            case 1:
                return "Waiting";
            case 2:
                return "Accepted. Departure expected";
            case 3:
                return "Sent";
            case 4:
                return "Arrived";
            case 5:
                return "Received";
            default:
                return "Is being formed";
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
      return new StringJoiner(", ", Delivery.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("item=" + this.item)
                .add("provider=" + this.provider)
                .add("number=" + this.number)
                .add("datestart=" + this.datestart)
                .add("dateend=" + this.dateend)
                .add("status=" + this.status)
                .add("price=" + this.price)
                .toString();
  }
  
  public String getAllFields(){
      return new StringJoiner(", ")
                .add("id")
                .add("item")
                .add("provider")
                .add("number")
                .add("datestart")
                .add("dateend")
                .add("status")
                .add("price")
                .toString();
  }
  
  public String getAllValues(){
      return new StringJoiner(", ")
                .add("'" + this.id+"'")
                .add("'" + this.item.getId()+"'")
                .add("'" + this.provider.getId()+"'")
                .add("'" + this.number+"'")
                .add("'" + this.datestart+"'")
                .add("'" + this.dateend+"'")
                .add("'" + this.status+"'")
                .add("'" + this.price+"'")
                .toString();
  }
  
  public String getAllFieldsNew(){
      return new StringJoiner(", ")
                .add("item")
                .add("provider")
                .add("number")
                .add("datestart")
                .add("dateend")
                .add("status")
                .add("price")
                .toString();
  }
  
  public String getAllValuesNew(){
      return new StringJoiner(", ")
                .add("'" + this.item.getId()+"'")
                .add("'" + this.provider.getId()+"'")
                .add("'" + this.number+"'")
                .add("'" + this.datestart+"'")
                .add("'" + this.dateend+"'")
                .add("'" + this.status+"'")
                .add("'" + this.price+"'")
                .toString();
  }
  
  public String getValuesForUpdate(){
      return new StringJoiner(", ")
                .add("\"item\"='" + this.item.getId()+"'")
                .add("\"provider\"='" + this.provider.getId()+"'")
                .add("\"number\"='" + this.number+"'")
                .add("\"datestart\"='" + this.datestart+"'")
                .add("\"dateend\"='" + this.dateend+"'")
                .add("\"status\"='" + this.status+"'")
                .add("\"price\"='" + this.price+"'")
                .toString();
  }
}
