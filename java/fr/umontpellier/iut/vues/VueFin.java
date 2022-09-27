package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJeu;
import fr.umontpellier.iut.rails.Joueur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Cette classe présente les routes et les villes sur le plateau.
 * <p>
 * On y définit le listener à exécuter lorsque qu'un élément du plateau a été choisi par l'utilisateur
 * ainsi que les bindings qui mettront ?à jour le plateau après la prise d'une route ou d'une ville par un joueur
 */
public class VueFin extends VBox {
    @FXML
    private HBox joueurUn, joueurUnId, joueurDeux, joueurTrois, joueurQuatre, joueurCinq, joueurDeuxId, joueurTroisId, joueurQuatreId, joueurCinqId;
    @FXML
    private VBox joueurUnPoint, joueurDeuxPoint, joueurTroisPoint, joueurQuatrePoint, joueurCinqPoint;
    @FXML
    private Label joueurUnNom, joueurDeuxNom, joueurTroisNom, joueurQuatreNom, joueurCinqNom;
    @FXML
    private Label joueurUnScore, joueurDeuxScore, joueurTroisScore, joueurQuatreScore, joueurCinqScore;

    private IJeu v;

    public VueFin(IJeu v) {
        this.v = v;
        List<Joueur> joueurs = v.getJoueurs();
        Label l = new Label("Score finaux: ");
        l.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        getChildren().add(l);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/finvueAutresJoueurs.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
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
        joueurUnId.setStyle(null);
        joueurUnNom.setStyle("-fx-font-weight: normal");

        HBox[] listeperso = {joueurUnId, joueurDeuxId, joueurTroisId, joueurQuatreId, joueurCinqId};
        Label[] nomPerso = {joueurUnNom, joueurDeuxNom, joueurTroisNom, joueurQuatreNom, joueurCinqNom};
        Label[] scorePerso = {joueurUnScore, joueurDeuxScore, joueurTroisScore, joueurQuatreScore, joueurCinqScore};
        for (int j = 0; j < joueurs.size(); j++) {
            Image im = new Image("images/avatars/avatar-" + joueurs.get(j).getCouleur() + ".png");
            ImageView imv = new ImageView(im);
            imv.setFitHeight(95);
            imv.setPreserveRatio(true);
            listeperso[j].getChildren().add(imv);
            nomPerso[j].setText(joueurs.get(j).getNom());
            scorePerso[j].setText(String.valueOf(joueurs.get(j).getScore()));
        }
        if(joueurs.size()==2){
            getChildren().remove(6);
            getChildren().remove(5);
            getChildren().remove(4);
        }
        else if(joueurs.size()==3){
            getChildren().remove(6);
            getChildren().remove(5);
        }
        else if(joueurs.size()==4){
            getChildren().remove(6);
        }
    }


}
