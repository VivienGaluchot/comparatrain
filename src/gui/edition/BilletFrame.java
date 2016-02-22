package gui.edition;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.elements.PanneauGroupe;
import gui.elements.MyJFrame;

import offre.Billet;

@SuppressWarnings("serial")
public class BilletFrame extends MyJFrame{
	Billet billet;

	public BilletFrame(Billet b, PanneauListe<Billet> f){
		billet = b;
		setTitle("Informations");
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		if(billet != null){
			JPanel box = new PanneauGroupe("Client");				
				JTextArea offre = new JTextArea(
						"Id : "+billet.getClient().getId() + "\n" +
						"Nom : "+billet.getClient().getNom() + "\n" +
						"Prenom : "+ billet.getClient().getPrenom());
				offre.setEditable(false);
				offre.setBackground(this.getBackground());
				box.add(offre);
			main.add(box);
			
			box = new PanneauGroupe("Offre");
				offre = new JTextArea(
						billet.getSegment().toString() + "\n" +
						"Train : " + billet.getTrain().getId() + "\n" +
						billet.strPlace());
				offre.setEditable(false);
				offre.setBackground(this.getBackground());
				box.add(offre);
			main.add(box);
		}
		else{
			JPanel box = new PanneauGroupe("Information");				
				JTextArea offre = new JTextArea("Ajout de billet non implémenté");
				offre.setEditable(false);
				offre.setBackground(this.getBackground());
				box.add(offre);
			main.add(box);
		}
		
		add(main);
		positionner();
	}
}