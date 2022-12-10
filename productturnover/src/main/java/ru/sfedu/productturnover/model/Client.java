package ru.sfedu.productturnover.model;

import com.opencsv.bean.CsvBindByName;
import java.util.StringJoiner;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import static ru.sfedu.productturnover.Constants.ROLE_ACCEPTOR;
import static ru.sfedu.productturnover.Constants.ROLE_ADMIN;
import static ru.sfedu.productturnover.Constants.ROLE_GUEST;
import static ru.sfedu.productturnover.Constants.ROLE_SALER;
import static ru.sfedu.productturnover.Constants.ROLE_SALER_ACCEPTOR;
import static ru.sfedu.productturnover.Constants.ROLE_USER;

/**
 * Class Client
 */
/**
 * Класс клиентов со всеми геттерами и сеттерами
 * @author Дмитрий
 */
@Root // - Аннотация, использующаяся при формировании xml объекта
public class Client{

  //
  // Fields
  //
  @Attribute // - аннотация, дающая команду записывать данное поле в аттрибут Root'а
  @CsvBindByName
  long id;
    
  @Element // - аннотация, указывающая, что поле в xml-объекте будет находиться внутри Root и являться его элементом
  @CsvBindByName // - аннотация, указывающая, что в csv-файле поля будут задаваться по имени
  private String name;
  
  @Element // - аннотация, указывающая, что поле в xml-объекте будет находиться внутри Root и являться его элементом
  @CsvBindByName // - аннотация, указывающая, что в csv-файле поля будут задаваться по имени
  private String login;
  
  @Element // - аннотация, указывающая, что поле в xml-объекте будет находиться внутри Root и являться его элементом
  @CsvBindByName // - аннотация, указывающая, что в csv-файле поля будут задаваться по имени
  private String password;
  
  @Element // - аннотация, указывающая, что поле в xml-объекте будет находиться внутри Root и являться его элементом
  @CsvBindByName // - аннотация, указывающая, что в csv-файле поля будут задаваться по имени
  private short role;
  
  //
  // Constructors
  //

  public Client(){}
  
  public Client(long id, String name, String login, String password, short role) throws InterruptedException {
      this.id=id;
      this.name=name;
      this.login=login;
      this.password=password;
      this.role=role;
  }
  
  public Client(String name, String login, String password, short role) throws InterruptedException {
      this.name=name;
      this.login=login;
      this.password=password;
      this.role=role;
  }
  
  public Client(String name, String login, String password) throws InterruptedException {
      this.name=name;
      this.login=login;
      this.password=password;
      this.role=1;
  }
  
  public Client(Client cl){
      this.id=cl.getId();
      this.name=cl.getName();
      this.login=cl.getLogin();
      this.password=cl.getPassword();
      this.role=cl.getRole();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }

    public boolean equals(Client cl){
        return cl.getLogin().equals(this.login) && cl.getName().equals(this.name) && cl.getPassword().equals(this.password) && cl.getRole()==this.role;
    }
    
    public String stringifyRole(){
        switch(this.role){
            case ROLE_GUEST:
                return "Guest";
            case ROLE_USER:
                return "User";
            case ROLE_SALER:
                return "Saler";
            case ROLE_ACCEPTOR:
                return "Acceptor";
            case ROLE_SALER_ACCEPTOR:
                return "Saler+Acceptor";
            case ROLE_ADMIN:
                return "Administrator";
            default:
                return "Guest";
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
      return new StringJoiner(", ", Client.class.getSimpleName() + "[", "]")
                .add("id=" + this.id)
                .add("name=" + this.name)
                .add("login=" + this.login)
                .add("password=" + this.password)
                .add("role=" + this.role)
                .toString();
  }
  
  public String getAllFields(){
      return new StringJoiner(", ")
                .add("id")
                .add("name")
                .add("login")
                .add("password")
                .add("role")
                .toString();
  }
  
  public String getAllValues(){
      return new StringJoiner(", ")
                .add("'"+this.id+"'")
                .add("'"+this.name+"'")
                .add("'"+this.login+"'")
                .add("'"+this.password+"'")
                .add("'"+this.role+"'")
                .toString();
  }
  
  public String getAllFieldsNew(){
      return new StringJoiner(", ")
                .add("name")
                .add("login")
                .add("password")
                .add("role")
                .toString();
  }
  
  public String getAllValuesNew(){
      return new StringJoiner(", ")
                .add("'"+this.name+"'")
                .add("'"+this.login+"'")
                .add("'"+this.password+"'")
                .add("'"+this.role+"'")
                .toString();
  }
  
  public String getValuesForUpdate(){
      return new StringJoiner(", ")
                .add("\"name\"='" + this.name+"'")
                .add("\"login\"='" + this.login+"'")
                .add("\"password\"='" + this.password+"'")
                .add("\"role\"='" + this.role+"'")
                .toString();
  }

}
