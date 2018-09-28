//Jeffrey Rodriguez 11073867
//CSE214.02 - R14 Homework #1

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.FocusModel;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * This class represents a rental service via GUI which makes use of the Book and Bookshelf classes
 * @author Jeffrey Rodriguez
 */
public class RipoffRental extends Application {

    Button addBook;
    Button swapBooks;
    Button loanBook;
    Button removeBook;
    Button duplicateBook;
    Button overrideWithClone;
    Button checkEquality;

    TabPane tabs;
    Tab shelf1;
    Tab shelf2;
    Tab shelf3;

    TreeView tree1;
    TreeView tree2;
    TreeView tree3;

    TreeItem root1;
    TreeItem root2;
    TreeItem root3;

    private static Bookshelf shelfA;
    private static Bookshelf shelfB;
    private static Bookshelf shelfC;

    public static void main(String[] args) {
        launch(args);
    }

    //Starts the GUI app
    @Override
    public void start(Stage primaryStage) throws Exception {

        shelfA = new Bookshelf();
        shelfB = new Bookshelf();
        shelfC = new Bookshelf();

        VBox root = new VBox();
        root.setSpacing(5);
        HBox buttons = new HBox();
        buttons.setSpacing(5);
        HBox buttons2 = new HBox();
        buttons2.setSpacing(5);

        addBook = new Button("Add book");
        swapBooks = new Button("Swap books");
        loanBook = new Button("Loan book");
        removeBook = new Button("Remove book");
        duplicateBook = new Button("Duplicate book");
        overrideWithClone = new Button("Override with clone");
        checkEquality = new Button("Check shelves for equality");

        buttons.getChildren().addAll(addBook, swapBooks, loanBook, removeBook, duplicateBook); //first row buttons
        buttons2.getChildren().addAll(overrideWithClone, checkEquality); //second row buttons

        tabs = new TabPane();

        shelf1 = new Tab("Shelf A");
        shelf1.setClosable(false);
        tree1 = new TreeView();
        root1 = new TreeItem("Books");
        tree1.setRoot(root1);
        shelf1.setContent(tree1);

        shelf2 = new Tab("Shelf B");
        shelf2.setClosable(false);
        tree2 = new TreeView();
        root2 = new TreeItem("Books");
        tree2.setRoot(root2);
        shelf2.setContent(tree2);

        shelf3 = new Tab("Shelf C");
        shelf3.setClosable(false);
        tree3 = new TreeView();
        root3 = new TreeItem("Books");
        tree3.setRoot(root3);
        shelf3.setContent(tree3);

        tabs.getTabs().addAll(shelf1, shelf2, shelf3);

        root.getChildren().add(buttons);
        root.getChildren().add(buttons2);
        root.getChildren().add(tabs);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        initializeButtons();

    }

    
    public void initializeButtons() {
        addBook.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField title = new TextField();
            title.setText("Title");
            TextField author = new TextField();
            author.setText("Author");
            TextField condition = new TextField();
            condition.setText("Condition (1-5)");
            TextField index = new TextField();
            index.setText("Index");

            hBox.getChildren().addAll(title, author, condition, index, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);
            // position and prompt
            OKbutton.setOnAction(ee -> {
               Book book = null;
                try {
                    book = new Book(
                            title.getText(),
                            author.getText(),
                            "",
                            Integer.parseInt(condition.getText())
                    );
                } catch (Exception ex) {
                    System.out.println("Invalid input!");
                }

                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        try {
                            shelfA.addBook(Integer.parseInt(index.getText()), book);
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root1, shelfA);
                        break;
                    case 1:
                        try {
                            shelfB.addBook(Integer.parseInt(index.getText()), book);
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root2, shelfB);
                        break;
                    case 2:
                        try {
                            shelfC.addBook(Integer.parseInt(index.getText()), book);
                        } catch (Exception ex) {
                            System.out.println("Invalid input!");
                        }
                        refreshShelf(root3, shelfC);
                        break;
                }
                st.close();
                ee.consume();
            });

            st.showAndWait();
            e.consume();
        });

        swapBooks.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField index1 = new TextField();
            index1.setText("Index of first book");
            TextField index2 = new TextField();
            index2.setText("Index of second book");

            hBox.getChildren().addAll(index1, index2, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);

            OKbutton.setOnAction(ee -> {
                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        try {
                            shelfA.swapBooks(
                                    Integer.parseInt(index1.getText()),
                                    Integer.parseInt(index2.getText())
                            );
                         } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root1, shelfA);
                        break;
                    case 1:
                        try {
                            shelfB.swapBooks(
                                    Integer.parseInt(index1.getText()),
                                    Integer.parseInt(index2.getText())
                            );
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root2, shelfB);
                        break;
                    case 2:
                        try {
                            shelfA.swapBooks(
                                    Integer.parseInt(index1.getText()),
                                    Integer.parseInt(index2.getText())
                            );
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root3, shelfC);
                        break;
                }
                st.close();
                ee.consume();
            });

            st.showAndWait();
            e.consume();
        });

        loanBook.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField borrower = new TextField();
            borrower.setText("Borrower");
            TextField condition = new TextField();
            condition.setText("Condition (1-5)");
            TextField index = new TextField();
            index.setText("Index");
            hBox.getChildren().addAll(borrower, condition, index, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);
            // position and prompt

            OKbutton.setOnAction(ee -> {
                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        try {
                            Book temp = shelfA.getBook(Integer.parseInt(index.getText()));
                            temp.setBorrower(borrower.getText());
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root1, shelfA);
                        break;
                    case 1:
                        try {
                            Book temp = shelfB.getBook(Integer.parseInt(index.getText()));
                            temp.setBorrower(borrower.getText());
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root2, shelfB);
                        break;
                    case 2:
                        try {
                            Book temp = shelfC.getBook(Integer.parseInt(index.getText()));
                            temp.setBorrower(borrower.getText());
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root3, shelfC);
                        break;
                }
                st.close();
                ee.consume();
            });

            st.showAndWait();

        });

        removeBook.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField index = new TextField();
            index.setText("Remove at index");

            hBox.getChildren().addAll(index, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);
            // position and prompt

            OKbutton.setOnAction(ee -> {
                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        try {
                            shelfA.removeBook(Integer.parseInt(index.getText()));
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root1, shelfA);
                        break;
                    case 1:
                        try {
                            shelfB.removeBook(Integer.parseInt(index.getText()));
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root2, shelfB);
                        break;
                    case 2:
                        try {
                            shelfC.removeBook(Integer.parseInt(index.getText()));
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root3, shelfC);
                        break;
                }
                st.close();
                ee.consume();
            });

            st.showAndWait();
        });

        duplicateBook.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField index1 = new TextField();
            index1.setText("Copy at index");
            TextField index2 = new TextField();
            index2.setText("Paste at index");

            hBox.getChildren().addAll(index1, index2, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);
            // position and prompt

            OKbutton.setOnAction(ee -> {
                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        try {
                            Book temp = shelfA.getBook(Integer.parseInt(index1.getText()));
                            shelfA.addBook(Integer.parseInt(index2.getText()), temp);
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root1, shelfA);
                        break;
                    case 1:
                        try {
                            Book temp = shelfB.getBook(Integer.parseInt(index1.getText()));
                            shelfB.addBook(Integer.parseInt(index2.getText()), temp);
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root2, shelfB);
                        break;
                    case 2:
                        try {
                            Book temp = shelfC.getBook(Integer.parseInt(index1.getText()));
                            shelfC.addBook(Integer.parseInt(index2.getText()), temp);
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root3, shelfC);
                        break;
                }
                st.close();
                ee.consume();
            });

            st.showAndWait();
        });

        overrideWithClone.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField destinationShelf = new TextField();
            destinationShelf.setText("Destination shelf: A, B, or C");

            hBox.getChildren().addAll(destinationShelf, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);
            // position and prompt

            OKbutton.setOnAction(ee -> {
                char choice = destinationShelf.getText().charAt(0);
                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        try {
                            if (choice == 'a' || choice == 'A') {
                            } else if (choice == 'b' || choice == 'B') {
                                shelfB = shelfA.clone();
                                refreshShelf(root2, shelfB);
                            } else if (choice == 'c' || choice == 'C') {
                                shelfC = shelfA.clone();
                                refreshShelf(root3, shelfC);
                            }
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        break;
                    case 1:
                        try {
                            if (choice == 'a' || choice == 'A') {
                                shelfA = shelfB.clone();
                                refreshShelf(root1, shelfA);
                            } else if (choice == 'b' || choice == 'B') {
                            } else if (choice == 'c' || choice == 'C') {
                                shelfC = shelfB.clone();
                                refreshShelf(root3, shelfC);
                            }
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        break;
                    case 2:
                        try {
                            if (choice == 'a' || choice == 'A') {
                                shelfA = shelfC.clone();
                                refreshShelf(root1, shelfA);
                            } else if (choice == 'b' || choice == 'B') {
                                shelfB = shelfC.clone();
                                refreshShelf(root2, shelfB);
                            } else if (choice == 'c' || choice == 'C') {
                            }
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        break;
                }
                st.close();
                ee.consume();
            });

            st.showAndWait();
        });

        checkEquality.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField index1 = new TextField();
            index1.setText("A, B or C");
            TextField index2 = new TextField();
            index2.setText("A, B or C");

            hBox.getChildren().addAll(index1, index2, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);
            // position and prompt

            OKbutton.setOnAction(ee -> {
                char choice1 = index1.getText().charAt(0);
                char choice2 = index2.getText().charAt(0);
                boolean result = false;

                switch (choice1) {
                    case 'a':
                    case 'A':
                        try {
                            if (choice2 == 'a' || choice2 == 'A') {
                                result = shelfA.equals(shelfA);
                            } else if (choice2 == 'b' || choice2 == 'B') {
                                result = shelfA.equals(shelfB);
                            } else if (choice2 == 'c' || choice2 == 'C') {
                                result = shelfA.equals(shelfC);
                            }
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        refreshShelf(root1, shelfA);
                        break;
                    case 'b':
                    case 'B':
                        try {
                            if (choice2 == 'a' || choice2 == 'A') {
                                result = shelfB.equals(shelfA);
                            } else if (choice2 == 'b' || choice2 == 'B') {
                                result = shelfB.equals(shelfB);
                            } else if (choice2 == 'c' || choice2 == 'C') {
                                result = shelfB.equals(shelfC);
                            }
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        break;
                    case 2:
                    case 'c':
                    case 'C':
                        try {
                            if (choice2 == 'a' || choice2 == 'A') {
                                result = shelfC.equals(shelfA);
                            } else if (choice2 == 'b' || choice2 == 'B') {
                                result = shelfC.equals(shelfB);
                            } else if (choice2 == 'c' || choice2 == 'C') {
                                result = shelfC.equals(shelfC);
                            }
                        } catch (Exception ex) {
                              System.out.println("Invalid input!");
                        }
                        break;
                }
                st.close();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("The result of comparison");
                alert.setContentText("");
                alert.setHeaderText(String.valueOf(result));

                alert.show();

                ee.consume();

            });

            st.showAndWait();

        });

    }

    
    //  updates the TreeView of the shelf after each transaction
    private void refreshShelf(TreeItem root, Bookshelf shelf) {
        root.getChildren().clear();
        for (int i = 0; i < shelf.numBooks(); i++) {
            root.getChildren().add(new TreeItem(shelf.getBook(i).toString()));
        }
    }
}