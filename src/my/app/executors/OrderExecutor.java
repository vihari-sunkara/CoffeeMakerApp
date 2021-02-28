package my.app.executors;

import java.util.Map;

import my.app.entities.Beverage;
import my.app.entities.CoffeeMachine;
import my.app.entities.Inventory;
import my.app.entities.Recipe;

public class OrderExecutor implements Runnable {
	private CoffeeMachine machine;
	String orderName;
	int orderId;
	public OrderExecutor(CoffeeMachine machine,int id, String orderName) {
		this.machine = machine;
		this.orderId = id;
		this.orderName = orderName;
	}
	@Override
	public void run() {
		System.out.println("inside thread exec");
		boolean status = false;
		Inventory inventory = this.machine.getInventory();
		Beverage beverage = null;
		synchronized(inventory) {
			status = inventory.checkInventoryForRecipe(new Recipe(this.orderName));
		if(!(status)) {
			System.out.println("no available ingredient for preparing");
			machine.getOrderMapping().put(this.orderId, null);
		}

		beverage = new Beverage(this.orderName);
		inventory.updateInventory(beverage);
		}
		Map<Integer,Beverage> mapping = machine.getOrderMapping();
		mapping.put(this.orderId, beverage);
		machine.setOrderMapping(mapping);	
	}

}
