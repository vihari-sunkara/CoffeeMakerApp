package my.app.entities;

import java.util.HashMap;
import java.util.Map;

public class RecipeMaker {
	public static Map<String, Integer> getRecipe(String name) {
		Map<String, Integer> req = new HashMap<String, Integer>();
		if (name == "Ginger Tea") {
			req.put("Hot Water", 50);
			req.put("Hot Milk", 10);
			req.put("Tea Leaves Syrup", 10);
			req.put("Ginger Syrup", 5);
			req.put("Sugar Syrup", 10);
		} else if (name == "Elaichi Tea") {
			req.put("Hot Water", 2000);
			req.put("Hot Milk", 10);
			req.put("Tea Leaves Syrup", 10);
			req.put("Elaichi Syrup", 5);
			req.put("Sugar Syrup", 10);
		} else if (name == "Coffee") {
			req.put("Hot Water", 50);
			req.put("Hot Milk", 10);
			req.put("Coffee Syrup", 10);
			req.put("Sugar Syrup", 10);
		} else if (name == "Hot Milk") {
			req.put("Hot Milk", 50);
		} else if (name == "Hot Water") {
			req.put("Hot Water", 50);
		}

		return req;
	}
}
