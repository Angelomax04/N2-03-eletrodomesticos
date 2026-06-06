package eletrodomesticos;

public class Geladeira implements Eletrodomestico {
    private static final int POTENCIA_MIN = 1;
    private static final int POTENCIA_MAX = 4;

    private static final int TEMPERATURA_MAX = 5;   // menor potência => +5°C
    private static final int TEMPERATURA_MIN = -5;  // maior potência => -5°C

    private boolean ligada;
    private int temperatura;
    private int potencia;

    public Geladeira() {
        this.ligada = false;
        this.potencia = 2;
        this.temperatura = calcularTemperaturaParaPotencia(this.potencia);
    }

    @Override
    public void ligar() {
        this.ligada = true;
        System.out.println("Geladeira ligada.");
        this.temperatura = calcularTemperaturaParaPotencia(this.potencia);
    }

    @Override
    public void desligar() {
        this.ligada = false;
        System.out.println("Geladeira desligada.");
    }

    @Override
    public void ajustarTemperatura(int temperatura) {
        System.out.println("A geladeira não possui controle manual de temperatura neste modelo.");
    }

    @Override
    public void aumentarPotencia() {
        if (!ligada) {
            System.out.println("Erro: a geladeira está desligada.");
            return;
        }
        if (potencia < POTENCIA_MAX) {
            potencia++;
            atualizarTemperaturaPorPotencia();
            System.out.println("Potência da geladeira aumentada para " + potencia);
        }
    }

    @Override
    public void diminuirPotencia() {
        if (!ligada) {
            System.out.println("Erro: a geladeira está desligada.");
            return;
        }
        if (potencia > POTENCIA_MIN) {
            potencia--;
            atualizarTemperaturaPorPotencia();
            System.out.println("Potência da geladeira diminuída para " + potencia);
        }
    }

    private void atualizarTemperaturaPorPotencia() {
        this.temperatura = calcularTemperaturaParaPotencia(this.potencia);
    }

    private int calcularTemperaturaParaPotencia(int potencia) {
        // Mapeamento linear: potência 1 => +5, potência 4 => -5
        // intervalo: 10°C em 3 passos => -3.333.. por passo.
        // para manter inteiros e bater exatamente nos extremos: arredondar.
        int t = TEMPERATURA_MAX + (potencia - POTENCIA_MIN) * (TEMPERATURA_MIN - TEMPERATURA_MAX) / (POTENCIA_MAX - POTENCIA_MIN);
        return t;
    }

    @Override
    public int getPotencia() {
        return potencia;
    }

    @Override
    public int getTemperatura() {
        return temperatura;
    }

    @Override
    public boolean isLigado() {
        return ligada;
    }

    @Override
    public String getNome() {
        return "Geladeira";
    }
}

