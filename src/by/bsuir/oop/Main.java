package by.bsuir.oop;

import by.bsuir.oop.model.CommunicationDevice;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Controller controller = new Controller();

        //Serialization menu controls
        RadioButton binaryRadioButton = new RadioButton("Binary");
        RadioButton xmlRadioButton = new RadioButton("XML");
        RadioButton textRadioButton = new RadioButton("Text");

        ToggleGroup radioGroup = new ToggleGroup();
        radioGroup.getToggles().addAll(binaryRadioButton, xmlRadioButton, textRadioButton);

        Button openButton = new Button("Open");
        openButton.setMinWidth(90);
        openButton.setMaxWidth(90);

        Button saveButton = new Button("Save");
        saveButton.setMinWidth(90);
        saveButton.setMaxWidth(90);

        //Pane for device fields
        ScrollPane fieldsPane = new ScrollPane();
        fieldsPane.setMinWidth(300);
        fieldsPane.setMaxWidth(300);
        fieldsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //List of devices
        ListView<CommunicationDevice> listView = new ListView<>();
        listView.setPrefHeight(Region.USE_COMPUTED_SIZE);
        listView.setMinWidth(300);
        listView.setMaxWidth(300);
        listView.setOnMousePressed(event -> {
            CommunicationDevice selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.showDeviceFields(selected, fieldsPane);
            }
        });

        //List controls
        ComboBox<Class> devicesComboBox = new ComboBox<>();
        devicesComboBox.setMinWidth(200);
        devicesComboBox.setMaxWidth(200);
        devicesComboBox.getItems().addAll(controller.getAvailableClasses());
        devicesComboBox.setConverter(controller.getClassStringConverter());

        devicesComboBox.getSelectionModel().selectFirst();

        Button addButton = new Button("Add");
        addButton.setMinWidth(90);
        addButton.setMaxWidth(90);
        addButton.setOnMousePressed(event -> controller.addDevice(devicesComboBox.getValue(), listView));

        Button deleteButton = new Button("Delete");
        deleteButton.setMinWidth(90);
        deleteButton.setMaxWidth(90);
        deleteButton.setOnMousePressed(event -> controller.deleteDevices(listView));

        //GridPane for control buttons
        GridPane gridPane = new GridPane();
        gridPane.setMinWidth(300);
        gridPane.setMaxWidth(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        GridPane.setConstraints(devicesComboBox, 0, 0);
        GridPane.setConstraints(addButton, 1, 0);
        GridPane.setConstraints(deleteButton, 1, 1);
        gridPane.getChildren().addAll(devicesComboBox, addButton, deleteButton);

        //Menu for Serializers
        VBox serializersMenu = new VBox();
        serializersMenu.setSpacing(10);
        serializersMenu.getChildren().addAll(binaryRadioButton, xmlRadioButton, textRadioButton, openButton, saveButton);

        //Communication devices list editing menu
        VBox listMenu = new VBox();
        listMenu.setPrefHeight(Region.USE_COMPUTED_SIZE);
        listMenu.setSpacing(10);
        listMenu.getChildren().addAll(listView, gridPane);

        //Main Layout
        HBox mainLayout = new HBox();
        mainLayout.setPadding(new Insets(15));
        mainLayout.setSpacing(10);
        mainLayout.getChildren().addAll(serializersMenu, listMenu, /*fieldsTable*/ fieldsPane);

        //Primary Stage
        Scene scene = new Scene(mainLayout, 740, 350);
        primaryStage.setMinWidth(740);
        primaryStage.setMinHeight(350);
        primaryStage.setTitle("Communication Devices");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
