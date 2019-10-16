import java.util.List;
import java.util.ArrayList;

class Combo extends MenuItem {
	private List<MenuItem> menuItems;

	public Combo(String cat, String item,  int id) {
		super(cat, item, 0, id);
		this.menuItems = new ArrayList<MenuItem>();
	}


	public void add(MenuItem menuItem) {
		this.menuItems.add(menuItem);		
	}

	@Override
	public int getPrice() {
		int price = 0;
		for (MenuItem item : menuItems) {
		price += item.getPrice();
		}

		price -= 50;

		return price;
	}
	
	@Override
	public String toString() {
		String str = String.format("#%d %s: %s (%d)\n", super.id, super.cat, super.item, getPrice());
		for (int i = 0; i < this.menuItems.size(); i++) {
			str += String.format("   %s", this.menuItems.get(i).toString());
			if (i != this.menuItems.size() - 1) {
				str += "\n";
			}
		}
		return str;
	}
}
