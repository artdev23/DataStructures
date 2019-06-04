import struct.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static alg.NumericAlgorithm.nextIntFromRange;
import static java.lang.Math.round;
import static java.lang.System.out;


public class RandomTrees
{

  private static int treeCount = 1000;
  private static int treeDepth = 3;
  private static Random rand = new Random();


  public static void main(String[] args)
  {
	List<BinaryTree<Integer, Object>> trees = new ArrayList<>(treeCount);

	for (int i = 1; i <= treeCount; i++)
	{
	  trees.add(genTree());
	}

	analyzeBalance(trees);
  }


  private static BinaryTree<Integer, Object> genTree()
  {
	BinaryTree<Integer, Object> tree = new BinaryTree<>();

	while (tree.depth() < treeDepth)
	{
	  int n = nextIntFromRange(rand, -20, 20);
	  tree.insert(n, null);
	}

	return tree;
  }


  private static void analyzeBalance(List<BinaryTree<Integer, Object>> trees)
  {
	long balCount = trees.stream()
						 .filter(BinaryTree::isBalanced)
						 .count();

	long notBalCount = treeCount - balCount;

	long notBalPercent = round(notBalCount * 100f / treeCount);


	out.println(balCount + " сбалансированных деревьев");
	out.println(notBalCount + " несбалансированных деревьев");
	out.println(notBalPercent + "% несбалансированных деревьев");
  }

}