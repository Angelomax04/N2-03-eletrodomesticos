package eletrodomesticos;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML private Button fornoToggleButton;
    @FXML private Button fornoIncButton;
    @FXML private Button fornoDecButton;
    @FXML private Label fornoStatusLabel;
    @FXML private Label fornoTempLabel;

    @FXML private Button gelToggleButton;
    @FXML private Label gelStatusLabel;
    @FXML private Label gelTempLabel;
    @FXML private Button gelIncButton;
    @FXML private Button gelDecButton;
    @FXML private Label gelPotenciaLabel;

    @FXML private Button arToggleButton;
    @FXML private Button arIncButton;
    @FXML private Button arDecButton;
    @FXML private Label arStatusLabel;
    @FXML private Label arTempLabel;

    private Forno forno = new Forno();
    private Geladeira geladeira = new Geladeira();
    private ArCondicionado ar = new ArCondicionado();

    @FXML
    private void initialize() {
        updateFornoUI();
        updateGelUI();
        updateArUI();

        fornoToggleButton.setOnAction(e -> {
            if (forno.isLigado()) forno.desligar(); else forno.ligar();
            updateFornoUI();
        });
        fornoIncButton.setOnAction(e -> {
            forno.aumentarTemperatura();
            updateFornoUI();
        });
        fornoDecButton.setOnAction(e -> {
            forno.diminuirTemperatura();
            updateFornoUI();
        });

        gelToggleButton.setOnAction(e -> {
            if (geladeira.isLigado()) geladeira.desligar(); else geladeira.ligar();
            updateGelUI();
        });

        gelIncButton.setOnAction(e -> {
            geladeira.aumentarPotencia();
            updateGelUI();
        });

        gelDecButton.setOnAction(e -> {
            geladeira.diminuirPotencia();
            updateGelUI();
        });

        arToggleButton.setOnAction(e -> {
            if (ar.isLigado()) ar.desligar(); else ar.ligar();
            updateArUI();
        });
        arIncButton.setOnAction(e -> {
            ar.aumentarTemperatura();
            updateArUI();
        });
        arDecButton.setOnAction(e -> {
            ar.diminuirTemperatura();
            updateArUI();
        });

        iniciarAtualizadorUI();
    }

    private void iniciarAtualizadorUI() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                fornoTempLabel.setText(forno.getTemperatura() + " °C");
            }
        };
        timer.start();
    }

    private void updateFornoUI() {
        fornoStatusLabel.setText(forno.isLigado() ? "Ligado" : "Desligado");
        fornoTempLabel.setText(forno.getTemperatura() + " °C");
        fornoIncButton.setDisable(!forno.isLigado());
        fornoDecButton.setDisable(!forno.isLigado());
        fornoToggleButton.setText(forno.isLigado() ? "Desligar" : "Ligar");
    }

    private void updateGelUI() {
        gelStatusLabel.setText(geladeira.isLigado() ? "Ligada" : "Desligada");
        gelTempLabel.setText(geladeira.getTemperatura() + " °C");
        gelPotenciaLabel.setText("Potência: " + geladeira.getPotencia() + "/4");
        gelIncButton.setDisable(!geladeira.isLigado() || geladeira.getPotencia() >= 4);
        gelDecButton.setDisable(!geladeira.isLigado() || geladeira.getPotencia() <= 1);
        gelToggleButton.setText(geladeira.isLigado() ? "Desligar" : "Ligar");
    }

    private void updateArUI() {
        arStatusLabel.setText(ar.isLigado() ? "Ligado" : "Desligado");
        arTempLabel.setText(ar.getTemperatura() + " °C");
        arIncButton.setDisable(!ar.isLigado());
        arDecButton.setDisable(!ar.isLigado());
        arToggleButton.setText(ar.isLigado() ? "Desligar" : "Ligar");
    }
}
