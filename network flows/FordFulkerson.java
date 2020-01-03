import java.io.*;
import java.lang.reflect.Array;
import java.security.spec.ECGenParameterSpec;
import java.util.*;

/**
 * Kriten Peng 260782511
 * collaborate Celeste Chen
 */


public class FordFulkerson {


	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();


		int nbNode = graph.getNbNodes();

		//create  list to store all precesceesor
		int predecessor[] = new int[nbNode];
		boolean visited[] = new boolean[nbNode];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(source);



		//initial visited to be ture for every slots
		visited[source] = true;
		predecessor[source]=-1;

		ArrayList<Edge> edges = graph.getEdges();
		int adj[][] = new int[nbNode][nbNode];


		//store weights in the adj list, the slot does not have
		//any num means that at that place the two nodes are not connected
		for(int i=0; i<edges.size(); i++) {
			Edge edge =  edges.get(i);
			adj[edge.nodes[0]][edge.nodes[1]] = edge.weight;
		}


		while (queue.size()!=0)
		{
			int u = queue.poll();


			for (int i=0; i<nbNode; i++)
			{
				//if nor visited and still has capacity
				if (visited[i]==false && adj[u][i] > 0)
				{
					predecessor[i] = u;
					queue.add(i);
					visited[i] = true;
				}
			}
		}


		//store the entire DFS result to a, which it contains all node
		ArrayList<Integer> a = new ArrayList<Integer>();
		if(visited[destination]) {
			for (int i=destination; i!=source; i=predecessor[i]){
				a.add(i);
			}
			a.add(source);
		}
		//cut a, then a only contains the path from s to t
		for(int i=a.size()-1; i>=0; i--) {
			Stack.add(a.get(i));
		}
		return Stack;
	}





	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260782511"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;

				/* YOUR CODE GOES HERE	*/

		// create  a residue graph

		WGraph graph_residual = new WGraph(graph);



		//the flow is 0 at first

		ArrayList<Edge> edges = graph.getEdges();
		for(int i=0; i<edges.size(); i++) {
			Edge e =  edges.get(i);
			e.weight=0;
		}




		//get path form DFS , if it exists enter the loop
		ArrayList<Integer> path = pathDFS(source,destination ,graph_residual);

		while(!path.isEmpty()) {

			int p_flow = Integer.MAX_VALUE;

			int node1=0;
			int node2=0;
			for(int i=0; i<path.size()-1; i++) {
				 node1 = path.get(i);//u
				 node2 = path.get(i+1);//v
				p_flow = Math.min(p_flow, graph_residual.getEdge(node1, node2).weight);
			}

			int w_originl,w_new,w_original1,w_new1;
			for (int i=0; i<path.size()-1; i++) {
				 node1 = path.get(i); //u
				 node2 = path.get(i+1);//v

				w_original1 = graph.getEdge(node1, node2).weight;
				w_new1 = w_original1 + p_flow;
				graph.setEdge(node1, node2, w_new1);


				 w_originl = graph_residual.getEdge(node1, node2).weight;
				 w_new = w_originl - p_flow;
				graph_residual.setEdge(node1, node2, w_new);


			}

			//update maxFow
			maxFlow += p_flow;
			path = pathDFS(source,destination ,graph_residual);
		}


		answer += maxFlow + "\n" + graph.toString();
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}




	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it

		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));

		 //* need to detelte the following lines
		//pathDFS(g.getSource(),g.getDestination(),g);
	 }
}
