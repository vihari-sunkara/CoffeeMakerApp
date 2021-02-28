package my.app.entities;

import java.util.HashMap;
import java.util.Map;
/*
 * This is the inventory entity that contains:
 * 1. Available ingredients and their corresponding quantities
 * 2. Minimum Requirements for each of the ingredients. If any ingredient goes below these limits, we get an indication when
 * we explicitly check the sufficiency of ingredients.
 */
public class Inventory {
	private Map<String,Integer> available;
	private Map<String,Integer> minRequirement;
	public Map<String, Integer> getAvailable() {
		return available;
	}
	public void setAvailable(Map<String, Integer> available) {
		this.available = available;
	}
	public Map<String, Integer> getMinRequirement() {
		return minRequirement;
	}
	public void setMinRequirement(Map<String, Integer> minRequirement) {
		this.minRequirement = minRequirement;
	}
	
	public boolean checkInventoryForRecipe(Recipe recipe) {
		Map<String,Integer> req = recipe.getRequirement();
		boolean status = true;
		for(String in: req.keySet()) {
			if(available.get(in)<req.get(in)) {
				System.out.println("insufficient: "+in+" required: "+req.get(in)+" currently present "+available.get(in));
				status = false;
			}
		}
		return status;
		
	}
	public void updateInventory(Beverage beverage) {
		Map<String,Integer> req = beverage.getRecipe().getRequirement();
		for(String in:req.keySet()) {
			int availableQuantity = this.available.get(in);
			int requiredQuantity = req.get(in);
			availableQuantity-=requiredQuantity;
			this.available.put(in, availableQuantity);
		}
		
	}
	/*
	 * This method checks which all ingredients are running low
	 */
	public Map<String,Integer> getRefillRequirements() {
		Map<String,Integer> refills = new HashMap<String, Integer>();
		for(String in: minRequirement.keySet()) {
			Integer av = available.get(in);
			Integer min = minRequirement.get(in);
			if(av<min) {
				System.out.println("Need atleast a refill of "+(min-av)+" for ingredient "+in.toUpperCase());
				refills.put(in, min-av);
			}
				
		}
		if(refills.size()==0)
			System.out.println("NO REFILL NEEDED AS OF NOW");
		return refills;
		
	}
}
