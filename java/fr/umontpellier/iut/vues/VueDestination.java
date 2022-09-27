package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IDestination;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Cette classe représente la vue d'une carte Destination.
 *
 * On y définit le listener à exécuter lorsque cette carte a été choisie par l'utilisateur
 */
public class VueDestination extends Pane {

    private IDestination destination;

    public VueDestination(IDestination destination) {
        this.destination = destination;
        String[] res = destination.getNom().split(" ");
        Image i = new Image("images/missions/eu-"+res[0].toLowerCase()+"-"+res[2].toLowerCase()+".png");
        ImageView im = new ImageView(i);
        im.setFitHeight(75);
        im.setPreserveRatio(true);
        getChildren().add(im);
    }

    public IDestination getDestination() {
        return destination;
    }

    public void creerBindings() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ((VueDuJeu) getScene().getRoot()).getJeu().uneDestinationAEteChoisie(destination.getNom());
                        System.out.println(destination.getNom());
                    }
                });
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((VueDuJeu) getScene().getRoot()).getVueJoueurCourant().getVoscartesdestinations().setText("Vos cartes destinations: ( "+destination+" )");
            }
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((VueDuJeu) getScene().getRoot()).getVueJoueurCourant().getVoscartesdestinations().setText("Vos cartes destinations");
            }
        });
    }
}
