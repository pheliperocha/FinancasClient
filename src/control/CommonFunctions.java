/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.NumberFormat;
import java.text.ParseException;

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
    
}
