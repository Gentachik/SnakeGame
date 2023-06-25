import java.util.EventObject;
public class PaintEvent extends EventObject {
    private final int appleCount;
    public PaintEvent(Object source, int appleCount) {
        super(source);
        this.appleCount=appleCount;
    }
    public int getAppleCount(){return appleCount;}
}
