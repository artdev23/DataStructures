import struct.Graph;
import struct.ListADT;

import static java.lang.System.out;


public class ShortestPath
{

  public static void main(String[] args)
  {
	Graph<String> graph = new Graph<>();

	Graph<String>.Node v1 = graph.createVertex("Москва");
	Graph<String>.Node v2 = graph.createVertex("Тула");
	Graph<String>.Node v3 = graph.createVertex("Липецк");
	Graph<String>.Node v4 = graph.createVertex("Воронеж");
	Graph<String>.Node v5 = graph.createVertex("Рязань");
	Graph<String>.Node v6 = graph.createVertex("Тамбов");
	Graph<String>.Node v7 = graph.createVertex("Саратов");
	Graph<String>.Node v8 = graph.createVertex("Калуга");
	Graph<String>.Node v9 = graph.createVertex("Орел");
	Graph<String>.Node v10 = graph.createVertex("Курск");

	v1.connectWith(v2);
	v2.connectWith(v3);
	v3.connectWith(v4);
	v1.connectWith(v5);
	v5.connectWith(v6);
	v6.connectWith(v7);
	v7.connectWith(v4);
	v1.connectWith(v8);
	v8.connectWith(v9);
	v9.connectWith(v10);
	v10.connectWith(v4);

	ListADT<Graph<String>.Node> path = graph.getShortestPath(v1, v4);
	path.iterate(out::println);
  }

}