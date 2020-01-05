
public class BellmanFord{

	
	/**
	 * Utility class. Don't use.
	 */
	public class BellmanFordException extends Exception{
		private static final long serialVersionUID = -4302041380938489291L;
		public BellmanFordException() {super();}
		public BellmanFordException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 * 
	 * Use this to specify a negative cycle has been found 
	 */
	public class NegativeWeightException extends BellmanFordException{
		private static final long serialVersionUID = -7144618211100573822L;
		public NegativeWeightException() {super();}
		public NegativeWeightException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 *
	 * Use this to specify that a path does not exist
	 */
	public class PathDoesNotExistException extends BellmanFordException{
		private static final long serialVersionUID = 547323414762935276L;
		public PathDoesNotExistException() { super();} 
		public PathDoesNotExistException(String message) {
			super(message);
		}
	}
	
    private int[] distances = null;
    private int[] predecessors = null;
    private int source;


    BellmanFord(WGraph g, int source) throws BellmanFordException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         *  
         *  When throwing an exception, choose an appropriate one from the ones given above
         */
        
        /* YOUR CODE GOES HERE */



               //step 1: initial distacne fro course to all other vertices
//        //initiallize distances[]
//        //1.get all nodes from calss WGraph
//        //2. set the [node]=Intger.MAX_VALUE
//        //* if there isn't a node 3, then distance[3] will be blank

//
        this.source = source;
        int nb_nodes = g.getNbNodes();
        distances = new int[nb_nodes];
        predecessors = new int[nb_nodes];
        predecessors[source] = source;


        /**
         * Initialize distances as max value
         */
        for (int i = 0; i < nb_nodes; ++i) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0;


//        //step 2: relax all edges |V|-1 times,
//        //to calculate the shortes estimate value of each node

        predecessors=new int [nb_nodes];
        int nbEdges=g.getEdges().size();
        for(int i=1; i<nb_nodes;i++){
            for (int j=0; j<nbEdges;j++){
                int u=g.getEdges().get(j).nodes[0];
                int v=g.getEdges().get(j).nodes[1];
                int w=g.getEdges().get(j).weight;
                //relax the edges
                if(distances[u] != Integer.MAX_VALUE &&distances[v]>distances[u]+w){
                    distances[v]=distances[u]+w;
                    predecessors[v]=u;
                }
            }
        }




        /**
         * check for negative-weight cycles
         * if it is the shortest distances, then graph doesn't contain negative weight cycle.
         * if  negative weight cycle exist, loop forever
         */
        int i = 0;
        //step3: check for negative cycle and throw exceptions if there is
        while(i< nb_nodes-1    ) {
            for (int j = 0; j < nbEdges; j++) {
                int u =g.getEdges().get(j).nodes[0];
                int v = g.getEdges().get(j).nodes[1];
                int weight = g.getEdges().get(j).weight;
                if(distances[u]+weight <  distances[v]) {
                    //if MIN_VALUE -6 >0.
                    distances[v] = Integer.MIN_VALUE+20000;
                    throw new NegativeWeightException("negative weight");
                }
            }
            i ++;
        }







    }

    public int[] shortestPath(int destination) throws BellmanFordException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Exception is thrown
         * Choose appropriate Exception from the ones given 
         */

        /* YOUR CODE GOES HERE (update the return statement as well!) */
        if (distances[destination] == Integer.MAX_VALUE){
            throw new PathDoesNotExistException("no path exist!");
        }

        if(distances[destination] == Integer.MIN_VALUE+20000) {
            throw new PathDoesNotExistException("no path exist!");
        }


        int sPath[]= new int[distances.length];
        for(int i=0; i<sPath.length;i++){
            sPath[i]=-1;
            //initialzie all the slots in sPath to p1
            //since the actual lenth for the shortest parth may be smaller than the
            //number of nodes, so , initialze them to -1 for cutting

        }
        int i=0;

        if(distances[destination]!=Integer.MAX_VALUE) {//reachable
            int pNode = destination;
            sPath[i]=pNode;i++;
            while (distances[pNode]!=0) {//not reach to source yet
                pNode = predecessors[pNode];
                sPath[i]=pNode;//sPath stores an array d->..n2->n1->s->-1->-1...
                i++;
             //   System.out.println(i+"is");
            }


        }



        int [] p=new int[i];
        int k=i-1;
        for(int j=0; j<i;j++){
            p[j]=sPath[k];
            k--;
        }
       // System.out.println("dest is "+destination);
        return p;
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }

   } 
}
