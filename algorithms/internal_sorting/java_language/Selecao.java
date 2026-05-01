import java.util.Scanner;

public class Selecao extends Geracao {

    public Selecao() {
    
        super();

    }

    public Selecao(int length) {

        super(length);

    }

    // Metodo sobrescrito para ordenacao por Selecao
    @Override
    public void sort() {

        for (int x = 0; x < this.length - 1; x = x + 1) {

            int menor = x; // Guardar valor atual o indice x

            // Variavel y vai de x + 1 ate n - 1
            for (int y = x + 1; y < this.length; y = y + 1) {

                if (this.array[menor] > this.array[y]) menor = y;

            }

            swap(menor, x);

        }

    }

}
