package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.IntermediateManager;
import ba.unsa.etf.rpr.domain.IntermediateTable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MyOrdersController {
    public Button close;

    @FXML
    public TableView<Ilma> listTable;
    @FXML
    public TableColumn<Ilma, String> titleColumn = new TableColumn<>();
    @FXML
    public TableColumn<Ilma, String> artistColumn = new TableColumn<>();
    @FXML
    public TableColumn<Ilma, String> priceColumn = new TableColumn<>();

    @FXML
    private final IntermediateManager intermediateManager = new IntermediateManager();

    public void initialize(){
        Model model = Model.getInstance();

        titleColumn.setCellValueFactory(new PropertyValueFactory<Ilma, String>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Ilma, String>("artist"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Ilma,String>("price"));

        refreshRecords();
    }

    void refreshRecords() {
        try {
            Model model = Model.getInstance();
            List<IntermediateTable> lista = intermediateManager.getByUser(model.getUser().getId());
            List<Ilma> ilmaLista = new ArrayList<>();
            for(IntermediateTable t : lista){
                ilmaLista.add(new Ilma(t.getRecord().getTitle(), t.getRecord().getArtist(),t.getRecord().getPrice()));
            }
            listTable.setItems(FXCollections.observableList(ilmaLista));
            listTable.refresh();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }



    public void actionClose(ActionEvent actionEvent)
    {
        Stage stage =(Stage)close.getScene().getWindow();
        stage.close();
    }

    public class Ilma{
        private String title;
        private String artist;
        private Double price;

        public Ilma() {
        }

        public Ilma(String title, String artist, Double price) {
            this.title = title;
            this.artist = artist;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

}