/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author PhelipeRocha
 */
public class CommonFunctions {
    
    public static String formatMoeda(double valor) {
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(valor);
        
        return String.valueOf(moneyString);
        
    }
    
    public static double desformatMoeda(String valor) throws ParseException {
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        Number moneyString = formatter.parse(valor);
        double value = moneyString.doubleValue();
        
        return value;
        
    }
    
    public static String formatBoolean(boolean valor) {
        
        if (valor == true) {
            return "Sim";
        } else {
            return "Não";
        }
        
    }
    
    public static String formatData(String data) throws ParseException {
        
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        
        return formattedDate;
        
    }
    
    public static String desformatData(String data) throws ParseException {
        
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        
        return formattedDate;
        
    }
    
}
