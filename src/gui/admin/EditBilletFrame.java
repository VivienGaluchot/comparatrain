package gui.admin;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import elements.Billet;
import gui.ChampTextField;
import gui.GroupPanel;
import gui.MyJFrame;
import gui.SpinnerChamp;

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
			SpinnerChamp idc = new SpinnerChamp("Id : ",9999);
				idc.setValue(billet.getClient().getId());
				idc.setEnabled(false);
				box.add(idc);
				ChampTextField nom = new ChampTextField("Nom : ");
				nom.setText(billet.getClient().getNom());
				nom.setEnabled(false);
				box.add(nom);
				ChampTextField prenom = new ChampTextField("Prenom : ");
				nom.setText(billet.getClient().getPrenom());
				prenom.setEnabled(false);
				box.add(prenom);
			main.add(box);
			
			box = new GroupPanel("Offre");
				JLabel offre = new JLabel(" billet.getOffreSegment().toString()");
				box.add(offre);
			main.add(box);
		
			add(main);
			positionner();
		}
	}
}