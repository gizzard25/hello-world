package localMST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MST {
	
	    /**
	     * Comparator to sort edges by weight in non decreasing order
	     */
	    public class EdgeComparator implements Comparator<Edge> {
	        @Override
	        public int compare(Edge edge1, Edge edge2) {
	            if (edge1.getWeight() <= edge2.getWeight()) {
	                return -1;
	            } else {
	                return 1;
	            }
	        }
	    }

	    public List<Edge> getMST(Graph graph) {
	        List<Edge> allEdges = graph.getAllEdges();
	        EdgeComparator edgeComparator = new EdgeComparator();

	        //sort all edges in non decreasing order
	        Collections.sort(allEdges, edgeComparator);
	        DisjointSet disjointSet = new DisjointSet();

	        //create as many disjoint sets as the total vertices
	        for (Vertex vertex : graph.getAllVertex()) {
	            disjointSet.makeSet(vertex.getId());
	        }

	        List<Edge> resultEdge = new ArrayList<Edge>();

	        for (Edge edge : allEdges) {
	            //get the sets of two vertices of the edge
	            int root1 = disjointSet.findSet(edge.getVertex1().getId());
	            int root2 = disjointSet.findSet(edge.getVertex2().getId());

	            //check if the vertices are in same set or different set
	            //if verties are in same set then ignore the edge
	            if (root1 == root2) {
	                continue;
	            } else {
	                //if vertices are in different set then add the edge to result and union these two sets into one
	                resultEdge.add(edge);
	                disjointSet.union(edge.getVertex1().getId(), edge.getVertex2().getId());
	            }

	        }
	        return resultEdge;
	    }
	    
	    public static void main(String args[]) {
	        Graph graph = new Graph();
//	        graph.addEdge(1, 2, 4);
//	        graph.addEdge(1, 3, 1);
//	        graph.addEdge(2, 5, 1);
//	        graph.addEdge(2, 6, 3);
//	        graph.addEdge(2, 4, 2);
//	        graph.addEdge(6, 5, 2);
//	        graph.addEdge(6, 4, 3);
//	        graph.addEdge(4, 7, 2);
//	        graph.addEdge(3, 4, 5);
//	        graph.addEdge(3, 7, 8);
	        graph.addEdge(5, 200, 5);
	        graph.addEdge(5, 8, 3);
	        graph.addEdge(5, 37, 10);
	        graph.addEdge(8, 184, 1);
	        graph.addEdge(8, 78, 3);
	        graph.addEdge(184, 200, 3);
	        graph.addEdge(37, 78, 1);
	        graph.addEdge(9, 78, 2);
	        graph.addEdge(9, 200, 5);
	        MST mst = new MST();
	        List<Edge> result = mst.getMST(graph);
	        for (Edge edge : result) {
	            System.out.println(edge.getVertex1() + " " + edge.getVertex2());
	        }
	    }

}
