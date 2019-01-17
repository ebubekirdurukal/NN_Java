//Ebubekir Durukal - July 2018
//This file implements basic feed forward neural network in Java

import java.lang.Math;

public class main {
	/*
	 * 40 input
	 * 5 hidden neurons
	 * 40 x 5 = 200
	 * 2 result neurons
	 * 5 x 2 = 10
	 * Total 200 + 10 = 210 connections (weights)
	 * 5 + 2 = 7 bias numbers
	 * 
	 */
	static double[][][] first_connections = new double[5][8][5];
	static double[][] second_connections = new double[5][2];
	static double[] L1_neurons = new double[5];
	static double[] L2_neurons = new double[2];
	
	public static void init(double a, double b) {
		
		for(int i=0; i<first_connections.length; i++) {
			for(int j=0; j<first_connections[0].length; j++) {
				for(int k=0; k<first_connections[0][0].length; k++) {
					first_connections[i][j][k] = a;
				}
			}
		}
		
		for(int i=0; i<second_connections.length; i++) {
			for(int j=0; j<second_connections[0].length; j++) {
				
				second_connections[i][j] = b;
			}
		}
		
	}
	
	public static void  multip( int[][]input ){
		
		for(int k=0; k<L1_neurons.length; k++) {
			for(int i=0; i<input.length; i++) {
				for(int j=0; j<input[0].length; j++) {
						
					L1_neurons[k] += (double)input[i][j]*first_connections[i][j][k];
				}
			}
		}
		for(int i=0; i<L1_neurons.length; i++) {
			L1_neurons[i] = 1/(1+Math.exp(-1*L1_neurons[i]));
		}
		
		for(int k=0; k<L2_neurons.length; k++) {
			for(int i=0; i<second_connections.length; i++) {
				
				L2_neurons[k] += L1_neurons[i]*second_connections[i][k];
			}
		}
		
		for(int i=0; i<L2_neurons.length; i++) {
			L2_neurons[i] = 1/(1+Math.exp(-1*L2_neurons[i]));
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		init(0.5,0.3);
		double errX = 0;
		double errY = 0;
		
		int[][] set11 = {{1,1,1,0,0,1,1,1},
						 {1,1,1,0,0,1,1,1},
						 {1,1,1,0,0,1,1,1},
						 {1,1,1,0,0,1,1,1},
						 {1,1,1,0,0,1,1,1}};
		
		int[][] set12 = {{1,1,0,0,0,1,1,1},
						 {1,1,0,0,0,1,1,1},
						 {1,1,0,0,0,1,1,1},
						 {1,1,0,0,0,1,1,1},
						 {1,1,0,0,0,1,1,1}};

		int[][] set13 = {{1,1,1,1,0,0,1,1},
						 {1,1,1,1,0,0,1,1},
						 {1,1,1,1,0,0,1,1},
						 {1,1,1,1,0,0,1,1},
						 {1,1,1,1,0,0,1,1}};
		
		int[][] set01 = {{1,1,0,0,0,0,1,1},
						 {1,1,0,1,1,0,1,1},
						 {1,1,0,1,1,0,1,1},
						 {1,1,0,1,1,0,1,1},
						 {1,1,0,0,0,0,1,1}};
		
		int[][] set02 = {{0,0,0,0,0,1,1,1},
				 		 {0,1,1,1,0,1,1,1},
				 		 {0,1,1,1,0,1,1,1},
				 		 {0,1,1,1,0,1,1,1},
				 		 {0,0,0,0,0,1,1,1}};
		
		int[][] set03 = {{1,1,1,0,0,0,0,1},
		 		 		 {1,1,1,0,1,1,0,1},
		 		 		 {1,1,1,0,1,1,0,1},
		 		 		 {1,1,1,0,1,1,0,1},
		 		 		 {1,1,1,0,0,0,0,1}};
		
		int N_of_Layer = 1;
		
		int counter = 0;
		while(counter<1000){
			multip(set11);
			errX = 1-L2_neurons[0];
			errY = 0-L2_neurons[1];
			init(errX*L2_neurons[0]*1-L2_neurons[0], errY*L2_neurons[1]*1-L2_neurons[1]);
			
			multip(set12);
			errX = 1-L2_neurons[0];
			errY = 0-L2_neurons[1];
			init(errX*L2_neurons[0]*1-L2_neurons[0], errY*L2_neurons[1]*1-L2_neurons[1]);
			
			multip(set13);
			errX = 1-L2_neurons[0];
			errY = 0-L2_neurons[1];
			init(errX*L2_neurons[0]*1-L2_neurons[0], errY*L2_neurons[1]*1-L2_neurons[1]);
			
			multip(set01);
			errX = 0-L2_neurons[0];
			errY = 1-L2_neurons[1];
			init(errX*L2_neurons[0]*1-L2_neurons[0], errY*L2_neurons[1]*1-L2_neurons[1]);
			
			multip(set02);
			errX = 0-L2_neurons[0];
			errY = 1-L2_neurons[1];
			init(errX*L2_neurons[0]*1-L2_neurons[0], errY*L2_neurons[1]*1-L2_neurons[1]);
			
			multip(set03);
			errX = 0-L2_neurons[0];
			errY = 1-L2_neurons[1];
			init(errX*L2_neurons[0]*1-L2_neurons[0], errY*L2_neurons[1]*1-L2_neurons[1]);
			counter++;
		}
		multip(set11);
		System.out.println("Result for 11:" + L2_neurons[0]+" .... "+ L2_neurons[1]);
		multip(set12);
		System.out.println("Result for 12:" + L2_neurons[0]+" .... "+ L2_neurons[1]);
		multip(set13);
		System.out.println("Result for 13:" + L2_neurons[0]+" .... "+ L2_neurons[1]);
		multip(set01);
		System.out.println("Result for 01:" + L2_neurons[0]+" .... "+ L2_neurons[1]);
		multip(set02);
		System.out.println("Result for 02:" + L2_neurons[0]+" .... "+ L2_neurons[1]);
		multip(set03);
		System.out.println("Result for 03:" + L2_neurons[0]+" .... "+ L2_neurons[1]);
		
	}

}
