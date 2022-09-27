package fr.umontpellier.iut.vues;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe correspond à une nouvelle fenêtre permettant de choisir le nombre et les noms des joueurs de la partie.
 *
 * Sa présentation graphique peut automatiquement être actualisée chaque fois que le nombre de joueurs change.
 * Lorsque l'utilisateur a fini de saisir les noms de joueurs, il demandera à démarrer la partie.
 */
public class VueChoixJoueurs extends Stage {
    private ObservableList<String> nomsJoueurs;
    private List<String> temp;

    public ObservableList<String> nomsJoueursProperty() {
        return nomsJoueurs;
    }
    public List<String> getNomsJoueurs() {
        return nomsJoueurs;
    }

    @FXML
    private VBox vboxdechoix;

    private ChoiceBox<Integer> choixnb;

    @FXML private TextField nomJ1;
    @FXML private TextField nomJ2;
    @FXML private TextField nomJ3;
    @FXML private TextField nomJ4;
    @FXML private TextField nomJ5;

    public VueChoixJoueurs() {
        getIcons().add(new Image("images/accueil.jpg"));
        setTitle("Aventurier du Rail");
        Pane p = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/vueDebut.fxml"));
            loader.setController(this);
            p = (Pane) loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(p);
        setScene(s);
        setResizable(false);
        temp = new ArrayList<>();
        choixnb = new ChoiceBox<Integer>();
        choixnb.setValue(2);
        choixnb.getItems().add(2);
        choixnb.getItems().add(3);
        choixnb.getItems().add(4);
        choixnb.getItems().add(5);
        choixnb.setStyle("-fx-cursor: hand;");
        nomJ1.setText("Guybrush");
        nomJ2.setText("Elaine");
        nomJ3.setText("Largo");
        nomJ4.setText("LeChuck");
        nomJ5.setText("ElPacino");
        vboxdechoix.getChildren().add(choixnb);
        nomsJoueurs = FXCollections.observableArrayList();
    }

    @FXML
    private void btnCommencer(ActionEvent event){

        if(choixnb.getValue()==2){
            temp.add(nomJ1.getText());
            temp.add(nomJ2.getText());
        }
        if(choixnb.getValue()==3){
            temp.add(nomJ1.getText());
            temp.add(nomJ2.getText());
            temp.add(nomJ3.getText());
        }
        if(choixnb.getValue()==4){
            temp.add(nomJ1.getText());
            temp.add(nomJ2.getText());
            temp.add(nomJ3.getText());
            temp.add(nomJ4.getText());
        }
        if(choixnb.getValue()==5){
            temp.add(nomJ1.getText());
            temp.add(nomJ2.getText());
            temp.add(nomJ3.getText());
            temp.add(nomJ4.getText());
            temp.add(nomJ5.getText());
        }

        setListeDesNomsDeJoueurs();
    }

    /**
     * Définit l'action à exécuter lorsque la liste des participants est correctement initialisée
     */
    public void setNomsDesJoueursDefinisListener(ListChangeListener<String> quandLesNomsDesJoueursSontDefinis) {
        nomsJoueursProperty().addListener(quandLesNomsDesJoueursSontDefinis);
    }

    /**
     * Définit l'action à exécuter lorsque le nombre de participants change
     */
    protected void setChangementDuNombreDeJoueursListener(ChangeListener<Integer> quandLeNombreDeJoueursChange) {

    }

    /**
     * Vérifie que tous les noms des participants sont renseignés
     * et affecte la liste définitive des participants
     */
    protected void setListeDesNomsDeJoueurs() {
        ArrayList<String> tempNamesList = new ArrayList<>();
        for (int i = 1; i <= getNombreDeJoueurs() ; i++) {
            String name = getJoueurParNumero(i);
            if (name == null || name.equals("")) {
                tempNamesList.clear();
                break;
            }
            else
                tempNamesList.add(name);
        }
        if (!tempNamesList.isEmpty()) {
            hide();
            nomsJoueurs.clear();
            nomsJoueurs.addAll(tempNamesList);
        }
    }

    @FXML
    private void ouvrirRegles(ActionEvent event){
        ImageView p1,p2,p3,p4,p5,p6,p7;
        p1 = new ImageView("images/regles/page01.png");
        p2 = new ImageView("images/regles/page02.png");
        p3 = new ImageView("images/regles/page03.png");
        p4 = new ImageView("images/regles/page04.png");
        p5 = new ImageView("images/regles/page05.png");
        p6 = new ImageView("images/regles/page06.png");
        p7 = new ImageView("images/regles/page07.png");

        VBox regles = new VBox();
        ImageView[] listeImage = {p1,p2,p3,p4,p5,p6,p7};
        for (int i = 0; i < listeImage.length; i++) {
            listeImage[i].setPreserveRatio(true);
            listeImage[i].setFitHeight(2000);
            listeImage[i].setFitWidth(1000);
            regles.getChildren().add(listeImage[i]);
        }

        ScrollPane scroller = new ScrollPane();
        scroller.setContent(regles);
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Stage test = new Stage();
        test.setMaxWidth(1025);
        test.setHeight(900);
        test.setMinWidth(1025);
        test.initModality(Modality.WINDOW_MODAL);

        Scene ge = new Scene(scroller);
        test.setScene(ge);
        if(!test.isShowing()){
            test.show();
        }else{
            test.hide();
        }
    }

    /**
     * Retourne le nombre de participants à la partie que l'utilisateur a renseigné
     */
    protected int getNombreDeJoueurs() {
        return temp.size();
    }

    /**
     * Retourne le nom que l'utilisateur a renseigné pour le ième participant à la partie
     * @param playerNumber : le numéro du participant
     */
    protected String getJoueurParNumero(int playerNumber) {
        return temp.get(playerNumber-1);
    }

}
