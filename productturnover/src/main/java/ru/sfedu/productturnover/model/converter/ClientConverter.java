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
import ru.sfedu.productturnover.model.Client;


public class ClientConverter extends AbstractBeanField<Client>{
    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        return String.valueOf(((Client)value).getId());
    }

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            return (new DataProviderCsv()).getClientById(Long.parseLong(s));
        } catch (Exception ex) {
            Logger.getLogger(ClientConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
