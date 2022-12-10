package ru.sfedu.productturnover.constant;

import static ru.sfedu.productturnover.Constants.*;
import ru.sfedu.productturnover.model.Type;
import ru.sfedu.productturnover.model.Provider;
import ru.sfedu.productturnover.model.Item;
import ru.sfedu.productturnover.model.Client;
import ru.sfedu.productturnover.model.Delivery;
import ru.sfedu.productturnover.model.Selling;

/**
 * Энумератор, хранящий все текущие модели проекта
 * @author Дмитрий
 */
public enum ClassType {

    CLIENT(COLUMNS_CLIENT, Client.class),
    ITEM(COLUMNS_ITEM, Item.class),
    TYPE(COLUMNS_TYPE, Type.class),
    PROVIDER(COLUMNS_PROVIDER, Provider.class),
    ITEMPROVIDER(COLUMNS_DELIVERY, Delivery.class),
    ITEMCLIENT(COLUMNS_SELLING, Selling.class);
    
    private String[] description;
    private Class cl;
    private ClassType() {
    }

    private ClassType(String[] description, Class cl) {
        this.description = description;
        this.cl=cl;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String[] getDescription() {
        return description;
    }

    public Class getCl() {
        return cl;
    }
}
