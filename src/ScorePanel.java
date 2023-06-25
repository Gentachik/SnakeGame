import javax.swing.*;
import java.awt.*;

class ScorePanel extends JPanel implements PaintListener{
    private int score;
    public ScorePanel() {
        this.score = 0;
    }
    public void updateScore(int newScore) {
        this.score = newScore;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30);
    }
    @Override
    public void paintScore(PaintEvent event) {
        updateScore(event.getAppleCount());
    }
}
