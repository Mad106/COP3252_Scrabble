import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Scrabble{
	
	private static Scrabble game;
	private JFrame frame;	//main frame
	private JFrame prompt;	//initial prompt window
	private JFrame game_over;
	private TextField people;
	private JButton start;
	private JLabel welcome;		//changes if user types invalid number of players
	private BoardPanel board;
	private int total;			//total number of players
	private JButton player1[];	//arrays that contain the tiles that each player has
	private JButton player2[];
	private JButton player3[];
	private JButton player4[];
	private int turn;			//indicates whose turn it is
	private JButton end_turn;
	private int player_points[];
	private JLabel point_label[];
	private int turn_points;
	private JButton clicked;	//will refer to indexed tile
	private int index;			//so can shift tiles over
	private int replace;		//how many tiles to replace at end of turn
	private JButton end_game;
	private JButton restart;
	private JButton replace_tile;
	
	public static void main(String[] args){
		game = new Scrabble();
		return;
	}
	
	public Scrabble(){
		//prompt user
		prompt = new JFrame("Prompt");
		prompt.setSize(200,250);
		
		//welcome message
		welcome = new JLabel("Welcome");
		welcome.setHorizontalAlignment(JLabel.CENTER);
		prompt.add(welcome);
		JLabel number_info = new JLabel("2-4 players");
		number_info.setHorizontalAlignment(JLabel.CENTER);
		prompt.add(number_info);
		
		//request how many people are playing at that time
		JLabel people_text = new JLabel("Number of Players");
		people_text.setHorizontalAlignment(JLabel.CENTER);
		prompt.add(people_text);
		people = new TextField();
		prompt.add(people);
		
		start = new JButton("START");
		prompt.add(start);
		
		prompt.setLayout(new GridLayout(7,1));
		prompt.setVisible(true);
		
		//event handler
		ScrabbleHandler handler = new ScrabbleHandler();	//once start button clicked
		start.addActionListener(handler);
		
	}
	
	char setPlayerTiles(){	//returns valid tile
		
		Random rand = new Random();
		int index;
		
		do{
			index = rand.nextInt(27);
		}while(board.available[index] == 0);
		
		board.available[index]--;
		
		switch(index){
			case 0: return 'A';
			case 1: return 'B';
			case 2: return 'C';
			case 3: return 'D';
			case 4: return 'E';
			case 5: return 'F';
			case 6: return 'G';
			case 7: return 'H';
			case 8: return 'I';
			case 9: return 'J';
			case 10: return 'K';
			case 11: return 'L';
			case 12: return 'M';
			case 13: return 'N';
			case 14: return 'O';
			case 15: return 'P';
			case 16: return 'Q';
			case 17: return 'R';
			case 18: return 'S';
			case 19: return 'T';
			case 20: return 'U';
			case 21: return 'V';
			case 22: return 'W';
			case 23: return 'X';
			case 24: return 'Y';
			case 25: return 'Z';
			case 26: return ' ';
		}
		
		return ' ';
	}
	
	int points(JButton tile){
		
		switch(tile.getText().charAt(0)){	//given tile and returns points
			case 'A': return 1;
			case 'B': return 3;
			case 'C': return 3;
			case 'D': return 2;
			case 'E': return 1;
			case 'F': return 4;
			case 'G': return 2;
			case 'H': return 4;
			case 'I': return 1;
			case 'J': return 8;
			case 'K': return 5;
			case 'L': return 1;
			case 'M': return 3;
			case 'N': return 1;
			case 'O': return 1;
			case 'P': return 3;
			case 'Q': return 10;
			case 'R': return 1;
			case 'S': return 1;
			case 'T': return 1;
			case 'U': return 1;
			case 'V': return 4;
			case 'W': return 4;
			case 'X': return 8;
			case 'Y': return 4;
			case 'Z': return 10;
			case ' ': return 0;
		}
		
		return 0;
	}
	
	JButton[] replaceTile(JButton player[]){
		String letter = board.clicked.getText();
		for(int i = 0; i < 7; i++){
			if(letter == player[i].getText()){
				player[i].setText(String.valueOf(game.setPlayerTiles()));
			}
		}
		
		switch(letter.charAt(0)){	//given tile and returns points
			case 'A': board.available[0]++;
			case 'B': board.available[1]++;
			case 'C': board.available[2]++;
			case 'D': board.available[3]++;
			case 'E': board.available[4]++;
			case 'F': board.available[5]++;
			case 'G': board.available[6]++;
			case 'H': board.available[7]++;
			case 'I': board.available[8]++;
			case 'J': board.available[9]++;
			case 'K': board.available[10]++;
			case 'L': board.available[11]++;
			case 'M': board.available[12]++;
			case 'N': board.available[13]++;
			case 'O': board.available[14]++;
			case 'P': board.available[15]++;
			case 'Q': board.available[16]++;
			case 'R': board.available[17]++;
			case 'S': board.available[18]++;
			case 'T': board.available[19]++;
			case 'U': board.available[20]++;
			case 'V': board.available[21]++;
			case 'W': board.available[22]++;
			case 'X': board.available[23]++;
			case 'Y': board.available[24]++;
			case 'Z': board.available[25]++;
			case ' ': board.available[26]++;
		}
		
		return player;
		
	}
	
	
	//inner class for event handling
	private class ScrabbleHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){	//start button was clicked
			if(event.getSource() == start){
				
				total = Integer.parseInt(people.getText());	//input from textfield
				
				if(total < 2){
					welcome.setText("Total of players under 2");
				}else if(total > 4){
					welcome.setText("Total of players over 4");
				}else{	//valid number of players
					prompt.setVisible(false);		//user can no longer see prompt window
					
					turn_points = 0;
					player_points = new int[4];
					for(int i = 0; i < total; i++){
						player_points[i] = 0;
					}
					replace = 0;
					ScrabbleHandler handler = new ScrabbleHandler();	//event handler
					
					frame = new JFrame("Scrabble");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(700,650);
					frame.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					
					//title at top
					JLabel title = new JLabel("Scrabble");
					title.setHorizontalAlignment(JLabel.CENTER);
					c.fill = GridBagConstraints.HORIZONTAL;
					frame.add(title);
					
					//board built from custom panel
					board = new BoardPanel(); //board is 15x15
					board.setLayout(new GridLayout(15,15));
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 1;
					frame.add(board,c);
					board.clicked = new JButton();
					board.clicked = null;
					
					//States points per letters
					JLabel points = new JLabel();
					points.setText("<HTML>Points per letter: <BR> 1 point: A   E   I   O   U   L   N   S   T   R <BR> 2 points: D   G <BR> 3 points: B   C   M   P <BR> 4 points: F   H   V   W   Y <BR> 5 points: K <BR> 8 points: J X <BR> 10 points: Q Z </HTML>");
					c.gridx = 1;
					c.gridy = 1;
					c.insets = new Insets(20,20,0,0);
					c.anchor= GridBagConstraints.NORTHEAST;
					frame.add(points,c);
					
					//replace selected tile button
					replace_tile = new JButton("Replace Selected Tile");
					c.gridx = 1;
					c.gridy = 2;
					frame.add(replace_tile,c);
					replace_tile.addActionListener(handler);
					
					//button indicates whose turn it is and when clicked shifts to new player
					turn = 1;
					end_turn = new JButton("Player 1 End Turn");
					c.gridx = 1;
					c.gridy = 3;
					frame.add(end_turn,c);
					//event handler
					end_turn.addActionListener(handler);
					
					end_game = new JButton("End Game");
					c.gridx = 1;
					c.gridy = 4;
					frame.add(end_game,c);
					end_game.addActionListener(handler);
					
					//second half of window (still in process)
					JPanel right_side = new JPanel();
					right_side.setLayout(new GridBagLayout());
					GridBagConstraints d = new GridBagConstraints();
					frame.add(right_side);
					
					point_label = new JLabel[total];
					
					//goes through for each player
					for(int i = 0; i < total; i++){
						int num = i + 1;
						JLabel player_num = new JLabel();
						player_num.setText("Player " + num);
						d.gridx = 2;
						d.gridy = (3*i) + 1;
						right_side.add(player_num,d);
						
						point_label[i] = new JLabel("Points: " + player_points[i]);
						d.gridx = 4;
						d.gridy = (3*i) + 1;
						right_side.add(point_label[i],d);
						
						char letter;
						//still need to set up tile arrays
						for(int j = 0; j < 7; j++){
							d.gridx = 2 + j;
							d.gridy = (3*i) + 2;
							
							if(j == 0){	//initialize arrays that will be used for game
								switch(i){
									case 0:
										player1 = new JButton[7];
										break;
									case 1:
										player2 = new JButton[7];
										break;
									case 2:
										player3 = new JButton[7];
										break;
									case 3:
										player4 = new JButton[7];
								}
							}
							
							switch(i){
								case 0:
									letter = game.setPlayerTiles();
									player1[j] = new JButton(String.valueOf(letter));
									right_side.add(player1[j],d);
									player1[j].addActionListener(handler);
									break;
								case 1:
									letter = game.setPlayerTiles();
									player2[j] = new JButton(String.valueOf(letter));
									right_side.add(player2[j],d);
									player2[j].addActionListener(handler);
									break;
								case 2:
									letter = game.setPlayerTiles();
									player3[j] = new JButton(String.valueOf(letter));
									right_side.add(player3[j],d);
									player3[j].addActionListener(handler);
									break;
								case 3:
									letter = game.setPlayerTiles();
									player4[j] = new JButton(String.valueOf(letter));
									right_side.add(player4[j],d);
									player4[j].addActionListener(handler);
							}
							
						}
						
					}
					
					turn = 1;	//start with player1
					frame.setVisible(true);
				}
				
			}else if(event.getSource() == end_turn){
				//adds total points for that turn to players total and goes to next player
				if(board.tw)
					turn_points *= 3;
				if(board.dw)
					turn_points *= 2;
				
				player_points[turn-1] += turn_points;
				point_label[turn-1].setText("Points: " + player_points[turn-1]);
				
				char letter;
				
				while(replace > 0){
					switch(turn){
						case 1:
							letter = game.setPlayerTiles();
							player1[7 - replace].setText(String.valueOf(letter));
							break;
						case 2:
							letter = game.setPlayerTiles();
							player2[7 - replace].setText(String.valueOf(letter));
							break;
						case 3:
							letter = game.setPlayerTiles();
							player3[7 - replace].setText(String.valueOf(letter));
							break;
						case 4:
							letter = game.setPlayerTiles();
							player4[7 - replace].setText(String.valueOf(letter));
							break;
					}
					replace--;
				}
				
				switch(turn){
					case 1:
						turn = 2;
						end_turn.setText("Player 2 End Turn");
						break;
					case 2:
						if(total < 3){
							turn = 1;
							end_turn.setText("Player 1 End Turn");
						}else{
							turn = 3;
							end_turn.setText("Player 3 End Turn");
						}
						break;
					case 3:
						if(total < 4){
							turn = 1;
							end_turn.setText("Player 1 End Turn");
						}else{
							turn = 4;
							end_turn.setText("Player 4 End Turn");
						}
						break;
					case 4:
						turn = 1;
						end_turn.setText("Player 1 End Turn");
						break;
				}
				turn_points = 0;
				board.dw = false;
				board.tw = false;
				
			}else if(event.getSource() == end_game){
				frame.setVisible(false);
				game_over = new JFrame("Game Over");
				game_over.setSize(200,200);
				game_over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JLabel end1 = new JLabel("Game Ended");
				end1.setHorizontalAlignment(JLabel.CENTER);
				game_over.add(end1);
				
				int high_score = 0;
				StringBuilder winner = new StringBuilder("Player 1");
				for(int i = 0; i < total; i++){
					if(player_points[i] > high_score){
						high_score = player_points[i];
						winner.replace(0,7,"Player " + (i+1));
					}
				}
				
				JLabel end2 = new JLabel("Winner");
				end2.setHorizontalAlignment(JLabel.CENTER);
				game_over.add(end2);
				
				JLabel end3 = new JLabel(String.valueOf(winner));
				end3.setHorizontalAlignment(JLabel.CENTER);
				game_over.add(end3);
				
				JLabel end4 = new JLabel(String.valueOf(high_score));
				end4.setHorizontalAlignment(JLabel.CENTER);
				game_over.add(end4);
				
				restart = new JButton("Restart Game");
				game_over.add(restart);
				ScrabbleHandler handler = new ScrabbleHandler();
				restart.addActionListener(handler);
				
				game_over.setLayout(new GridLayout(5,1));
				game_over.setVisible(true);
				
			}else if(event.getSource() == restart){
				game = new Scrabble();
			}else if(event.getSource() == replace_tile){
				char letter = game.setPlayerTiles();
				switch(turn){
					case 1:
						player1 = replaceTile(player1);
						break;
					case 2:
						player2 = replaceTile(player2);
						break;
					case 3:
						player3 = replaceTile(player3);
						break;
					case 4:
						player4 = replaceTile(player4);
						break;
				}
			}else{	//if a player's tile is clicked
				JButton temp[] = new JButton[7];
				switch(turn){	//only valid it is their turn
					case 1:
						temp = player1;
						break;
					case 2:
						temp = player2;
						break;
					case 3:
						temp = player3;
						break;
					case 4:
						temp = player4;
						break;
				}
				for(int i = 0; i < 7; i++){
					if(event.getSource() == temp[i]){
						board.clicked = temp[i];
						index = i;
						i = 7;
					}
				}
				
			}
			
			
		}	
	}
	
	private class BoardPanel extends JPanel{
	
		private JButton[][] tiles;	//board tiles
		private int available[];	//bag of tiles
		private JButton clicked;
		private boolean dw;
		private boolean tw;
		
		public BoardPanel(){
			super();
			
			dw = false;
			tw = false;
			
			//keep record of how many tiles "in bag"
			available = new int[27];
			available[0] = 9;	//A
			available[1] = 2;	//B
			available[2] = 2;	//etc.
			available[3] = 4;
			available[4] = 12;
			available[5] = 2;
			available[6] = 3;
			available[7] = 2;
			available[8] = 9;
			available[9] = 1;
			available[10] = 1;
			available[11] = 4;
			available[12] = 2;
			available[13] = 6;
			available[14] = 8;
			available[15] = 2;
			available[16] = 1;
			available[17] = 6;
			available[18] = 4;
			available[19] = 6;
			available[20] = 4;
			available[21] = 2;
			available[22] = 2;
			available[23] = 1;
			available[24] = 2;
			available[25] = 1;	//Z
			available[26] = 2;	//blank tiles
			
			tiles = new JButton[15][15];	//15x15 board
			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					switch(i){
						case 0:
							if(j == 0)
								tiles[i][j] = new JButton("DL");
							else if(j == 2)
								tiles[i][j] = new JButton("TW");
							else if(j == 6)
								tiles[i][j] = new JButton("TL");
							else if(j == 8)
								tiles[i][j] = new JButton("TL");
							else if(j == 11)
								tiles[i][j] = new JButton("DL");
							else if(j == 13)
								tiles[i][j] = new JButton("TW");
							else
								tiles[i][j] = new JButton("");
							break;
						case 1:
							if(j == 1)
								tiles[i][j] = new JButton("TL");
							else if(j == 5)
								tiles[i][j] = new JButton("DW");
							else if(j == 9)
								tiles[i][j] = new JButton("DL");
							else if(j == 12)
								tiles[i][j] = new JButton("TL");
							else if(j == 14)
								tiles[i][j] = new JButton("TW");
							else
								tiles[i][j] = new JButton("");
							break;
						case 2:
							if(j == 0)
								tiles[i][j] = new JButton("TW");
							else if(j == 4)
								tiles[i][j] = new JButton("DL");
							else if(j == 10)
								tiles[i][j] = new JButton("DW");
							else if(j == 13)
								tiles[i][j] = new JButton("TL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 3:
							if(j == 3)
								tiles[i][j] = new JButton("DW");
							else if(j == 7)
								tiles[i][j] = new JButton("DL");
							else if(j == 11)
								tiles[i][j] = new JButton("DL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 4:
							if(j == 2)
								tiles[i][j] = new JButton("DL");
							else if(j == 6)
								tiles[i][j] = new JButton("DW");
							else if(j == 8)
								tiles[i][j] = new JButton("DL");
							else if(j == 12)
								tiles[i][j] = new JButton("DW");
							else
								tiles[i][j] = new JButton("");
							break;
						case 5:
							if(j == 1)
								tiles[i][j] = new JButton("DW");
							else if(j == 5)
								tiles[i][j] = new JButton("DL");
							else if(j == 9)
								tiles[i][j] = new JButton("DW");
							else if(j == 13)
								tiles[i][j] = new JButton("DL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 6:
							if(j == 0)
								tiles[i][j] = new JButton("TL");
							else if(j == 4)
								tiles[i][j] = new JButton("DW");
							else if(j == 10)
								tiles[i][j] = new JButton("DL");
							else if(j == 14)
								tiles[i][j] = new JButton("TL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 7:
							if(j == 3)
								tiles[i][j] = new JButton("DL");
							else if(j == 7)
								tiles[i][j] = new JButton("*");
							else if(j == 11)
								tiles[i][j] = new JButton("DL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 8:
							if(j == 0)
								tiles[i][j] = new JButton("TL");
							else if(j == 4)
								tiles[i][j] = new JButton("DL");
							else if(j == 10)
								tiles[i][j] = new JButton("DW");
							else if(j == 14)
								tiles[i][j] = new JButton("TL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 9:
							if(j == 1)
								tiles[i][j] = new JButton("DL");
							else if(j == 5)
								tiles[i][j] = new JButton("DW");
							else if(j == 9)
								tiles[i][j] = new JButton("DL");
							else if(j == 13)
								tiles[i][j] = new JButton("DW");
							else
								tiles[i][j] = new JButton("");
							break;
						case 10:
							if(j == 2)
								tiles[i][j] = new JButton("DW");
							else if(j == 6)
								tiles[i][j] = new JButton("DL");
							else if(j == 8)
								tiles[i][j] = new JButton("DW");
							else if(j == 12)
								tiles[i][j] = new JButton("DL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 11:
							if(j == 0)
								tiles[i][j] = new JButton("DL");
							else if(j == 3)
								tiles[i][j] = new JButton("DL");
							else if(j == 7)
								tiles[i][j] = new JButton("DL");
							else if(j == 11)
								tiles[i][j] = new JButton("DW");
							else
								tiles[i][j] = new JButton("");
							break;
						case 12:
							if(j == 1)
								tiles[i][j] = new JButton("TL");
							else if(j == 4)
								tiles[i][j] = new JButton("DW");
							else if(j == 10)
								tiles[i][j] = new JButton("DL");
							else if(j == 14)
								tiles[i][j] = new JButton("TW");
							else
								tiles[i][j] = new JButton("");
							break;
						case 13:
							if(j == 0)
								tiles[i][j] = new JButton("TW");
							else if(j == 2)
								tiles[i][j] = new JButton("TL");
							else if(j == 5)
								tiles[i][j] = new JButton("DL");
							else if(j == 9)
								tiles[i][j] = new JButton("DW");
							else if(j == 13)
								tiles[i][j] = new JButton("TL");
							else
								tiles[i][j] = new JButton("");
							break;
						case 14:
							if(j == 1)
								tiles[i][j] = new JButton("TW");
							else if(j == 3)
								tiles[i][j] = new JButton("DL");
							else if(j == 6)
								tiles[i][j] = new JButton("TL");
							else if(j == 8)
								tiles[i][j] = new JButton("TL");
							else if(j == 12)
								tiles[i][j] = new JButton("TW");
							else if(j == 14)
								tiles[i][j] = new JButton("DL");
							else
								tiles[i][j] = new JButton("");
							break;
					}
					tiles[i][j].setHorizontalAlignment(SwingConstants.CENTER);	//center text
					tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));	//draw border
					
					//event handler
					BoardHandler handler = new BoardHandler();	//when user clicks a button
					tiles[i][j].addActionListener(handler);
					
				}
			}
			
			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					this.add(tiles[i][j]);
				}
			}
			
		}
		
		//inner class for event handling
		private class BoardHandler implements ActionListener{
			public void actionPerformed(ActionEvent event){
				
				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						if(event.getSource() == tiles[i][j]){
							//if a tile has been clicked add points and change board tile text
							if(clicked != null){
								if(tiles[i][j].getText() == "DL"){
									game.turn_points += (game.points(clicked)*2);
								}else if(tiles[i][j].getText() == "DW"){
									dw = true;
									game.turn_points += game.points(clicked);
								}else if(tiles[i][j].getText() == "TL"){
									game.turn_points += (game.points(clicked)*3);
								}else if(tiles[i][j].getText() == "TW"){
									tw = true;
									game.turn_points += game.points(clicked);
								}else{
									game.turn_points += game.points(clicked);
								}
								tiles[i][j].setText(clicked.getText());
								tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));	//draw border
								clicked = null;
								game.replace++;
								
								switch(game.turn){
									case 1:
										for(int s = game.index; s < 7 - game.replace; s++){
											game.player1[s].setText(game.player1[s + 1].getText());
										}
										player1[7 - game.replace].setText(" ");
										break;
									case 2:
										for(int s = game.index; s < 7 - game.replace; s++){
											game.player2[s].setText(game.player2[s + 1].getText());
										}
										player2[7 - game.replace].setText(" ");
										break;
									case 3:
										for(int s = game.index; s < 7 - game.replace; s++){
											game.player3[s].setText(game.player3[s + 1].getText());
										}
										player3[7 - game.replace].setText(" ");
										break;
									case 4:
										for(int s = game.index; s < 7 - game.replace; s++){
											game.player4[s].setText(game.player4[s + 1].getText());
										}
										player4[7 - game.replace].setText(" ");
										break;
								}
							}
							
							
						}
					}
				}
				
			}
		}
	}
	
}

