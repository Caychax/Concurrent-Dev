package ass01allwords;

import java.util.*;





public class Main {
public static void main(String[] args) {
	
		char[][] test_grid = {{'l','u','k','a','s','o'},
							  {'l','u','e','l','l','h'},
							  {'l','h','f','u','n','e'},
							  {'f','h','e','u','l','f'},
							  {'l','u','e','l','n','u'},
							  {'l','h','n','l','l','n'}};
		
		Scanner scr= new Scanner(System.in);
		
		System.out.println("enter word of any length to search in cross word");
		
		String WORD=scr.nextLine();
		char[] letters = new char[WORD.length()];
		for(int i=0;i<WORD.length();i++) {
			letters[i]=WORD.charAt(i);
		}
		
		int nThreads = Runtime.getRuntime().availableProcessors();//gets the optimal thread count for this computers processor
	     	        
	        
	    ArrayList<SearchThread> workers = new ArrayList<SearchThread>();//list of threads
		
		char[][] grid=matrixGenerator();

		
		int index[] = new int[nThreads+1];//list to store index of each chunk of data from f
        
        for(int j = 0; j <= nThreads; j++) { //loop to assign each index to the index storing list
        	index[j] = (j*grid.length)/nThreads;
        	//System.out.println(index[j]);
        }
        
        long start = System.nanoTime();

		for(int i=0; i<nThreads;i++ ) {// loop that instantiates each thread with data chunks
			workers.add(new SearchThread(grid, index[i], index[i+1],letters));
        	workers.get(i).start();//starts the threads
		}
		
		 for(int i=0; i<nThreads;i++ ) {// loop to join all threads simultaneously 
	        	try {
	        		workers.get(i).join();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
	        }
		 long end = System.nanoTime();
		int counter=0;
		for(int i=0; i<nThreads;i++ ) {// loop that instantiates each thread with data chunks
        	counter += workers.get(i).getFound();
		}
		
		//counter=t.getFound();
		
		System.out.print("found: "+counter);
		
		System.out.print("\nExecution Time: "+(float)(end-start)/1000000000+"s");

	}
	
	public static char[][] matrixGenerator() {
		
		Random randomGenerator = new Random(); char[][] arr = new char[10000][10000];
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = (char) (randomGenerator.nextInt(26)+97); 
			}
		}
		
		return arr;
	}
	public static void display(char[][]arr) {
		
		for (int i = 0; i < arr[0].length; i++) {
			System.out.printf("%-3d", i);
			}
			System.out.println();
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					System.out.print(arr[i][j]+"  "); 
				}
				System.out.println(); 
			}
		
	}

}
