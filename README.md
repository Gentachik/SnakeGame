# SnakeGame
This is a simple implementation of the Snake game in Java using Swing for the graphical user interface. The game features a snake that moves around a grid, eating apples and growing longer. The objective of the game is to control the snake and avoid collision with the walls or its own body.

# How to Play
To play the game, follow these steps:

1. Use the arrow keys or WASD keys to control the snake's movement.
2. The snake will automatically move in the direction specified until you change its direction or the game ends.
3. The game ends if the snake collides with the border or itself.

# Classes
* BoardVisual
a class that extends AbstractTableModel and represents the game board. It provides data and information about the board's dimensions and contents.

* Cell
a class that implements TableCellRenderer and represents the rendering of a single cell in the game board. It determines the color of each cell based on its value.

* Direction
a enum representing the possible directions of movement for the snake.

* Inter
a interface that defines the methods and functionality required by the game logic.

* Logic
a class that implements the Inter interface and provides the game logic. It controls the movement of the snake, detects collisions, and manages the game state.

* MoveEvent
a event class that is triggered when a move occurs. It contains information about the position of the move.

* PaintEvent
a event class that is triggered when the game needs to be repainted. It contains information such as the current score.

* PaintListener
a interface for the paint event listener.

* RepaintListener
a interface for the repaint event listener.

* ScorePanel
a JPanel that displays the current score during the game.

* Window
a JFrame that represents the game window. It contains the game board, score panel, and handles user input.
