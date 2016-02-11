package donnee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import defaut.Erreur;
import elements.Indexable;

public class Structure<E extends Indexable>{
	private ArrayList<E> elements;
	
	Structure(){
		elements = new ArrayList<E>();
	}
	
	public void add(E element) throws Erreur{
		for(E e : elements)
			if(element.equals(e)) throw new Erreur(Erreur.EXISTE);
		elements.add(element);
	}
	
	public void remove(E element){
		elements.remove(element);
	}
	
	public int size(){
		if(elements == null) return 0;
		return elements.size();
	}
	
	public List<E> getElements(){
		return Collections.unmodifiableList(elements);
	}
	
	public void changeId(E el, int newId) throws Erreur{
		if(el.getId() != null && el.getId() == newId) return;
		
		if(get(newId) != null)
			throw new Erreur(Erreur.EXISTE);
		else
			el.setId(newId);
	}
	
	public void setElements(ArrayList<E> e){
		elements = e;
	}
	
	public E get(int id){
		for(E e : elements)
			if(e.getId() == id)
				return e;
		return null;
	}
	
	public int getFreeId(){
		for(int i = 0; i<100000 ;i++){
			if(get(i) == null) return i;
		}
		return -1;
	}
}
