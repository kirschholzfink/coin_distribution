import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Controller-Class dieser Anwendung.
 * 
 * @author jana
 *
 */
public class Talerverteilung {
	/**
	 * Menge, die in Untermengen aufgeteilt werden soll.
	 */
	private Menge eingangsMenge;
	/**
	 * Liste an generierten Untermengen.
	 */
	private List<Menge> outputMengen;
	/**
	 * Anzahl der Untermengen, die aus der Eingangsmenge gebildet werden sollen.
	 */
	private int anzahlOutputMengen;
	/**
	 * Wert jeder zu bildenden Untermenge.
	 */
	private int subsetValues;
	/**
	 * Konstruktor der Klasse Talerverteilung.
	 * Fragt Parameter der Eingangsmenge über Nutzereingabe ab.
	 * Generiert auf dieser Basis Talerinstanzen und fügt sie der Eingangsmenge hinzu.
	 * Fragt die Anzahl der zu bildenden Untermengen über Nutzereingabe ab.
	 */
	public Talerverteilung() {
		List<Taler> initialSet = new ArrayList<Taler>();
		this.eingangsMenge = new Menge(initialSet);
		this.outputMengen = new ArrayList<Menge>();
		this.anzahlOutputMengen = 0;

		// Parameter über Scanner abfragen
		Scanner scan = new Scanner(System.in);
		int talerWert = 0;
		int anzahlTaler = 0;
		boolean moreTaler = true;
		while (moreTaler) {
			// Talerwerte abfragen
			boolean acceptInput1 = false;
			while (!acceptInput1) {
				acceptInput1 = true;
				try {
					System.out.println("Gib einen Talerwert >= 1 ein: (Abbruch mit RETURN)");
					String s = scan.next();
					if (s.contentEquals("RETURN")) {
						System.exit(1);
					} else {
						talerWert = Integer.parseInt(s);

						if (talerWert <= 0) {
							System.out.println("Der Taler muss größer oder gleich 1 sein. Probier es nochmal.");
							acceptInput1 = false;

						}
					}
				} catch (Exception e) {
					System.out.println("Da ist etwas schief gegangen, probier es erneut!");
					acceptInput1 = false;
					scan.nextLine();
				}
			}
			// Talermengen abfragen
			boolean acceptInput2 = false;
			while (!acceptInput2) {
				acceptInput2 = true;
				try {
					System.out.println("Wie viele Taler sollen von diesem Wert generiert werden? (Abbruch mit RETURN");
					String s = scan.next();
					if (s.contentEquals("RETURN")) {
						System.exit(1);
					} else {
						anzahlTaler = Integer.parseInt(s);
						if (anzahlTaler <= 0) {
							System.out.println("Du musst mindestens einen Taler generieren.");
							acceptInput2 = false;
						}
					}
				} catch (Exception e) {
					System.out.println("Da ist etwas schief gegangen, probier es erneut!");
					acceptInput2 = false;
					scan.nextLine();
				}
			}
			// Talerinstanzen generieren und zur Eingangsmenge hinzufügen
			for (int i = 0; i < anzahlTaler; i++) {
				Taler taler = new Taler(talerWert);
				this.eingangsMenge.addTaler(taler);
			}
			// Abfragen, ob weitere Taler generiert werden sollen.
			boolean acceptInput3 = false;
			while (!acceptInput3) {
				acceptInput3 = true;
				try {
					System.out
							.println("Sollen weitere Taler generiert werden? Für ja, gib '1', für nein, gib '0' ein.");
					int response = scan.nextInt();
					if (response > 1 || response < 0) {
						System.out.println("Du kannst nur einen Wert zwischen 0 und 1 angeben. Probiere es erneut!");
						acceptInput3 = false;
					}

					if (response == 0) {
						moreTaler = false;
					}
				} catch (Exception e) {
					System.out.println("Da ist etwas schief gegangen, probier es erneut!");
					acceptInput3 = false;
					scan.nextLine();
				}
			}
		}
		// Mengenanzahl abfragen
		boolean acceptInput4 = false;
		while (!acceptInput4) {
			acceptInput4 = true;
			try {
				System.out.println("Wie viele gleichwertige Mengen sollen aus den Talern gebildet werden?");
				this.anzahlOutputMengen = scan.nextInt();
				if (this.anzahlOutputMengen <= 0) {
					System.out.println("Du musst mindestens 1 eingeben.");
					acceptInput4 = false;
				}
			} catch (Exception e) {
				System.out.println("Da ist etwas schief gegangen, probier es erneut!");
				acceptInput4 = false;
				scan.nextLine();
			}
		}

		scan.close();

		this.subsetValues = calcSubsetValue();
	}
	
	/**
	 * Führt eine Vorprüfung durch, ob das Problem zu lösen ist.
	 * Gibt false zurück, falls Gesamtwert der Eingangsmenge nicht durch Anzahl der Output-Mengen teilbar.
	 * Gibt false zurück, falls der Maximalwert der Menge größer ist als der Wert der zu bildenden Untermengen.
	 * Sonst true.
	 * 
	 * @return false/true
	 */
	public boolean isItPossible() {
		boolean possible = true;
		int gesamtWert = this.eingangsMenge.getGesamtWert();
		int maxWert = this.eingangsMenge.getMaxTalerWert();
		if (gesamtWert % this.anzahlOutputMengen != 0 || this.subsetValues < maxWert) {
			possible = false;
		}

		return possible;
	}
	
	/**
	 * Sortiert eine Menge aufgrund der Werte ihrer enthaltenen Taler in absteigender Reihenfolge.
	 * 
	 * @param Die zu sortierende Menge wird übergeben.
	 */
	public void sortMenge(Menge menge) {

		List<Taler> talerList = menge.getTaler();

		talerList.sort(Taler::compareTo);
	}
	/**
	 * Berechnet den Wert aller zu bildenden Untermengen.
	 * 
	 * @return
	 */
	public int calcSubsetValue() {
		int sum = this.eingangsMenge.getGesamtWert();
		int n = this.anzahlOutputMengen;
		int result = sum / n;
		return result;
	}
	/**
	 * Rekursive Funktion zur Befüllung von Untermengen.
	 * Erhält eine (anfangs leere) zu befüllende Untermenge.
	 * Ruft einen Taler aus der Eingangsmenge am übergebenen Index auf.
	 * Taler wird der Menge hinzugefügt, falls sie dadurch den angestrebten Gesamtwert nicht überschreitet.
	 * In dem Fall wird er aus der Eingangsmenge gelöscht und der Index wird auf 0 gesetzt.
	 * Falls doch, wird der Index erhöht, die Funktion erneut aufgerufen, um den nächsten Taler zu überprüfen.
	 * Entspricht der Gesamtwert der Untermenge dem angestrebten Gesamtwert, wird die Untermenge returned.
	 * Wird der Index soweit hochgezählt, dass er über die Größe der Eingangsmenge hinausreicht, ist das Problem nicht lösbar.
	 * 
	 * @param subSet Eine (anfangs leere) Menge.
	 * @param index	Index, an dem in der Eingangsmenge nach einem passenden Taler gesucht werden soll.
	 * @return Das Programm wird entweder abgebrochen, oder die gebildete Untermenge wird übergeben.
	 */
	public Menge addElements(Menge subSet, int index) {
		Taler newTaler = this.eingangsMenge.getTaler().get(index);
		if (subSet.getGesamtWert() + newTaler.getWert() <= this.subsetValues) {
			subSet.addTaler(newTaler);
			this.eingangsMenge.removeTaler(newTaler);
			index = 0;
			if (subSet.getGesamtWert() == this.subsetValues) {
				return subSet;
			} else {
				return addElements(subSet, index);
			}

		} else {
			if (index + 1 > this.eingangsMenge.getTaler().size() - 1) {
				System.out.println("Keine Lösung möglich (nach Probieren).");
				System.exit(1);
				return subSet;
			} else {
				index++;
				return addElements(subSet, index);
			}
		}
	}
	/**
	 * Generiert Untermengen.
	 * Iteriert über die Anzahl der zu generierenden Untermengen.
	 * Ruft jeweils die Funktion zur Befüllung von Untermengen auf und übergibt eine leere Menge.
	 * Das Ergebnis der Funktion wird der Liste der auszugebenden Mengen hinzugefügt.
	 * 
	 * @param Erhält eine Menge (die Eingangsmenge), die in gleichwertige Untermengen zerteilt werden soll.
	 */
	public void createMengen(Menge menge) {
		for (int i = 0; i < this.anzahlOutputMengen; i++) {
			List<Taler> subTaler = new ArrayList<Taler>();
			Menge emptySet = new Menge(subTaler);
			Menge subSet = addElements(emptySet, 0);
			this.outputMengen.add(subSet);
		}
	}

	/**
	 * Gibt die Liste der generierten Untermengen auf der Konsole aus.
	 */
	public void mengenAusgeben() {
		// TODO
		for (int i = 0; i < outputMengen.size(); i++) {
			System.out.println("Menge " + (i + 1) + ": ");
			outputMengen.get(i).mengeAusgeben();
		}
	}

	/**
	 * Errechnet den Gesamtwert der Eingangsmenge.
	 * Errechnet den maximalen Talerwert der Eingangsmenge.
	 * Ruft zur Vorprüfung des Problems die Funktion isItPossible() auf.
	 * Sortiert die Eingangsmenge.
	 * Ruft die Funktion zur Bildung der Untermengen auf und übergibt die Eingangsmenge.
	 * Gibt die gebildeten Untermengen aus.
	 */
	public void execute() {
		this.eingangsMenge.calcGesamtWert();
		this.eingangsMenge.calcMaxTalerWert();
		if (!isItPossible()) {
			System.out.println("Keine Lösung möglich (Vorprüfung).");
			System.exit(1);
		}
		sortMenge(eingangsMenge);
		createMengen(eingangsMenge);
		mengenAusgeben();
	}

}
