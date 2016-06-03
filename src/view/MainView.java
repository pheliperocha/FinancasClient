package view;

import static control.CommonFunctions.formatMoeda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainView implements ActionListener {
    
    private final JFrame frame = new JFrame();
    private final Container container = frame.getContentPane();
    
    private final Container top = new JPanel();
    private final Container footer = new JPanel();
    private JScrollPane scrollPane = new JScrollPane();
    
    private final Container receitasPanel = new JPanel();
    private final Container despesasPanel = new JPanel();
    private final Container totalPanel = new JPanel();
    
    private final TableView table = new TableView();
    
    private final JLabel lblReceitas = new JLabel("Receitas:");
    private final JLabel lblDespesas = new JLabel("Despesas:");
    private final JLabel lblTotal = new JLabel("Total:");
    
    static final JLabel valReceitas = new JLabel();
    static final JLabel valDespesas = new JLabel();
    static final JLabel valTotal = new JLabel();
    
    
    static final Color RED = Color.decode("#F45252");
    static final Color GREEN = Color.decode("#39B663");
    static final Color WHITE = Color.decode("#FFFFFF");
    
    public MainView(double totalReceitas, double totalDespesas, double totalGeral, ArrayList<ws.Movimento> listaMovimentos) {
        frame.setTitle("Gerenciador de finanças pessoais");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        container.setLayout(new BorderLayout(20,20));

        top.setLayout(new GridLayout(1,3, 10, 0));

        lblReceitas.setFont(lblReceitas.getFont().deriveFont(20.0f));
        
        valReceitas.setText(formatMoeda(totalReceitas));
        valReceitas.setFont(valReceitas.getFont().deriveFont(16.0f));
        valReceitas.setForeground(GREEN);
        
        
        lblDespesas.setFont(lblDespesas.getFont().deriveFont(20.0f));
        
        valDespesas.setText(formatMoeda(totalDespesas));
        valDespesas.setFont(valDespesas.getFont().deriveFont(16.0f));
        valDespesas.setForeground(RED);
        
        lblTotal.setFont(lblTotal.getFont().deriveFont(20.0f));
        
        valTotal.setText(formatMoeda(totalGeral));
        valTotal.setFont(valTotal.getFont().deriveFont(16.0f));
        

        receitasPanel.setLayout(new GridLayout(1,2));
        receitasPanel.add(lblReceitas);
        receitasPanel.add(valReceitas);
        
        
        despesasPanel.setLayout(new GridLayout(1,2));
        despesasPanel.add(lblDespesas);
        despesasPanel.add(valDespesas);
        
        
        totalPanel.setLayout(new GridLayout(1,2));
        totalPanel.add(lblTotal);
        totalPanel.add(valTotal);
        
        top.add(receitasPanel);
        top.add(despesasPanel);
        top.add(totalPanel);
        
        
        scrollPane = new JScrollPane(table.getTable(listaMovimentos));

        JButton btnAdd = new JButton("Adicionar");
        btnAdd.addActionListener(this);
        
        JButton btnCat = new JButton("Gerenciar Categorias");
        
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.add(btnCat);
        footer.add(btnAdd);
        
        container.add(BorderLayout.NORTH, top);
        container.add(BorderLayout.CENTER, scrollPane);
        container.add(BorderLayout.SOUTH, footer);
        
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 800);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        AddView addFrame = new AddView();
        
    }
    
}
