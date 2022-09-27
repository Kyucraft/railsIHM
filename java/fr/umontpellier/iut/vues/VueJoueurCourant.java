package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.ICouleurWagon;
import fr.umontpellier.iut.IDestination;
import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.rails.CouleurWagon;
import fr.umontpellier.iut.rails.Destination;
import fr.umontpellier.iut.rails.Joueur;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Cette classe présente les éléments appartenant au joueur courant.
 *
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueJoueurCourant extends HBox {
    private ChangeListener<IJoueur> joueurCourantChanges;
    private URL url;
    private FXMLLoader fxmlLoader;
    @FXML private HBox cartesWagonsJoueurs;
    @FXML private HBox cartesDestinationsJoueurs;
    @FXML private Label nomJoueurCourant;
    @FXML private HBox hboxNomJoueurCourant;
    private int nbtour =-1;
    @FXML private Label voscartesdestinations;

    @FXML private Label nbGares;
    @FXML private Label nbWagons;

    public VueJoueurCourant(){
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vueJoueurCourant.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
      } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public int getNbtour() {
        return nbtour;
    }

    public Label getVoscartesdestinations() {
        return voscartesdestinations;
    }

    @FXML public void creerBindings() {
        joueurCourantChanges = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    if(ancienJoueur!=null){
                        cartesWagonsJoueurs.getChildren().clear();
                        cartesDestinationsJoueurs.getChildren().clear();

                        //Joueur1
                        Label nomJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurUnPoint().getChildren().get(0);
                        if(nouveauJoueur.getNom().equals(nomJ.getText())){
                            nbtour++;
                            ((VueDuJeu) getScene().getRoot()).getNbtour().setText(String.valueOf(nbtour));
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurUn().getChildren().get(0).setStyle("-fx-border-color:black; -fx-border-radius:5; -fx-border-width:2.0");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurUnPoint().getChildren().get(0).setStyle("-fx-font-weight: bold; -fx-font-size: 15;");

                        }
                        if(ancienJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurUn().getChildren().get(0).setStyle("-fx-border-style: none;");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurUnPoint().getChildren().get(0).setStyle("-fx-font-weight: normal; -fx-font-size: 14;");
                            Label pointsJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurUnPoint().getChildren().get(1);
                            pointsJ.setText(String.valueOf(ancienJoueur.getScore()));
                        }

                        //Joueur2
                        nomJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurDeuxPoint().getChildren().get(0);
                        if(nouveauJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurDeux().getChildren().get(0).setStyle("-fx-border-color:black; -fx-border-radius:5; -fx-border-width:2.0");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurDeuxPoint().getChildren().get(0).setStyle("-fx-font-weight: bold; -fx-font-size: 15;");

                        }
                        if(ancienJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurDeux().getChildren().get(0).setStyle("-fx-border-style: none;");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurDeuxPoint().getChildren().get(0).setStyle("-fx-font-weight: normal; -fx-font-size: 14;");
                            Label pointsJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurDeuxPoint().getChildren().get(1);
                            pointsJ.setText(String.valueOf(ancienJoueur.getScore()));
                        }

                        //Joueur3
                        nomJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurTroisPoint().getChildren().get(0);
                        if(nouveauJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurTrois().getChildren().get(0).setStyle("-fx-border-color:black; -fx-border-radius:5; -fx-border-width:2.0");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurTroisPoint().getChildren().get(0).setStyle("-fx-font-weight: bold; -fx-font-size: 15;");

                        }
                        if(ancienJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurTrois().getChildren().get(0).setStyle("-fx-border-style: none;");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurTroisPoint().getChildren().get(0).setStyle("-fx-font-weight: normal; -fx-font-size: 14;");
                            Label pointsJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurTroisPoint().getChildren().get(1);
                            pointsJ.setText(String.valueOf(ancienJoueur.getScore()));
                        }

                        //Joueur4
                        nomJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurQuatrePoint().getChildren().get(0);
                        if(nouveauJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurQuatre().getChildren().get(0).setStyle("-fx-border-color:black; -fx-border-radius:5; -fx-border-width:2.0");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurQuatrePoint().getChildren().get(0).setStyle("-fx-font-weight: bold; -fx-font-size: 15;");

                        }
                        if(ancienJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurQuatre().getChildren().get(0).setStyle("-fx-border-style: none;");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurQuatrePoint().getChildren().get(0).setStyle("-fx-font-weight: normal; -fx-font-size: 14;");
                            Label pointsJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurQuatrePoint().getChildren().get(1);
                            pointsJ.setText(String.valueOf(ancienJoueur.getScore()));
                        }

                        //Joueur5
                        nomJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurCinqPoint().getChildren().get(0);
                        if(nouveauJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurCinq().getChildren().get(0).setStyle("-fx-border-color:black; -fx-border-radius:5; -fx-border-width:2.0");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurCinqPoint().getChildren().get(0).setStyle("-fx-font-weight: bold; -fx-font-size: 15;");

                        }
                        if(ancienJoueur.getNom().equals(nomJ.getText())){
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurCinq().getChildren().get(0).setStyle("-fx-border-style: none;");
                            ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurCinqPoint().getChildren().get(0).setStyle("-fx-font-weight: normal; -fx-font-size: 14;");
                            Label pointsJ = (Label) ((VueDuJeu) getScene().getRoot()).getVueAutreJoueur().getJoueurCinqPoint().getChildren().get(1);
                            pointsJ.setText(String.valueOf(ancienJoueur.getScore()));
                        }
                    }
                    for (int i = 0; i < nouveauJoueur.getCartesWagon().size(); i++) {
                        VueCarteWagon v = new VueCarteWagon(nouveauJoueur.getCartesWagon().get(i));
                        v.creerBindings();
                        cartesWagonsJoueurs.getChildren().add(v);
                    }
                    for (int i = 0; i < nouveauJoueur.getDestinations().size(); i++) {
                        VueDestination v = new VueDestination(nouveauJoueur.getDestinations().get(i));
                        cartesDestinationsJoueurs.getChildren().add(v);
                        v.creerBindings();
                    }

                    nbGares.setText(String.valueOf(nouveauJoueur.getNbGares()));
                    nbWagons.setText(String.valueOf(nouveauJoueur.getNbWagons()));
                });
            }
        };

        ((VueDuJeu) getScene().getRoot()).getJeu().joueurCourantProperty().addListener(joueurCourantChanges);
    }


}
