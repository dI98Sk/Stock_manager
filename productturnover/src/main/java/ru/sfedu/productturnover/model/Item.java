package ru.sfedu.productturnover.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import java.util.StringJoiner;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.productturnover.model.converter.TypeConverter;

/**
 * Class Item
 */
/**
 * Класс товаров со всеми геттерами и сеттерами
 * @author Дмитрий
 */
@Root
public class Item{

  //
  // Fields
  //
    
    @Attribute // - аннотация, дающая команду записывать данное поле в аттрибут Root'а
    @CsvBindByName
    long id;
    
  @Element
  @CsvBindByName
  private String name;
  
  @Element
  @CsvBindByName
  private String description="";
  
  @Element
  @CsvCustomBindByName(converter=TypeConverter.class)
  private Type type;
  
  @Element
  @CsvBindByName
  private long balance=0;
  
  public Item(){}
  
    public Item(long id, String name, String description, Type type, long balance) throws InterruptedException {
        this.id=id;
        this.name=name;
        this.description=description;
        this.type=type;
        this.balance=balance;
    }
    
    public Item(String name, String description, Type type, long balance) throws InterruptedException {
        this.name=name;
        this.description=description;
        this.type=type;
        this.balance=balance;
    }
    
    public Item(Item obj) throws InterruptedException {
        this.id=obj.getId();
        this.name=obj.getName();
        this.description=obj.getDescription();
        this.type=obj.getType();
        this.balance=obj.getBalance();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return name;
    }

    public void setDescription(String description) {
        this.name = name;
    }
    
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
    
    public boolean equals(Item obj){
        if(obj.getType()==null) return false;
        return obj.getName().equals(this.name)&&obj.getDescription().equals(this.description)&&obj.getType().equals(this.type)&&obj.getBalance()==this.balance;
    }
  //
  // Other methods
  //
  /**
   * 
   *  
   */
  public String toString(){
      return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("name=" + this.name)
                .add("description=" + this.description)
                .add("type=" + this.type)
                .add("balance=" + this.balance)
                .toString();
  }
  
  public String getAllFields(){
      return new StringJoiner(", ")
                .add("id")
                .add("name")
                .add("description")
                .add("type")
                .add("balance")
                .toString();
  }
  
  public String getAllValues(){
      return new StringJoiner(", ")
                .add("'" + this.id+"'")
                .add("'" + this.name+"'")
                .add("'" + this.description+"'")
                .add("'" + this.type.getId()+"'")
                .add("'" + this.balance+"'")
                .toString();
  }
  
  public String getAllFieldsNew(){
      return new StringJoiner(", ")
                .add("name")
                .add("description")
                .add("type")
                .add("balance")
                .toString();
  }
  
  public String getAllValuesNew(){
      return new StringJoiner(", ")
                .add("'" + this.name+"'")
                .add("'" + this.description+"'")
                .add("'" + this.type.getId()+"'")
                .add("'" + this.balance+"'")
                .toString();
  }
  
  public String getValuesForUpdate(){
      return new StringJoiner(", ")
                .add("\"name\"='" + this.name+"'")
                .add("\"description\"='" + this.description+"'")
                .add("\"type\"='" + this.type.getId()+"'")
                .add("\"balance\"='" + this.balance+"'")
                .toString();
  }

}
