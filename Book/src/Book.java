//Jeffrey Rodriguez 110733867
//CSE214.02 - R14 Homework #1

/**
 * This class represents a book with a title, author, condition, and borrower
 *
 * @author Jeffrey Rodriguez
 */

public class Book {

    private String title;
    private String author;
    private String borrower;
    private int condition;

    /**
     * This constructor creates a default book
     */

    public Book() {
        this.title = "";
        this.author = "";
        this.borrower = "";
        this.condition = 0;
    }

    /**
     * This constructor creates a new book with no borrower
     *
     * @param title     The title of the book
     * @param author    The author of the book
     * @param condition The condition of the book
     */
    public Book(String title, String author, int condition) {
        this.title = title;
        this.author = author;
        setCondition(condition);
        this.borrower = "";
    }

    /**
     * This constructor creates a new book with a borrower
     *
     * @param title     The title of the book
     * @param author    The author of the book
     * @param borrower  The borrower of the book
     * @param condition The condition of the book
     */
    public Book(String title, String author, String borrower, int condition) {
        this.title = title;
        this.author = author;
        this.borrower = borrower;
        setCondition(condition);
    }

    /**
     * This method sets the title of the book
     *
     * @param title The title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method obtains the title of the book
     *
     * @return The title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * This sets the author of the book
     *
     * @param author The author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * This method obtains the author of the book
     *
     * @return The author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method sets the borrower of the book
     *
     * @param borrower The borrower of the book
     */
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    /**
     * This method obtains the borrower of the book
     *
     * @return The borrower of the book
     */
    public String getBorrower() {
        return borrower;
    }

    /**
     * This method sets the condition of the book
     *
     * @param condition
     */
    public void setCondition(int condition) {
        if (condition > 5) //Automatically set large number equal to 5
            this.condition = 5;
        else if (condition < 1) //Automatically set low number equal to 1
            this.condition = 1;
        else
            this.condition = condition; //If an appropriate integer is used, the setter works as normal
    }

    /**
     * This method obtains the condition of the book
     *
     * @return The condition of the book
     */
    public int getCondition() {
        return condition;
    }

    /**
     * This method tests to see if a book and object are equivalent
     *
     * @param obj An object to be compared with a book object
     * @return True if the book and object are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book book = (Book) obj;

            if (book.title.equals(this.title) && book.author.equals(this.author)
                    && book.condition == this.condition) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method clones a book object
     *
     * @return A clone if the book with no borrower
     */
    public Book clone() {
        Book book = new Book(this.title, this.author, this.condition);
        return book;
    }

    /**
     * This method returns a string representation of this object
     *
     * @return A string representation of the object
     */
    public String toString() {
        if (this.borrower.equals("")){
            return "Title: " + this.title + "\n" +
                    "Author: " + this.author + "\n" +
                    "This book is available.\n" +
                    "Condition:  " + this.condition;
        }
        else
            return "Title: " + this.title + "\n" +
                "Author: " + this.author + "\n" +
                "Borrower: " + this.borrower + "\n" +
                "Condition:  " + this.condition;
    }
}
