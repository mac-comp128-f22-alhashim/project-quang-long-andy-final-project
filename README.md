# Tic Tac Toe and the Minimax AI

#### Project Contributors: Long Truong, Quang Nguyen, Andy Xu

#### Our aim of the project is to practice Object-Oriented Programming and apply different Data Structures learned from Comp128. We realize Tic Tac Toe game is a simple game but open up to many challenging features. Thus, we do our research and code up our own Tic Tac Toe game.

## 0. Data Structure
- 2D Array
- HashMap
- Decision Tree (sort of)

## 1. Project Description
This game is a Java version of the Tic Tac Toe game. This game has:
- A Simple 3x3 Tic Tac Toe game board
- A Big Caro game board with easy to configure board size
- A MiniMax AI playing in the command prompt.

## 2. Manual
- Run `BigTTT.java` to play a Caro game. The board size is big and you have to get 5 consecutive marks to win*
- Run `TTT.java` to play the a simple Tic Tac Toe game.
- Run `TTTwithAI.java` to play a simple Tic Tac Toe game against an AI in the command prompt.**

<em>
<font size = 1>
**The `BigTTT.java` is not too sensitive so player may need to be patient to click on the next move. In addition, there are some bugs for the top row. To select a cell, click the bottom left of the cell to execute.

*This AI is unbeatable and to change who goes first, go to `TTTwithAI.java` and change the variable `isHumanPlayerX` to `true/ false`
</em> </font>

#### <bold>3x3 Tic Tac Toe game:</bold>
![](https://i.imgur.com/RVvGqnh.gif)

#### <bold>Caro Game:</bold>
![](https://i.imgur.com/x3xgt89.gif)

#### <bold>MiniMax AI:</bold>
![](https://i.imgur.com/rv0CtXZ.gif)

## 3.Explain the Minimax Algorithm
Reference video: https://www.youtube.com/watch?v=trKjYdBASyQ&t=1232s \
In general, the Minimax algorithm simply thinks many moves ahead and find the most optimal move. 
(Kinda like thr Dr.Strange's view of 14,000,605 possible outcomes). \
In details, Each state of the game has a unidentified score. And we find the score of a state by finding the minimum/ maximum score of the nextest possible states. Or if the base case is reached, meaning the game state already has a clear result, we can declare the score. Then, depending what player the AI is, maximizing (plays as X) or minimizing (plays as O), it finds the move that yields minimum/ maximum to play. 

## 4.Challenges (Time limited)
- The codebase is not clean enough 
- There are some bugs that are not fixed 
- Adapt the code base of the new `Board` architect and the `AI` to the GUI
- Extend the Minimax AI to the Caro Game (Advanced XO)

