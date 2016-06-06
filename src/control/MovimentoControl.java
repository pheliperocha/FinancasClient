/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author PhelipeRocha
 */
public class MovimentoControl {

    public static double getTotal(java.lang.String type) {
        ws.FinancasService_Service service = new ws.FinancasService_Service();
        ws.FinancasService port = service.getFinancasServicePort();
        return port.getTotal(type);
    }
    
    public static int atualizarMovimento(int id, int col, java.lang.String value) {
        ws.FinancasService_Service service = new ws.FinancasService_Service();
        ws.FinancasService port = service.getFinancasServicePort();
        return port.atualizarMovimento(id, col, value);
    }

    public static java.util.List<ws.Movimento> listarMovimentos() {
        ws.FinancasService_Service service = new ws.FinancasService_Service();
        ws.FinancasService port = service.getFinancasServicePort();
        return port.listarMovimentos();
    }

    public static int inserirMovimento(java.lang.String nome, boolean frequencia, int categoria, int valor, java.lang.String data) {
        ws.FinancasService_Service service = new ws.FinancasService_Service();
        ws.FinancasService port = service.getFinancasServicePort();
        return port.inserirMovimento(nome, frequencia, categoria, valor, data);
    }
    
}
