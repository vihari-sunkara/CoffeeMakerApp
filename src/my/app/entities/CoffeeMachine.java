package my.app.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/*
 * This is the entity of Coffee Machine.
 * It contains:
 * 1. Inventory entity
 * 2. List of Beverage entities
 * 3. orderId vs orderName tracking(orderMapping)
 * 4. Outlets(to take and serve in parallel)
 * 5. Executor Service to manage the parallel processing of orders
 * 6. orderNum to track number of orders made
 */
public class CoffeeMachine {
	private int orderNum;
	private ExecutorService execService;
	private List<Beverage> beveragesServed;
	private Inventory inventory;
	private int outlets;
	private Map<Integer, Beverage> orderMapping;
	/*
	 * Constructor needs only an initial number of outlets.
	 * Instantiates outlets count, 
	 * Initializes number of orders to zero
	 * Initializes a fixed threadPool based on the number of outlets
	 * OrderMapping indicating orderId and the name of the beverage ordered  
	 * Initializes the dedicated inventory of this coffee machine instance
	 */
	public CoffeeMachine(int N) {
		this.outlets = N;
		execService = Executors.newFixedThreadPool(N);
		orderNum = 0;
		inventory = new Inventory();
		orderMapping = new HashMap<Integer, Beverage>();
	}
	/*
	 * Refills the inventory of the coffee machine, given an ingredient and it's corresponding refill quantity
	 */
	public void refillInventory(String in, int quantity) {
		Map<String,Integer> av = inventory.getAvailable();
		av.put(in,quantity);
	}
	
	/*
	 * Crucial method which takes the name of the Beverage to place the order.
	 * Logically, we submit our order placement task which will be picked by the available thread in the thread pool
	 * In case if the max number of parallel orders(number of outlets) is reached, we need to wait for some thread to complete earlier task and become available.
	 * This method also checks for the ingredient availability to make the beverage and sends a NULL object with a warning that some particular ingredients are running low. 
	 */

	public Future<Beverage> placeOrder(String name) throws InterruptedException, ExecutionException {
		orderNum++;
		int id = orderNum;

		Future<Beverage> future = this.execService.submit(() -> {
			System.out.println("Placing order for "+name+" with order number "+id);
			boolean status = false;
			Inventory inventory = getInventory();
			Beverage beverage = null;
			synchronized(inventory) {
				status = inventory.checkInventoryForRecipe(new Recipe(name));

				if(!(status)) {
					orderMapping.put(id, null);
				}
				else {	
					beverage = new Beverage(name);

					inventory.updateInventory(beverage);
				}
			}

			//This is added as a processing time just to make testing easier
			Thread.sleep(5000);
			return beverage;
		
	});
		return future;
	}
	/*
	 * Initializes the inventory with all the ingredients needed and sets the thresholds to receive indication for refill
	 */
	public void spinUpInventory(Map<String,Integer> available,Map<String,Integer> minReqs) {
		this.setInitialAvailabilty(available);
		this.setIngredientThresholds(minReqs);
		
	}
	public void setIngredientThresholds(Map<String, Integer> minReqs) {
		this.inventory.setMinRequirement(minReqs);
		
	}
	public void setInitialAvailabilty(Map<String, Integer> available) {
		this.inventory.setAvailable(available);
	}
	/*
	 * All relevant setters and getters
	 */
	public List<Beverage> getBeveragesServed() {
		return beveragesServed;
	}
	public void setBeveragesServed(List<Beverage> beveragesServed) {
		this.beveragesServed = beveragesServed;
	}
	public int getOutlets() {
		return outlets;
	}
	public void setOutlets(int outlets) {
		this.outlets = outlets;
	}
	public ExecutorService getExecService() {
		return execService;
	}
	public void setExecService(ExecutorService execService) {
		this.execService = execService;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public Map<String,Integer> getRefillRequirements(){
		return this.inventory.getRefillRequirements();
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Map<Integer, Beverage> getOrderMapping() {
		return orderMapping;
	}
	public void setOrderMapping(Map<Integer, Beverage> orderMapping) {
		this.orderMapping = orderMapping;
	}
	
}
