package view;

import static control.CategoriaControl.listarCategorias;
import static control.MovimentoControl.inserirMovimento;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddView implements ActionListener {
    
    private final JFrame frame = new JFrame();
    private final JTextField nomeField = new JTextField();
    private final JTextField frequenciaField = new JTextField();
    private final JTextField valorField = new JTextField();
    private final JTextField dataField = new JTextField();
    
    public AddView() {
        frame.setTitle("Adicionar");
        
        Container c = frame.getContentPane();
        c.setLayout(new GridLayout(6,1,20,20));
        
        JLabel lblNome = new JLabel("Nome:");
        
        Container nomePanel = new JPanel();
        nomePanel.setLayout(new GridLayout(2,1));
        nomePanel.add(lblNome);
        nomePanel.add(nomeField);
        
        JLabel lblFrequencia = new JLabel("Frequência:");
        
        Container frequenciaPanel = new JPanel();
        frequenciaPanel.setLayout(new GridLayout(2,1));
        frequenciaPanel.add(lblFrequencia);
        frequenciaPanel.add(frequenciaField);
        
        JLabel lblCategoria = new JLabel("Categoria:");
        
        Container categoriaPanel = new JPanel();
        categoriaPanel.setLayout(new GridLayout(2,1));
        categoriaPanel.add(lblCategoria);
        categoriaPanel.add(getComboCat());
        
        JLabel lblValor = new JLabel("Valor:");
        
        Container valorPanel = new JPanel();
        valorPanel.setLayout(new GridLayout(2,1));
        valorPanel.add(lblValor);
        valorPanel.add(valorField);
        
        JLabel lblData = new JLabel("Data:");
        
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
        
        inserirMovimento(nomeField.getText(), Boolean.parseBoolean(frequenciaField.getText()), 1, Integer.getInteger(valorField.getText()), dataField.getText());
        
        frame.setVisible(false);
    }
    
    private JComboBox getComboCat() {
        
        List<ws.Categoria> listaCategorias = listarCategorias();
        String[] catList = new String[listaCategorias.size()];
        
        for (int i = 0; i < listaCategorias.size(); i++) {
            catList[i] = listaCategorias.get(i).getNome();
        }
        
        JComboBox jcb = new JComboBox(catList);
        jcb.setEditable(true);
        
        return jcb;
    }
    
}
