package struct;


import java.util.function.Consumer;


public class Graph<E>
{

  private ListADT<Node> nodes;


  public Graph()
  {
	nodes = new TwoWayLinkedList<>();
  }


  public Node createVertex(E obj)
  {
	Node node = new Node(obj);
	nodes.insertFirst(node);

	return node;
  }


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
	  {
		nodes.iterate(x -> x.visited = false);
		return false;
	  }
	}

	nodes.iterate(x -> x.visited = false);
	return true;
  }


  public ListADT<Node> getShortestPath(Node from, Node to)
  {
	if (to == from)
	  throw new IllegalArgumentException("вершины не должны совпадать");

	breadthFirst(from, null);
	ListADT<Node> path = buildPath(from, to);

	return path;
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


  private ListADT<Node> buildPath(Node from, Node to)
  {
	ListADT<Node> path = new TwoWayLinkedList<>();

	if (to == from)
	{
	  path.insertFirst(from);
	  return path;
	}

	if (to.pred == null)
	  return null;

	Node curr = to;
	do
	{
	  path.insertFirst(curr);
	  curr = curr.pred;
	  if (curr == null)
		return null;
	}
	while (curr != from);

	path.insertFirst(from);

	return path;
  }


  public class Node
  {

	ListADT<Link> links;
	E obj;
	Node pred;
	boolean visited;
	long dist;


	Node(E e)
	{
	  obj = e;
	  links = new TwoWayLinkedList<>();
	}


	public void connectWith(Node node)
	{
	  for (Link l : links)
	  {
		if (l.node2 == node)
		  return;
	  }

	  Link link = new Link(this, node);
	  links.insertFirst(link);
	  node.links.insertFirst(link);
	}


	@Override
	public String toString()
	{
	  return obj.toString();
	}

  }

  private class Link
  {

	Node node1;
	Node node2;


	Link(Node from, Node to)
	{
	  node1 = from;
	  node2 = to;
	}

  }


  public enum IterType
  {
	DEPTH_FIRST,
	BREADTH_FIRST,
  }

}