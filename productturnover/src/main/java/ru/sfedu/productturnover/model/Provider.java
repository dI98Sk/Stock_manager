package ru.sfedu.productturnover.model;

import com.opencsv.bean.CsvBindByName;
import java.util.StringJoiner;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.productturnover.constant.ClassType;

/**
 * Class Provider
 */
/**
 * Класс поставщиков со всеми геттерами и сеттерами
 * @author Дмитрий
 */
@Root
public class Provider{

  //
  // Fields
  //
    @Attribute // - аннотация, дающая команду записывать данное поле в аттрибут Root'а
    @CsvBindByName
    long id;
    
  @Element
  @CsvBindByName
  private String name;
  
  //
  // Constructors
  //
  
  public Provider(){}
    
    public Provider(long id, String name) throws InterruptedException {
        this.id=id;
        this.name=name;
    }
    
    public Provider(String name) throws InterruptedException {
        this.name=name;
    }
    
    public Provider(Provider obj) throws InterruptedException {
        this.id=obj.getId();
        this.name=obj.getName();
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
    
    public boolean equals(Provider obj){
        return obj.getName().equals(this.name);
    }
  //
  // Other methods
  //
  /**
   * 
   *  
   */
  public String toString(){
      return new StringJoiner(", ", Provider.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("name=" + this.name)
                .toString();
  }
  
  public String getAllFields(){
      return new StringJoiner(", ")
                .add("id")
                .add("name")
                .toString();
  }
  
  public String getAllValues(){
      return new StringJoiner(", ")
                .add("'" + this.id+"'")
                .add("'" + this.name+"'")
                .toString();
  }
  
  public String getAllFieldsNew(){
      return new StringJoiner(", ")
                .add("name")
                .toString();
  }
  
  public String getAllValuesNew(){
      return new StringJoiner(", ")
                .add("'" + this.name+"'")
                .toString();
  }
  
  public String getValuesForUpdate(){
      return new StringJoiner(", ")
                .add("\"name\"='" + this.name+"'")
                .toString();
  }

}
