import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Menu menu = new Menu();
		while (sc.next().equals("add")) {
			String cat = sc.next();
			String name = sc.next();
			List<Integer> numbers = new ArrayList<Integer>();	
			while (sc.hasNextInt()) {
				numbers.add(sc.nextInt());
			}
			
			if (numbers.size() == 1) {
				menu.add(cat, name, numbers.get(0));
			} else {
				menu.add(cat, name, numbers);
			} 

			
		
		}

		menu.print();

		Order order = new Order(menu);
		while (sc.hasNextInt()) {
			order.add(new int[] {sc.nextInt()});	
		}

		System.out.printf("\n--- Order ---\n%s\n", order);
	}
}
