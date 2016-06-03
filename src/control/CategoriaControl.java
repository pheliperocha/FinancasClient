package control;

/**
 *
 * @author PhelipeRocha
 */
public class CategoriaControl {

    public static java.util.List<ws.Categoria> listarCategorias() {
        ws.FinancasService_Service service = new ws.FinancasService_Service();
        ws.FinancasService port = service.getFinancasServicePort();
        return port.listarCategorias();
    }
    
}
