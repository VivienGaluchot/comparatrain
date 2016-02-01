/**
 * 
 */
package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Evaluable, objet contenant un ensemble de criteres élémentaires
 */
public interface Evaluable<T> {
	/**
	 * @param pref preferences permetant d'effectuer l'évaluation
	 * @return un réel entre 0 et 1, 1 etant le plus proche de preference
	 */
	double eval(T o);
}
