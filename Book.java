import java.io.Serializable;

//-----------------------------------------------------
//Assignment 3
//Written by: Miroslav Miskovski 40249269
//-----------------------------------------------------
/**
 * This Class will take care of each lines read in the fileReader and will initialize the data to a book and its attributes. 
 * If anything is wrong with a book an error will be thrown.
 * Each book must contain a name, an author, a price, an ISBN, a genre and a year. 
 * Each book can be sent to a binary file.
 * @author miro
 *
 */
public class Book implements Serializable {
	protected String bookName;
	protected String bookAuthor;
	protected double bookPrice;
	protected String bookISBN;
	protected String bookGenre;
	protected int bookYear;

/**
 * default constructor
 */
public Book() {
	
}

/**
 * This constructor will take a line of info( a book) will sort it and will throw an Error if it contains not wanted
 * @param x string(the line files each storing the info of a book that will be sorted with a support method looking for books that syntaxicly correct
 * @throws TooManyFieldsException
 * @throws TooFewFieldsException
 * @throws MissingFieldException
 */
public Book(String x) throws TooManyFieldsException, TooFewFieldsException, MissingFieldException{
	bookSorting(x);
}

/**
 * 
 * @param x string(the line files each storing the info of a book that will be sorted with a support method looking for books that symanticly correct
 * @param y used to override the constructor taking a string
 * @throws BadISBN10Exception
 * @throws BadISBN13Exception
 * @throws BadYearException
 * @throws BadPriceException
 */
public Book(String x, boolean y) throws BadISBN10Exception, BadISBN13Exception, BadYearException, BadPriceException{
	symBookSorting(x);
}
/**
 * constructor taking each attributes, not used in this current implementation
 * @param bookName
 * @param bookAuthor
 * @param bookPrice
 * @param bookISBN
 * @param bookGenre
 * @param bookYear
 */
public Book(String bookName, String bookAuthor, double bookPrice, String bookISBN, String bookGenre, int bookYear) {
	this.bookName = bookName;
	this.bookAuthor = bookAuthor;
	this.bookPrice = bookPrice;
	this.bookISBN = bookISBN;
	this.bookGenre = bookGenre;
	this.bookYear = bookYear;
}

/**
 * Support method to the first constructor that will analyze each line of info sent to it and either assign the info to the attributes or throw an error
 * It will sort each line to things that are important is that it will check if the line starts with a quote or not and from there it will do the sorting differently for both scenarios
 * @param x line of info(book info)
 * @throws TooManyFieldsException
 * @throws TooFewFieldsException
 * @throws MissingFieldException
 */
private void bookSorting(String x) throws TooManyFieldsException, TooFewFieldsException, MissingFieldException{
	int firstIndexOfQuote = x.indexOf('"');

    if (firstIndexOfQuote == 0) {
        int lastIndexOfQuote = x.lastIndexOf('"');
        this.bookName = x.substring(firstIndexOfQuote + 1, lastIndexOfQuote);
        x = x.substring(lastIndexOfQuote + 2);//THIS MIGHT CREATE A BUG REVIEW LATER
        String[] bookLine = x.split(",");
        if(bookLine.length > 5) {
            throw new TooManyFieldsException("Error: Too many fields in the input string.");
        }
        if(bookLine.length < 5) {
            throw new TooFewFieldsException("Error: Too few fields in the input string.");
        }
        
        int numFields = bookLine.length;
        for (int i = 0; i < numFields; i++) {
            String element = bookLine[i].trim();
            if (element.isEmpty()) {
                switch(i) {
                    case 0:
                        throw new MissingFieldException("Error: missing authors");
                    case 1:
                        throw new MissingFieldException("Error: missing price");
                    case 2:
                        throw new MissingFieldException("Error: missing ISBN");
                    case 3:
                        throw new MissingFieldException("Error: missing genre");
                    case 4:
                        throw new MissingFieldException("Error: missing year");
                }
            } 
            else {
                switch(i) {
                    case 0:
                        this.bookAuthor = element;
                        break;
                    case 1:
                        this.bookPrice = Double.parseDouble(element);
                        break;
                    case 2:
                        this.bookISBN = element;
                        break;
                    case 3:
                        this.bookGenre = element;
                        break;
                    case 4:
                        this.bookYear = Integer.parseInt(element);
                        break;
                }
            }
        }
    } 
    else {
        String[] bookLine = x.split(",");
        if (bookLine.length < 6) {
            throw new TooFewFieldsException("Error: Too few fields in the input string.");
        }
        for (int i = 0; i < bookLine.length; i++) {
            String element = bookLine[i].trim();
            if (element.isEmpty()) {
                switch(i) {
                    case 0:
                        throw new MissingFieldException("Error: missing title");
                    case 1:
                        throw new MissingFieldException("Error: missing authors");
                    case 2:
                        throw new MissingFieldException("Error: missing price");
                    case 3:
                        throw new MissingFieldException("Error: missing ISBN");
                    case 4:
                        throw new MissingFieldException("Error: missing genre");
                    case 5:
                        throw new MissingFieldException("Error: missing year");
                }
            } 
            else {
                switch(i) {
                    case 0:
                        this.bookName = element;
                        break;
                    case 1:
                        this.bookAuthor = element;
                        break;
                    case 2:
                    	this.bookPrice = Double.parseDouble(element);
                        break;
                          
                    case 3:
                        this.bookISBN = element;
                        break;
                           
                    case 4:
                        this.bookGenre = element;
                        break;
                            
                    case 5:
                        this.bookYear = Integer.parseInt(element);
                        break;
                            
                    default:
                    throw new TooManyFieldsException("Error: Too many fields in the input string.");
            
               }
             }
           }
         }
}

/**
 * Support methos to the second constructor( taking a string and a boolean) it will sort each line looking for books that are correct symanticly if not an error will be thrown
 * @param x
 * @throws BadISBN10Exception
 * @throws BadISBN13Exception
 * @throws BadYearException
 * @throws BadPriceException
 */
private void symBookSorting(String x) throws BadISBN10Exception, BadISBN13Exception, BadYearException, BadPriceException{
	int firstIndexOfQuote = x.indexOf('"');

    if (firstIndexOfQuote == 0) {
        int lastIndexOfQuote = x.lastIndexOf('"');
        this.bookName = x.substring(firstIndexOfQuote + 1, lastIndexOfQuote);
        x = x.substring(lastIndexOfQuote + 2);//THIS MIGHT CREATE A BUG REVIEW LATER
        String[] bookLine = x.split(",");
        int numFields = bookLine.length;
        for (int i = 0; i < numFields; i++) {
           
        	String element = bookLine[i].trim();
           
            switch(i) {
            case 0:
                this.bookAuthor = element;
                break;
            case 1:
                this.bookPrice = Double.parseDouble(element);
                break;
            case 2:
                this.bookISBN = element;
                break;
            case 3:
                this.bookGenre = element;
                break;
            case 4:
                this.bookYear = Integer.parseInt(element);
                break;
            }
        }
    
    }
    else {        
    	String[] bookLine = x.split(",");
    	 for (int i = 0; i < bookLine.length; i++) {
             String element = bookLine[i].trim();
            
           switch(i) {
             case 0:
                 this.bookName = element;
                 break;
             case 1:
                 this.bookAuthor = element;
                 break;
             case 2:
             	this.bookPrice = Double.parseDouble(element);
                 break;
                   
             case 3:
                 this.bookISBN = element;
                 break;
                    
             case 4:
                 this.bookGenre = element;
                 break;
                     
             case 5:
                 this.bookYear = Integer.parseInt(element);
                 break;
                     
           	}
    	}
    }
    
    //creation of all if statements that will check if there contains an symantic error
    
    //price check
    if(this.bookPrice < 0) {
    	throw new BadPriceException();
    }
   
    //year check
    if(this.bookYear < 1995 || this.bookYear > 2010) {
    	throw new BadYearException();
    }
    
    //checking for ISBN 10
    if(this.bookISBN.length() == 10) {
    	int [] numbers = new int[10];
    	long sum =0;
    	for(int i = 0; i < 10; i++) {
    		
    		char c1 = this.bookISBN.charAt(i);
    		
    		if(c1 == 'X') {
    			throw new BadISBN10Exception();
    		}
    		
    		int numb = Character.digit(c1, 10);
    		
            if (numb == -1) {
                throw new BadISBN10Exception();
            }
            
            numbers[i] = numb;
    	}
    	
    	for(int i = 0; i < 10; i++) {
    		sum += (numbers[i] * (10 - i));
    	}
    	
    	if(sum % 11 != 0) {
    		throw new BadISBN10Exception();
    	}
    	
    }
    
    //ISBN13 CHECK
    if(this.bookISBN.length() == 13) {
    	int [] numbers = new int[13];
    	long sum =0;
    	for(int i = 0; i < 13; i++) {
    		
    		char c1 = this.bookISBN.charAt(i);
    		
    		if(c1 == 'X') {
    			throw new BadISBN13Exception();
    		}
    		
    		int numb = Character.digit(c1, 10);
    		
            if (numb == -1) {
                throw new BadISBN13Exception();
            }
            
            numbers[i] = numb;
    	}
    	
    	for(int i = 0; i < 13; i++) {
    		
    		if(i % 2 == 1) {
    			sum += (numbers[i] * (3));
    		}
    		if(i % 2 == 0) {
    		sum += numbers[i];
    		}
    		
    	}
    	
    	if(sum % 10 != 0) {
    		throw new BadISBN13Exception();
    	}
    	
    }
    

    
   
    


}

public String getBookName() {
	return bookName;
}

public void setBookName(String bookName) {
	this.bookName = bookName;
}

public String getBookAuthor() {
	return bookAuthor;
}

public void setBookAuthor(String bookAuthor) {
	this.bookAuthor = bookAuthor;
}

public double getBookPrice() {
	return bookPrice;
}

public void setBookPrice(double bookPrice) {
	this.bookPrice = bookPrice;
}

public String getBookISBN() {
	return bookISBN;
}

public void setBookISBN(String bookISBN) {
	this.bookISBN = bookISBN;
}

public String getBookGenre() {
	return bookGenre;
}

public void setBookGenre(String bookGenre) {
	this.bookGenre = bookGenre;
}

public int getBookYear() {
	return bookYear;
}

public void setBookYear(int bookYear) {
	this.bookYear = bookYear;
}


/**
 * equals method, will comapre two books to see if they are the same if needed for later implemantation
 */
public boolean equals(Object x) {
	if(x == null || this.getClass() != x.getClass()) {
		return false;
	}
	else {
		Book y = (Book) x;
		
		return(this.bookName == y.bookName && this.bookAuthor == y.bookAuthor && this.bookPrice == y.bookPrice && this.bookISBN == y.bookISBN && this.bookGenre == y.bookGenre && this.bookYear == y.bookYear);
	}
	
}


/**
 * will display info of each book
 */
public String toString() {
	return "\"" + this.bookName + "\"," + this.bookAuthor + "," + this.bookPrice + "," + this.bookISBN + "," + this.bookGenre + "," + this.bookYear;
	
}
	
}
