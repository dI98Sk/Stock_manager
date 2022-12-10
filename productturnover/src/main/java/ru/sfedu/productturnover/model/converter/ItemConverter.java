/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.productturnover.model.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sfedu.productturnover.api.DataProviderCsv;
import ru.sfedu.productturnover.model.Item;


public class ItemConverter extends AbstractBeanField<Item>{
    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        return String.valueOf(((Item)value).getId());
    }

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            return (new DataProviderCsv()).getItemById(Long.parseLong(s));
        } catch (Exception ex) {
            Logger.getLogger(ItemConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
