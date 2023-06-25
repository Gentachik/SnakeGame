import java.util.ArrayList;

public interface Inter {
    void setDirection(Direction direction);
    Object getValue(int rowIndex, int columnIndex);
    int getRowCount();
    int getColumnCount();
    void setRepaintListener(RepaintListener repaintListener);
    void setPaintListener(PaintListener window);
    void setGameOverListener(GameOverListener window);
}
