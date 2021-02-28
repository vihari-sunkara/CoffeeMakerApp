package my.app.functional.tests;

import java.util.HashMap;
import java.util.Map;

import my.app.entities.CoffeeMachine;
/*
 * This class is written to create situations for testing specific scenarios
 */
public class MakingTestsReady {
	public static void gettingReadyForParallelExecTests(CoffeeMachine machine) {
		Map<String, Integer> available = new HashMap<String, Integer>();
		available.put("Hot Water", 2000);
		available.put("Hot Milk", 2000);
		available.put("Tea Leaves Syrup", 200);
		available.put("Ginger Syrup", 100);
		available.put("Sugar Syrup", 100);
		available.put("Coffee Syrup", 100);
		available.put("Elaichi Syrup", 100);

		// Setting the thresholds to get indication later
		Map<String, Integer> minReqs = new HashMap<String, Integer>();
		minReqs.put("Hot Water", 200);
		minReqs.put("Hot Milk", 40);
		minReqs.put("Tea Leaves Syrup", 40);
		minReqs.put("Ginger Syrup", 20);
		minReqs.put("Sugar Syrup", 40);
		minReqs.put("Coffee Syrup", 40);
		minReqs.put("Elaichi Syrup", 20);

		// Putting the required ingredients in the inventory and adding the threshold
		// limits for getting an indication if running low
		machine.spinUpInventory(available, minReqs);
	}

	public static void gettingReadyForRefillReqTests(CoffeeMachine machine) {
		Map<String, Integer> available = new HashMap<String, Integer>();
		available.put("Hot Water", 20);
		available.put("Hot Milk", 2000);
		available.put("Tea Leaves Syrup", 30);
		available.put("Ginger Syrup", 100);
		available.put("Sugar Syrup", 100);
		available.put("Coffee Syrup", 100);
		available.put("Elaichi Syrup", 100);

		// Setting the thresholds to get indication later
		Map<String, Integer> minReqs = new HashMap<String, Integer>();
		minReqs.put("Hot Water", 200);
		minReqs.put("Hot Milk", 40);
		minReqs.put("Tea Leaves Syrup", 40);
		minReqs.put("Ginger Syrup", 20);
		minReqs.put("Sugar Syrup", 40);
		minReqs.put("Coffee Syrup", 40);
		minReqs.put("Elaichi Syrup", 20);

		// Putting the required ingredients in the inventory and adding the threshold
		// limits for getting an indication if running low
		machine.spinUpInventory(available, minReqs);
	}

	public static void gettingReadyForInsufficientIngredTests(CoffeeMachine machine) {
		Map<String, Integer> available = new HashMap<String, Integer>();
		available.put("Hot Water", 200);
		available.put("Hot Milk", 2000);
		available.put("Tea Leaves Syrup", 5);
		available.put("Ginger Syrup", 100);
		available.put("Sugar Syrup", 100);
		available.put("Coffee Syrup", 100);
		available.put("Elaichi Syrup", 100);

		// Setting the thresholds to get indication later
		Map<String, Integer> minReqs = new HashMap<String, Integer>();
		minReqs.put("Hot Water", 200);
		minReqs.put("Hot Milk", 40);
		minReqs.put("Tea Leaves Syrup", 40);
		minReqs.put("Ginger Syrup", 20);
		minReqs.put("Sugar Syrup", 40);
		minReqs.put("Coffee Syrup", 40);
		minReqs.put("Elaichi Syrup", 20);

		// Putting the required ingredients in the inventory and adding the threshold
		// limits for getting an indication if running low
		machine.spinUpInventory(available, minReqs);
	}
}
