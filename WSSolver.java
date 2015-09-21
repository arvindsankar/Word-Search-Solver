import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList; 

	/*
	 * WordSearch Solver
	 */

public class WSSolver {		
		// Scanner
		public Scanner sc = new Scanner(System.in);

		// Number of columns in the puzzle
		private int numCol; 
		
		// Number of columns in the puzzle
		private int numRow; 
		
		// Word that program looks for.
		private String s;
		
		// 2-D Array that holds the wordsearch
		private char grid [][];
		
		// Coordinates of the Solution.
		private ArrayList<Integer> sigRows = new ArrayList<Integer>();
		private ArrayList<Integer> sigCols = new ArrayList<Integer>();

		// The name of the file to open.
        String fileName = "wordSearch.txt";

        // This will reference one line at a time
        String line = null;

		// Set number of columns of puzzle. 
		private void setNumCol(int c){
			numCol = c;
		}
		
		// Set number of columns of puzzle. 
		private void setNumRow(int r){
			numRow = r;
		}
		
		public void fillArray(){
			// Column Length
			System.out.println("Enter number of columns: ");
			setNumCol(sc.nextInt());
			
			//Row Length
			System.out.println("Enter number of rows: ");
			setNumRow(sc.nextInt()); 
			
			grid = new char [numCol][numRow];
			
			try{
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = new FileReader(fileName);

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = new BufferedReader(fileReader);
		            
		            int r = 0;
		            while((line = bufferedReader.readLine()) != null){
		    				for(int c = 0; c <numCol; c++){
		    					grid [c][r] = line.charAt(c);
		    				}
		    				r++;
		            }    

		            bufferedReader.close();            
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(
		                "Unable to open file '" + 
		                fileName + "'");                
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error reading file '" 
		                + fileName + "'");                   
		        }
		}
		
		// Print the WordSearch entered by the user.
		public void printArray(){
			if(sigCols.size() == 0){
				System.out.println("SigCols = 0 :(");
			}
			for(int i = 0; i < sigCols.size(); i++){
				grid[sigCols.get(i)][sigRows.get(i)] = '*';
			}
			
			for(int r = 0; r <numRow; r++){
				for(int c = 0; c <numCol; c++){
					System.out.print(grid[c][r]);
				}
				System.out.println();
			}
		}
		
		public boolean checkUp(int col, int row){
			try{
				for(int r = row; r > row - s.length(); r--){
					if(s.charAt(row-r) != grid[col][r]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			for(int r = row; r > row - s.length(); r--){
				sigCols.add(col);
				sigRows.add(r);
			}
			return true;
		}
		
		public boolean checkDown(int col, int row){
			try{
				for(int r = row; r < row + s.length(); r++){
					if(s.charAt(r-row) != grid[col][r]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			for(int r = row; r < row + s.length(); r++){
				sigCols.add(col);
				sigRows.add(r);
			}
			return true;
		}
		
		public boolean checkLeft(int col, int row){
			try{
				for(int c = col; c > col - s.length(); c--){
					if(s.charAt(col - c) != grid[c][row]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			for(int c = col; c > col - s.length(); c--){
				sigCols.add(c);
				sigRows.add(row);
			}
			return true;
		}
		
		public boolean checkRight(int col, int row){
			try{
				for(int c = col; c < col + s.length(); c++){
					if(s.charAt(c - col) != grid[c][row]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			for(int c = col; c < col + s.length(); c++){
				sigCols.add(c);
				sigRows.add(row);
			}
			return true;
		}
		
		public boolean checkUpRight(int col, int row){
			try{
				int r = row; 
				int c = col;
				while(r > row - s.length()){
					if(s.charAt(row-r) != grid[c][r]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
					r--;
					c++;
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			int r = row; 
			int c = col;
			while(r > row - s.length()){
				sigCols.add(c);
				sigRows.add(r);
				r--;
				c++;
			}
			return true;
		}
		
		public boolean checkDownRight(int col, int row){
			try{
				int r = row; 
				int c = col;
				while(r < row + s.length()){
					if(s.charAt(r-row) != grid[c][r]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
					r++;
					c++;
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			int r = row; 
			int c = col;
			while(r < row + s.length()){
				sigCols.add(c);
				sigRows.add(r);
				r++;
				c++;
			}
			return true;
		}
		
		public boolean checkUpLeft(int col, int row){
			try{
				int r = row; 
				int c = col;
				while(r > row - s.length()){
					if(s.charAt(row-r) != grid[c][r]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
					r--;
					c--;
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			int r = row; 
			int c = col;
			while(r > row - s.length()){
				sigCols.add(c);
				sigRows.add(r);
				r--;
				c--;
			}
			return true;
		}
		
		public boolean checkDownLeft(int col, int row){
			try{
				int r = row; 
				int c = col;
				while(r < row + s.length()){
					if(s.charAt(r-row) != grid[c][r]){
						sigCols.clear();
						sigRows.clear();
						return false;
					}
					r++;
					c--;
				}
			}catch(IndexOutOfBoundsException e){
				sigCols.clear();
				sigRows.clear();
				return false;
			}
			int r = row; 
			int c = col;
			while(r < row + s.length()){
				sigCols.add(c);
				sigRows.add(r);
				r++;
				c--;
			}
			return true;
		}
		
		
		// * is invoked even if the method is incorrect
		public void searchArray(){
			System.out.println("Enter word you are looking for: ");
			s = (sc.next());
			for(int i = 0; i < numCol; i++){
				for(int j = 0; j < numRow; j++){
					if(s.charAt(0) == grid[i][j]){
						if(checkDown(i,j)){
							return;
						}
						else if(checkUp(i,j)){
							return;
						}
						else if(checkLeft(i,j)){
							return;
						}
						else if(checkRight(i,j)){
							return;
						}
						else if(checkUpRight(i,j)){
							return;
						}
						else if(checkUpLeft(i,j)){
							return;
						}
						else if(checkDownRight(i,j)){
							return;
						}
						else if(checkDownLeft(i,j)){
							return;
						}
					}
				}
			}
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WSSolver ws = new WSSolver();
		ws.fillArray();
		ws.searchArray();
		ws.printArray();
	}
}