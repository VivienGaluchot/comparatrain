package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Evaluable, objet pouvant etre évalué par rapport a un autre objet
 */
public interface Evaluable<T> {
	/**
	 * @param pref preferences permetant d'effectuer l'évaluation
	 * @return un réel entre 0 et 1, 1 etant la meilleure évaluation possible
	 */
	double eval(T o);
}
