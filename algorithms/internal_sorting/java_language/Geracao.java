import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.util.Date;

// Classe Geracao filha da classe Array
public class Geracao extends Array {

    // Construtor vazio herdado
    public Geracao() {

        super();

    }

    // Construtor com parametro herdado
    public Geracao(int length) {

        super(length);

    }

    // Funcao para identificar se arranjo esta vazio
    public boolean isVoid() {

        boolean OK = true;

        for (int x = 0; x < this.length && OK; x = x + 1) {

            if (this.array[x] != 0) OK = false;

        }

        return OK;

    }

    // Metodo de troca de valores
    public void swap(int a, int b) {

        int tmp = this.array[a];
        this.array[a] = this.array[b];
        this.array[b] = tmp;

    }

    // Preencher arranjo em ordem crescente
    public void crescent() {

        if (isVoid()) {

            for (int x = 0; x < this.length; x = x + 1) {

                this.array[x] = x + 1;
                this.n++;

            }

        } else System.out.println("Erro: Arranjo nao vazio.");

    }

    // Preencher arranjo em ordem decrescente
    public void decrescent() {

        if (isVoid()) {

            for (int x = 0; x < this.length; x = x + 1) {

                this.array[x] = this.length - x;
                this.n++;

            }

        } else System.out.println("Erro: Arranjo nao vazio.");

    }

    // Metodo para limpar arranjo
    public void clear() {

        if (this.n > 0) {

            for (int x = this.n - 1; x >= 0; x = x - 1) {
             
                this.array[x] = 0;
                this.n--;

            }

        } else System.out.println("Erro: Array ja vazio.");

    }

    // Metodo para gerar valores randomicos no arranjo
    public void random() {

        if (isVoid()) {

            Random rand = new Random();
            crescent();
            for (int x = 0; x < this.length; x = x + 1) {

                this.array[x] = Math.abs(rand.nextInt()) % this.length + 1;

            }

        } else System.out.println("Erro: Arranjo nao vazio.");

    }

    // Funcao para retornar hora atual
    public long now() {

        return new Date().getTime();

    }

    // Funcao para verificar se arranjo esta em ordem crescente
    public boolean isOrdered() {

        boolean OK = true;
        for (int x = 1; x < this.length; x = x + 1) {

            if (this.array[x] > this.array[x - 1]) {

                x = this.length;
                OK = false;

            }

        }

        return OK;

    }

    // Metodo para ser sobrescrito para implementar metodos de ordenacao
    public void sort() {

        System.out.print("Metodo para implementar os algoritmos de ordenacao.");

    }

}
