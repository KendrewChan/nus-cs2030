import java.util.ArrayList;
import java.util.List;

class Order {
	private Menu menu;
	private List<Integer> orderIds;

	public Order(Menu menu) {
		this.menu = menu;
		this.orderIds = new ArrayList<>();
	}

	public Order init() {
		Order newOrder = new Order(this.menu);
		newOrder.orderIds = List.copyOf(this.orderIds);
		return newOrder;
	}

	public Order add(int[] orderIds) {
		for (int id : orderIds) {
			this.orderIds.add(id);
		}

		return init();
	}

	@Override
	public String toString() {
		return this.menu.getOrder(this.orderIds);
	}
}
