
package gamemorpiontoe;

import gamemorpiontoe.InterfaceGame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gamemorpiontoe.GameMP;



public class Grille extends JFrame implements ActionListener{

    private InterfaceGame unJeu;		
    private JButton[] tab;	
    				
    private int[] tabGagne; 
    			
    private boolean fini;
    private Color couleur;
    private JLabel message;

   
    public Grille() {
    	int i;
    	JButton bouton;
    	JPanel pGrille = new JPanel();
    	JPanel pControle = new JPanel();
    	JButton reStart;

    	message=new JLabel("Tic Tac Toe");
    	tab = new JButton[9];
    	tabGagne = new int[3];
    	setSize(300,200);
    	pGrille.setLayout(new GridLayout(3,3));
    	setTitle("Tic Tac Toe");

    	for(i = 0; i<9; i++){
    		bouton = new JButton("");
    		bouton.setActionCommand(""+i);
    	    bouton.addActionListener(this);

			pGrille.add(bouton);
			tab[i]=bouton;
    	}
    	couleur = tab[0].getBackground();

    	reStart = new JButton("Nouvelle Partie");
    	reStart.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent evt){
    			initialiser();
    		}
    	});

    	pControle.add(reStart);
    	pControle.add(message);
    	add(pGrille, BorderLayout.CENTER);
    	add(pControle, BorderLayout.SOUTH);

    	setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		unJeu = new GameMP();
		initialiser();
    }

   
    private void initialiser(){
    	for( int i = 0; i<9; i++){
    		tab[i].setBackground(couleur);
    		tab[i].setText("");
    	}
    		unJeu.initialise();
    	for(int i=0; i<3; i++)
    		tabGagne[i]=-1;
    	fini = false;
    	message.setText("");
    }

    public void actionPerformed (ActionEvent evt) {
    	String arg = evt.getActionCommand();
    	JButton bouton = (JButton)evt.getSource();

    	if(!fini && bouton.getText().equals("")){
    		bouton.setText("X");
    		boutonClique(Integer.parseInt(arg));
    	}

    }
 void boutonClique(int i){
    	if(fini)
    		return;
    	unJeu.setX(i);			
    	if(unJeu.gagnant("X",tabGagne)){
    		fini = true;
                marque(tabGagne);
    		message.setText("X gagne!");
    	}
    	else
    		if(!unJeu.isPartieNulle()){	
    			int cellule = unJeu.getO();
                

    			tab[cellule].setText("O");		
    											 // réfléter le choix de O
    			if(unJeu.gagnant("O",tabGagne)){
    				fini = true;
                                
    				marque(tabGagne);
                
    				message.setText("O gagne!");
    			}
    		}
    		else
    		{
    			fini = true;
    			message.setText("Partie nulle!");
    		}
    }

    
    public void marque(int[] t){
    	for(int i=0; i<3;i++){
          tab[t[i]].setBackground(Color.GREEN); 
    	}
    }

    public void testDebug(int t[]){
    	for(int i=0; i<t.length; i++){
    		if( i%2 == 0 && t[i] != -1)
    			tab[t[i]].setText("X");
    		else
    			if(i%2 == 1 && t[i] != -1)
    				tab[t[i]].setText("O");
    	}
    	unJeu.testDebug(t);
    }

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, UnsupportedLookAndFeelException, IllegalAccessException{

//    UIManager.setLookAndFeel(
//        UIManager.getCrossPlatformLookAndFeelClassName());
//        
    	Grille g = new Grille();
    	int[] essai = {0,2,4,5};
    	g.testDebug(essai);
    }

}
