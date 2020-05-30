import java.util.List;

/**
 * Eine Menge von Talern.
 * 
 * @author jana
 *
 */
public class Menge {
	/**
	 * Die Summe aller in der Menge enthaltenen Taler.
	 */
	private int gesamtWert;
	/**
	 * Der Wert des/der enthaltenen Taler mit dem höchsten Wert.
	 */
	private int maxTalerWert;
	/**
	 * Liste der enthaltenen Taler.
	 */
	private List<Taler> taler;

	/**
	 * Konstruktor der Klasse Taler.
	 * 
	 * @param taler Eine Liste der zu enthaltenen Taler wird übergeben.
	 * Weitere Klassenattribute werden im späteren Verlauf berechnet.
	 */
	public Menge(List<Taler> taler) {
		this.taler = taler;
		this.gesamtWert = 0;
		this.maxTalerWert = 0;
	}

	/**
	 * Gibt eine Liste der enthaltenen Taler zurück.
	 * 
	 * @return Liste der Taler.
	 */
	public List<Taler> getTaler() {
		return taler;
	}
	
	/**
	 * Gibt den Gesamtwert der Menge zurück.
	 * 
	 * @return Gesamtwert.
	 */
	public int getGesamtWert() {
		return gesamtWert;
	}
	
	/**
	 * Gibt den Wert der/s Taler(s) mit dem maximalen Wert zurück.
	 * 
	 * @return Maximaler Talerwert.
	 */
	public int getMaxTalerWert() {
		return maxTalerWert;
	}

	/**
	 * Fügt der Menge einen neuen Taler hinzu. Berechnet den neuen Gesamtwert.
	 * Berechnet den neuen maximalen Talerwert.
	 * 
	 * @param newTaler
	 */
	public void addTaler(Taler newTaler) {
		taler.add(newTaler);
		this.calcGesamtWert();
		this.calcMaxTalerWert();
	}

	/**
	 * Entfernt einen Taler aus der Menge. Berechnet den neuen Gesamtwert.
	 * Berechnet den neuen maximalen Talerwert.
	 * 
	 * @param usedTaler
	 */
	public void removeTaler(Taler usedTaler) {
		taler.remove(usedTaler);
		this.calcGesamtWert();
		this.calcMaxTalerWert();
	}

	/**
	 * Ermittelt den Wert des Talers mit dem maximalen Wert.
	 * 
	 * @return maximaler Wert.
	 */
	public int calcMaxTalerWert() {
		int max = 0;
		for (Taler taler : taler) {
			int n = taler.getWert();
			if (n > max) {
				max = n;
			}
		}
		this.maxTalerWert = max;
		return max;
	}
	/**
	 * Ermittelt die Summe aller Taler in der Menge.
	 * 
	 * @return Summe.
	 */
	public int calcGesamtWert() {
		int result = 0;
		for (Taler taler : taler) {
			result += taler.getWert();
		}
		this.gesamtWert = result;
		return result;
	}
	/**
	 * Gibt eine Menge auf der Konsole aus.
	 * Iteriert über alle Taler in der Liste und gibt ihren Wert aus.
	 */
	public void mengeAusgeben() {
		for (Taler taler : taler) {
			System.out.println(taler.getWert());
		}
	}
}