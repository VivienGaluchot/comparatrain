package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import comparaison.Comparateur;
import defaut.Erreur;
import modele.Escale;
import modele.Gare;
import modele.Horaire;

public class PanneauAdmin extends JPanel{
	
	private GareComboBox comboBoxD;
	private GareComboBox comboBoxA;
	private GareComboBox comboBoxE;
	private JLabel lblHoraires;

	private ChampHoraire champHoraire1;
	private ChampHoraire champHoraire2;
	private ChampHoraire champHoraire3;
	private ChampHoraire champHoraire4;
	
	private JButton ajouterE;
	private JButton ajouter;
	private JButton deco;
	
	private ArrayList<Escale> escales = new ArrayList<Escale>();
	
	public void ClearOnClick(JTextField textField){
		String save = textField.getText();
		textField.addFocusListener(new FocusListener(){
	        public void focusGained(FocusEvent e){
	            textField.setText("");
	            textField.setForeground(Color.BLACK);
	        }

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals("")){
					textField.setForeground(new Color(100,100,100));
					textField.setText(save);
				}
				else textField.removeFocusListener(this);
			}
	    });
	}
	
	
	public PanneauAdmin(Comparateur comp,JTabbedPane onglets){
		
		Color color =new Color(100,100,100);
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel box1 = new GroupPanel("Départ");
			comboBoxD = new GareComboBox("Gare de départ : ",comp);
			box1.add(comboBoxD);
			champHoraire1 = new ChampHoraire("Horaire : ");
		box1.add(champHoraire1);
		this.add(box1);
		
		JPanel box2 = new GroupPanel("Escales");
			JPanel box20 = new JPanel();
			DefaultListModel<String> listeM = new DefaultListModel();
			JList liste = new JList(listeM);
			JScrollPane scrollPane = new JScrollPane(liste);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(500,120));
			box20.add(scrollPane);
		box2.add(box20);
		
		JPanel box22 = new JPanel();
			comboBoxE = new GareComboBox("Gare : ",comp);
			box22.add(comboBoxE);
			ajouterE = new JButton("Ajouter!");
			ajouterE.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	            	Horaire h1 = champHoraire2.getHoraire();
	            	Horaire h2 = champHoraire3.getHoraire();
	            	if(h1.estInit() && h2.estInit()){
						try {
							escales.add( new Escale((Gare) comboBoxE.getSelectedItem(),h1,h2));
							listeM.addElement(comboBoxE.getSelectedItem().toString()+"   "+ h1.toStringLong() +"   "+ h2.toStringLong() +"\n");
						} catch (Erreur e1) {
							champHoraire2.setWrong(true);
							champHoraire3.setWrong(true);
							System.out.println(e1);
						}
	            	}
	            }
	        });
			box22.add(ajouterE);
		box2.add(box22);
		champHoraire2 = new ChampHoraire("Horaire d'arrivée : ");
		box2.add(champHoraire2);
		champHoraire3 = new ChampHoraire("Horaire départ : ");
		box2.add(champHoraire3);
		
		this.add(box2);
		
		
		JPanel box3 = new GroupPanel("Arrivéé");
		comboBoxA = new GareComboBox("Gare d'arrivée : ",comp);
		box3.add(comboBoxA);
		champHoraire4 = new ChampHoraire("Horaires : ");
		box3.add(champHoraire4);
		this.add(box3);
		
		JPanel box4 = new JPanel();
			
			ajouter = new JButton("Ajouter!");
			ajouter.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	                
	            }
	        });
			box4.add(ajouter);
			
			deco = new JButton("Déconnexion!");
			deco.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	onglets.remove(1);
	                
	            }
	        });
			//deco.setHorizontalAlignment(SwingConstants.RIGHT);
			box4.add(deco);
		this.add(box4);
	}
}
	
	
	
