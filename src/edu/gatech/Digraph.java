package edu.gatech;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;


public class Digraph {
        private final int vetices;
        private HashMap<Integer, ArrayList<Integer>> adj;
        private static final int INFINITY = Integer.MAX_VALUE;
        private HashMap<Integer, boolean[]> marked = new HashMap<Integer, boolean[]>(); 
        private HashMap<Integer, int[]> edgeTo = new HashMap<Integer, int[]>();
        private HashMap<Integer, int[]> distTo = new HashMap<Integer, int[]>();
        
        
        public Digraph(int vetices) {
            this.vetices = vetices;
            this.adj = new HashMap<Integer, ArrayList<Integer>>();
            for (int v = 0; v < vetices; v++) {
                ArrayList<Integer> nearVetices = new ArrayList<Integer>();                
                this.adj.put(v, nearVetices);
                boolean[] mark = new boolean[vetices];
                this.marked.put(v, mark);
                int[] edgeArray = new int[vetices];
                this.edgeTo.put(v, edgeArray);
                int[] distArray = new int[vetices];
                this.distTo.put(v, distArray);
            }                  
        }
        
        public void addEdge(int v, int w) {
            ArrayList<Integer> nearVetices = adj.get(v);
            nearVetices.add(w);
        }
        
        public ArrayList<Integer> adj(int v) {
            return this.adj.get(v);
        }  
        
        public void BreadthFirstDirectedPaths() {
            for (int s = 0; s < vetices; s++) {
                int[] distArray = this.distTo.get(s);
                for (int v = 0; v < vetices; v++) {
                    if (v == s) {
                        continue;
                    } else {
                        distArray[v] = INFINITY;
                    }
                }
                bfs(s);
            }
        }
        
        private void bfs(int s) {
            Queue<Integer> q = new LinkedList<Integer>();
            int[] distArray = this.distTo.get(s);
            boolean[] mark = this.marked.get(s);
            int[] edgeArray = this.edgeTo.get(s);
            mark[s] = true;
            distArray[s] = 0;
            q.add(s);
            while (!q.isEmpty()) {
                int v = q.remove();
                for (int w : adj(v)) {
                    if (!mark[w]) {
                        edgeArray[w] = v;
                        distArray[w] = distArray[v] + 1;
                        mark[w] = true;
                        q.add(w);
                    }
                }
            }
        }
        
        public boolean hasPathTo(int s, int v) {
            boolean[] mark = this.marked.get(s);
            return mark[v];
        }

        public Iterable<Integer> pathTo(int s, int v) {
            if (!hasPathTo(s, v)) return null;
            Stack<Integer> path = new Stack<Integer>();
            int[] distArray = this.distTo.get(s);
            int[] edgeArray = this.edgeTo.get(s);
            int x;
            for (x = v; distArray[x] != 0; x = edgeArray[x]) {
                path.push(x);
            }
            path.push(x);
            return path;
        }
    }