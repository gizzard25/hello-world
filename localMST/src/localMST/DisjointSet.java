package localMST;

import java.util.HashMap;
import java.util.Map;


public class DisjointSet {

    private Map<Integer, Node> map = new HashMap<>();

    class Node {
        Integer data;
        Node parent;
        int rank;
    }

    /**
     * Create a set with only one element.
     */
    public void makeSet(Integer data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(Integer data1, Integer data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if ((int)parent1.data == (int)parent2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    /**
     * Finds the representative of this set
     */
    public int findSet(Integer data) {
        return findSet(map.get(data)).data;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }
    
}
