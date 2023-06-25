import javax.swing.table.AbstractTableModel;

public class BoardVisual extends AbstractTableModel implements RepaintListener  {
    private final Inter inter;
    public BoardVisual(Inter inter){
        this.inter=inter;
    }
    @Override
    public int getRowCount() {
        if(inter!=null){
            return inter.getRowCount();
        }
        return 0;
    }
    @Override
    public int getColumnCount() {
        if(inter!=null){
            return inter.getColumnCount();
        }
        return 0;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(inter!=null) {
            return inter.getValue(rowIndex, columnIndex);
        }
        return null;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }
    @Override
    public void repaintPosition(MoveEvent event) {
        fireTableCellUpdated(event.getRow(),event.getColumn());
        fireTableDataChanged();
    }
}
