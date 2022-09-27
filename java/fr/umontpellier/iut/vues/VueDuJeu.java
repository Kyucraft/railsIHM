package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Cette classe correspond à la fenêtre principale de l'application.
 * <p>
 * Elle est initialisée avec une référence sur la partie en cours (Jeu).
 * <p>
 * On y définit les bindings sur les éléments internes qui peuvent changer
 * (le joueur courant, les 5 cartes Wagons visibles, les destinations lors de l'étape d'initialisation de la partie, ...)
 * ainsi que les listeners à exécuter lorsque ces éléments changent
 */
public class VueDuJeu extends BorderPane {

    private IJeu jeu;

    private VuePlateau plateau;
    private VueAutresJoueurs vueAutreJoueur;
    private VueJoueurCourant vueJoueurCourant;
    private VuePiocheEtAutres vuePiocheEtAutres;

    private Button boutonPasser;
    private Button boutonRegle;
    private Button boutonTerminer;

    private VBox listeDestination;
    private Label nbtour;
    private HBox destASuppr;

    private static ListChangeListener<IDestination> destinationsChangés;
    private static ListChangeListener<ICouleurWagon> carteWagonsVisibles;
    private ChangeListener<String> instructionChanges;
    private Label t;

    public VueDuJeu(IJeu jeu) {
        this.jeu = jeu;
        boutonPasser = new Button("PASSER");
        boutonRegle = new Button("REGLES");
        boutonTerminer = new Button("TERMINER PARTIE");
        boutonPasser.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand");
        boutonRegle.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand");
        boutonTerminer.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand");
        t = new Label("CC");
        t.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        listeDestination = new VBox(9);
        listeDestination.setAlignment(Pos.CENTER);
        plateau = new VuePlateau();
        vueJoueurCourant = new VueJoueurCourant();
        vueAutreJoueur = new VueAutresJoueurs();
        vuePiocheEtAutres = new VuePiocheEtAutres();


        VBox left = new VBox(40);
        left.getChildren().add(boutonTerminer);
        nbtour = new Label("0");
        nbtour.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        Label l = new Label("Nombre de tours");
        l.setStyle("-fx-font-size: 15");
        left.getChildren().add(l);

        left.getChildren().add(nbtour);
        ImageView im = new ImageView();
        im.setFitWidth(150);
        left.getChildren().add(im);
        left.setAlignment(Pos.CENTER);

        HBox h = new HBox(10);
        h.getChildren().add(boutonPasser);
        h.getChildren().add(boutonRegle);
        h.setAlignment(Pos.CENTER);
        left.getChildren().add(h);
        destASuppr = new HBox();
        destASuppr.setAlignment(Pos.CENTER);
        left.getChildren().add(destASuppr);

        left.getChildren().add(listeDestination);

        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().add(t);
        bottom.getChildren().add(vueJoueurCourant);

        setTop(vueAutreJoueur);
        setLeft(left);
        setBottom(bottom);
        setRight(vuePiocheEtAutres);
        setCenter(plateau);

    }

    public IJeu getJeu() {
        return jeu;
    }

    public Label getNbtour() {
        return nbtour;
    }

    public Button trouveLabelDestination(IDestination dest) {
        for (Node node : listeDestination.getChildren()) {
            Button l = (Button) node;
            if (l.getText().equals(dest.getNom())) {
                return l;
            }
        }
        return null;
    }

    public VueCarteWagon trouverCarte(ICouleurWagon ca) {
        for (Node node : vuePiocheEtAutres.getCartesVisibles().getChildren()) {
            VueCarteWagon c = (VueCarteWagon) node;
            if (c.getCouleurWagon().equals(ca)) {
                return c;
            }
        }
        return null;
    }

    public VueJoueurCourant getVueJoueurCourant() {
        return vueJoueurCourant;
    }

    public void creerBindings() {
        vueJoueurCourant.creerBindings();
        //bindings destinations
        destinationsChangés = new ListChangeListener<IDestination>() {
            @Override
            public void onChanged(Change<? extends IDestination> change) {
                Platform.runLater(() -> {
                    while (change.next()) {
                        if (change.wasAdded()) {
                            destASuppr.getChildren().clear();
                            destASuppr.getChildren().add(new Label("Destination à supprimer :"));
                            for (int i = 0; i < change.getAddedSubList().size(); i++) {
                                Button l = new Button(change.getAddedSubList().get(i).getNom());
                                l.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand");
                                l.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        jeu.uneDestinationAEteChoisie(l.getText());
                                    }
                                });
                                listeDestination.getChildren().add(l);
                            }
                        } else if (change.wasRemoved()) {
                            destASuppr.getChildren().clear();

                            for (int i = 0; i < change.getRemoved().size(); i++) {

                                listeDestination.getChildren().remove(trouveLabelDestination(change.getRemoved().get(i)));
                            }
                        }
                    }

                });
            }
        };

        //bindings picoher carte wagons
        carteWagonsVisibles = new ListChangeListener<ICouleurWagon>() {
            @Override
            public void onChanged(Change<? extends ICouleurWagon> change) {
                Platform.runLater(() -> {
                    while (change.next()) {
                        if (change.wasAdded()) {
                            for (int i = 0; i < change.getAddedSubList().size(); i++) {
                                VueCarteWagon v = new VueCarteWagon(change.getAddedSubList().get(i));
                                v.creerBindings();
                                vuePiocheEtAutres.getCartesVisibles().getChildren().add(v);
                            }
                        } else if (change.wasRemoved()) {
                            for (int i = 0; i < change.getRemoved().size(); i++) {
                                vuePiocheEtAutres.getCartesVisibles().getChildren().remove(trouverCarte(change.getRemoved().get(i)));
                            }
                        }
                    }

                });
            }
        };

        instructionChanges = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Platform.runLater(() -> {
                    t.setText(t1);
                });
            }
        };


        jeu.cartesWagonVisiblesProperty().addListener(carteWagonsVisibles);
        jeu.destinationsInitialesProperty().addListener(destinationsChangés);
        jeu.instructionProperty().addListener(instructionChanges);
        vueAutreJoueur.creerBindings();
        vuePiocheEtAutres.creerBindings();
        plateau.creerBindings();
        boutonPasser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                jeu.passerAEteChoisi();
            }
        });
        boutonTerminer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                onStopGame();
            }

        });

        boutonRegle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ImageView p1, p2, p3, p4, p5, p6, p7;
                p1 = new ImageView("images/regles/page01.png");
                p2 = new ImageView("images/regles/page02.png");
                p3 = new ImageView("images/regles/page03.png");
                p4 = new ImageView("images/regles/page04.png");
                p5 = new ImageView("images/regles/page05.png");
                p6 = new ImageView("images/regles/page06.png");
                p7 = new ImageView("images/regles/page07.png");

                VBox regles = new VBox();
                ImageView[] listeImage = {p1, p2, p3, p4, p5, p6, p7};
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
                if (!test.isShowing()) {
                    test.show();
                } else {
                    test.hide();
                }
            }
        });

    }


    public void onStopGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("On arrête de jouer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ((Stage) getScene().getWindow()).close();
            Stage test = new Stage();
            VBox v = new VBox();
            v.getChildren().add(new Label("cc"));
            VueFin f = new VueFin(getJeu());
            Scene ge = new Scene(f);
            test.setScene(ge);
            if(!test.isShowing()){
                test.show();
            }else{
                test.hide();
            }
        }
    }

    public VueAutresJoueurs getVueAutreJoueur() {
        return vueAutreJoueur;
    }
}