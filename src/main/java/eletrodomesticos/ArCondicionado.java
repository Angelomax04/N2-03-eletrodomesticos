package eletrodomesticos;

public class ArCondicionado implements Eletrodomestico {
    private boolean ligado;
    private int temperatura;

    public ArCondicionado() {
        this.ligado = false;
        this.temperatura = 22;
    }

    @Override
    public void ligar() {
        this.ligado = true;
        System.out.println("Ar-condicionado ligado.");
    }

    @Override
    public void desligar() {
        this.ligado = false;
        System.out.println("Ar-condicionado desligado.");
    }

    @Override
    public void ajustarTemperatura(int temperatura) {
        if (!ligado) {
            System.out.println("Erro: o ar-condicionado está desligado.");
            return;
        }
        if (temperatura < 16) temperatura = 16;
        if (temperatura > 30) temperatura = 30;
        this.temperatura = temperatura;
        System.out.println("Temperatura do ar-condicionado ajustada para " + temperatura + "°C.");
    }

    @Override
    public int getTemperatura() {
        return temperatura;
    }

    @Override
    public boolean isLigado() {
        return ligado;
    }

    @Override
    public String getNome() {
        return "Ar-Condicionado";
    }
}
