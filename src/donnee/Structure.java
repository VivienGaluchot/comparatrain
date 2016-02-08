package donnee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import defaut.Erreur;
import elements.Indexable;

public class Structure<E extends Indexable> {
	private ArrayList<E> elements;
	
	public void add(E element) throws Erreur{
		for(E e : elements)
			if(element.equals(e)) throw new Erreur(Erreur.EXISTE);
		elements.add(element);
	}
	
	public void remove(E element){
		elements.remove(element);
	}
	
	public int size(){
		return elements.size();
	}
	
	public List<E> getElements(){
		return Collections.unmodifiableList(elements);
	}
	
	public E get(int id){
		for(E e : elements)
			if(e.getId() == id)
				return e;
		return null;
	}
}