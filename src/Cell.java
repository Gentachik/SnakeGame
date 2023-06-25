import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Cell implements TableCellRenderer {
    private final JPanel jPanel;
    public Cell() {
        jPanel=new JPanel();
        jPanel.setPreferredSize(new Dimension(60,60));
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value!=null){
            if (value.equals("*")) {
                jPanel.setBackground(Color.RED);
            }
            if(value.equals("@")){
                jPanel.setBackground(new Color(29,97,35));
            }
            if(value.equals("#")){
                jPanel.setBackground(Color.GREEN);
            }
            if(value.equals("X")){
                jPanel.setBackground(Color.BLACK);
            }
        }else {
            jPanel.setBackground(Color.WHITE);
        }

        return jPanel;
    }
}
