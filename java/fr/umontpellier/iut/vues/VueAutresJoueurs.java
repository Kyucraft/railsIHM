package fr.umontpellier.iut.vues;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

import fr.umontpellier.iut.IJeu;

/**
 * Cette classe présente les éléments des joueurs autres que le joueur courant,
 * en cachant ceux que le joueur courant n'a pas à connaitre.
 * <p>
 * On y définit les bindings sur le joueur courant, ainsi que le listener à exécuter lorsque ce joueur change
 */
public class VueAutresJoueurs extends HBox {
    @FXML
    private HBox joueurUn,joueurUnId,joueurDeux,joueurTrois,joueurQuatre,joueurCinq,joueurDeuxId,joueurTroisId,joueurQuatreId,joueurCinqId;
    @FXML
    private VBox joueurUnPoint,joueurDeuxPoint,joueurTroisPoint,joueurQuatrePoint,joueurCinqPoint;
    @FXML
    private Label joueurUnNom,joueurDeuxNom,joueurTroisNom,joueurQuatreNom,joueurCinqNom;
    @FXML
    private Label joueurUnScore,joueurDeuxScore,joueurTroisScore,joueurQuatreScore,joueurCinqScore;
    public VueAutresJoueurs() {


    }

    public void creerBindings() {
        if(((VueDuJeu) getScene().getRoot()).getJeu().getJoueurs().size()==2){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vueAutresJoueurs2.fxml"));
                loader.setRoot(this);
                loader.setController(this);
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(((VueDuJeu) getScene().getRoot()).getJeu().getJoueurs().size()==3){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vueAutresJoueurs3.fxml"));
                loader.setRoot(this);
                loader.setController(this);
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  else if(((VueDuJeu) getScene().getRoot()).getJeu().getJoueurs().size()==4){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vueAutresJoueurs4.fxml"));
                loader.setRoot(this);
                loader.setController(this);
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vueAutresJoueurs5.fxml"));
                loader.setRoot(this);
                loader.setController(this);
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        joueurUnId.getChildren().clear();
        joueurDeuxId.getChildren().clear();
        joueurTroisId.getChildren().clear();
        joueurQuatreId.getChildren().clear();
        joueurCinqId.getChildren().clear();

        joueurUnNom.setText("");
        joueurDeuxNom.setText("");
        joueurTroisNom.setText("");
        joueurQuatreNom.setText("");
        joueurCinqNom.setText("");

        joueurUnScore.setText("");
        joueurDeuxScore.setText("");
        joueurTroisScore.setText("");
        joueurQuatreScore.setText("");
        joueurCinqScore.setText("");
        HBox[] listeperso = {joueurUnId,joueurDeuxId,joueurTroisId,joueurQuatreId,joueurCinqId};
        Label[] nomPerso = {joueurUnNom,joueurDeuxNom,joueurTroisNom,joueurQuatreNom,joueurCinqNom};
        Label[] scorePerso = {joueurUnScore,joueurDeuxScore,joueurTroisScore,joueurQuatreScore,joueurCinqScore};
        for (int j = 0; j <  ((VueDuJeu) getScene().getRoot()).getJeu().getJoueurs().size(); j++) {
            Image im = new Image("images/avatars/avatar-" +  ((VueDuJeu) getScene().getRoot()).getJeu().getJoueurs().get(j).getCouleur() + ".png");
            ImageView imv = new ImageView(im);
            imv.setFitHeight(95);
            imv.setPreserveRatio(true);
            listeperso[j].getChildren().add(imv);
            nomPerso[j].setText(((VueDuJeu) getScene().getRoot()).getJeu().getJoueurs().get(j).getNom());
            scorePerso[j].setText("0");
        }
    }

    public HBox getJoueurUn() {
        return joueurUn;
    }

    public HBox getJoueurDeux() {
        return joueurDeux;
    }

    public HBox getJoueurTrois() {
        return joueurTrois;
    }

    public HBox getJoueurQuatre() {
        return joueurQuatre;
    }

    public HBox getJoueurCinq() {
        return joueurCinq;
    }

    public HBox getJoueurDeuxId() {
        return joueurDeuxId;
    }

    public HBox getJoueurTroisId() {
        return joueurTroisId;
    }

    public HBox getJoueurQuatreId() {
        return joueurQuatreId;
    }

    public HBox getJoueurCinqId() {
        return joueurCinqId;
    }

    public HBox getJoueurUnId() {
        return joueurUn;
    }

    public VBox getJoueurUnPoint() {
        return joueurUnPoint;
    }

    public VBox getJoueurDeuxPoint() {
        return joueurDeuxPoint;
    }

    public VBox getJoueurTroisPoint() {
        return joueurTroisPoint;
    }

    public VBox getJoueurQuatrePoint() {
        return joueurQuatrePoint;
    }

    public VBox getJoueurCinqPoint(){return joueurCinqPoint;}
}
