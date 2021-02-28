package my.app.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import my.app.constants.BeverageNames;
import my.app.entities.Beverage;
import my.app.entities.CoffeeMachine;
import my.app.functional.tests.MakingTestsReady;
/*
 * This class contains the test scenarios in the main method.
 * We need to uncomment each line of code to test a particular scenario.
 * Each test method itself creates the necessary situation needed to test the scenario
 */
public class Driver {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		// Number of beverages served in parallel
		int N = 3;
		CoffeeMachine machine = new CoffeeMachine(N);

		// Adding beverages as an example
		List<Beverage> beveragesServed = new ArrayList<Beverage>();
		beveragesServed.add(new Beverage(BeverageNames.GINGER_TEA));
		beveragesServed.add(new Beverage(BeverageNames.ELAICHI_TEA));
		beveragesServed.add(new Beverage(BeverageNames.COFFEE));
		beveragesServed.add(new Beverage(BeverageNames.HOT_MILK));
		beveragesServed.add(new Beverage(BeverageNames.HOT_WATER));
		machine.setBeveragesServed(beveragesServed);

		/*
		 * 
		 * Retrieving the list of beverages served
		 */

		testingBeveragesServed(machine);

		/*
		 * Testing for N orders in parallel..Logs generated represent the concurrency of
		 * N orders and waiting for availability of threads
		 */
//		testParallelExecution(machine);

		/*
		 * Testing Indicator to show ingredients are running low..We can edit
		 * MakingTestsReady.gettingReadyForRefillReqTests(machine) to test more
		 * scenarios
		 */
//		testIngredientsRunningLow(machine);

		/*
		 * Testing scenario where the ingredients are insufficient for a beverage
		 */
//		testInsufficientIngredientsForABeverage(machine);

	}

	private static void testingBeveragesServed(CoffeeMachine machine) {
		List<Beverage> blist = machine.getBeveragesServed();
		System.out.println("The following beverages are served by the coffee machine");
		for (Beverage b : blist) {
			System.out.println(b.getName());
		}
	}

	private static void testInsufficientIngredientsForABeverage(CoffeeMachine machine)
			throws InterruptedException, ExecutionException {
		MakingTestsReady.gettingReadyForInsufficientIngredTests(machine);
		Future<Beverage> future1 = machine.placeOrder(BeverageNames.GINGER_TEA);
		if (future1.get() == null)
			System.out.println("Recieved NULL since need to refill the ones with insufficient ingredients");
	}

	private static void testIngredientsRunningLow(CoffeeMachine machine) {
		MakingTestsReady.gettingReadyForRefillReqTests(machine);
		machine.getRefillRequirements();// actually returns a map of ingredient and the minimum amount of refill
	}

	private static void testParallelExecution(CoffeeMachine machine) throws InterruptedException, ExecutionException {
		MakingTestsReady.gettingReadyForParallelExecTests(machine);

		System.out.println(machine.getInventory().getAvailable());

		Future<Beverage> future1 = machine.placeOrder(BeverageNames.GINGER_TEA);
		Future<Beverage> future2 = machine.placeOrder(BeverageNames.ELAICHI_TEA);
		Future<Beverage> future3 = machine.placeOrder(BeverageNames.COFFEE);
		Future<Beverage> future4 = machine.placeOrder(BeverageNames.ELAICHI_TEA);
		Future<Beverage> future5 = machine.placeOrder(BeverageNames.HOT_WATER);
		Future<Beverage> future6 = machine.placeOrder(BeverageNames.HOT_WATER);
		System.out.println(future1.get() != null ? future1.get().getName() : "NULL");
		System.out.println(future2.get() != null ? future2.get().getName() : "NULL");
		System.out.println(future3.get() != null ? future3.get().getName() : "NULL");
		System.out.println(future4.get() != null ? future4.get().getName() : "NULL");
		System.out.println(future5.get() != null ? future5.get().getName() : "NULL");
		System.out.println(future6.get() != null ? future6.get().getName() : "NULL");
		
		System.out.println(machine.getInventory().getAvailable());
	}

}
