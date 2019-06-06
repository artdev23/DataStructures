package struct;


import java.util.function.Consumer;


public class Graph<E>
{

  private ListADT<Node> nodes;


  public void iterate(Consumer<E> action, IterType type)
  {
	switch (type)
	{
	  case DEPTH_FIRST:
		depthFirst(nodes.first(), action);
		break;

	  case BREADTH_FIRST:
		breadthFirst(nodes.first(), action);
		break;

	  default:
		throw new IllegalArgumentException(type + " не реализован");
	}

	nodes.iterate(x -> x.visited = false);
  }


  public boolean isConnected()
  {
	depthFirst(nodes.first(), null);

	for (Node v : nodes)
	{
	  if (!v.visited)
		return false;
	}

	return true;
  }


  private void depthFirst(Node from, Consumer<E> action)
  {
	StackADT<Node> stack = new ListStack<>();

	if (action != null)
	  action.accept(from.obj);
	from.visited = true;
	stack.push(from);

	while (!stack.isEmpty())
	{
	  Node v = stack.pop();

	  for (Link e : v.links)
	  {
		if (!e.node2.visited)
		{
		  if (action != null)
			action.accept(e.node2.obj);
		  e.node2.visited = true;
		  stack.push(e.node2);
		}
	  }
	}
  }


  private void breadthFirst(Node from, Consumer<E> action)
  {
	QueueADT<Node> queue = new ListQueue<>();

	nodes.iterate(x ->
				  {
					x.dist = Long.MAX_VALUE;
					x.pred = null;
				  });

	from.dist = 0;

	if (action != null)
	  action.accept(from.obj);
	from.visited = true;
	queue.enqueue(from);

	while (!queue.isEmpty())
	{
	  Node v = queue.dequeue();

	  for (Link e : v.links)
	  {
		if (!e.node2.visited)
		{
		  e.node2.dist = v.dist + 1;
		  e.node2.pred = v;
		  if (action != null)
			action.accept(e.node2.obj);
		  e.node2.visited = true;
		  queue.enqueue(e.node2);
		}
	  }
	}
  }


  private class Node
  {

	ListADT<Link> links;
	E obj;
	Node pred;
	boolean visited;
	long dist;

  }

  private class Link
  {

	Node node1;
	Node node2;

  }


  public enum IterType
  {
	DEPTH_FIRST,
	BREADTH_FIRST,
  }

}