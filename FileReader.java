import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
//-----------------------------------------------------
//Assignment 3
//Written by: Miroslav Miskovski 40249269
//-----------------------------------------------------
/**
 * This class will contain the methods that will read the files and store the data, the methos that will interac with the user is also in the this class.
 * @author miro
 *
 */
public class FileReader  {

	private String [] booksCategoryFiles = {"Cartoons_Comics_Books.csv.txt", "Hobbies_Collectibles_Books.csv.txt", "Movies_TV.csv.txt", "Music_Radio_Books.csv.txt", "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio.csv.txt", "Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt"};
	private String [] booksCategoryFilesBin = {"Cartoons_Comics_Books.csv.txt.ser", "Hobbies_Collectibles_Books.csv.txt.ser", "Movies_TV.csv.txt.ser", "Music_Radio_Books.csv.txt.ser", "Nostalgia_Eclectic_Books.csv.txt.ser", "Old_Time_Radio.csv.txt.ser", "Sports_Sports_Memorabilia.csv.txt.ser", "Trains_Planes_Automobiles.csv.txt.ser"};

	private String [] bookGenreCode = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
	
	/**
	 * string containing the directory to acces basic files to read
	 */
	private String D  = "/Users/miro/Desktop/school/COMP 249/Comp249_W23_Assg3/";
	
	private int ccbCount = 0;
	private int hcbCount = 0;
	private int mtvCount = 0;
	private int mrbCount = 0;
	private int nebCount = 0;
	private int otrCount = 0;
	private int ssmCount = 0;
	private int tpaCount = 0;
	private int errCount = 0;
	
	private int symccbCount = 0;
	private int symhcbCount = 0;
	private int symmtvCount = 0;
	private int symmrbCount = 0;
	private int symnebCount = 0;
	private int symotrCount = 0;
	private int symssmCount = 0;
	private int symtpaCount = 0;
	private int symerrCount = 0;
	
	private int isbn10error = 0;
	private int isbn13error = 0;
	private int priceerror = 0;
	private int yearerror = 0;
	
	/**
	 * This method will go over the main file "part1_input_file_names.txt" that will contain all the file names that need to be read
	 * inside the will be a number of lines each containing a book info. Each line will be read and book object will be created and stored in the proper category for each book analized.
	 * Each book containing an error will be stored inside an syntax error file.
	 */
	public void do_part1() {
		
		//streams needed
		Scanner sc1 = null;//for first file
		Scanner sc2 = null;// for the other files containing the books 
		PrintStream errStream = null;
		
		try {
			
			//streams declarations
			
			sc1 = new Scanner(new FileInputStream(D + "part1_input_file_names.txt"));
			errStream = new PrintStream(new FileOutputStream(D + "syntax_error_file.txt",true));
			
			int numberOfFiles = Integer.parseInt(sc1.nextLine());
			
			for(int i = 0; i < numberOfFiles; i++) {
				
				String y = sc1.nextLine();
				String FileToRead = D + y;
				
				File file = new File(FileToRead);
				
				sc2 = new Scanner(new FileInputStream(file));
				
				if(sc2.hasNextLine() && file.exists()) {
					errStream.println("syntax error in file: " + y);
					errStream.println("====================");
					fileDocsReaderAndWriter(sc2, errStream);
				}
				
				else {
					System.out.println("doc not readable" + FileToRead);
					continue;
				}
			}
		
		
		}catch(FileNotFoundException e) {
			System.out.println("1A");
			System.out.println(e.getMessage());
			
		}	
		catch(Exception e) {
			System.out.println("1b");
			System.out.println(e.getMessage());

		
		}finally {
			sc1.close();
			sc2.close();
			errStream.close();
		}
		
	
	
	}
	
	
	
	/**
	 * This method is used as support to the part one method, this method will be the core of "do_part1()" it will take two parameters 
	 * one scanner and printstream, where the scanner will read each file containing the books and the printstream will store the books containing a syntax error
	 * @param sc2 scanner for the files.
	 * @param errStream syntax error writer.
	 */
	private void fileDocsReaderAndWriter(Scanner sc2, PrintStream errStream) {
		
		PrintWriter [] pw = new PrintWriter[booksCategoryFiles.length];
		Book book = null;
		try {
		
		for(int i = 0; i < booksCategoryFiles.length; i++) {
			pw[i] = new PrintWriter(new FileOutputStream( D + booksCategoryFiles[i],true));
		}
		
		String bookString = null;
		
		while(sc2.hasNextLine()) {
			
			bookString = sc2.nextLine();
			
			try {//HERE READING EACH BOOK AND PUTING INFO IN CORRECT FILE ALSO EXCEPTION HANDLING AND INPUT PROPER ERROR IN SYNTAX FILE
				book = new Book(bookString);
				
				boolean wrongGenreCodeBoolean = false;
				for(int j = 0; j < bookGenreCode.length; j++) {
					
					if(book.bookGenre.equals(bookGenreCode[j])){
						wrongGenreCodeBoolean = true;
					}
					
				}
				if(!wrongGenreCodeBoolean) {
					throw new UnknownGenreException();
				}
				
				//we will print each syntaxicly correct book to the correct genre file.
				for (int i = 0; i < bookGenreCode.length;i++) {
					if(book.bookGenre.equals(bookGenreCode[i])) {
						pw[i].println(bookString);
						if(book.bookGenre.equals("CCB")){
							ccbCount++;
						}
						if(book.bookGenre.equals("HCB")){
							hcbCount++;
						}
						if(book.bookGenre.equals("MTV")){
							mtvCount++;
						}
						if(book.bookGenre.equals("MRB")){
							mrbCount++;
						}
						if(book.bookGenre.equals("NEB")){
							nebCount++;
						}
						if(book.bookGenre.equals("OTR")){
							otrCount++;
						}
						if(book.bookGenre.equals("SSM")){
							ssmCount++;
						}
						if(book.bookGenre.equals("TPA")){
							tpaCount++;
						}
						
					}
				}
				
				
			}	
			catch(UnknownGenreException e) {
				errStream.println("Error: invalid genre");//
				errStream.println(bookString);
				errStream.println("");
				++errCount;
			}
			catch(TooManyFieldsException e) {
				++errCount;
			}
			catch(TooFewFieldsException e) {
				++errCount;

			}

			catch(MissingFieldException e) {//store the the data with missing info in here
				
				errStream.println(e.getMessage());//category that is missing
				errStream.println(bookString);
				errStream.println("");
				++errCount;

			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			
		}//END WHILE LOOP
		
		
		}catch(Exception e){
			
		}finally {
			
			
			for(int i = 0; i < booksCategoryFiles.length; i++) {
				pw[i].close();
			}
		}
		
	}
	
	
	/**
	 * This method will create 8 arrays one for each category and inside we will be storing all books that had correct syntax but also this they would also have to 
	 * be symanticly correct(the length of each array will be the counter for each counter for correct books in each genre). This part will read each category file
	 * and will be looking for symantic correct books if so they will be put inside their proper genre array and at the end these 8 array of type books will be sent
	 * to a binary file.Each binary file will contain 1 array.
	 */
	public void do_part2() {
		
		Scanner sc1 = null;//this will be to read all the categorie files
		PrintStream errStream = null;
		Book book = null;

	
		//array that will store correct semantic books inside **MIGHT CONTAIN ARRAYS WITH NULL INDEX(NOT FULL) WHEN DONE READING THE FILES**
		Book [] symCCB = new Book[ccbCount];
		Book [] symHCB = new Book[hcbCount];
		Book [] symMTV = new Book[mtvCount];
		Book [] symMRB = new Book[mrbCount];
		Book [] symNEB = new Book[nebCount];
		Book [] symOTR = new Book[otrCount];
		Book [] symSSM = new Book[ssmCount];
		Book [] symTPA = new Book[tpaCount];
		
		try {
			
			errStream = new PrintStream(new FileOutputStream(D + "symantic_error_file.txt",true));
			
			for(int i = 0; i < booksCategoryFiles.length; i++) {
				
				sc1 = new Scanner(new FileInputStream(D + booksCategoryFiles[i]));
				errStream.println("symantic error in file: " + booksCategoryFiles[i]);
				errStream.println("====================");
				
				String x = null;
				while(sc1.hasNextLine()) {
					 x = sc1.nextLine();
					 try {
						 
						 book = new Book(x,true);
						
						 if( i == 0) {
							 symCCB[symccbCount] = book;
							 symccbCount++; 
						 }
						 else if ( i == 1) {
							 symHCB[symhcbCount] = book;
							 symhcbCount++;
						 }
						 else if ( i == 2) {
							 symMTV[symmtvCount] = book;
							 symmtvCount++;
						 }
						 else if ( i == 3) {
							 symMRB[symmrbCount] = book;
							 symmrbCount++;
						 }
						 else if ( i == 4) {
							 symNEB[symnebCount] = book;
							 symnebCount++;
						 }
						 else if ( i == 5) {
							 symOTR[symotrCount] = book;
							 symotrCount++;
						 }
						 else if ( i == 6) {
							 symSSM[symssmCount] = book;
							 symssmCount++;
						 }
						 else if ( i == 7) {
							 symTPA[symtpaCount] = book;
							 symtpaCount++;
						 }
						 
					 }catch(BadISBN10Exception e) {
						 errStream.println("Error: ISBN10");//
							errStream.println(x);
							errStream.println("");
							++symerrCount;
							isbn10error++;
					 }catch(BadISBN13Exception e) {
						 errStream.println("Error: ISBN13");//
							errStream.println(x);
							errStream.println("");
							++symerrCount;
							isbn13error++;
					 }catch(BadYearException e) {
						 errStream.println("Error: invalid Year");//
							errStream.println(x);
							errStream.println("");
							++symerrCount;
							yearerror++;
					 }catch(BadPriceException e) {
						 errStream.println("Error: invalid Price");//
							errStream.println(x);
							errStream.println("");
							++symerrCount;
							priceerror++;
					 }
					
				}
			
			}
			
		}catch(FileNotFoundException e) {
		
		}catch(Exception e) {
			
		}finally {
			errStream.close();
			sc1.close();
		}
		
		//Sending the arrays to the binary files
		ObjectOutputStream os = null;
		try {
			for(int i = 0; i < booksCategoryFilesBin.length ; i++) {
				
				os = new ObjectOutputStream(new FileOutputStream(D + booksCategoryFilesBin[i]));
				
				if( i == 0) {
					 os.writeObject(symCCB);
				 }
				 else if ( i == 1) {
					 os.writeObject(symHCB);
				 }
				 else if ( i == 2) {
					 os.writeObject(symMTV);
				 }
				 else if ( i == 3) {
					 os.writeObject(symMRB);
				 }
				 else if ( i == 4) {
					 os.writeObject(symNEB);
				 }
				 else if ( i == 5) {
					 os.writeObject(symOTR);
				 }
				 else if ( i == 6) {
					 os.writeObject(symSSM);
				 }
				 else if ( i == 7) {
					 os.writeObject(symTPA);
				 }
				
				os.close();
			}
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) { 
			
		}finally {
			
		}
		
	}
	
	
	/**
	 * This method will be the final piece of the program. In here the user will be able to navigate over the information in binary file. He will mainly
	 * have the option to view a file, select another file that he can after view OR leave and close the program. This method deserialize each binary file when ever a user will want
	 * to look at the info inside. He will be able to navigate over the info from each file. He will be given the choice how many books he wants to see. Its important to note,
	 * the user can go backwards in the display choice of the books and he cant go over the limits of each file.
	 * 
	 */
	public void do_part3() {
		
		Scanner sc1 = new Scanner(System.in);
		
		Boolean exit = false;
		int currentFileIndex = 0;
		int currentIndex = 0;
		
		Book [] currentArrayOfBooksToRead = readObjectFromBinaryFile(D + booksCategoryFilesBin[currentFileIndex]);//will need to add the path to it
		
		int [] countForNumberOfRecords = { symccbCount, symhcbCount, symmtvCount, symmrbCount, symnebCount, symotrCount, symssmCount, symtpaCount};	
		
		while(!exit) {
			
			System.out.println("----------------------------");
            System.out.println("         Main Menu");
            System.out.println("----------------------------");
            System.out.println("v  View the selected file: " + booksCategoryFilesBin[currentFileIndex] + " (" + countForNumberOfRecords[currentFileIndex] + " records)");
            System.out.println("s  Select a file to view");
            System.out.println("x  Exit");
            System.out.println("----------------------------");
            System.out.print("Enter Your Choice: ");
            String choice = sc1.next();
            
			switch(choice.toLowerCase()) {
				
				case "v":
					System.out.println("viewing: " + booksCategoryFilesBin[currentFileIndex] + " (" + countForNumberOfRecords[currentFileIndex] + " records)");
					System.out.println("Enter a number(n) to display n consecutive books for the list.");
					int n = 0;
					boolean pass = false;
					
					//making sure the input from user is correct
					while(!pass) {
					
						try{ 
						n=sc1.nextInt();
						pass =true;
					}catch(InputMismatchException e) {
						
					}catch(Exception e) {
						
					}
					
					}
			
					int start,end;
					
					//first condition
					if(n == 0) {
						System.out.println("You have chosen 0, the viewing session ends and controle will display the main menu again");
						break;
					}
					//second codition
					else if(n > 0) {
						
						int lastBookToRead = currentIndex + n - 1;
						start = currentIndex;
						end = lastBookToRead;
						
						
						
						if(lastBookToRead >= countForNumberOfRecords[currentFileIndex]) {
							for(int i = start; i < countForNumberOfRecords[currentFileIndex]; i++) {//might remove this for loop it is useless, TO BE TESTED
								System.out.println(currentArrayOfBooksToRead[i]);
							}
							System.out.println("EOF has been reached,cant go any further.You can press 1 or -1 to read the last book info.");
							currentIndex = countForNumberOfRecords[currentFileIndex] - 1;
						}
						
						else{
							
							if(n == 1) {
								System.out.println(currentArrayOfBooksToRead[currentIndex]);
								break;
							}
							
							for(int i = start; start < countForNumberOfRecords[currentFileIndex] - 1; i++) {
							if(i == end + 1) {
								break;
								}
								System.out.println(currentArrayOfBooksToRead[i]);
							}
							currentIndex = end;
						}
					
						
					}
					
					//third condition
					else if (n <0) {
						
						int lastBookToRead = currentIndex - Math.abs(n) - 1;
						
						
						if(lastBookToRead < 0 ) {
							for(int i = currentIndex;  i > 0 ; i--) {
								System.out.println(currentArrayOfBooksToRead[i]);
							}
							System.out.println("BOF has been reached,cant go any further.You can press 1 or -1 to read the last book info.");
							currentIndex = 0;
						}
						
						
						else {
							if( n == -1) {
								System.out.println(currentArrayOfBooksToRead[currentIndex]);
								break;
							}
							
							for(int i = currentIndex; i >= lastBookToRead; i--) {
								System.out.println(currentArrayOfBooksToRead[i]);
							}
							
							currentIndex = lastBookToRead;
							
						}
						
						
						
					}
					break;
				
				case "s":
					System.out.println("------------------------------");
                    System.out.println("        File Sub-Menu");
                    System.out.println("------------------------------");
                    for(int i = 0; i < booksCategoryFilesBin.length; i++) {
                    	System.out.println((i + 1) + " " + booksCategoryFilesBin[i] + " (" + countForNumberOfRecords[i] + " records)");
                    }
                    System.out.println("------------------------------");
                    System.out.println("Enter Your Choice");
                    int fileToRead = sc1.nextInt();
                    while(!(fileToRead > 0) || !(fileToRead <= 8)) {
                    	System.out.println("choice not valid, please enter valid number.");
                    	fileToRead = sc1.nextInt();
                    }
                    currentFileIndex = fileToRead - 1;
                    currentArrayOfBooksToRead = readObjectFromBinaryFile(D + booksCategoryFilesBin[currentFileIndex]);
                    currentIndex = 0;
                    break;
				case "x":
					exit = true;
					System.out.println("You have chosen to leave the program.");
					break;
				default:
					System.out.println("Invalid choice. Try again.");
					break;
                    
			
			}
		}
		
	}
	/**
	 * This method is a support method to "do_part3()". It will return the proper array that contains the books of each category.Everytime the user will chose a file, the file name will be given
	 * to this method and it will return the array containing the info.
	 * @param file to read
	 * @return the array containg all the books to be read for each binary file.
	 */
	private Book[] readObjectFromBinaryFile(String file) {
		
		ObjectInputStream is = null;
		
		try {
			
			is = new ObjectInputStream(new FileInputStream(file));//might need to add the directory
			Book [] bookA = (Book[]) is.readObject();
			is.close();
			return bookA;
			
		}catch(Exception e) {
			System.out.println("Error when we tried to read the object from file " + file);
			return null;
		}
	
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
