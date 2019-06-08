package struct;


import java.util.function.Consumer;

import static java.lang.Math.*;


public class BinaryTree<K extends Comparable<? super K>, E>
{

  private BinaryTreeNode<K, E> root;
  private int size;


  public BinaryTree()
  {
	root = null;
	size = 0;
  }


  public int size()
  {
	return size;
  }


  public void iterate(Consumer<E> action)
  {
	iterate(action, IterType.INORDER);
  }


  public void iterate(Consumer<E> action, IterType type)
  {
	switch (type)
	{
	  case DEPTH_FIRST:
		depthFirst(action);
		break;
	  case INORDER:
		inorder(root, action);
		break;
	  case POSTORDER:
		postorder(root, action);
		break;
	  case PREORDER:
		preorder(root, action);
		break;
	  default:
		throw new IllegalArgumentException(type + " не реализован");
	}
  }


  public boolean contains(K key)
  {
	return find(key) != null;
  }


  public void insert(K key, E val)
  {
	BinaryTreeNode<K, E> x = new BinaryTreeNode<>(key, val);
	insert(x);
	size++;
  }


  public void delete(K key)
  {
	if (isEmpty())
	  throw new UnderflowException();

	BinaryTreeNode<K, E> node = find(key);

	if (node == null)
	  throw new IllegalArgumentException("Удаляемого ключа нет в дереве");

	delete(node);
	size--;
  }


  public boolean isEmpty()
  {
	return root == null;
  }


  public int depth()
  {
	return depth(root);
  }


  private int depth(BinaryTreeNode<K, E> node)
  {
	if (node == null)
	  return -1;

	int depthLeft = depth(node.left);
	int depthRight = depth(node.right);

	return 1 + max(depthLeft, depthRight);
  }


  public boolean isBalanced()
  {
	if (isEmpty())
	  return true;

	return isBalanced(root);
  }


  private boolean isBalanced(BinaryTreeNode<K, E> node)
  {
	if (node == null)
	  return true;

	if (abs(height(node.left) - height(node.right)) > 1)
	  return false;

	return isBalanced(node.left) &&
		   isBalanced(node.right);
  }


  private int height(BinaryTreeNode<K, E> node)
  {
	if (node == null)
	  return 0;

	return 1 + max(height(node.left), height(node.right));
  }


  protected BinaryTreeNode<K, E> find(K key)
  {
	BinaryTreeNode<K, E> x = root;

	while (x != null && key != x.key)
	{
	  x = key.compareTo(x.key) < 0 ?
		  x.left :
		  x.right;
	}

	return x;
  }


  protected void insert(BinaryTreeNode<K, E> node)
  {
	BinaryTreeNode<K, E> dest = null;
	BinaryTreeNode<K, E> x = root;

	while (x != null)
	{
	  dest = x;
	  x = node.key.compareTo(x.key) < 0 ? x.left : x.right;
	}

	node.parent = dest;

	if (dest == null)
	  root = node;
	else if (node.key.compareTo(dest.key) < 0)
	  dest.left = node;
	else
	  dest.right = node;
  }


  protected void delete(BinaryTreeNode<K, E> node)
  {
	if (node.left == null)
	{
	  replaceSubtree(node, node.right);
	}
	else if (node.right == null)
	{
	  replaceSubtree(node, node.left);
	}
	else
	{
	  BinaryTreeNode<K, E> y = minimum();
	  if (y.parent != node)
	  {
		replaceSubtree(y, y.right);
		y.right = node.right;
		y.right.parent = y;
	  }

	  replaceSubtree(node, y);
	  y.left = node.left;
	  y.left.parent = y;
	}
  }


  protected BinaryTreeNode<K, E> minimum()
  {
	BinaryTreeNode<K, E> x = root;

	while (x.left != null)
	{
	  x = x.left;
	}

	return x;
  }


  protected void replaceSubtree(BinaryTreeNode<K, E> u, BinaryTreeNode<K, E> v)
  {
	if (u.parent == null)
	  root = v;
	else if (u == u.parent.left)
	  u.parent.left = v;
	else
	  u.parent.right = v;

	if (v != null)
	  v.parent = u.parent;
  }


  protected void preorder(BinaryTreeNode<K, E> node, Consumer<E> action)
  {
	if (node != null)
	{
	  action.accept(node.val);
	  preorder(node.left, action);
	  preorder(node.right, action);
	}
  }


  protected void inorder(BinaryTreeNode<K, E> node, Consumer<E> action)
  {
	if (node != null)
	{
	  inorder(node.left, action);
	  action.accept(node.val);
	  inorder(node.right, action);
	}
  }


  protected void postorder(BinaryTreeNode<K, E> node, Consumer<E> action)
  {
	if (node != null)
	{
	  postorder(node.left, action);
	  postorder(node.right, action);
	  action.accept(node.val);
	}
  }


  protected void depthFirst(Consumer<E> action)
  {
	if (isEmpty())
	  return;

	QueueADT<BinaryTreeNode<K, E>> children = new ListQueue<>();

	children.enqueue(root);
	while (!children.isEmpty())
	{
	  BinaryTreeNode<K, E> x = children.dequeue();

	  action.accept(x.val);

	  if (x.left != null)
		children.enqueue(x.left);
	  if (x.right != null)
		children.enqueue(x.right);
	}
  }


  public enum IterType
  {
	DEPTH_FIRST,
	INORDER,
	POSTORDER,
	PREORDER
  }


  protected static class BinaryTreeNode<K, T>
  {

	K key;
	T val;
	BinaryTreeNode<K, T> left;
	BinaryTreeNode<K, T> right;
	BinaryTreeNode<K, T> parent;
	int level;


	BinaryTreeNode(K _key, T _value)
	{
	  key = _key;
	  val = _value;
	  left = null;
	  right = null;
	  parent = null;
	}

  }


  public static class UnderflowException
		  extends RuntimeException
  {

	UnderflowException()
	{
	  super("Дерево опустошено");
	}

  }

}