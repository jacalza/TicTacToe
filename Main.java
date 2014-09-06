import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static final char X = 'X';
	private static final char O = 'O';
	private static final char EMPTY = '_';
	
public static char[] getCorner(String state) {
		
		char[] corner = new char[4];
		
		corner[0]=state.charAt(0);
		corner[1]=state.charAt(6);
		corner[2]=state.charAt(2);
		corner[3]=state.charAt(8);
				 
		return corner;
	}
	
	
public static char[] getEdge(String state) {
		
		char[] edge = new char[4];
		
		edge[0]=state.charAt(3);
		edge[1]=state.charAt(1);
		edge[2]=state.charAt(7);
		edge[3]=state.charAt(5);
				 
		return edge;
	}
	
public static int position(String state){
	
	for(int i=0; i<state.length(); i++) {
		if(state.charAt(i) == X || state.charAt(i) == O )
			return i;
	}
	return -1;
	
}


public static int emptyPosition(String state){
	
	for(int i=0; i<state.length(); i++) {
		if(state.charAt(i) == EMPTY )
			return i;
	}
	return -1;
	
}

public static int isEmpty(String state){
	
	for(int i=0; i<state.length(); i++) {
		if(state.charAt(i) != EMPTY )
			return -1;
	}
	return 1;
	
}


public static int charNum(String state, char element){
	
	int counter = 0;
	
	for(int i=0; i<state.length(); i++) {
		if(state.charAt(i) == element )
			counter++;
	}
	return counter;
	
}


public static int playerTurn(String state){
	
	int xNum = charNum(state, X);
	int oNum = charNum(state, O);
	
	if(xNum==oNum)
		return 0;
	else if (oNum%2 == 1)
		return 0;
	else if(xNum%2 == 1)
		return 1;
	

	else return 0;
	
}



public static int edgeO(String state) {
	
	char[] edge = new char[4];
	edge=getEdge(state);
	String tempEdge = new String(edge);
	int play = ((position(tempEdge) + 2)%4);
			 
	return play;
}

public static int edgeOplay(String state) {
	int[] corconv = {0,6,2,8};
	int play = -1;
	
	char[] edge = new char[4];
	edge=getEdge(state);
	
	String tempEdge = new String(edge);
	
	char[] corner = new char[4];
	corner = getCorner(state);
	
	String tempcorner = new String(corner);
	
	if(charNum(tempcorner,EMPTY)==4)
	{
		if(charNum(tempEdge,O)==1)
		{
			play = corconv[((position(tempEdge) + 2)%4)];
			
		}
		
	}
		 
	return play;
}




public static int playCorner(String state) {
	int[] corconv = {0,6,2,8};
	int play = -1;
	
	
	
	char[] corner = new char[4];
	corner = getCorner(state);
	
	String tempcorner = new String(corner);
	if(emptyPosition(tempcorner)!=-1)
	play = corconv[emptyPosition(tempcorner)];
		 
	return play;
}


public static int playCornerO(String state) {
	
	int play = -1;
	
	char[] corner = new char[4];
	corner = getCorner(state);
	
	String tempcorner = new String(corner);
	if(emptyPosition(tempcorner)!=-1)
	play = emptyPosition(tempcorner);
		 
	return play;
}





public static int playEmpty(String state) {
	
	int play = -1;
	
	play = emptyPosition(state);
		 
	return play;
}





public static String row(String state, int rowNum) {
	
	char[] row = new char[3];
		for (int i=0;i<3;i++)
	{
		int rowPos = rowNum + i*3;
		row[i] = state.charAt(rowPos);
		
	}
	
	String temprow = new String(row);
	return temprow;
}


public static String column(String state, int colNum) {
	
	char[] column = new char[3];
		for (int i=0;i<3;i++)
	{
		int colPos = colNum*3 + i;
		column[i] = state.charAt(colPos);
		
	}
	
	String tempcol = new String(column);
	return tempcol;
}



public static String diagonal(String state, int diagNum) {
	
	char[] diagonal = new char[3];
	
	if(diagNum == 0)
	{
		diagonal[0] = state.charAt(0);
		diagonal[1] = state.charAt(4);
		diagonal[2] = state.charAt(8);
	}
	
	else if(diagNum == 1)
	{
		diagonal[0] = state.charAt(2);
		diagonal[1] = state.charAt(4);
		diagonal[2] = state.charAt(6);
	}
		
	
	String tempdiag = new String(diagonal);
	return tempdiag;
}




public static int check3win(String state) {
	
	char[] temp = new char[3];
	
	temp[0] = state.charAt(0);
	temp[1] = state.charAt(1);
	temp[2] = state.charAt(2);
	
	if(temp[0] == EMPTY || temp[1] == EMPTY || temp[2] == EMPTY)
	{
		
		if((temp[0]==X && temp[1]==X))
			return 1;
		else if((temp[1]==X && temp[2]==X))
			return 1;
		else if((temp[0]==X && temp[2]==X))
			return 1;
		
	}
	
	return -1;
}

public static int check3block(String state) {
	
	char[] temp = new char[3];
	
	temp[0] = state.charAt(0);
	temp[1] = state.charAt(1);
	temp[2] = state.charAt(2);
	
	if(temp[0] == EMPTY || temp[1] == EMPTY || temp[2] == EMPTY)
	{
		
		if((temp[0]==O && temp[1]==O) )
			return 1;
		else if((temp[1]==O && temp[2]==O) )
			return 1;
		else if((temp[0]==O && temp[2]==O))
			return 1;
		
	}
	
	return -1;
}


public static int getWin(String state) {
	
	String temp;
	int check=0;
	int play = -1;

	for(int i=0;i<3;i++)
	{
		temp = row(state,i);
		check = check3win(temp);		
		if (check==1)
		{
			play = i+3*emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<3;i++)
	{
		temp = column(state,i);
		check = check3win(temp);		
		if (check==1)
		{
			play = 3*i+emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<2;i++)
	{
		temp = diagonal(state,i);
		check = check3win(temp);		
		if (check==1)
		{
			if(i == 0)
			play = 4*emptyPosition(temp);
			else if(i == 1)
			play = 2*emptyPosition(temp) + 2;
			return play;
		}
	}
	
	return -1;
}



public static int getBlock(String state) {
	
	String temp;
	int check=0;
	int play = -1;

	for(int i=0;i<3;i++)
	{
		temp = row(state,i);
		check = check3block(temp);		
		if (check==1)
		{
			play = i+3*emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<3;i++)
	{
		temp = column(state,i);
		check = check3block(temp);		
		if (check==1)
		{
			play = 3*i+emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<2;i++)
	{
		temp = diagonal(state,i);
		check = check3block(temp);		
		if (check==1)
		{
			if(i == 0)
			play = 4*emptyPosition(temp);
			else if(i == 1)
			play = 2*emptyPosition(temp) + 2;
			return play;
		}
	}
	
	return -1;
}

















public static int getBlockO(String state) {
	
	String temp;
	int check=0;
	int play = -1;

	for(int i=0;i<3;i++)
	{
		temp = row(state,i);
		check = check3win(temp);		
		if (check==1)
		{
			play = i+3*emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<3;i++)
	{
		temp = column(state,i);
		check = check3win(temp);		
		if (check==1)
		{
			play = 3*i+emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<2;i++)
	{
		temp = diagonal(state,i);
		check = check3win(temp);		
		if (check==1)
		{
			if(i == 0)
			play = 4*emptyPosition(temp);
			else if(i == 1)
			play = 2*emptyPosition(temp) + 2;
			return play;
		}
	}
	
	return -1;
}



public static int getWinO(String state) {
	
	String temp;
	int check=0;
	int play = -1;

	for(int i=0;i<3;i++)
	{
		temp = row(state,i);
		check = check3block(temp);		
		if (check==1)
		{
			play = i+3*emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<3;i++)
	{
		temp = column(state,i);
		check = check3block(temp);		
		if (check==1)
		{
			play = 3*i+emptyPosition(temp);
			return play;
		}
	}
	
	for(int i=0;i<2;i++)
	{
		temp = diagonal(state,i);
		check = check3block(temp);		
		if (check==1)
		{
			if(i == 0)
			play = 4*emptyPosition(temp);
			else if(i == 1)
			play = 2*emptyPosition(temp) + 2;
			return play;
		}
	}
	
	return -1;
}
















	
	// Return random available play
	public static int getRandomPlay(String state) {
		// Return invalid state
		if(state.length() != 9) return -1;
		
		// Get empty plays on board
		ArrayList<Integer> avail = new ArrayList<Integer>();
		for(int i=0; i<state.length(); i++) {
			if(state.charAt(i) == EMPTY) avail.add(i);
		}
		
		// Select random empty play
		int randomIndex = 0 + (int)(Math.random() * (avail.size()-1)); 
		return avail.get(randomIndex);
	}
	
	
	// Return reasonable play
	public static int getPlay(String state, int player) {
		
		//int player = 1;
		
		int play = -1;
		ArrayList<Integer> xPlays = new ArrayList<Integer>();
		ArrayList<Integer> oPlays = new ArrayList<Integer>();
		ArrayList<Integer> avail = new ArrayList<Integer>();
		
		// Collect X, O, and available plays
		for(int i=0; i<state.length(); i++) {
			char mark = state.charAt(i);
			if(mark == EMPTY) avail.add(i);
			else if(mark == X) xPlays.add(i);
			else if(mark == O) oPlays.add(i);
		}
		
		if(player == 0)
		{
		
		//If board is empty play at center
				
		if(xPlays.isEmpty() && oPlays.isEmpty())
		{
			play = 4;
			return play;
		}
		
		play = getWin(state);
		
		if(play!=-1)
			return play;
		
		play = getBlock(state);
		
		if(play!=-1)
			return play;
		
		play = edgeOplay(state);
		
		if(play!=-1)
			return play;
		
		play = playCorner(state);

		if(play!=-1)
			return play;
		
		else play = playEmpty(state);
		
		if(play!=-1)
			return play;
		
		}
		
		else 
		{
		
			play = getWinO(state);
			
			if(play!=-1)
				return play;
			
			play = getBlockO(state);
			
			if(play!=-1)
				return play;
						
			if(state.charAt(4) == EMPTY)
			return 4;
			
			
			play = playCornerO(state);
			
			if(play!=-1)
				return play;
			
			else play = playEmpty(state);
			
			if(play!=-1)
				return play;
			
		}
		
		return play;
	}

	public static void main(String args[]) {
		// Get the current state of the game
		Scanner scanner = new Scanner(System.in);
		int boardSize = scanner.nextInt();
		int p1 = scanner.nextInt();
		int p2 = scanner.nextInt();
		
		int[] board = new int[9];
		
		String state = "";
		
		for(int i=0;i<9;i++)
		{
			int mark = scanner.nextInt();
			if(mark == p1)
				state += X;
			else if (mark == p2)
				state+=O;
			else state+=EMPTY;
			
		}
		scanner.close();
		
		// Print random play to STDOUT
		//System.out.println(getRandomPlay(state));
		int player = playerTurn(state);
		// Print reasonable play to STDOUT
		System.out.println(getPlay(state,player)+1);	
		
	}
}
