package di05_tarea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    public ListView<String>  lst_Art;

    @FXML
    public Button btn_art;

    @FXML
    public Button btn_client;

    @FXML
    public Button btn_salir;
    public Label lbl_coName;

    @FXML
    private void initialize() {
       loadArts();

    }
        //Buscar artistas en BBDD y rellenar la lista
    private void loadArts()
    {
        Connection conn = HelloApplication.getConnection();
        if (conn == null)
        {
            return;
        }
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM artists");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lst_Art.getItems().add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void inf_Arts(ActionEvent actionEvent) {
        String selectArt = lst_Art.getSelectionModel().getSelectedItem();
        if (selectArt != null) {
            System.out.println("Informe Artitas...: " + selectArt);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ArtistName", selectArt);

            InputStream reportStream = HelloApplication.class.getResourceAsStream("/Artistas.jrxml");

            if (reportStream != null) {
                HelloApplication.generateReport(reportStream, parameters);
            } else {
                System.err.println("Artistas.jrxml not found");
            }
        }


    }
    @FXML
    public void inf_Cliente(ActionEvent actionEvent) {
        System.out.println("Informe Clientes ...");
        Map<String, Object> parameters = new HashMap<>();
        InputStream reportStream = HelloApplication.class.getResourceAsStream("/Clientes.jrxml");
        if (reportStream != null) {
            HelloApplication.generateReport(reportStream, parameters);
        } else {
            System.err.println("/Clientes.jrxml not found/");
        }
    }

    public void salir(ActionEvent actionEvent) {

        Stage stage = (Stage) btn_salir.getScene().getWindow();
        stage.close();
    }




}