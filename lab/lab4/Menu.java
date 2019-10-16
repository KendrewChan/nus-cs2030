import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Menu {
    private MenuMap menuMap;
    private int counter;
    private Combo combo;

    public Menu() {
        this.counter = 0;
        this.menuMap = new MenuMap();
    }

    public MenuMap getMenuMap() {
        return this.menuMap;
    }

    public MenuItem add(String cat, String item, int price) {
	MenuItem newItem = new MenuItem(cat, item, price, this.counter); 
	
	this.menuMap.addValue(cat, newItem);

        this.counter++;

        return newItem;
    }

    public Combo add(String cat, String item, List<Integer> lst) {
    Combo combo = new Combo("Combo", item, this.counter);
    for (int id : lst) {
	for (List<MenuItem> items : this.menuMap.getValues()) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId() == id) {
				combo.add(items.get(i));
			}
		}
	} 
    }

    this.menuMap.addValue(cat, combo);

    this.counter++;

    return combo;
    }

    public void print() {
	this.menuMap.print(); 
    }

    public String getOrder(List<Integer> orderIds) {
    	// for each order id
	// loop thru menu map
	// loop thru menu items
	// if order is in order id return toString
	String order = "\n";
	int total = 0;
	for (int id : orderIds) {
		for (List<MenuItem> lst : this.menuMap.getValues()) {
		for (MenuItem item : lst) {
			if (id == item.getId()) {
				order += item.toString() + "\n";
				total += item.getPrice();
			}
		}
		}
	}

       		
	order += String.format("Total: %d", total);
	return order;
    }
}
