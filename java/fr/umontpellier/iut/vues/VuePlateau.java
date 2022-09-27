package fr.umontpellier.iut.vues;

import fr.umontpellier.iut.IJoueur;
import fr.umontpellier.iut.IRoute;
import fr.umontpellier.iut.IVille;
import fr.umontpellier.iut.rails.Joueur;
import fr.umontpellier.iut.rails.Route;
import fr.umontpellier.iut.rails.Ville;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Cette classe présente les routes et les villes sur le plateau.
 * <p>
 * On y définit le listener à exécuter lorsque qu'un élément du plateau a été choisi par l'utilisateur
 * ainsi que les bindings qui mettront ?à jour le plateau après la prise d'une route ou d'une ville par un joueur
 */
public class VuePlateau extends Pane {

    @FXML private Group villes;
    @FXML private Group routes;

    private ChangeListener<IJoueur> joueurVilleChange;
    private ChangeListener<IJoueur> joueurRouteChange;

    public VuePlateau() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/plateau.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void creerBindings() {
        joueurVilleChange = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    for (Node groupeVille : villes.getChildren()) {
                        for (Object o : ((VueDuJeu) getScene().getRoot()).getJeu().getVilles()) {
                            IVille v = (IVille) o;
                            if (groupeVille.getId().equals(v.getNom())) {
                                if (v.getProprietaire() != null) {
                                    if (v.getProprietaire().equals(nouveauJoueur)) {
                                        Circle uneVille = (Circle) groupeVille;
                                        if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.ROSE)) {
                                            uneVille.setFill(Color.rgb(199,21,133));
                                        } else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.VERT)) {
                                            uneVille.setFill(Paint.valueOf("GREEN"));
                                        }else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.BLEU)) {
                                            uneVille.setFill(Paint.valueOf("BLUE"));
                                        }else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.ROUGE)) {
                                            uneVille.setFill(Paint.valueOf("RED"));
                                        }else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.JAUNE)) {
                                            uneVille.setFill(Color.rgb(255,255,0));
                                        }

                                    }
                                }

                            }
                        }
                    }

                });
            }
        };
        joueurRouteChange = new ChangeListener<IJoueur>() {
            @Override
            public void changed(ObservableValue<? extends IJoueur> observableValue, IJoueur ancienJoueur, IJoueur nouveauJoueur) {
                Platform.runLater(() -> {
                    for (Node node : routes.getChildren()) {
                        for (Object o : ((VueDuJeu) getScene().getRoot()).getJeu().getRoutes()) {
                            IRoute v = (IRoute) o;
                            if (node.getId().equals(v.getNom())) {
                                if (v.getProprietaire() != null) {
                                    if (v.getProprietaire().equals(nouveauJoueur)) {
                                        Group groupeDeVilles = (Group) node;
                                        for(Node rectangles: groupeDeVilles.getChildren()){
                                            Rectangle rectangle = (Rectangle) rectangles;
                                            if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.ROSE)) {
                                                rectangle.setFill(Color.rgb(199,21,133));
                                            } else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.VERT)) {
                                                rectangle.setFill(Paint.valueOf("GREEN"));
                                            }else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.BLEU)) {
                                                rectangle.setFill(Paint.valueOf("BLUE"));
                                            }else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.ROUGE)) {
                                                rectangle.setFill(Paint.valueOf("RED"));
                                            }else if (v.getProprietaire().getCouleur().equals(IJoueur.Couleur.JAUNE)) {
                                                rectangle.setFill(Color.rgb(255,255,0));
                                            }
                                        }


                                    }
                                }

                            }
                        }
                    }
                });
            }
        };

        for (Object o : ((VueDuJeu) getScene().getRoot()).getJeu().getVilles()) {
            IVille v = (IVille) o;
            v.proprietaireProperty().addListener(joueurVilleChange);
        }
        for (Object o : ((VueDuJeu) getScene().getRoot()).getJeu().getRoutes()) {
            IRoute v = (IRoute) o;
            v.proprietaireProperty().addListener(joueurRouteChange);
        }
    }

    @FXML
    public void choixRouteOuVille(MouseEvent event) {
        try { //les routes
            Group save = ((Group) event.getSource());
            ((VueDuJeu) getScene().getRoot()).getJeu().uneVilleOuUneRouteAEteChoisie(save.getId());
        } catch (RuntimeException e) { //si crash alors c'est une ville donc on fait autre chose
            Circle save = (Circle) event.getSource();
            ((VueDuJeu) getScene().getRoot()).getJeu().uneVilleOuUneRouteAEteChoisie(save.getId());
        }
    }
}
