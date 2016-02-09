package gui.admin;

import java.util.List;

import elements.Indexable;
import gui.MyJFrame;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class AdminFrame<E extends Indexable> extends MyJFrame{
	public AdminFrame(String titre, Class<E> paramClass, List<E> elements){		
		setTitle(titre);
		add(new ListPanel<E>("Base de donnée",paramClass, elements));
		positionner();
	}
}
