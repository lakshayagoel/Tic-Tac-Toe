import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener{
	
	
	public static int Board_Size=3;
	public static enum GameStatus{
		Incomplete,XWins,ZWins,Tie
	}
	

	private JButton[][] buttons=new JButton[Board_Size][Board_Size];

	boolean crossTurn=true;
	
	//constructor
	public TicTacToe() {
		
		
		super.setTitle("TicTacToe");
		super.setSize(800, 800);
		
		GridLayout grid=new GridLayout(Board_Size,Board_Size);
		
		super.setLayout(grid);  
		
		Font font=new Font("Comic Sans",1,150);
		
	//now we create button for every row & col
		for(int row=0;row<Board_Size;row++) {
			for(int col=0;col<Board_Size;col++) {
				JButton button=new JButton("");
				
	//storing JButton object at every index
				buttons[row][col]=button;
				
		//setting the font of every button
				button.setFont(font);
				
				
				button.addActionListener(this);
				
				
				 
				super.add(button); 
				
			}
		}
		
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//clickedButton reference will be pointing to the button that was clicked
		JButton clickedButton=(JButton)e.getSource();
		
		makeMove(clickedButton);
		
		
		GameStatus gs=this.getGameStatus();
		
		if(gs==GameStatus.Incomplete) {
			return;
		}
		
		declareWinner(gs);
		
		int choice=JOptionPane.showConfirmDialog(this, "Do you want to restart the game ?");
		
		if(choice==JOptionPane.YES_OPTION) {
			for(int row=0;row<Board_Size;row++) {
				for(int col=0;col<Board_Size;col++) {
					buttons[row][col].setText("");
		}
		
	}
			crossTurn=true;
			
		}else {
			super.dispose(); //terminate the application 
			
		}
			
			
		}
	
	

	private void makeMove(JButton clickedButton) {
		
		String btntext=clickedButton.getText();
		if(btntext.length()>0) {
			
			JOptionPane.showMessageDialog(this, "Invalid Move");
			
		}else { //if btntext.length==0
			if(crossTurn) {
				clickedButton.setText("X");
			}else {
				clickedButton.setText("0");
			}
			
			crossTurn=!crossTurn;
			
		}
	}
	
	
	
	private GameStatus getGameStatus() {
		
		//text1 will denote text of 1st button
		String text1="",text2="";
		
		int row=0,col=0;
		
		//to check text in all the rows one by one
		row=0;
		while(row<Board_Size) {
			col=0;
			while(col<Board_Size-1) {
				
				text1=buttons[row][col].getText();
				
				text2=buttons[row][col+1].getText();
				
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				col++;
			}
			if(col==Board_Size-1) {
				if(text1.equals("X")) {
					return GameStatus.XWins;
				}else {
					return GameStatus.ZWins;
				}
			}
			row++;
		}
		
		
		//to check equality of text in all the cols one by one
				col=0;
				while(col<Board_Size) {
					row=0;
					while(row<Board_Size-1) {
						
						text1=buttons[row][col].getText();
						
						text2=buttons[row+1][col].getText();
						
						if(!text1.equals(text2) || text1.length()==0) {
							break;
						}
						row++;
					}
					if(row==Board_Size-1) {
						if(text1.equals("X")) {
							return GameStatus.XWins;
						}else {
							return GameStatus.ZWins;
						}
					}
					col++;
				}
				
				
				//logic for diagonal1
				row=0;
				col=0;
				while(row<Board_Size-1) {
					text1=buttons[row][col].getText();
			
					text2=buttons[row+1][col+1].getText();
					
					if(!text1.equals(text2) || text1.length()==0) {
						break;
					}
					
					row++;
					col++;
					
					
				}
				if(row==Board_Size-1) {
					if(text1.equals("X")) {
						return GameStatus.XWins;
					}else {
						return GameStatus.ZWins;
					}
					
				}
				
				
				
				row=Board_Size-1;
				col=0;
				while(row>0) {
					text1=buttons[row][col].getText();
			
					text2=buttons[row-1][col+1].getText();
					
					if(!text1.equals(text2) || text1.length()==0) {
						break;
					}
					
					row--;
					col++;
					
					
				}
				if(row==0) {
					if(text1.equals("X")) {
						return GameStatus.XWins;
					}else {
						return GameStatus.ZWins;
					}
					
				}
				
				
				
		//when no one has won
				String txt="";
				for( row=0;row<Board_Size;row++) {
					for( col=0;col<Board_Size;col++) {
						txt=buttons[row][col].getText();
						//if empty cell found
						if(txt.length()==0) {
							return GameStatus.Incomplete;
						}
					}
				}
				
				return GameStatus.Tie;
				
	}
	
	
	private void declareWinner(GameStatus gs) {
		
		if(gs==GameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "X Wins");
		}else if(gs==GameStatus.ZWins) {
			JOptionPane.showMessageDialog(this, "Z Wins");
		}else {
			JOptionPane.showMessageDialog(this, "It is a Tie");
		}
	}


}
