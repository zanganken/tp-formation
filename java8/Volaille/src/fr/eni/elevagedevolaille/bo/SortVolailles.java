package fr.eni.elevagedevolaille.bo;

import java.util.Comparator;

/**
 * Bonus : création d'une classe sortVolailles.
 * Tri par nom de classe, puis par id.
 * @author Zanganken
 *
 */
public class SortVolailles implements Comparator<Volaille> {
	/**
	 * Compare deux volailles par nom de classe, puis par id
	 */
	public int compare(Volaille v1, Volaille v2) {
		return Comparator.comparing(Volaille::getClassName)
				.thenComparing(Volaille::getId)
				.compare(v1, v2);
	}
}
