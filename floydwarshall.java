package com.example.supermaket_user;

// A Java program for Floyd Warshall All Pairs Shortest
// Path algorithm.
import java.util.*;
import java.lang.*;
import java.io.*;


public class floydwarshall
{
    final static int INF = 99999, V = 21;
    int graph[][] = {
            {0,2,INF,INF,INF,INF,1,INF,INF,INF,INF,1,INF,INF,INF,INF,2,INF,INF,INF,INF},
            {2,0,1,INF,INF,INF,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,1,0,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,1,0,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,1,0,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,1,0,INF,INF,INF,INF,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {1,1,INF,INF,INF,INF,0,1,INF,INF,INF,1,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,1,0,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,1,0,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,1,0,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,1,INF,INF,INF,1,0,INF,INF,INF,INF,1,INF,INF,INF,INF,INF},
            {1,INF,INF,INF,INF,INF,1,INF,INF,INF,INF,0,1,INF,INF,INF,1,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,0,1,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,0,1,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,0,1,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF,INF,INF,1,0,INF,INF,INF,INF,1},
            {2,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF,INF,INF,INF,0,1,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,0,1,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,0,1,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,0,1},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF,INF,INF,1,0},
    };
    void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    int[] multi(int graph[][],int loc[],int n){
        int sum=0;
        int min=100;
        int comp=0;
        int key=0;
        for(int i=0;i<n;i++)
        {
            comp=graph[0][loc[i]];
            if(comp<min)
            {
                min=comp;
                key=i;
            }

        }
        swap(loc,key,n-1);
        for(int i=0;i<n;i++)
        {
            System.out.println(loc[i]);
        }
        sum=sum+min;
        for(int j=n-1;j>0;j--)
        {
            min=100;
            for(int i=0;i<j;i++)
            {
                comp=graph[loc[j]][loc[i]];
                if(comp<min)
                {
                    min=comp;
                    key=i;
                }
            }
            swap(loc,key,j-1);
            for(int i=0;i<n;i++)
            {
                System.out.println(loc[i]);
            }
            sum=sum+min;
        }
        System.out.println(sum);
        return loc;
    }
    int[][] floydWarshall(int graph[][])
    {
        int dist[][] = new int[V][V];
        int i, j, k;

		/* Initialize the solution matrix same as input graph matrix.
		Or we can say the initial values of shortest distances
		are based on shortest paths considering no intermediate
		vertex. */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

		/* Add all vertices one by one to the set of intermediate
		vertices.
		---> Before start of an iteration, we have shortest
			distances between all pairs of vertices such that
			the shortest distances consider only the vertices in
			set {0, 1, 2, .. k-1} as intermediate vertices.
		----> After the end of an iteration, vertex no. k is added
				to the set of intermediate vertices and the set
				becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print the shortest distance matrix
        //printSolution(dist);
        return dist;
    }

    void printSolution(int dist[][])
    {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }
    }

    // Driver program to test above function
    public static void main (String[] args)
    {


        floydwarshall a = new floydwarshall();

        // Print the solution
        int[][] dist=a.floydWarshall(a.graph);
        int[] array=new int[4];
        array[0]=2;
        array[1]=3;
        array[2]=18;
        array[3]=16;
        int[]x=a.multi(dist,array,4);
        for(int i=0;i<x.length;i++)
        {
            System.out.println(x[i]);
        }
    }
}