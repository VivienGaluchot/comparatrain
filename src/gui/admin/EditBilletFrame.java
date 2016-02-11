package gui.admin;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import elements.Billet;
import gui.GroupPanel;
import gui.MyJFrame;

@SuppressWarnings("serial")
public class EditBilletFrame extends MyJFrame{
	Billet billet;

	
	public EditBilletFrame(Billet b, ListPanel<Billet> f){
		billet = b;
		
		setTitle("Informations");
		if(billet != null){
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		
			JPanel box = new GroupPanel("Client");
			JLabel idc = new JLabel("Id : "+billet.getClient().getId());
				box.add(idc);
				JLabel nom = new JLabel("Nom : "+billet.getClient().getNom());
				box.add(nom);
				JLabel prenom = new JLabel("Prenom : "+ billet.getClient().getPrenom());
				box.add(prenom);
			main.add(box);
			
			box = new GroupPanel("Offre");
				JLabel offre = new JLabel( billet.getSegment().toString());
				box.add(offre);
				offre = new JLabel("Train : " + billet.getTrain().getId());
				box.add(offre);
				offre = new JLabel("Place : " + billet.getSiege());
				box.add(offre);
			main.add(box);
		
			add(main);
			positionner();
		}
	}
}