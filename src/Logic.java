import java.util.ArrayList;
import java.util.List;
class Logic implements Inter {
    // * - apple
    // @ - head
    // # - tail
    // X - border
    private int score=0;
    private Direction direction = Direction.UP;
    private final String[][] cells;
    private GameOverListener window;
    private RepaintListener repaint;
    private PaintListener paint;
    private final int[] applePosition = new int[2];
    private final int[] headPosition = new int[2];
    private final List<int[]> tailPositions;
    private boolean play=true;
    Logic() {
        cells = new String[25][16];
        for(int i=0;i< cells.length;i++){
            cells[i][0]="X";
            cells[i][15]="X";
        }
        for(int i=0;i< cells[0].length;i++){
            cells[0][i]="X";
            cells[24][i]="X";
        }
        placeHead();
        newApple();
        tailPositions = new ArrayList<>();
        tailPositions.add(headPosition.clone());
        Thread gameThread = new Thread(() -> {
            try {
                while (play) {
                    Thread.sleep(150);
                    move();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        gameThread.start();
    }
    private void newApple() {
        while (true) {
            int column = (int) (Math.random() * cells.length);
            int row = (int) (Math.random() * cells[0].length);
            if (cells[column][row] == null) {
                cells[column][row] = "*";
                applePosition[0] = column;
                applePosition[1] = row;
                if (repaint != null) {
                    repaint.repaintPosition(new MoveEvent(this, applePosition));
                }
                break;
            }
        }
    }
    private void placeHead() {
        while (true) {
            int column = (int) (Math.random() * 10 + 5);
            int row = (int) (Math.random() * 5 + 5);
            if (cells[column][row] == null) {
                cells[column][row] = "@";
                headPosition[0] = column;
                headPosition[1] = row;
                break;
            }
        }
    }
    private void move() {
        cells[headPosition[0]][headPosition[1]] = "#";
        if (isValidPosition(headPosition[0], headPosition[1])) {
            switch (direction) {
                case UP -> headPosition[1] -= 1;
                case DOWN -> headPosition[1] += 1;
                case LEFT -> headPosition[0] -= 1;
                case RIGHT -> headPosition[0] += 1;
            }
            if (cells[headPosition[0]][headPosition[1]] != null) {
                if (cells[headPosition[0]][headPosition[1]].equals("#") || cells[headPosition[0]][headPosition[1]].equals("X")) {
                    gameOver();
                }
            }
            cells[headPosition[0]][headPosition[1]] = "@";
            if (headPosition[0] == applePosition[0] && headPosition[1] == applePosition[1]) {
                newApple();
                score += 1;
                if(paint!=null) {
                    paint.paintScore(new PaintEvent(this, score));
                }
            } else {
                int[] lastTailPosition = tailPositions.remove(0);
                cells[lastTailPosition[0]][lastTailPosition[1]] = null;
                if (repaint != null) {
                    repaint.repaintPosition(new MoveEvent(this, lastTailPosition));
                }
            }
            tailPositions.add(headPosition.clone());
            if (repaint != null) {
                repaint.repaintPosition(new MoveEvent(this, headPosition));
            }
        }else {
            gameOver();
        }
    }
    private void gameOver() {
        play=false;
        window.gameOver();
    }
    private boolean isValidPosition(int column, int row) {
        return column-1 >= 0 && column+1 < cells.length && row-1 >= 0 && row+1 < cells[0].length;
    }
    @Override
    public void setDirection(Direction direction) {
        if ((this.direction == Direction.UP && direction != Direction.DOWN)
                || (this.direction == Direction.DOWN && direction != Direction.UP)
                || (this.direction == Direction.LEFT && direction != Direction.RIGHT)
                || (this.direction == Direction.RIGHT && direction != Direction.LEFT)) {
            this.direction = direction;
        }
    }
    @Override
    public Object getValue(int rowIndex, int columnIndex) {return cells[columnIndex][rowIndex];}
    @Override
    public int getRowCount() {return cells[0].length;}
    @Override
    public int getColumnCount() {return cells.length;}
    public void setRepaintListener(RepaintListener repaint) {this.repaint = repaint;}
    @Override
    public void setPaintListener(PaintListener paint) {this.paint=paint;}
    @Override
    public void setGameOverListener(GameOverListener window) {
        this.window=window;
    }
}
