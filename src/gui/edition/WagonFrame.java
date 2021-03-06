package gui.edition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import gui.elements.MyJFrame;
import gui.elements.SpinnerChamp;

import train.Wagon;

@SuppressWarnings("serial")
public class WagonFrame extends MyJFrame{	
	Wagon wagon;
	boolean nouveau;
	PanneauListe<Wagon> father;
	
	private SpinnerChamp id;
	
	public WagonFrame(Wagon w, PanneauListe<Wagon> f){
		
		
		father = f;
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		JPanel box = new JPanel();
			id = new SpinnerChamp("Id : ",9999);
			box.add(id);
		main.add(box);
		box = new JPanel();

			final String[] st = {"1er Classe","2ieme Classe","Wagon Bar"};
			final JComboBox<String> ComboBoxWagon = new JComboBox<String>(st);

			box.add(ComboBoxWagon);
		main.add(box);
		
		if(w == null){
			wagon = new Wagon(id.getValue(),ComboBoxWagon.getSelectedIndex()+1);
			nouveau = true;
		}
		else{
			wagon = w;
			nouveau = false;
		}
		
		box = new JPanel();
			JButton annuler = new JButton("Annuler");
			annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
	        	setVisible(false);
	        }});
			box.add(annuler);
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
        		
        		Integer type;
        		if(((String)ComboBoxWagon.getSelectedItem()).compareTo(st[0]) == 0)
        			type = Wagon.PREMIERE;
        		else if(((String)ComboBoxWagon.getSelectedItem()).compareTo(st[1]) == 0)
        			type = Wagon.SECONDE;
        		else
        			type = Wagon.BAR;
        		if(nouveau){
        			wagon = new Wagon(id.getValue(),type);
    				father.addElement(wagon);
        		}
        		else{
        			wagon.setId(id.getValue());
        			wagon.setType(type);
        			wagon.build();
        		}
        		
            	father.majList();
            	setVisible(false);
	        }});
			box.add(valider);
		main.add(box);
		
		// Mise a jour des champs
		if(wagon.getId() != null)
			id.setValue(wagon.getId());
		if(wagon.getType() != null && wagon.getType() == Wagon.PREMIERE)
			ComboBoxWagon.setSelectedIndex(0);
		else if(wagon.getType() != null && wagon.getType() == Wagon.SECONDE)
			ComboBoxWagon.setSelectedIndex(1);
		else if(wagon.getType() != null && wagon.getType() == Wagon.BAR)
			ComboBoxWagon.setSelectedIndex(2);
		
		add(main);
		
		positionner();
	}
}