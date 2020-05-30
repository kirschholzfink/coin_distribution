/**
 * Ein in einer Menge enthaltener Taler. Implementiert Comparable-Interface.
 * 
 * @author jana
 *
 */
public class Taler implements Comparable<Taler> {
	/**
	 * Wert des Talers.
	 */
	private int wert;

	/**
	 * Konstruktor der Klasse Taler.
	 * 
	 * @param Der Wert des Talers wird übergeben.
	 */
	public Taler(int wert) {
		this.wert = wert;
	}

	/**
	 * Der Wert des Talers wird zurückgegeben.
	 * 
	 * @return Wert.
	 */
	public int getWert() {
		return wert;
	}
	/**
	 * compareTo-Methode des Comparable-Interfaces.
	 * Gibt negative/positive Werte aufgrund des Talerwertes zurück.
	 * Ermöglicht, dass Taler in absteigender Reihenfolge sortiert werden.
	 */
	@Override
	public int compareTo(Taler other) {
		if (this.getWert() > other.getWert()) {
			return -1;
		} else if (this.getWert() == other.getWert()) {
			return 0;
		} else
			return 1;
	}

}
