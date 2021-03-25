import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Scrabble{
	
	private static Scrabble game;
	private JFrame frame;	//main frame
	private JFrame prompt;	//initial prompt window
	private TextField people;
	private TextField computers;
	private JButton start;
	private JLabel welcome;		//changes if user types invalid number of players
	private BoardPanel board;
	private int total;			//total number of players
	private JButton player1[];	//arrays that contain the tiles that each player has
	private JButton player2[];
	private JButton player3[];
	private JButton player4[];
	
	public static void main(String[] args){
		game = new Scrabble();
		return;
	}
	
	public Scrabble(){
		//prompt user
		prompt = new JFrame("Prompt");
		prompt.setSize(200,250);
		
		welcome = new JLabel("Welcome");
		welcome.setHorizontalAlignment(JLabel.CENTER);
		prompt.add(welcome);
		JLabel number_info = new JLabel("2-4 players");
		number_info.setHorizontalAlignment(JLabel.CENTER);
		prompt.add(number_info);
		
		JLabel people_text = new JLabel("players (people)");
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
	
	char setPlayerTiles(){	//returns replacement tile
		
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
					
					frame = new JFrame("Scrabble");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(700,500);
					frame.setLayout(new GridBagLayout());
					GridBagConstraints c = new GridBagConstraints();
					
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
					
					//second half of window (still in process)
					JPanel right_side = new JPanel();
					right_side.setLayout(new GridBagLayout());
					GridBagConstraints d = new GridBagConstraints();
					frame.add(right_side);
					
					//goes through for each player
					for(int i = 0; i < total; i++){
						int num = i + 1;
						JLabel player_num = new JLabel();
						player_num.setText("Player " + num);
						//player_num.setHorizontalAlignment(JLabel.CENTER);
						d.gridx = 2;
						d.gridy = (3*i) + 1;
						right_side.add(player_num,d);
						char letter;
						//still need to set up tile arrays
						for(int j = 0; j < 7; j++){
							d.gridx = 2;
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
									break;
								case 1:
									letter = game.setPlayerTiles();
									player2[j] = new JButton(String.valueOf(letter));
									right_side.add(player2[j],d);
									break;
								case 2:
									letter = game.setPlayerTiles();
									player3[j] = new JButton(String.valueOf(letter));
									right_side.add(player3[j],d);
									break;
								case 3:
									letter = game.setPlayerTiles();
									player4[j] = new JButton(String.valueOf(letter));
									right_side.add(player4[j],d);
							}
						}
						
					}
					
				/*	JLabel temp = new JLabel("this is to show what happens");
					frame.add(temp,BorderLayout.LINE_END);
				*/	
					frame.setVisible(true);
				}
				
			}
			
			
		}	
	}
	
	private class BoardPanel extends JPanel{
	
		private JButton[][] tiles;	//board tiles
		private int available[];	//bag of tiles
		
		public BoardPanel(){
			super();
			
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
							
							
							
						}
					}
				}
				
			}
		}
	}
	
}

