package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	
	//CONSTRUCTEUR
	public Fenetre(){
		
		//initialization of our fenetre
		this.setTitle("Petit editeur de Victor et Hugues");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
		
		//JPanel
		JPanel pan = new JPanel();
		this.setContentPane(pan);
	}
}
