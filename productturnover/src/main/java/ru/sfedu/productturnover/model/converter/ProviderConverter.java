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
import ru.sfedu.productturnover.model.Provider;


public class ProviderConverter extends AbstractBeanField<Provider>{
    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        return String.valueOf(((Provider)value).getId());
    }

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            return (new DataProviderCsv()).getProviderById(Long.parseLong(s));
        } catch (Exception ex) {
            Logger.getLogger(ProviderConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
