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
		System.out.println("(Let's say, in case you want to distribute this money among your children, equally.)");
		System.out.println("It will let you know if this is possible, and if yes, show you what these subsets look like.\n");
		
		System.out.println("To give it a try, you may want to use these parameters:\n");
		
		System.out.println("Value 1 : 5 coins.");
		System.out.println("Value 3 : 3 coins.");
		System.out.println("Value 5 : 2 coins.\n");
		System.out.println("n : 2\n");
		
		Talerverteilung tal = new Talerverteilung();
		tal.execute();
	}

}
