package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.rails.CouleurWagon;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class VuePiocheEtAutres extends VBox {

    @FXML
    private VBox cartesVisibles;
    @FXML
    private HBox piocheDestination;
    @FXML
    private HBox piocheWagon;


    public VuePiocheEtAutres() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vueCartesWagons.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public VBox getCartesVisibles() {
        return cartesVisibles;
    }

    @FXML
    public void creerBindings() {
        piocheDestination.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ((VueDuJeu) getScene().getRoot()).getJeu().uneDestinationAEtePiochee();

                    }
                });
        piocheWagon.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ((VueDuJeu) getScene().getRoot()).getJeu().uneCarteWagonAEtePiochee();
                    }
                });
    }
}
