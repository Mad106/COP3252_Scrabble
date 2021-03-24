import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Scrabble{
	
	private JFrame frame;
	private JFrame prompt;
	private TextField people;
	private TextField computers;
	private JButton start;
	private JLabel welcome;
	
	public static void main(String[] args){
		Scrabble game = new Scrabble();
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
		JLabel computers_text = new JLabel("players (computers)");
		computers_text.setHorizontalAlignment(JLabel.CENTER);
		prompt.add(computers_text);
		computers = new TextField();
		prompt.add(computers);
		
		start = new JButton("START");
		prompt.add(start);
		
		prompt.setLayout(new GridLayout(7,1));
		prompt.setVisible(true);
		
		//event handler
		ScrabbleHandler handler = new ScrabbleHandler();
		start.addActionListener(handler);
		
	}
	
	//inner class for event handling
	private class ScrabbleHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == start){
				
				int total = Integer.parseInt(people.getText()) + Integer.parseInt(computers.getText());
				
				if(total < 2){
					welcome.setText("Total of players under 2");
				}else if(total > 4){
					welcome.setText("Total of players over 4");
				}else{
					prompt.setVisible(false);
					
					frame = new JFrame("Scrabble");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(700,500);
					
					JLabel title = new JLabel("Scrabble");
					title.setHorizontalAlignment(JLabel.CENTER);
					frame.add(title,BorderLayout.PAGE_START);
					
					BoardPanel board = new BoardPanel(); //board is 15x15
					board.setLayout(new GridLayout(15,15));
					frame.add(board,BorderLayout.CENTER);
					
					JLabel temp = new JLabel("this is to show what happens");
					frame.add(temp,BorderLayout.LINE_END);
					
					frame.setVisible(true);
				}
				
				
				
				
			}
			
			
		}	
	}
	
}

class BoardPanel extends JPanel{
	
	public BoardPanel(){
		super();
		JLabel[][] labels = new JLabel[15][15];
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				switch(i){
					case 0:
						if(j == 0)
							labels[i][j] = new JLabel("DL");
						else if(j == 2)
							labels[i][j] = new JLabel("TW");
						else if(j == 6)
							labels[i][j] = new JLabel("TL");
						else if(j == 8)
							labels[i][j] = new JLabel("TL");
						else if(j == 11)
							labels[i][j] = new JLabel("DL");
						else if(j == 13)
							labels[i][j] = new JLabel("TW");
						else
							labels[i][j] = new JLabel("");
						break;
					case 1:
						if(j == 1)
							labels[i][j] = new JLabel("TL");
						else if(j == 5)
							labels[i][j] = new JLabel("DW");
						else if(j == 9)
							labels[i][j] = new JLabel("DL");
						else if(j == 12)
							labels[i][j] = new JLabel("TL");
						else if(j == 14)
							labels[i][j] = new JLabel("TW");
						else
							labels[i][j] = new JLabel("");
						break;
					case 2:
						if(j == 0)
							labels[i][j] = new JLabel("TW");
						else if(j == 4)
							labels[i][j] = new JLabel("DL");
						else if(j == 10)
							labels[i][j] = new JLabel("DW");
						else if(j == 13)
							labels[i][j] = new JLabel("TL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 3:
						if(j == 3)
							labels[i][j] = new JLabel("DW");
						else if(j == 7)
							labels[i][j] = new JLabel("DL");
						else if(j == 11)
							labels[i][j] = new JLabel("DL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 4:
						if(j == 2)
							labels[i][j] = new JLabel("DL");
						else if(j == 6)
							labels[i][j] = new JLabel("DW");
						else if(j == 8)
							labels[i][j] = new JLabel("DL");
						else if(j == 12)
							labels[i][j] = new JLabel("DW");
						else
							labels[i][j] = new JLabel("");
						break;
					case 5:
						if(j == 1)
							labels[i][j] = new JLabel("DW");
						else if(j == 5)
							labels[i][j] = new JLabel("DL");
						else if(j == 9)
							labels[i][j] = new JLabel("DW");
						else if(j == 13)
							labels[i][j] = new JLabel("DL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 6:
						if(j == 0)
							labels[i][j] = new JLabel("TL");
						else if(j == 4)
							labels[i][j] = new JLabel("DW");
						else if(j == 10)
							labels[i][j] = new JLabel("DL");
						else if(j == 14)
							labels[i][j] = new JLabel("TL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 7:
						if(j == 3)
							labels[i][j] = new JLabel("DL");
						else if(j == 11)
							labels[i][j] = new JLabel("DL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 8:
						if(j == 0)
							labels[i][j] = new JLabel("TL");
						else if(j == 4)
							labels[i][j] = new JLabel("DL");
						else if(j == 10)
							labels[i][j] = new JLabel("DW");
						else if(j == 14)
							labels[i][j] = new JLabel("TL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 9:
						if(j == 1)
							labels[i][j] = new JLabel("DL");
						else if(j == 5)
							labels[i][j] = new JLabel("DW");
						else if(j == 9)
							labels[i][j] = new JLabel("DL");
						else if(j == 13)
							labels[i][j] = new JLabel("DW");
						else
							labels[i][j] = new JLabel("");
						break;
					case 10:
						if(j == 2)
							labels[i][j] = new JLabel("DW");
						else if(j == 6)
							labels[i][j] = new JLabel("DL");
						else if(j == 8)
							labels[i][j] = new JLabel("DW");
						else if(j == 12)
							labels[i][j] = new JLabel("DL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 11:
						if(j == 0)
							labels[i][j] = new JLabel("DL");
						else if(j == 3)
							labels[i][j] = new JLabel("DL");
						else if(j == 7)
							labels[i][j] = new JLabel("DL");
						else if(j == 11)
							labels[i][j] = new JLabel("DW");
						else
							labels[i][j] = new JLabel("");
						break;
					case 12:
						if(j == 1)
							labels[i][j] = new JLabel("TL");
						else if(j == 4)
							labels[i][j] = new JLabel("DW");
						else if(j == 10)
							labels[i][j] = new JLabel("DL");
						else if(j == 14)
							labels[i][j] = new JLabel("TW");
						else
							labels[i][j] = new JLabel("");
						break;
					case 13:
						if(j == 0)
							labels[i][j] = new JLabel("TW");
						else if(j == 2)
							labels[i][j] = new JLabel("TL");
						else if(j == 5)
							labels[i][j] = new JLabel("DL");
						else if(j == 9)
							labels[i][j] = new JLabel("DW");
						else if(j == 13)
							labels[i][j] = new JLabel("TL");
						else
							labels[i][j] = new JLabel("");
						break;
					case 14:
						if(j == 1)
							labels[i][j] = new JLabel("TW");
						else if(j == 3)
							labels[i][j] = new JLabel("DL");
						else if(j == 6)
							labels[i][j] = new JLabel("TL");
						else if(j == 8)
							labels[i][j] = new JLabel("TL");
						else if(j == 12)
							labels[i][j] = new JLabel("TW");
						else if(j == 14)
							labels[i][j] = new JLabel("DL");
						else
							labels[i][j] = new JLabel("");
						break;
				}
				labels[i][j].setHorizontalAlignment(JLabel.CENTER);
				labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
		
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				this.add(labels[i][j]);
			}
		}
	}
	
}