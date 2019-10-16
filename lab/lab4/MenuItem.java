class MenuItem {
	protected String cat;
	protected String item;
	protected int price;
	protected int id;

	public MenuItem(String cat, String item, int price, int id) {
		this.cat = cat;
		this.item = item;
		this.price = price;
		this.id = id;
	
	}

	public String getCat() {
		return this.cat;
	}
	
	public int getId() {
		return this.id;
	}

	public int getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return String.format("#%d %s: %s (%d)", this.id, this.cat, this.item, this.price);
	}
}
