//Jeffrey Rodriguez 110733867
//CSE214.02 - R14 Homework #1

/**
 * This class represents a book with a title, author, condition, and borrower
 *
 * @author Jeffrey Rodriguez
 */

/**
 * This class represents a bookshelf containing an amount of books determined by count,
 * with a restriction of size CAPACITY
 */
public class Bookshelf {

    private Book[] books;
    private int count;
    final int CAPACITY = 20;

    /**
     * This constructor creates a default bookshelf
     */
    public Bookshelf(){
        this.books = new Book[CAPACITY];
        this.count = 0;
    }

    /**
     * This contstructor creates a new bookshelf
     * @param books
     * The books on the shelf
     * @param count
     * The number of books on the shelf
     */
    public Bookshelf(Book[] books, int count) {
        this.books = books;
        this.count = count;
    }

    /**
     * This method returns the number of books on the shelf
     * @return
     * The number of books on the shelf
     */
    public int numBooks() {
        return this.count;
    }

    /**
     * This method obtains the book at an index on the shelf
     * @param index
     * @return
     * The book at a given index on the shelf
     */

    public Book getBook(int index) {
        if (index > CAPACITY - 1 || index < 0) { //See that index falls within capacity size
            throw new ArrayIndexOutOfBoundsException("Pick an index between 1 and the bookshelf's capacity");
        }
        return this.books[index];
    }

    /**
     * This exception is used if there is no book on the bookshelf
     */
    public class EmptyShelfException extends Exception {
    }

    /**
     * This method removes a book from the bookshelf
     * @param index
     * The index at which to remove this book
     * @return
     * The removed book
     * @throws EmptyShelfException
     * Thrown if no book is on the bookshelf
     */
    public Book removeBook(int index) throws EmptyShelfException {
        if (numBooks() == 0) { //See if there are books on the shelf
            throw new EmptyShelfException();
        }
        if (index > CAPACITY - 1 || index < 0){ //See if index is within capacity size
            throw new ArrayIndexOutOfBoundsException("...");
        }
        Book temp = this.books[index];
        for (int i = index; i < CAPACITY - 1; i++) {
            this.books[i] = this.books[i + 1];
        }
        this.count--;
        this.books[this.count] = null; //We repositioned all of the books,
                                       // but without this, there would be two books in the last position
        return temp;
    }

    /**
     * This exception is thrown if the bookshelf is at its capacity
     */
    public class FullShelfException extends Exception{
    }

    /**
     * This method adds a book to the bookshelf
     * @param index
     * The index at which to place the book
     * @param book
     * The book to add to this shelf
     * @throws FullShelfException
     * This exception is thrown if the bookshelf is at capacity
     */
    public void addBook(int index, Book book) throws FullShelfException{
        if (index > CAPACITY - 1 || index < 0){ //See if index is within capacity size
            throw new ArrayIndexOutOfBoundsException("...");
        }
        else if (index > this.count){
            throw new IllegalArgumentException("This creates a gap in the shelf. Select a lower index.");
        }
        else if (this.count == CAPACITY){
            throw new FullShelfException();
        }
        else{
            for (int i = this.count; i > index; i--) {
                this.books[i] = this.books[i - 1];
            }
            this.books[index] = book;
        }
        this.count++;
    }

    /**
     * The method swaps to books on the bookshelf
     * @param index1
     * The index of one book you wish to swap
     * @param index2
     * The index of the other book you wish to swap
     */
    public void swapBooks(int index1, int index2){
        if ((index1 > this.count || index2 > this.count) || (index1 < 0 || index2 < 0)){
            throw new ArrayIndexOutOfBoundsException();
        }
        Book temp = this.books[index2];
        this.books[index2] = this.books[index1];
        this.books[index1] = temp;
    }

    /**
     * This method tests for equality between two bookshelves
     * @param obj
     * The object we wish to compare to with a bookshelf
     * @return
     * True if the bookshelf and object are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Bookshelf) {
            Bookshelf shelf = (Bookshelf) obj;
            if (shelf.count == this.count) {
                for (int i = 0; i < this.count; i++) {
                    if (!shelf.books[i].equals(this.books[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * This method clones the bookshelf
     * @return
     * A deep cloned copy of the bookshelf
     */
    public Bookshelf clone(){
        Book[] newBooks = new Book[CAPACITY];
        for (int i = 0; i < this.count; i++){
            newBooks[i] = this.books[i].clone();
        }
        Bookshelf shelf = new Bookshelf(newBooks, this.count);
        return shelf;
    }

    /**
     * This method returns a string representation of the bookshelf
     * @return
     * A string representation of the bookshelf
     */
    public String toString(){
        String s1 = "";
        for (Book book  : this.books){
            s1 += "Title: " + book.getTitle() + "\nAuthor: " +book.getAuthor() + "\n";
        }
        return "There are " + this.count + " books on the shelf." + "\nThe books are:\n" + s1;
    }
}