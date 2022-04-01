public class ProdutorConsumidorSincronizado {
    
    public static void main(String args[]){
        RecipienteSincronizado rec = new RecipienteSincronizado();
        Produtor2 p = new Produtor2(rec);
        Consumidor2 c = new Consumidor2(rec);

        p.start();
        c.start();
    }
}

class RecipienteSincronizado {
    int valor = 0;
    boolean escrever = true;

    public synchronized void escreverValor(int v) {
        while (!escrever) {
            try {
                wait();
            } catch (InterruptedException excecao) {
                excecao.printStackTrace();
            }
        }
        valor = v;
        System.out.println("Recipiente recebeu o valor " + v);
        escrever = false;
        notify();
    }

    public synchronized int lerValor() {
        while (escrever) {
            try {
                wait();
            } catch (InterruptedException excecao) {
                excecao.printStackTrace();
            }
        }
        notifyAll();
        System.out.println("O valor " + valor + " foi lido do recipiente");
        escrever = true;
        return valor;
    }
}

class Produtor2 extends Thread {
    int cont = 0, soma = 0;
    RecipienteSincronizado rec;

    public Produtor2(RecipienteSincronizado c) {
        rec = c;
    }

    public void run() {
        for (int v = 1; v <= 15; v++){
            try {
                Thread.sleep((int)(Math.random()*4000));
            } catch (Exception excecao) {
                System.err.println("Exceção encontrada: " + excecao.getMessage());
            }
            rec.escreverValor(v);
        }
    }
}

class Consumidor2 extends Thread {
    int cont = 0, soma = 0;
    RecipienteSincronizado rec;

    public Consumidor2(RecipienteSincronizado c) {
        rec = c;
    }

    public void run() {
        do {
            try {
                Thread.sleep((int)(Math.random()*4000));
            } catch (Exception excecao) {
                System.err.println("Exceção encontrada: " + excecao.getMessage());
            }
            cont = rec.lerValor();
            soma += cont;
        } while (cont != 15);
        System.out.println("A soma dos valores lidos pelo consumidor foi " + soma);
    }

}
