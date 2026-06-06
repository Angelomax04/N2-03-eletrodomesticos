package eletrodomesticos;

public interface Eletrodomestico {
    void ligar();
    void desligar();
    void ajustarTemperatura(int temperatura);

    default void aumentarTemperatura() {
        if (!isLigado()) {
            System.out.println(getNome() + " não está ligado.");
            return;
        }
        int t = getTemperatura();
        ajustarTemperatura(t + 1);
    }

    default void diminuirTemperatura() {
        if (!isLigado()) {
            System.out.println(getNome() + " não está ligado.");
            return;
        }
        int t = getTemperatura();
        ajustarTemperatura(t - 1);
    }

    default void aumentarPotencia() {
    }

    default void diminuirPotencia() {
    }

    default int getPotencia() {
        return 0;
    }

    int getTemperatura();
    boolean isLigado();
    String getNome();
}
