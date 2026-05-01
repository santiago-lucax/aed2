// Classe com tipo generico
public class Array {

    // Atributos protegidos para serem acessados apenas por ele e classes filhas
    protected int array[];
    protected int length;
    protected int n;

    // Construtor padrao
    public Array() {

        this.array = null;
        this.length = 0;
        this.length = 0;

    }

    // Construtor com tamanho para arranjo
    public Array(int length) {

        try {

            if (length > 0) {
    
                this.array = new int[length]; // Cast necessario
                this.length = length;
                this.n = 0;

            }

        } catch(NegativeArraySizeException e) {

            System.out.println("Erro: Tamanho invalido.");

        } catch(NullPointerException e) {

            System.out.println("Erro: Arranjo nulo");

        }

    }

    // Funcao para retornar tamanho do arranjo
    public int getLength() {

        return this.length;

    }

    // Funcao para retornar quantidade de elementos no array
    public int getN() {

        return this.n;

    }

    // Funcao para retornar valor da posicao
    public int get(int position) {

        int value = 0;

        if (position >= 0 && position < this.length) value = this.array[position];
        else System.out.println("Erro: Posicao invalida.");

        return value;

    }

    // Metodo para adicionar valor a determinado indice
    public void set(int position, int value) {

        try {

            if (position >= 0 && position < this.length) {

                if (this.array[position] != 0) {

                    System.out.println("Erro: Posicao preenchida.");

                } else {

                    this.array[position] = value;
                    this.n++;

                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Erro: Acessou posicao fora do arranjo.");

        }

    }

    // Metodo para remocao de valor de determinado indice
    public void remove(int position) {

        try {

            if (position >= 0 && position < this.length) {

                if (this.array[position] != 0) {

                    this.array[position] = 0;
                    this.n--;

                } else System.out.println("Erro: Posicao ja vazia.");

            } 

        } catch(ArrayIndexOutOfBoundsException e) {}

    }

    // Metodo para exibir arranjo na tela
    public void print() {

        if (this.array != null && this.length > 0) {

            for (int x = 0; x < this.length; x = x + 1) {

                System.out.print(this.array[x] + " ");

            }

            System.out.println();

        } else System.out.println("Erro: Arranjo vazio ou nulo");

    }

}
