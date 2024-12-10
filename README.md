This is implementation of the Football World Cup Score Board as a simple
library.

Assumptions
- The score board uses in-memory list of Game objects to store data.
- Game has two teams and start time.
- Game cannot have two teams with identical names.
- Team has team's name and team's score.
- Score of a team cannot be lower than zero.

1. Start a game. 

    Method accepts Game object as argument. Preconditions:
    - Score of both teams has to be 0
    - Neither of team names can be already on the board
   
    When game starts, start time field of Game object is initialized.
2. Finish a game.

    Method accepts Game object as argument.
    If a game with matching team names is found on the score board, then it is removed from it.
3. Update a game. 

   Method accepts Game object as argument.
   If a game with matching team names is found on the score board, then score of the teams is updated with score values extracted from the argument.
4. Get a summary of games by total score.

   Method accepts no arguments.
   It sorts the score board's copy in order to return expected summary as string.



