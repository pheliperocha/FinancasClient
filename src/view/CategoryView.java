/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PhelipeRocha
 */
public class CategoryView {
    private final JFrame frame = new JFrame();
    
    public CategoryView() {
        frame.setTitle("Adicionar");
        
        Container c = frame.getContentPane();
        c.setLayout(new GridLayout(2,1,10,10));
        
        JLabel lblNome = new JLabel("Nome da categoria:");
        JTextField nomeField = new JTextField();
        
        Container nomePanel = new JPanel();
        nomePanel.setLayout(new GridLayout(2,1));
        nomePanel.add(lblNome);
        nomePanel.add(nomeField);
        
        JButton btnSalvar = new JButton("Salvar");
        
        Container btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(btnSalvar);
        
        c.add(nomePanel);
        c.add(btnPanel);
        
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 150);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }
}
