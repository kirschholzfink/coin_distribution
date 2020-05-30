/**
 * Wird von Klasse Taler implementiert.
 * Vergleicht Taler aufgrund ihres Wertes.
 * 
 * @author jana
 *
 * @param <T> Die Objekte, die miteinander verglichen werden sollen, werden
 *            übergeben.
 */
public interface Comparable<T> {
	int compareTo(T other);
}
