# Nine Men's Morris Game

This is a Java implementation of the classic strategy board game **Nine Men's Morris**, also known as **Mill**, using Java Swing and AWT for the graphical user interface.

## Table of Contents

1. [Game Overview](#game-overview)
2. [Features](#features)
3. [Installation](#installation)
4. [How to Play](#how-to-play)
5. [Controls](#controls)
6. [Technologies Used](#technologies-used)
7. [Future Enhancements](#future-enhancements)

---

## Game Overview

Nine Men's Morris is a two-player board game that dates back to the Roman Empire. Players take turns placing their pieces on the board, trying to form mills (three pieces in a row). Once a mill is formed, a player can remove one of the opponent's pieces. The goal is to reduce the opponent to two pieces or to block them from making a legal move.

This Java-based implementation provides a graphical interface for playing the game using the `Swing` and `AWT` libraries.

## Features

- **Multiplayer**: Local multiplayer mode (two players on the same device).
- **Move validation**: Validates legal moves according to Nine Men's Morris rules.
- **Graphical interface**: Interactive board with draggable pieces using Java Swing and AWT.

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/minhtuan542002/9-Man-Morris.git
    ```

2. **Compile the Java code**:
    Make sure you have a Java Development Kit (JDK) installed (version 8 or higher).

    ```bash
    javac -d bin -sourcepath src src/game/Main.java
    ```

3. **Run the game**:
    ```bash
    java -cp bin com.game.Main
    ```

Alternatively, you can use any IDE like **Eclipse** or **IntelliJ IDEA** to open the project, build, and run the game.

## How to Play

1. **Phase 1: Placing Pieces**:
   - Each player starts with nine pieces. Players take turns placing their pieces on the empty intersections of the board. The goal is to form mills (three pieces in a row).
   
2. **Phase 2: Moving Pieces**:
   - Once all pieces have been placed, players take turns moving their pieces to adjacent empty spots. If a mill is formed, they can remove one of their opponent’s pieces (except those in a mill).

3. **Phase 3: Flying (Final Phase)**:
   - When a player is reduced to three pieces, they are allowed to "fly," which means they can move to any vacant spot on the board, not just adjacent ones.

4. **Endgame**:
   - The game ends when one player has only two pieces left or cannot make a valid move.

## Controls

- **Left-click**: Select a piece or place it on the board.

## Technologies Used

- **Java**: Core game logic.
- **Swing & AWT**: Graphical User Interface (GUI) components and rendering.
- **Java 2D**: Drawing board and pieces.

## Future Enhancements
- AI Improvement: Implement a more advanced AI for single-player mode.
- Online Multiplayer: Allow players to compete online.
- Sound Effects: Add sound effects for moves and forming mills.
