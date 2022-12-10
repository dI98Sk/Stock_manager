package ru.sfedu.productturnover.model;

import com.opencsv.bean.CsvBindByName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.productturnover.constant.ClassType;

/**
 * Class Type
 */
/**
 * Класс типов товаров со всеми геттерами и сеттерами
 * @author Дмитрий
 */
@Root
public class Type{

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
  
  //
  // Constructors
  //
    
    public Type(){}
    
    public Type(long id, String name, String description) throws InterruptedException {
        this.id=id;
        this.name=name;
        this.description=description;
    }
    
    public Type(String name, String description) throws InterruptedException {
        this.name=name;
        this.description=description;
    }
    
    public Type(Type obj){
      this.id=obj.getId();
      this.name=obj.getName();
      this.description=obj.getDescription();
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean equals(Type obj){
        return obj.getName().equals(this.name) && obj.getDescription().equals(this.description);
    }
  //
  // Other methods
  //
  /**
   * 
   *  
   */
  public String toString(){
      return new StringJoiner(", ", Type.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("name=" + this.name)
                .add("description=" + this.description)
                .toString();
  }
  
  public String getAllFields(){
      return new StringJoiner(", ")
                .add("id")
                .add("name")
                .add("description")
                .toString();
  }
  
  public String getAllValues(){
      return new StringJoiner(", ")
                .add("'" + this.id+"'")
                .add("'" + this.name+"'")
                .add("'" + this.description+"'")
                .toString();
  }
  
  public String getAllFieldsNew(){
      return new StringJoiner(", ")
                .add("name")
                .add("description")
                .toString();
  }
  
  public String getAllValuesNew(){
      return new StringJoiner(", ")
                .add("'" + this.name+"'")
                .add("'" + this.description+"'")
                .toString();
  }
  
  public String getValuesForUpdate(){
      return new StringJoiner(", ")
                .add("\"name\"='" + this.name+"'")
                .add("\"description\"='" + this.description+"'")
                .toString();
  }

}
