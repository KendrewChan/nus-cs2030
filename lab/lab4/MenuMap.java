import java.util.ArrayList;
import java.util.List;

class MenuMap {
	private List<String> keys;
	private List<List<MenuItem>> values;

	public MenuMap(){
		this.keys = new ArrayList<String>();
		this.values = new ArrayList<List<MenuItem>>();
	}

	public List<String> getKeys() {
		return this.keys;
	}

	public List<List<MenuItem>> getValues() {
		return this.values;
	}	

	public void addKey(String key) {
		this.keys.add(key);
		this.values.add(new ArrayList<MenuItem>());		
	}

	public void addValue(String key, MenuItem menuItem) 
	{
		if (!this.keys.contains(key)) {
			addKey(key);
		}
		int keyIndex = this.keys.indexOf(key);
		List<MenuItem> lst =  this.values.get(keyIndex);
		lst.add(menuItem);
	}

	public void print() {
		// Loops value lists
		// print item
		List<String> combos = new ArrayList<String>();

		for (List<MenuItem> lst : this.values) {
			for (MenuItem menuItem : lst) {
				if (menuItem.getCat() == "Combo") {
					combos.add(menuItem.toString());
				} else {
					System.out.println(menuItem);
				}
			}
		}

		for (String combo : combos) {
				System.out.println(combo);
		}

	} 
}
