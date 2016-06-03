package financas;

import static control.MovimentoControl.getTotal;
import static control.MovimentoControl.listarMovimentos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import view.CategoryView;
import view.MainView;

public class Financas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<ws.Movimento> listMovimento = new ArrayList();
        listMovimento = (ArrayList<ws.Movimento>) listarMovimentos();
        
        double totalReceita = getTotal("receita");
        double totalDespesa = getTotal("despesa");
        double totalGeral = getTotal("total");
       
        MainView janela = new MainView(totalReceita, totalDespesa, totalGeral, listMovimento);


    }
    
}
