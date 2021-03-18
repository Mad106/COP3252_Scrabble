import java.awt.*;
import javax.swing.*;

public class Scrabble{
	
	private JFrame frame;
	
	public static void main(String[] args){
		Scrabble game = new Scrabble();
		return;
	}
	
	public Scrabble(){
		//set up jframe
		frame = new JFrame("Scrabble");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
	}
	
}