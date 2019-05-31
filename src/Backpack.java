import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.System.out;


public class Backpack
{

  private Set<Thing> optSet;
  private double maxWeight;
  private double maxCost;
  private Set<Thing> things;


  public Backpack(double maxWeight)
  {
	optSet = null;
	this.maxWeight = maxWeight;
	maxCost = 0;
	things = null;
  }


  public Set<Thing> optimumThingsSet(Set<Thing> things)
  {
	if (things == null || things.isEmpty())
	  throw new IllegalArgumentException("Argument is empty");

	if (this.things == null)
	{
	  checkAllSubsets(things);
	  this.things = things;
	  return optSet;
	}

	if (this.things.equals(things))
	  return optSet;

	checkAllSubsets(things);
	this.things = things;

	return optSet;
  }


  private void checkAllSubsets(Set<Thing> things)
  {
	if (!things.isEmpty())
	  checkSet(things);

	Iterator<Thing> it = things.iterator();
	while (it.hasNext())
	{
	  Thing thing = it.next();
	  Set<Thing> subset = new HashSet<>(things);
	  subset.remove(thing);
	  checkAllSubsets(subset);
	}

  }


  private void checkSet(Set<Thing> things)
  {
	if (sumWeigth(things) <= maxWeight && sumCost(things) > maxCost)
	{
	  optSet = new HashSet<>(things);
	  maxCost = sumCost(things);
	}
  }


  private double sumWeigth(Set<Thing> things)
  {
	return things.stream()
				 .mapToDouble(x -> x.weigth)
				 .sum();
  }


  private double sumCost(Set<Thing> things)
  {
	return things.stream()
				 .mapToDouble(x -> x.cost)
				 .sum();
  }


  public static void main(String[] args)
  {
	Set<Thing> things = new HashSet<Thing>()
	{{
	  add(new Thing("Книга", 1, 600));
	  add(new Thing("Бинокль", 2, 5000));
	  add(new Thing("Аптечка", 4, 1500));
	  add(new Thing("Ноутбук", 2, 40000));
	  add(new Thing("Котелок", 1, 500));
	}};

	Backpack bp = new Backpack(8);
	Set<Thing> set = bp.optimumThingsSet(things);
	out.println(set.toString().replaceAll(",", "\n"));
  }


  private static class Thing
  {

	String name;
	double weigth;
	double cost;


	Thing(String name, double weigth, double cost)
	{
	  this.name = name;
	  this.weigth = weigth;
	  this.cost = cost;
	}


	@Override
	public String toString()
	{
	  return name + " вес = " + weigth + "кг " + cost + "руб";
	}

  }

}