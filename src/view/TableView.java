/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static control.CategoriaControl.listarCategorias;
import static control.CommonFunctions.desformatData;
import static control.CommonFunctions.formatMoeda;
import static control.CommonFunctions.desformatMoeda;
import static control.CommonFunctions.formatBoolean;
import static control.CommonFunctions.formatData;
import java.awt.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import static control.MovimentoControl.atualizarMovimento;
import static control.MovimentoControl.getTotal;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import static view.MainView.GREEN;
import static view.MainView.RED;
import static view.MainView.WHITE;

/**
 *
 * @author PhelipeRocha
 */
public class TableView implements TableModelListener {
    private final JTable table;
    private final DefaultTableModel model;
    private final Object[] columnsName;
    
    static final int COUNT_COL = 7;
    static final int SELECT_COL = 0;
    static final int ID_COL = 1;
    static final int DATA_COL = 2;
    static final int NOME_COL = 3;
    static final int CAT_COL = 4;
    static final int FREQ_COL = 5;
    static final int VAL_COL = 6;
    
    String[] comboBoxArray;
    
    
    public TableView() {
        this.columnsName = new Object[COUNT_COL];
        this.model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case SELECT_COL:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        Integer[][] data = null;
        this.table = new JTable() {
            
            // Impedir que edite as colunas de IDs
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != ID_COL;
            };
            
            // Colorir as linhas de acordo com o valor, se é receita ou despesa
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                
                String valStr = getValueAt(row, VAL_COL).toString();
                double value = 0;
                
                try {
                    value = desformatMoeda(valStr);
                } catch (ParseException ex) {
                    Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (value < 0) {
                    c.setBackground(RED);
                    c.setForeground(WHITE);
                } else {
                    c.setBackground(GREEN);
                    c.setForeground(WHITE);
                }

                return c;
            }
            
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                
                // Impede que coloque valores não monetários na coluna de valor
                if (column == VAL_COL) {
                    double value = 0;
                    
                    try {
                        value = Double.parseDouble(aValue.toString());
                    } catch(NumberFormatException nfe) {
                        try {
                            value = desformatMoeda(aValue.toString());
                        } catch (ParseException ex) {
                            return;
                        }
                    }
                    
                    aValue = formatMoeda(value);
                }
                
                // Impede que coloque valor diferente de sim e não na coluna da frequência
                if (column == FREQ_COL) {
                    
                    aValue = Normalizer.normalize(aValue.toString().toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

                    if ("SIM".equals(aValue)) {
                        aValue = "Sim";
                    } else if ("NAO".equals(aValue)) {
                        aValue = "Não";
                    } else {
                        return;
                    }
                    
                }
                
                // Impede que coloque valor diferente de formato data DD/MM/YYYY
                if (column == DATA_COL) {
                    
                    try {
                        aValue = formatData(desformatData(aValue.toString()));
                    } catch (ParseException ex) {
                        return;
                    }
                    
                }
                
                // Impede que coloque uma categoria que não exista
                if (column == CAT_COL) {
                    
                    if (!Arrays.asList(comboBoxArray).contains(aValue)) {
                        return;
                    }
                    
                }
                
                getModel().setValueAt(aValue, convertRowIndexToModel(row),
                        convertColumnIndexToModel(column));
            }
            
        };

        columnsName[SELECT_COL] = "Selecionar";
        columnsName[ID_COL] = "ID";
        columnsName[DATA_COL] = "Data";
        columnsName[NOME_COL] = "Nome";
        columnsName[CAT_COL] = "Categoria";
        columnsName[FREQ_COL] = "Frequênte";
        columnsName[VAL_COL] = "Valor";
        model.setColumnIdentifiers(columnsName);
        
    }
    
    /**
     *
     * @param listaMovimentos
     * @return
     */
    public JTable getTable(ArrayList<ws.Movimento> listaMovimentos) throws ParseException {
        Object[] rowData = new Object[COUNT_COL];

        for (int i = 0; i < listaMovimentos.size(); i++) {
            rowData[SELECT_COL] = Boolean.FALSE;
            rowData[ID_COL] = listaMovimentos.get(i).getId();
            rowData[DATA_COL] = formatData(listaMovimentos.get(i).getData());
            rowData[NOME_COL] = listaMovimentos.get(i).getNome();
            rowData[CAT_COL] = listaMovimentos.get(i).getCategoria();
            rowData[FREQ_COL] = formatBoolean(listaMovimentos.get(i).isFrequencia());
            rowData[VAL_COL] = formatMoeda(listaMovimentos.get(i).getValor());
            
            model.addRow(rowData);
        }
        
        
        
        table.setModel(model);
        
        table.getColumnModel().getColumn(CAT_COL).setCellEditor(new DefaultCellEditor(getComboCat()));
        
        model.addTableModelListener(this);

        return table;
       
    }
    
    private JComboBox getComboCat() {
        
        List<ws.Categoria> listaCategorias = listarCategorias();
        comboBoxArray = new String[listaCategorias.size()];
        
        for (int i = 0; i < listaCategorias.size(); i++) {
            comboBoxArray[i] = listaCategorias.get(i).getNome();
        }
        
        JComboBox jcb = new JComboBox(comboBoxArray);
        jcb.setEditable(true);
        
        return jcb;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        
        int col = e.getColumn();
        int row = e.getFirstRow();
        int id = Integer.parseInt(model.getValueAt(row, ID_COL).toString());

        String val = model.getValueAt(row, col).toString();
        
        if (col == VAL_COL) {
            
            try {
                val = String.valueOf(desformatMoeda(val));
            } catch (ParseException ex) {
                Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if (col == FREQ_COL) {
            
            if ("SIM".equals(val.toUpperCase())) {
                val = "1";
            } else {
                val = "0";
            }
            
        }
        
        if (col == DATA_COL) {
            
            try {
                val = desformatData(val);
            } catch (ParseException ex) {
                Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        atualizarMovimento(id, col, val);
        
        if (col == VAL_COL) {
            
            double totalReceita = getTotal("receita");
            double totalDespesa = getTotal("despesa");
            double totalGeral = getTotal("total");

            MainView.valReceitas.setText(String.valueOf(formatMoeda(totalReceita)));
            MainView.valDespesas.setText(String.valueOf(formatMoeda(totalDespesa)));
            MainView.valTotal.setText(String.valueOf(formatMoeda(totalGeral)));
        }

    }
    
}
