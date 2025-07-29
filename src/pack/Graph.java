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
    public int vertex;
    public List<Character>[] adj;
    
    public boolean[] marked;
    public char[] edgeTo;
    public int[] distTo;
	private char startingVertex;
	//-----------------------------------------------------
	// Summary:vertex holds the number of nodes of the graph.adj holds the list of 
	// adjacency nodes to which each node is connected."marked" is an array that holds
	// the nodes traversed during the breadth first search."edgeTo" holds which node we traverse
	// to reach a node."distTo" holds the distance to reach each node as an integer. 
	//-----------------------------------------------------
    public Graph(int vertex) {
        this.vertex = vertex;
        
        adj = new ArrayList[26];  
        for (int i = 0; i < 26; i++) {
        	
            adj[i] = new ArrayList<>();
            
        }
        
        marked = new boolean[26];  
        distTo = new int[26];  
        edgeTo = new char[26];  
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

    public List<Character> findAdj(char vertex) {
    	//--------------------------------------------------------
    	// Summary: This is a method that returns the neighbours in the adj list for that vertex.
    	// Precondition: "vertex" is a char.
    	// Postcondition: Returns the list of adjacent vertices for the given vertex.
    	//--------------------------------------------------------
    	int vertexIndex = vertex - 'a';
    	return adj[vertexIndex];
    }
    
    public void bfs(Graph G, char startingVertex) {
 
    	 //--------------------------------------------------------
    	 // Summary: is a function that applies the Breadth-First Search (BFS) algorithm starting from 
    	 // the starting vertex. First, all nodes are assumed to be unvisited (marked array is set to false),
    	 // distances (distTo) and previous node information (edgeTo) are reset to zero. 
    	 // Then, the start node is added to a queue, its distance is set to 0 and BFS is initialised. 
    	 // Each node is removed from the queue and its neighbours are visited. 
    	 // If the neighbour has not been visited before, the neighbour is added to the queue, 
    	 // its distance is increased by one and the node from which it came is recorded. 
    	 // This process continues until all nodes have been visited. Thus, the shortest distance 
    	 // and path information of each node is obtained.
    	 // Precondition: G is a graph and startingVertex is a char
    	 // Postcondition: All neighbours of all vertexes were visited and all neighbours were added to the queue.
    	 //--------------------------------------------------------
    	for (int i = 0; i < marked.length; i++) {
    	    marked[i] = false;
    	}
    	
    	for (int i = 0; i < distTo.length; i++) {
    	    distTo[i] = -1;
    	}
    	
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = '\0';
        }

        Queue<Character> q = new LinkedList<>();
        q.add(startingVertex);
        marked[startingVertex - 'a'] = true;
        distTo[startingVertex - 'a'] = 0; 

        while (!q.isEmpty()) {
        	
            char v = q.poll();
            List<Character> adjList = G.findAdj(v); 
            
            for (int i = 0; i < adjList.size(); i++) {  
            	
                char w = adjList.get(i);  
                if (!marked[w - 'a']) {
                	
                    q.add(w);
                    marked[w - 'a'] = true;
                    edgeTo[w - 'a'] = v;  
                    distTo[w - 'a'] = distTo[v - 'a'] + 1;  
                    
                }
            }
        }
    }	
    

    
    public List<Character> getPath(char startingVertex, char endingVertex) {
    	//--------------------------------------------------------
    	// Summary: The purpose of this method is to retrace the shortest path between the 
    	// startingVertex and the endingVertex found with the Breadth-First Search (BFS)
    	// algorithm and return this path as a list.In other words, we obtain a path using the 
    	// path information found with BFS.
    	// Precondition: "startingVertex" is a char and "endingVertex" is a char.
    	// Postcondition: Returns the list of vertices representing the shortest path from the starting vertex
    	// to the ending vertex, with the vertices in the correct order from start to end.
    	//--------------------------------------------------------
        List<Character> path = new ArrayList<>();
        char v = endingVertex;
        if (distTo[endingVertex - 'a'] == -1) {
        	
            return path; 
            
        }
        
        while (v != startingVertex) {
        	
            path.add(v);
            v = edgeTo[v - 'a'];
            
        }
        path.add(startingVertex);
        Collections.reverse(path);
        return path;
    }
    
    public static void printPath(List<Character> path) {
    	//--------------------------------------------------------
    	// Summary:The printPath method is used to print the path list returned from the getPath method to the screen. 
    	// This list contains the shortest path from one vertex to another, and each vertex is separated by a space.
    	// Precondition: The path is a list of Characters.
    	// Postcondition: Returns the list of adjacent vertices for the given vertex, 
    	// representing all the vertices directly connected to the specified vertex.
    	//--------------------------------------------------------
        for (int i = 0; i < path.size(); i++) {
        	
            System.out.print(path.get(i));
            
            if (i < path.size() - 1) {
            	
                System.out.print(" ");
                
            }
        }
    }

    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        
        int numberOfLandmarks = scanner.nextInt();
        int numberOfRoads = scanner.nextInt();
        
        
        Graph graph = new Graph(numberOfLandmarks);


        for (int i = 0; i < numberOfRoads; i++) {
        	
        	// We get two vertex  from the user and add a path between them
            char firstVertex = scanner.next().charAt(0);
            char secondVertex = scanner.next().charAt(0);
            graph.addEdge(firstVertex, secondVertex); 
            
        }

        
        graph.startingVertex = 'c';  
        boolean allVisited = false;  // A variable to check if all nodes have been reached.

        // We check the reachability of each node by running BFS for all vertexes
        for (char start = 'a'; start < 'a' + numberOfLandmarks; start++) {
        	
            graph.bfs(graph, start); 

            boolean visitedAll = true;
            
            for (char node = 'a'; node < 'a' + numberOfLandmarks; node++) {
            	
                if (graph.distTo[node - 'a'] == -1) { 
                	
                    visitedAll = false;  
                    break;
                    
                }
            }

            if (visitedAll) {  
            	
                allVisited = true;  
                break;
                
            }
        }

        
        if (allVisited) {
        	// We run BFS starting from vertex ‘c’
            for (char start = 'c'; start < 'a' + numberOfLandmarks; start++) {
            	
                graph.bfs(graph, start); 

            	// We obtain path information by reaching all nodes
                for (char node = 'a'; node < 'a' + numberOfLandmarks; node++) {
                	
                    if (graph.distTo[node - 'a'] != -1) {  
                    	
                        List<Character> path = graph.getPath(start, node);  
                        
                        if (path.size() == numberOfLandmarks) {  
                        	
                            System.out.println(graph.distTo[node - 'a']);  
                            printPath(path);  
                            System.out.println();  
                            return;  
                            
                        }
                    }
                }
            }
        } else {
            System.out.println(-1);  // If all nodes are not reached, we print -1
        }

        scanner.close();
    }
	}
