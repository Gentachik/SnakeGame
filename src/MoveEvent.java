import java.util.EventObject;

public class MoveEvent extends EventObject {
    private final int column;
    private final int row;
    public MoveEvent(Object source, int[] position) {
        super(source);
        column=position[0];
        row=position[1];
    }
    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
}
