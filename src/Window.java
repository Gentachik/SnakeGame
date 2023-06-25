import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Window extends JFrame implements GameOverListener {
    private Inter inter;
    private final ScorePanel scorePanel;
    private final JTable boardTable;
    Window(){
        boardTable = new JTable();
        boardTable.setRowHeight(20);
        boardTable.setDefaultRenderer(Object.class, new Cell());

        scorePanel = new ScorePanel();

        scorePanel.setPreferredSize(new Dimension(getWidth(), 50));
        boardTable.setPreferredSize(new Dimension(getWidth(), getHeight() - scorePanel.getPreferredSize().height));


        this.add(scorePanel, BorderLayout.NORTH);
        this.getContentPane().add(boardTable, BorderLayout.CENTER);


        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(inter!=null) {
                    switch (keyCode) {
                        case KeyEvent.VK_UP, KeyEvent.VK_W -> inter.setDirection(Direction.UP);
                        case KeyEvent.VK_DOWN, KeyEvent.VK_S -> inter.setDirection(Direction.DOWN);
                        case KeyEvent.VK_LEFT, KeyEvent.VK_A -> inter.setDirection(Direction.LEFT);
                        case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> inter.setDirection(Direction.RIGHT);
                        default -> requestFocusInWindow();
                    }
                }
            }
        });
        boardTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        this.setSize(640, 480);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setInter(Inter inter){
        this.inter=inter;
        BoardVisual boardVisual = new BoardVisual(inter);
        inter.setRepaintListener(boardVisual);
        inter.setPaintListener(scorePanel);
        inter.setGameOverListener(this);
        boardTable.setModel(boardVisual);
    }
    @Override
    public void gameOver() {
        this.getContentPane().removeAll();
        JPanel gameOverPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                FontMetrics fontMetrics = g.getFontMetrics();
                String message = "Game Over";
                int x = (getWidth() - fontMetrics.stringWidth(message)) / 2;
                int y = fontMetrics.getAscent();
                g.drawString(message, x, y);
                g.setFont(new Font("Arial", Font.BOLD, 15));
            }
        };
        gameOverPanel.setLayout(new BorderLayout());
        gameOverPanel.setBackground(Color.BLACK);
        this.getContentPane().add(gameOverPanel);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}
