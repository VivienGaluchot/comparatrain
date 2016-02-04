package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import comparaison.Comparateur;
import train.Train;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class AdminTrainFrame extends JFrame{
	public AdminTrainFrame(Comparateur comp){
		setTitle("Trains");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width;
		int hauteur = dim.height;
		setBounds(largeur/2-215,hauteur/2-170,430,340);
		
		DefaultListModel<Train> listeM = new DefaultListModel<Train>();
		
		for(Train t : comp.getData().getTrains())
			listeM.addElement(t);
		
		JScrollPane scrollPane = new JScrollPane(new JList<Train>(listeM));
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		GroupPanel group1 = new GroupPanel("Liste des trains");
		group1.add(scrollPane);
		
		main.add(group1);
		
		this.add(main);
	}
}
