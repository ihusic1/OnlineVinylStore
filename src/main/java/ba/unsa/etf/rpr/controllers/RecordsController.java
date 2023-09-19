package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GenreManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Genre;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;




public class RecordsController {

    @FXML
    public TableView<Record> recordTable;
    public TextField searchText;
    private final RecordManager recordManager = new RecordManager();
    private final GenreManager genreManager = new GenreManager();

    public static Record record  = new Record();
    public static User user = new User();
    public Button Undo;


    @FXML
    public TableColumn<Record, String> titleColumn  = new TableColumn<>();
    @FXML
    public TableColumn<Record, String> artistColumn  = new TableColumn<Record, String>();
    @FXML
    public TableColumn<Record, Double>  priceColumn  = new TableColumn<>();
    @FXML
    public TableColumn<Record, Integer> orderColumn;
    private int loggedUserId;

    public RecordsController(){
    }

    public RecordsController(int loggedUserId) {
        this.loggedUserId = loggedUserId;
        //System.out.println("User" + " " + loggedUserId);
    }


    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Record, String>("artist"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Record, Double>("price"));

        class OrderCell extends TableCell<Record, Integer> {
            private Button btn = new Button("Order");

            public OrderCell() {
                btn.setOnAction(event -> {
                    record = getTableView().getItems().get(getIndex());
                    Model model = Model.getInstance();
                    model.setMedicine(record);
                    System.out.println(record.getTitle());

                    // Add  logic here to handle the button click event
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/order.fxml"));
                        OrderController orderController = new OrderController();
                        loader.setController(orderController);
                        Parent root = loader.load();
                        stage.setTitle("Order");
                        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                        stage.setResizable(false);
                        //stage.setOnHiding();
                        stage.show();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
            }
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        }
        orderColumn.setCellFactory(column -> new OrderCell());


        refreshRecord();
    }

    void refreshRecord() {
        try {
            recordTable.setItems(FXCollections.observableList(recordManager.getAll()));
            recordTable.refresh();
        } catch (RecordException e) {
            e.printStackTrace();
        }
    }

    // Define the searchByText function
    public void searchByText(ActionEvent actionEvent)
    {
        try {
            List<Record> lista = recordManager.searchRecord(searchText.getText());
            if(lista.size() > 0){
                System.out.println(lista.get(0).getTitle() + " " + lista.get(0).getArtist());
            }
            recordTable.setItems(FXCollections.observableList(lista));
        } catch (RecordException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage()).show();
        }
    }

    public void openMyOrders(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/myorders.fxml"));
            MyOrdersController myOrdersController = new MyOrdersController();
            loader.setController(myOrdersController);
            Parent root = loader.load();
            stage.setTitle("My Orders");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            //stage.setOnHiding();
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    public void radioButtonClick(ActionEvent actionEvent) {

        RadioButton radioButton = (RadioButton) actionEvent.getSource();

        if(radioButton.getText().equals("All")){
            refreshRecord();
        }else{
            try {
                List<Genre> listaZanrova = genreManager.searchGenreId(radioButton.getText());
                recordTable.setItems(FXCollections.observableList( recordManager.searchByGenre(listaZanrova.get(0))));
                recordTable.refresh();
            } catch (RecordException e) {
                e.printStackTrace();
            }
        }
    }

    public void openHomeWindow(ActionEvent actionEvent) {
        Stage stage =(Stage)Undo.getScene().getWindow();
        stage.close();
    }
}