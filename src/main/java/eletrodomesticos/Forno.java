package eletrodomesticos;

public class Forno implements Eletrodomestico {
    private static final int TEMPERATURA_MIN = 120;
    private static final int TEMPERATURA_MAX = 400;
    private static final int PASSO_AJUSTE = 10;

    private boolean ligado;
    private int temperatura;
    private int temperaturaAlvo;
    private Thread simulacaoThread;
    private volatile boolean executandoSimulacao;

    public Forno() {
        this.ligado = false;
        this.temperatura = TEMPERATURA_MIN;
        this.temperaturaAlvo = TEMPERATURA_MIN;
        this.executandoSimulacao = false;
    }

    @Override
    public void ligar() {
        this.ligado = true;
        System.out.println("Forno ligado.");
        iniciarSimulacao();
    }

    @Override
    public void desligar() {
        this.ligado = false;
        this.executandoSimulacao = false;
        System.out.println("Forno desligado.");
    }

    @Override
    public void ajustarTemperatura(int temperatura) {
        if (!ligado) {
            System.out.println("Erro: o forno está desligado.");
            return;
        }
        if (temperatura < TEMPERATURA_MIN) temperatura = TEMPERATURA_MIN;
        if (temperatura > TEMPERATURA_MAX) temperatura = TEMPERATURA_MAX;
        this.temperaturaAlvo = temperatura;
        System.out.println("Temperatura alvo do forno ajustada para " + temperatura + "°C.");
    }

    @Override
    public void aumentarTemperatura() {
        if (!ligado) {
            System.out.println(getNome() + " não está ligado.");
            return;
        }
        ajustarTemperatura(getTemperatura() + PASSO_AJUSTE);
    }

    @Override
    public void diminuirTemperatura() {
        if (!ligado) {
            System.out.println(getNome() + " não está ligado.");
            return;
        }
        ajustarTemperatura(getTemperatura() - PASSO_AJUSTE);
    }

    private void iniciarSimulacao() {
        if (executandoSimulacao) return;
        
        executandoSimulacao = true;
        simulacaoThread = new Thread(() -> {
            while (executandoSimulacao && ligado) {
                if (temperatura < temperaturaAlvo) {
                    temperatura += 2;
                    if (temperatura > temperaturaAlvo) temperatura = temperaturaAlvo;
                } else if (temperatura > temperaturaAlvo) {
                    temperatura -= 2;
                    if (temperatura < temperaturaAlvo) temperatura = temperaturaAlvo;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        simulacaoThread.setDaemon(true);
        simulacaoThread.start();
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
        return "Forno";
    }
}



