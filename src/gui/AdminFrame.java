package gui;

import java.util.List;

import elements.Indexable;
import gui.edition.PanneauListe;
import gui.elements.MyJFrame;

@SuppressWarnings("serial")
public class AdminFrame<E extends Indexable> extends MyJFrame{
	public AdminFrame(String titre, Class<E> paramClass, List<E> elements){		
		setTitle(titre);
		add(new PanneauListe<E>("Base de donnée",paramClass, elements));
		positionner();
	}
}
