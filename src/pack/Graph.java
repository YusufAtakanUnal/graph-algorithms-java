package pack;

import java.util.*;
//-----------------------------------------------------
// Title: Graph Class
// Author: Yusuf Atakan Ünal
// ID: 10060282728
// Section: 2
// Assignment: 1
// Description:In this class, we define what the structure of the graph contains,
// all graph methods and the main function
//-----------------------------------------------------
public class Graph {
    private int vertex;
    private List<Character>[] adj;
    private char startingVertex, endingVertex;

    public Graph(int vertex, int edges) {
        this.vertex = vertex;
        
        adj = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }
    }
    
    public void addEdge(char v, char w) {
    	//--------------------------------------------------------
    	// Summary: adds an edge between two given vertices, this edge is added to the adjacency list of both vertices. 
    	// This indicates that it is an undirected graph, i.e. if there is an edge between a and b, 
    	// both b is added to a's neighbourhood and a is added to b's neighbourhood.
    	// Precondition: v is a char and w is a char
    	// Postcondition: Added edge between given vertexes
    	//--------------------------------------------------------
        adj[v - 'a'].add(w);
        adj[w - 'a'].add(v);
    }
    
    
    public void removeElementFromList(List<Character> list, char element) {
    	//--------------------------------------------------------
    	// Summary: This method allows a given element (vertex) to be deleted from the neighbourhood list. 
    	// First, the index at which the element (vertex) appears in the list is determined, and then 
    	// the element is removed from the list. This is particularly useful when temporarily added edges need to be undone, 
    	// so that the effect of the changes is temporary and the original graph structure is preserved.
    	// Precondition: "list" denotes a list containing elements of type Character. "element" is char.
    	// Postcondition:The specified element has been removed from the list, if it exists. 
    	// If the element is not found in the list, the list remains unchanged.
    	//--------------------------------------------------------
    	int index = -1;
    	
    	for (int i = 0; i < list.size(); i++) {
    		
    		if (list.get(i) == element) {
    			
    			index = i;
    			break;
    			
    		}
    	}
    	
    	if (index != -1) {
    		
    		list.remove(index);
    		
    	}
    }
    public int[] findShortestPaths(char source) {
    	//--------------------------------------------------------
    	// Summary:  This method uses the Breadth-First Search (BFS) algorithm to find the shortest distances 
    	// from a given starting vertex to all other vertices. Initially, all distances are set to -1,
    	// then starting from the initial vertex, all neighbours are reached in a queue and the distances are updated.
    	// To find the shortest distance to each vertex, this algorithm calculates the distances in the 
    	// order of travelling and adds them to the queue.
    	// Precondition: source is a char
    	// Postcondition: Returns an array of integers representing the shortest distances from the given 
    	// source vertex to all other vertices in the graph, with -1 indicating that a vertex is not reachable from the source.
    	//--------------------------------------------------------
    	
        int[] distances = new int[26]; 
        
        for(int i = 0; i <distances.length;i++) {
        	
        	distances[i] = -1;
        	
        }

        char[] list = new char[26];
        int firstIndex = 0;
        int lastIndex = 0;
        list[lastIndex++] = source;
        distances[source - 'a'] = 0;
        
        
        while(firstIndex < lastIndex) {
        	
        	char current = list[firstIndex++];
	
        	for(int i = 0;i<adj[current - 'a'].size();i++) {
        		
        		char connectedVertex = adj[current - 'a'].get(i);
        		
        		if(distances[connectedVertex - 'a']==-1) {
			
			distances[connectedVertex -'a'] = distances[current - 'a'] +1;
			list[lastIndex++] = connectedVertex;
		}
        }
        }

        return distances;
    }

    
    public void findNewConnections() {
    	//--------------------------------------------------------
    	// Summary:  This method checks whether new edges can be added to the neighbourhood list of the graph 
    	// while preserving the current shortest path distance. First, the current distance between the given 
    	// start and goal vertex is calculated. Then, all vertex pairs are checked and if there is no existing 
    	// edge between two vertex pairs, a temporary edge is added between these two vertex pairs. After adding 
    	// the new edge, the distance is checked again by running BFS; if the distance does not change, the new 
    	// connection is considered valid and added to the list. Finally, valid connections are printed, or -1 is
    	// returned if no valid connections are found
    	// Postcondition:Identifies and prints all new valid edges that can be added to the graph without changing
    	// the shortest path distance between the given starting and ending vertices. If no valid connections are found,
    	// it prints -1.
    	//--------------------------------------------------------
        int[] shortestDistances = findShortestPaths(startingVertex);
        int initialDistance = shortestDistances[endingVertex - 'a'];

        if (initialDistance == -1) {
        	
            System.out.println("-1");  
            return;
            
        }

        List<String> validConnections = new ArrayList<>();

    
        for (char firstVertex = 'a'; firstVertex < 'a' + vertex; firstVertex++) {
        	
            for (char secondVertex = (char) (firstVertex + 1); secondVertex < 'a' + vertex; secondVertex++) {
            	
                if (!adj[firstVertex - 'a'].contains(secondVertex)) {
                	
                   
                    adj[firstVertex - 'a'].add(secondVertex);
                    adj[secondVertex - 'a'].add(firstVertex);

                    
                    int[] updatedDistances = findShortestPaths(startingVertex);

                    
                    if (updatedDistances[endingVertex - 'a'] == initialDistance) {
                    	
                        validConnections.add(firstVertex + " " + secondVertex);  
                        
                    }

                    removeElementFromList(adj[firstVertex - 'a'], secondVertex);
                    removeElementFromList(adj[secondVertex - 'a'], firstVertex);
                }
            }
        }

        
        if (validConnections.size() == 0) {
        	
            System.out.println("-1");  
            
        } else {
            System.out.println(validConnections.size());
            for (String connection : validConnections) {
            	
                System.out.println(connection);
                
            }
        }
    }
    
    public static void main(String[] args) {
    	//--------------------------------------------------------
    	// Summary: main is the entry point of the programme and creates a Graph object with the 
    	// data entered by the user. After receiving the number of nodes and paths from the user,
    	// the edges of the graph are added with the addEdge method for each path. Then, the start
    	// and destination vertexes are determined and the findNewConnections method is run to check
    	// whether new connections can be added. This method finds new connections that fit the current
    	// structure of the graph and provides the output to the user.
    	//--------------------------------------------------------
        Scanner scanner = new Scanner(System.in);
        int numberOfLandmarks = scanner.nextInt();
        int numberOfRoads = scanner.nextInt();
        Graph graph = new Graph(numberOfLandmarks, numberOfRoads);

        for (int i = 0; i < numberOfRoads; i++) {
        	
            char firstVertex = scanner.next().charAt(0);
            char secondVertex = scanner.next().charAt(0);
            graph.addEdge(firstVertex, secondVertex);
            
        }

        graph.startingVertex = scanner.next().charAt(0);
        graph.endingVertex = scanner.next().charAt(0);

        graph.findNewConnections();
        scanner.close();
    }
}