
package ru.sfedu.productturnover.constant;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Класс, хранящий статус результата и сообщение ошибки
 * @author Дмитрий
 */
public class Result {
    private StatusType status;
    private String errorMsg;
    private long id=0;
    /**
     * конструктор по-умолчанию
     */
    public Result() {
        this.status=StatusType.OK;
        this.errorMsg="";
    }
    
    /**
     * конструктор, устанавшивающий статус
     * @param status
     */
    public Result(StatusType status) {
        this.status = status;
    }
    
    /**
     * конструктор, устанавшивающий статус и сообщение об ошибке
     * @param status
     * @param errorMsg
     */
    public Result(StatusType status, String errorMsg) {
        this(status);
        this.errorMsg = errorMsg;
    }
    
    public Result(StatusType status, String errorMsg, long id) {
        this(status,errorMsg);
        this.id=id;
    }
    
    /**
     *  the status
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * @param satus the status to set
     */
    public void setStatus(StatusType status) {
        this.status = status;
    }

    /**
     *  the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Сравнение переданного объекта с текущим
     * @param obj
     *  
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Result other = (Result) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
        return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                .add("status: " + this.status)
                .add("message: " + this.errorMsg)
                .add("id: " + this.id)
                .toString();
    }
}
