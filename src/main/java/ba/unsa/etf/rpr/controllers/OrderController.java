package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.IntermediateManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.IntermediateTable;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.RecordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class OrderController {
    public Button cancel;
    public Label recordTitle;
    public Label recordArtist;
    public Label recordPrice;
    //  public TextField addressName;
    OrderManager orderManager = new OrderManager();
    UserManager userManager = new UserManager();
    private String email;
    public static Order order = new Order();



    @FXML
    public void initialize()
    {
        recordTitle.setText(RecordsController.record.getTitle());
        recordArtist.setText(RecordsController.record.getArtist());
        recordPrice.setText(String.valueOf(RecordsController.record.getPrice()) + "$");
        // addressName.setText(OrderController.order.getAddress());

    }


    public void insertData() throws RecordException {
        Model model = Model.getInstance();
        Order order = new Order();
        order.setUser(model.getUser());
        order.setPayment_amount(model.getRecord().getPrice());
        //order.setAddress(addressName.getText());
        orderManager.add(order);

        IntermediateTable intermediateTable = new IntermediateTable();
        List<Order> lista = orderManager.getAll();
        if(!lista.isEmpty()) {
            for (Order o : lista) {
                if ((o.getPayment_amount() - order.getPayment_amount()) < 0.00001 && o.getUser().equals(order.getUser())) {
                    model.setOrder(o);

                    intermediateTable.setOrder(model.getOrder());
                }
            }
            intermediateTable.setRecord(model.getRecord());
            IntermediateManager intermediateManager = new IntermediateManager();
            intermediateManager.add(intermediateTable);
        }else{
            throw new RecordException("No orders found. Cannot proceed with data insertion.");
        }
    }


    public void confirm(ActionEvent actionEvent) throws  RecordException {

        try {
            insertData();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/confirmorder.fxml"));
            ConfirmOrderController confirmOrderController = new ConfirmOrderController();
            loader.setController(confirmOrderController);
            Parent root = loader.load();
            stage.setTitle("Confirm order");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            //stage.setOnHiding();
            stage.show();
        } catch (IOException | RecordException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    public void cancelAction(ActionEvent actionEvent) {
        Stage stage =(Stage)cancel.getScene().getWindow();
        stage.close();
    }
}