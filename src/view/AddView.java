package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddView implements ActionListener {
    
    private final JFrame frame = new JFrame();
    
    public AddView() {
        frame.setTitle("Adicionar");
        
        Container c = frame.getContentPane();
        c.setLayout(new GridLayout(6,1,20,20));
        
        JLabel lblNome = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        
        Container nomePanel = new JPanel();
        nomePanel.setLayout(new GridLayout(2,1));
        nomePanel.add(lblNome);
        nomePanel.add(nomeField);
        
        JLabel lblFrequencia = new JLabel("Frequência:");
        JTextField frequenciaField = new JTextField();
        
        Container frequenciaPanel = new JPanel();
        frequenciaPanel.setLayout(new GridLayout(2,1));
        frequenciaPanel.add(lblFrequencia);
        frequenciaPanel.add(frequenciaField);
        
        String[] petStrings = { "Contas", "Serviços", "Transporte", "Alimentação", "Outros", "Nova Categoria" };
        JLabel lblCategoria = new JLabel("Categoria:");
        JComboBox categoriaField = new JComboBox(petStrings);
        categoriaField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                JComboBox comboBox = (JComboBox) event.getSource();
                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("Nova Categoria")) {
                    CategoryView categoryFrame = new CategoryView();
                }
                

            }
        });
        
        Container categoriaPanel = new JPanel();
        categoriaPanel.setLayout(new GridLayout(2,1));
        categoriaPanel.add(lblCategoria);
        categoriaPanel.add(categoriaField);
        
        JLabel lblValor = new JLabel("Valor:");
        JTextField valorField = new JTextField();
        
        Container valorPanel = new JPanel();
        valorPanel.setLayout(new GridLayout(2,1));
        valorPanel.add(lblValor);
        valorPanel.add(valorField);
        
        JLabel lblData = new JLabel("Data:");
        JTextField dataField = new JTextField();
        
        Container dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(2,1));
        dataPanel.add(lblData);
        dataPanel.add(dataField);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        
        Container btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(btnSalvar);
        
        c.add(nomePanel);
        c.add(frequenciaPanel);
        c.add(categoriaPanel);
        c.add(valorPanel);
        c.add(dataPanel);
        c.add(btnPanel);
        
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 400);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inserirMovimento("Novo", false, 1, 968, "2015-03-10");
        frame.setVisible(false);
    }

    private static int inserirMovimento(java.lang.String nome, boolean frequencia, int categoria, int valor, java.lang.String data) {
        ws.FinancasService_Service service = new ws.FinancasService_Service();
        ws.FinancasService port = service.getFinancasServicePort();
        return port.inserirMovimento(nome, frequencia, categoria, valor, data);
    }
    
}
