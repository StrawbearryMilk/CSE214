//Jeffrey Rodriguez 110733867
//CSE214.R14 HW2

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class implements the linked list based classes to represent a delivery driver
 * @author Jeffrey Rodriguez
 */
public class DeliveryDriver extends Application {
    Button addDel;
    Button remDel;
    Button cutDel;
    Button pasteDel;
    Button cursToHead;
    Button cursToTail;
    Button cursForward;
    Button cursBackward;
    Button switchDel;
    Button printDel;
    Button quit;

    TabPane tabs;
    Tab list1;
    Tab list2;

    TreeView tree1;
    TreeView tree2;

    TreeItem root1;
    TreeItem root2;

    private static DeliveryList listA;
    private static DeliveryList listB;
    private static Delivery copied; //used for cut and paste options

    public static void main(String[] args) {
        launch(args);
    }

    //Starts the GUI app
    @Override
    public void start(Stage primaryStage) throws Exception {

        listA = new DeliveryList();
        listB = new DeliveryList();

        VBox root = new VBox();
        root.setSpacing(4);
        HBox buttons = new HBox();
        buttons.setSpacing(4);
        HBox buttons2 = new HBox();
        buttons2.setSpacing(4);
        HBox buttons3 = new HBox();
        buttons3.setSpacing(4);

        addDel = new Button("Add a Delivery After Cursor");
        remDel = new Button("Remove Delivery at Cursor");
        cutDel = new Button("Cut Delivery at Cursor");
        pasteDel = new Button("Paste After Cursor");
        cursToHead = new Button("Cursor to Head");
        cursToTail = new Button("Cursor to Tail");
        cursForward = new Button("Cursor Forward");
        cursBackward = new Button("Cursor Backward");
        quit = new Button("Quit");

        buttons.getChildren().addAll(addDel, remDel); //first row buttons
        buttons2.getChildren().addAll(cutDel, pasteDel,cursToHead); //second row buttons
        buttons3.getChildren().addAll(cursToTail, cursForward, cursBackward, quit);

        tabs = new TabPane();

        list1 = new Tab("J's Delivery Service");
        list1.setClosable(false);
        tree1 = new TreeView();
        root1 = new TreeItem("List");
        tree1.setRoot(root1);
        list1.setContent(tree1);

        list2 = new Tab("J's Not-So-Hidden Discounted Service");
        list2.setClosable(false);
        tree2 = new TreeView();
        root2 = new TreeItem("List");
        tree2.setRoot(root2);
        list2.setContent(tree2);

        tabs.getTabs().addAll(list1, list2);

        root.getChildren().add(buttons);
        root.getChildren().add(buttons2);
        root.getChildren().add(buttons3);
        root.getChildren().add(tabs);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        initializeButtons();

    }

    //  updates the TreeView of the list after each order is fulfilled
    private void refreshList(TreeItem root, DeliveryList list) {
        root.getChildren().clear();
        DeliveryListNode c = list.getHead();
        if (list.numDeliveries() == 1)
            root.getChildren().add(new TreeItem("->\n" + c.getData().toString()));
        else {
            while (c != null) {//If the cursor is pointing at a node, attach "->"
                root.getChildren().add(new TreeItem((c.getData() == list.getCursor() ? "->\n" + c.getData().toString() : //If c is the head, don't attach anything
                        (c == list.getHead()) ? c.getData().toString() : "~\n" + c.getData().toString()))); //Otherwise attach  "~"
                c = c.getNext();
            }
        }
    }

    public void initializeButtons() {
        copied = null;
        addDel.setOnAction(e -> {

            HBox hBox = new HBox();
            Scene sc = new Scene(hBox);

            Button OKbutton = new Button("OK");

            TextField source = new TextField();
            source.setText("From:");

            TextField dest = new TextField();
            dest.setText("Destination:");

            TextField instruction = new TextField();
            instruction.setText("Special instructions:");

            hBox.getChildren().addAll(source, dest, instruction, OKbutton);

            Stage st = new Stage();
            st.setScene(sc);
            // position and prompt
            OKbutton.setOnAction(ee -> {
                Delivery del = new Delivery(source.getText(), dest.getText(), instruction.getText());
                switch (tabs.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        if (listA.getCursor() == null || listA.getCursor() == listA.getHead().getData())
                            listA.insertAfterCursor(del);
                        else
                            listA.appendToTail(del);
                        refreshList(root1, listA);
                        break;
                    case 1:
                        if (listB.getCursor() == null || listB.getCursor() == listB.getHead().getData())
                            listB.insertAfterCursor(del);
                        else
                            listB.appendToTail(del);
                        refreshList(root2, listB);
                        break;
                }
                st.close();
                ee.consume();
            });

            st.showAndWait();
            e.consume();
        });

        remDel.setOnAction(e -> {
            switch (tabs.getSelectionModel().getSelectedIndex()) {
                case 0:
                    try {
                        listA.removeCursor();
                    } catch (DeliveryList.EndOfListException ex) {
                        System.out.println("Cursor is null!");
                    }
                    refreshList(root1, listA);
                    break;
                case 1:
                    try {
                        listB.removeCursor();
                    } catch (DeliveryList.EndOfListException ex) {
                        System.out.println("Cursor is null!");
                    }
                    refreshList(root2, listB);
                    break;
            }
        });

        cutDel.setOnAction(e -> {
            switch (tabs.getSelectionModel().getSelectedIndex()) {
                case 0:
                    try {
                        copied = listA.getCursor();
                        listA.removeCursor();
                    } catch (DeliveryList.EndOfListException ex) {
                        System.out.println("Cursor is null!");
                    }
                    refreshList(root1, listA);
                    break;
                case 1:
                    try {
                        copied = listB.getCursor();
                        listB.removeCursor();
                    } catch (DeliveryList.EndOfListException ex) {
                        System.out.println("Cursor is null!");
                    }
                    refreshList(root2, listB);
                    break;
            }
        });

        pasteDel.setOnAction(e -> {
            switch (tabs.getSelectionModel().getSelectedIndex()) {
                case 0:
                    try{
                        listA.insertAfterCursor(new Delivery(copied.getSource(), copied.getDest(), copied.getInstruction()));
                    }
                    catch (IllegalArgumentException ex){
                        System.out.println("Nothing to paste!");
                    }
                    refreshList(root1, listA);
                    break;
                case 1:
                    try{
                        listB.insertAfterCursor(copied);
                    }
                    catch (IllegalArgumentException ex){
                        System.out.println("Nothing to paste!");
                    }
                    refreshList(root2, listB);
                    break;
            }
        });

        cursToHead.setOnAction(e -> {
            switch (tabs.getSelectionModel().getSelectedIndex()){
                case 0:
                    listA.resetCursorToHead();
                    refreshList(root1, listA);
                    break;
                case 1:
                    listB.resetCursorToHead();
                    refreshList(root2, listB);
                    break;
            }
        });

        cursToTail.setOnAction(e -> {
            switch (tabs.getSelectionModel().getSelectedIndex()){
                case 0:
                    if (listA.getTail() != null){
                        listA.setCursor(listA.getTail());
                    }
                    else
                        System.out.println("No tail!");
                    refreshList(root1, listA);
                    break;
                case 1:
                    if (listB.getTail() != null){
                        listB.setCursor(listB.getTail());
                    }
                    else
                        System.out.println("No tail!");
                    refreshList(root2, listB);
                    break;
            }
        });

        cursForward.setOnAction(e -> {
            switch (tabs.getSelectionModel().getSelectedIndex()){
                case 0:
                    try {
                        listA.cursorForward();
                    }
                    catch (DeliveryList.EndOfListException ex){
                        System.out.println("Cursor is at the tail. Cannot move further forward.");
                    }
                    refreshList(root1, listA);
                    break;
                case 1:
                    try {
                        listB.cursorForward();
                    }
                    catch (DeliveryList.EndOfListException ex){
                        System.out.println("Cursor is at the tail. Cannot move further forward.");
                    }
                    refreshList(root2, listB);
                    break;
            }
        });

        cursBackward.setOnAction(e -> {
            switch (tabs.getSelectionModel().getSelectedIndex()){
                case 0:
                    try {
                        listA.cursorBackward();
                    }
                    catch (DeliveryList.EndOfListException ex){
                        System.out.println("Cursor is at the head. Cannot move further back.");
                    }
                    refreshList(root1, listA);
                    break;
                case 1:
                    try {
                        listB.cursorBackward();
                    }
                    catch (DeliveryList.EndOfListException ex){
                        System.out.println("Cursor is at the head. Cannot move further back.");
                    }
                    refreshList(root2, listB);
                    break;
            }
        });

        quit.setOnAction(e -> {
            System.out.println("Goodbye!");
            Platform.exit();
        });
    }
}