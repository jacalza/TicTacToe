from random import random
import subprocess as sub
def sign(value):
    ret = 1;
    if value < 0:
        ret = -1;
    return ret
def draw_board(board):
    vals=['X','_','O']
    print("".join([vals[i+1] for i in board[0:3]]))
    print("".join([vals[i+1] for i in board[3:6]]))
    print("".join([vals[i+1] for i in board[6:9]]))
    print("\n")
def what_move_to_make(existing_board, me, them, max, engine="octave -qf --eval engine"):
        '''
        %assumes that the board is a nun matrix of values
        %play returns the position on the board where the play should be made.
        %does not check the board
        '''
        board_data = str([me+1,them+1,[i+1 for i in existing_board]]).replace("]","").replace("[","").replace(",",""); #move from -1..1 to 0..2
        command = "echo %s %s|%s" % (max, board_data, engine);
		
        p = sub.Popen(command+" 2>&1",stdout=sub.PIPE,stderr=sub.PIPE, shell=True);
        output, errors = p.communicate();
        play = int(output)-1
        return play
def make_move(boardin,play,turn):
        board=boardin;
        '''
        %if (turn)
        %        input ("Press any key when you're done the move");
        %endif
        '''
        board[play] = int(round(turn-.5));
        return board;
def who_won(board,max):
    winner = [];
    ends = [c for c in range(0,max*max,max)]
    #if there is a winner, one of these 4 will be eq to max or -max
    #else if the board is not full, then winner is empty. Empty+2 => Empty
    rows = [0, 3, 6, 1, 4, 7, 2, 5, 8];
    cols = [0, 1, 2, 3, 4, 5, 6, 7, 8];
    diag1 = [0, 4, 8]
    diag2 = [2, 4, 6]
    testcases =[sum([board[i] for i in rows[0:3]]), sum([board[i] for i in rows[3:6]]), sum([board[i] for i in rows[6:9]]),
     sum([board[i] for i in cols[0:3]]), sum([board[i] for i in cols[3:6]]), sum([board[i] for i in cols[6:9]]),
     sum([board[i] for i in diag1[0:3]]), sum([board[i] for i in diag2[0:3]])]
    if max in testcases:
        winner = [2];
    elif -max in testcases:
        winner = [0];
    elif 0 not in board:
        winner = [1];
    return winner
def playtictactoe(number_of_games=1,player2="python human.py",player1="octave -qf --eval engine"):
        dimension = 3;
        scores=[0, 0, 0];
        for i in range(number_of_games):
                existing_board = [0, 0, 0,0, 0, 0,0, 0, 0];
                turn = round(random());
                me = -1; them = 1; #me is player1, them is player2
                response = ['Player1 won', 'It was a draw','Player2 won'];
                winner = [];
                while len(winner) == 0:
                        draw_board(existing_board);
                        if (turn):
                                #ask player1 to play
                                play = what_move_to_make(existing_board, me, them, dimension, player1);
                                existing_board = make_move(existing_board, play, turn); #since human can play differently, this is here
                        else:
                                #ask player2 to play
                                play = what_move_to_make(existing_board, me, them, dimension, player2);
                                existing_board = make_move(existing_board, play, turn); #since human can play differently, this is here
                        turn = (turn+1)%2;
                        winner = who_won(existing_board,dimension);
                draw_board(existing_board)
                print(response[winner[0]])
                scores[winner[0]]=scores[winner[0]]+1;
        print ("Player1 win %s, Draw %s times, Player2 win %s" % (scores[0], scores[1], scores[2]))
if __name__ == "__main__":
        playtictactoe(10, "java Main", "python engine.py")
