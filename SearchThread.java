package ass01allwords;

public class SearchThread extends Thread{
	private char[][] grid;
	private int start,end;
	private int found;
	private char[] WORD;
	private int length;
	
	public SearchThread(char[][] grid,int start,int end,char[] word) {
		this.grid = grid;
		this.start=start;
		this.end=end;
		this.found = 0;
		this.WORD=word;
		this.length=WORD.length-1;
	}
	@Override
	public void run() {
		
		for(int i = start; i<end;i++) {
			char[] row = grid[i];
			for(int j=0; j<row.length;j++) {
				if(row[j]==WORD[0]) { 
					//System.out.println("["+i+", "+j+"]");
					match(i,j);
				}
			}
		}
		
		 
	}
	private void match(int y , int x) {
		//System.out.println("entered match: x=>"+x+" | y=>"+y);
			if(x<grid[0].length-WORD.length+1) {
				//System.out.println(WORD[0]);
				if(horizontal(y,x)) {
					found++;
					System.out.println("match hor:"+"["+y+","+x+"-> "+(x+length)+"]");
				}
			}
			if(y<grid.length-WORD.length+1) {
				
				if(vertical(y,x)) {
					found++;
					System.out.println("match vert:"+"["+y+"-> "+(y+length)+","+x+"]");
				}
				//System.out.println("found along y");
				
			}
			if(x<grid[0].length-WORD.length+1&y<grid.length-WORD.length+1) {
				
				if(diagonal(y,x)) {
					found++;
					System.out.println("match diag:"+"["+y+","+x+"]");
				}
					//System.out.println("found diag");
				
			}
	}
	private boolean horizontal(int y , int x) {
		
		boolean fit=true;
		
		for(int i = 1; i<WORD.length;i++) {
			
			if(grid[y][x+i]!=WORD[i]) fit=false;
			
		}
		
		return fit;
	}
	private boolean vertical(int y, int x) {

		boolean fit=true;
		
		for(int i = 1; i<WORD.length;i++) {
			
			if(grid[y+i][x]!=WORD[i]) fit=false;
			
		}
		
		return fit;
	}
	private boolean diagonal(int y, int x) {
		boolean fit=true;
		
		for(int i = 1; i<WORD.length;i++) {
			
			if(grid[y+i][x+i]!=WORD[i]) fit=false;
			
		}
		
		return fit;
	}
	public int getFound() {
		return found;
	}

}
