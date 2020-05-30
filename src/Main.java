/**
 * Main-Klasse dieser Anwendung.
 * @author jana
 *
 */
public class Main {
	/**
	 * Main-Methode dieser Anwendung.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("This app will take different amounts of 'coins', holding diverse values.");
		System.out.println("(Say, the contents of your wallet: multiple 50 ct, 20 ct, 1 € etc. pieces.)");
		System.out.println("It then generates n subsets of this set of coins, all holding the exact same sum value.");
		System.out.println("(Let's say, in case you want to equally distribute these coins among your children.)");
		System.out.println("It will let you know if this is possible, and if yes, show you what these subsets look like.\n");
		
		System.out.println("To give it a try, you may want to use these values:\n");
		
		Talerverteilung tal = new Talerverteilung();
		tal.execute();
	}

}
