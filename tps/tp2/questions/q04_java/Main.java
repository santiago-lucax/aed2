/*
 * Nome: Lucas Santiago Pereira
 * Matricula: 897498
*/

// biblioteca padrão para leitura
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/*
 * classe para armazenar métodos e funções úteis para o projeto
*/
class Utils {
    // função estática para identificar se caractere é um inteiro
    public static boolean isNum(final char c) {
        boolean ok = false;
        if (c >= '0' && c <= '9') ok = true; // verificar se é um número
        return ok;
    }

    // função estática para tranformar string em um inteiro
    public static int toInt(String s) {
        if (s == null || s.length() == 0) throw new IllegalArgumentException("String vazia.");

        boolean isNegative = false; // variável para identificar se número é ou não negativo
        int acumulado = 0; // variável do valor númerico da string
        int i = 0;

        if (s.charAt(0) == '-') { // identificar se é positivo ou negativo
            isNegative = true;
            i = 1; // começa a ler depois do sinal
        }

        // laço para transformar string em um número inteiro
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNum(c)) {
                acumulado = (acumulado * 10) + (c - '0'); // transformar caractere em seu valor inteiro através da tabela ascii
            } else throw new IllegalArgumentException("Número inválido."); // enviar exceção caso encontre algum caractere não númerico
        }

        if (isNegative) acumulado = (-1) * acumulado; // se for negativo, multiplicar por -1

        return acumulado; // retornar valor inteiro
    }

    // função estática para transformar string em um double
    public static double toDouble(String s) {
        if (s == null || s.length() == 0) throw new IllegalArgumentException("String vazia.");

        boolean isNegative = false;
        double acumulado = 0.0; // variavel do valor númerico da string
        double divisor = 1.0; // usado para calcular as casas decimais
        boolean passouPonto = false; // flag para rastrear se já encontramos o separador
        int i = 0;

        if (s.charAt(0) == '-') { // identificar se é positivo ou negativo
            isNegative = true;
            i = 1; // começa a ler depois do sinal
        }

        // laço para transformar string em um número decimal
        for (; i < s.length(); i++) {
            char c = s.charAt(i);

            // é aceito tanto ponto quanto vírgula como separador
            if (c == '.' || c == ',') {
                if (passouPonto) {
                    throw new IllegalArgumentException("Formato inválido: múltiplos separadores decimais.");
                }
                passouPonto = true; // ativa a leitura de casas decimais
            } 
            else if (isNum(c)) {
                if (!passouPonto) {
                    // se ainda não passou do ponto, é a parte inteira
                    acumulado = (acumulado * 10) + (c - '0');
                } else {
                    // se já passou do ponto, é a parte fracionária
                    divisor *= 10; // O divisor cresce: 10, 100, 1000...
                    acumulado = acumulado + ((c - '0') / divisor);
                }
            } else {
                throw new IllegalArgumentException("Número inválido: caractere não numérico.");
            }
        }

        if (isNegative) acumulado = (-1) * acumulado;

        return acumulado;
    }

    // função para dividir uma String em substrings
    public static String[] splitLine(String texto, char delimitador) {
        int cnt = 0; // quantidade de partes
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == delimitador) cnt++; // toda vez que encontrar o delimitador, cnt++
        }

        String[] partes = new String[cnt + 1]; // número de partes é igual quanitdade de delimitadores +1

        int indiceArray = 0;
        int inicioCorte = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == delimitador) {
                partes[indiceArray] = extrairSubString(texto, inicioCorte, i);
                indiceArray++;
                inicioCorte = i + 1;
            }
        }

        // adicionar última substring
        partes[indiceArray] = extrairSubString(texto, inicioCorte, texto.length());

        return partes;
    }

    // função para extrair as subtrings
    public static String extrairSubString(String str, int inicio, int fim) {
        char[] novoArray = new char[fim - inicio];
        int j = 0;
        for (int i = inicio; i < fim; i++) {
            novoArray[j] = str.charAt(i);
            j++;
        }

        return new String(novoArray);
    }

    // função para contar quantidade de ocorrencia de determinado caractere na string
    public static int contadorCaractere(String str, char c) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) cnt++;
        }
        return cnt;
    }

    // função para comparar se duas Strings são idênticas
    public static boolean isEquals(String s1, String s2) {
        boolean ok = true;
        if (s1.length() != s2.length()) ok = false;
        else {
            int length = s1.length();
            for (int i = 0; i < length; i++) {
                if (s1.charAt(i) != s2.charAt(i)) ok = false;
            }
        }

        return ok;
    }

    // função para ler uma string e identificar se nela está escrito true ou false
    public static boolean toBool(String str) {
        boolean ok;
        // tratamento para strings para evitar erros com espaços em branco extras do csv
        if (isEquals(str.trim(), "true")) ok = true;
        else if (isEquals(str.trim(), "false")) ok = false;
        else throw new IllegalArgumentException("String não pode ser convertida para boolean.");
        return ok;
    }
} // fim Utils

/*
 * classe para definir formato adequado de data
*/ 
class Data {
    // atributos privados
    private int ano;
    private int mes;
    private int dia;

    // construtor vazio com chamada para construtor com parâmetros
    public Data() {
        this(1, 1, 1);
    }

    // construtor com parâmetros
    public Data(int dia, int mes, int ano) {
        // a ordem importa: setar ano e mes antes do dia para a validação funcionar
        setAno(ano);
        setMes(mes);
        setDia(dia);
    }

    // construtor com parâmetro que inicializa com string
    public Data(String dataStr) {
        parseData(dataStr);
    }

    // get e set para ano
    public int getAno() { return this.ano; }
    public void setAno(int ano) {
        if (ano < 0) throw new IllegalArgumentException("Ano inválido.");
        this.ano = ano;
    }

    // get e set para mes
    public int getMes() { return this.mes; }
    public void setMes(int mes) {
        if (mes < 1 || mes > 12) throw new IllegalArgumentException("Mês inválido.");
        this.mes = mes;
    }

    // get e set para dia com validação bruta de calendário
    public int getDia() { return this.dia; }
    public void setDia(int dia) {
        int maxDias = 31; // padrão para a maioria dos meses

        // define o limite de dias com base no mês atual
        if (this.mes == 4 || this.mes == 6 || this.mes == 9 || this.mes == 11) {
            maxDias = 30;
        } else if (this.mes == 2) {
            // verifica se o ano é bissexto (divisível por 4, mas não por 100, exceto se por 400)
            boolean isBissexto = (this.ano % 4 == 0 && this.ano % 100 != 0) || (this.ano % 400 == 0);
            if (isBissexto) {
                maxDias = 29;
            } else {
                maxDias = 28;
            }
        }

        if (dia < 1 || dia > maxDias) {
            throw new IllegalArgumentException("Dia inválido para o mês e ano informados.");
        }
        this.dia = dia;
    }

    // método para ler a string e adaptar a estrutura da classe
    public void parseData(String dataStr) {
        int tamanho = dataStr.length();
        int i = 0;
        int parteAtual = 0; // 0 = ano, 1 = mês, 2 = dia
        int acumulado = 0; // variável que monta o número direto

        // enquanto existir caracteres
        while (i < tamanho) {
            char c = dataStr.charAt(i); // copia temporária

            if (Utils.isNum(c)) {
                acumulado = (acumulado * 10) + (c - '0'); // transformar caractere numérico através da tabela ascii
            } else if (c == '-') {
                // divisão entre ano, mes e dia
                if (parteAtual == 0) {
                    setAno(acumulado);
                } else if (parteAtual == 1) {
                    setMes(acumulado);
                }
                
                acumulado = 0; // zera o acumulador para a próxima parte
                parteAtual++;
            } else {
                throw new IllegalArgumentException("Caractere inválido: " + c); // caso data não seja valída
            }
            
            i++;
        }

        // definir dia com o valor que restou no acumulador no fim da string
        if (parteAtual == 2) {
            setDia(acumulado);
        }
    }

    // função que retorna data no formato: dd/mm/yyyy
    public String formatar() {
        String dia = getDia() < 10 ? "0" + getDia() : "" + getDia(); // formato para dia
        String mes = getMes() < 10 ? "0" + getMes() : "" + getMes(); // formato para mes
        String ano = getAno() < 10 ? "0" + getAno() : "" + getAno(); // formato para ano

        String format = dia + "/" + mes + "/" + ano; // string formatada
        return format; // retorno
    }
} // fim Data

/*
 * classe para definir formato adequado de horas e minutos
*/ 
class Hora {
    // atributos privados
    private int hora;
    private int minuto;

    // contrutor sem parâmetros
    public Hora() {
        this(0, 0); // chamada para construtor com parâmetros
    }

    // construtor com parâmetros
    public Hora(int hora, int minuto) {
        setHora(hora);
        setMin(minuto);
    }

    // construtor que inicializa com string
    public Hora(String horaStr) {
        parseHora(horaStr);
    }

    // get e set para horas
    public int getHora() { return this.hora; }
    public void setHora(int hora) {
        if (hora < 0 || hora > 23) throw new IllegalArgumentException("Hora inválida.");
        this.hora = hora;
    }

    // get e set para minutos
    public int getMin() { return this.minuto; }
    public void setMin(int minuto) {
        if (minuto < 0 || minuto > 59) throw new IllegalArgumentException("Minuto inválido.");
        this.minuto = minuto;
    }

    // método para ler a string e adaptar a estrutura da classe
    public void parseHora(String horaStr) {
        int tamanho = horaStr.length();
        int i = 0;
        int parteAtual = 0; // 0 = horas, 1 = minutos
        int acumulado = 0; // variável que monta o número direto

        while (i < tamanho) {
            char c = horaStr.charAt(i);

            if (Utils.isNum(c)) {
                acumulado = (acumulado * 10) + (c - '0'); // transformar caractere numérico através da tabela ascii
            } else if (c == ':') {
                if (parteAtual == 0) {
                    setHora(acumulado);
                    acumulado = 0; // zera o acumulador para ler os minutos
                    parteAtual++;
                } else {
                    throw new IllegalArgumentException("Formato de hora inválido (muitos ':')");
                }
            } else {
                throw new IllegalArgumentException("Caractere inválido: " + c);
            }

            i++;

        }
        // salvar o que sobrou no acumulador após o fim do laço
        if (parteAtual == 0) {
            setHora(acumulado); // caso a string tenha apenas a hora (ex: "11")
            setMin(0); 
        } else {
            setMin(acumulado);
        }
    }

    // função que retorna hora no formato: HH:mm
    public String formatar() {
        String hora = getHora() < 10 ? "0" + getHora() : "" + getHora(); // formato para hora
        String minuto = getMin() < 10 ? "0" + getMin() : "" + getMin(); // formato para minuto

        String format = hora + ":" + minuto; // string formatada
        return format; // retorno
    }
} // fim Hora

/*
 * classe para definir restaurante
*/
class Restaurante {
    // atributos privados
    private int id; // identificador
    private String nome; // nome do restaurante
    private String cidade; // cidade onde se localiza o restaurante
    private int capacidade; // capacidade do restaurante
    private double avaliacao; // avaliacao $
    private String[] tiposCozinha; // tipos de pratos
    private int faixaPreco; // faixa de precos
    private Hora horarioAbertura; // abertura
    private Hora horarioFechamento; // fechamento
    private Data dataAbertura; // data de abertura
    private boolean aberto; // status de aberto ou fechado

    // construtor sem parâmetros
    public Restaurante() {
        this(
            1, // ID válido (maior que 0)
            "Nome Padrão", 
            "Cidade Padrão", 
            0, // capacidade pode ser 0
            0.0, // avaliação pode ser 0.0
            new String[]{"Não definido"}, // passando um Array de Strings válido
            1, // Faixa de preço válida (entre 1 e 4)
            new Hora(0, 0), // hora aceita zeros
            new Hora(0, 0), 
            new Data(1, 1, 1), // dia e mês devem começar em 1
            false // fechado
        );
    }

    // construtor com parâmetros
    public Restaurante(int id, String nome, String cidade, int capacidade, 
                       double avaliacao, String[] tiposCozinha, int faixaPreco, 
                       Hora horarioAbertura, Hora horarioFechamento, 
                       Data dataAbertura, boolean aberto) {
        
        setId(id); // definir id
        setNome(nome); // definir nome
        setCidade(cidade); // definir cidade
        setCapacidade(capacidade); // definir capacidade
        setAvaliacao(avaliacao); // definir avaliacao
        setTiposCozinha(tiposCozinha); // definir tipos de pratos
        setFaixaPreco(faixaPreco); // definir faixa de preco
        setHorarioAbertura(horarioAbertura); // definir horario de abertura
        setHorarioFechamento(horarioFechamento); // definir horario de fechamento
        setDataAbertura(dataAbertura); // definir data de abertura
        setAberto(aberto); // definir status 
        
    }

    // get e set do identificador
    public int getId() { return this.id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser maior que zero.");
        this.id = id;
    }

    // get e set do nome 
    public String getNome() { return this.nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    // get e set para cidade
    public String getCidade() { return this.cidade; }
    public void setCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A cidade não pode ser nula ou vazia.");
        }
        this.cidade = cidade;
    }

    // get e set para capacidade
    public int getCapacidade() { return this.capacidade; }
    public void setCapacidade(int capacidade) {
        if (capacidade < 0) throw new IllegalArgumentException("A capacidade não pode ser negativa.");
        this.capacidade = capacidade;
    }

    // get e set para avaliacao
    public double getAvaliacao() { return this.avaliacao; }
    public void setAvaliacao(double avaliacao) {
        if (avaliacao < 0.0 || avaliacao > 5.0) {
            throw new IllegalArgumentException("A avaliação deve estar entre 0.0 e 5.0.");
        }
        this.avaliacao = avaliacao;
    }

    // get e set para faixa de precos
    public int getFaixaDePreco() { return this.faixaPreco; }
    public void setFaixaPreco(int faixaPreco) {
        if (faixaPreco < 1 || faixaPreco > 4) {
            throw new IllegalArgumentException("A faixa de preço deve estar entre 1 e 4.");
        }
        this.faixaPreco = faixaPreco;
    }

    // get e set para tipos de pratos
    public String[] getTipos() { return this.tiposCozinha; }
    public void setTiposCozinha(String[] tiposCozinha) {
        if (tiposCozinha == null || tiposCozinha.length == 0) {
            throw new IllegalArgumentException("O restaurante deve ter pelo menos um tipo de cozinha.");
        }
        this.tiposCozinha = tiposCozinha;
    }

    // get e set de horario de abertura
    public Hora getHorarioAbertura() { return this.horarioAbertura; }
    public void setHorarioAbertura(Hora horarioAbertura) {
        if (horarioAbertura == null) throw new IllegalArgumentException("Horário de abertura não pode ser nulo.");
        this.horarioAbertura = horarioAbertura;
    }

    // get e set de horario de fechamento
    public Hora getHorarioFechamento() { return this.horarioFechamento; }
    public void setHorarioFechamento(Hora horarioFechamento) {
        if (horarioFechamento == null) throw new IllegalArgumentException("Horário de fechamento não pode ser nulo.");
        this.horarioFechamento = horarioFechamento;
    }

    // get e set para data de abertura
    public Data getDataAbertura() { return this.dataAbertura; }
    public void setDataAbertura(Data dataAbertura) {
        if (dataAbertura == null) throw new IllegalArgumentException("Data de abertura não pode ser nula.");
        this.dataAbertura = dataAbertura;
    }

    // get e set para status
    public boolean getAberto() { return this.aberto; }
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    // método para ler a string e adaptar a estrutura da classe
    public void parseRestaurante(String restauranteStr) {
        String[] partes = Utils.splitLine(restauranteStr, ','); // dividir string lida em substrings e atribuí-las
        String[] tmp = Utils.splitLine(partes[7], '-'); // horario de abertura e fechamento
        
        setId(Utils.toInt(partes[0])); // posição 0: id
        setNome(partes[1]); // posição 1: nome
        setCidade(partes[2]); // posição 2: cidade
        setCapacidade(Utils.toInt(partes[3])); // posição 3: capacidade
        setAvaliacao(Utils.toDouble(partes[4])); // posição 4: avaliação
        setTiposCozinha(Utils.splitLine(partes[5], ';')); // posição 5: tipos (separados por ponto e vírgula)
        setFaixaPreco(Utils.contadorCaractere(partes[6], '$')); // posição 6: quantidade de cifrões
        setHorarioAbertura(new Hora(tmp[0])); // primeira metade do horário
        setHorarioFechamento(new Hora(tmp[1])); // segunda metade do horário
        setDataAbertura(new Data(partes[8])); // posição 8: data
        setAberto(Utils.toBool(partes[9])); // posição 9: status booleano
    }

    // função para formatar e exibir dados do restaurante em uma string
    public String formatar() {
        // ler tipos e atribuí-los a uma string temporária
        String tipos = "";
        for (int i = 0; i < tiposCozinha.length; i++) {
            if (i < tiposCozinha.length - 1) tipos += tiposCozinha[i] + ",";
            else tipos += tiposCozinha[i]; // último elemento não recebe vírgula no final
        }

        // contar quantos cifrões representam a faixa de preço
        String cifroes = "";
        for (int i = 0; i < getFaixaDePreco(); i++) cifroes += '$';

        // formatar de acordo com a saída esperada (ex: pub.out)
        String tmp = "["
            + getId() + " ## "
            + getNome() + " ## "
            + getCidade() + " ## "
            + getCapacidade() + " ## "
            + getAvaliacao() + " ## "
            + "[" + tipos + "]" + " ## "
            + cifroes + " ## "
            + getHorarioAbertura().formatar() + "-" + getHorarioFechamento().formatar() + " ## "
            + getDataAbertura().formatar() + " ## "
            + getAberto() +
        "]";

        return tmp; // retornar string temporária
    }
}

/*
 * classe para gerenciar um arranjo de restaurantes lidos de um arquivo csv
*/
class ColecaoRestaurantes {
    // atributos privados
    private int tamanho; // quantidade de restaurantes da coleção
    private Restaurante[] restaurantes; // arranjo com os restaurantes

    // construtor sem parâmetros
    public ColecaoRestaurantes() {
        this.tamanho = 0;
        this.restaurantes = new Restaurante[0];
    }

    // get para o tamanho da coleção
    public int getTamanho() { return this.tamanho; }

    // get para o arranjo de restaurantes
    public Restaurante[] getRestaurantes() { return this.restaurantes; }

    // lê o arquivo CSV, cria os restaurantes e configura a coleção
    public void lerCsv(String path) {
        try {
            File arquivo = new File(path);
            
            // --- primeiro passo: contar as linhas válidas para redimensionar o arranjo ---
            Scanner contador = new Scanner(arquivo);
            int quantidadeLinhas = 0;
            
            // pula a primeira linha (cabeçalho) se ela existir
            if (contador.hasNextLine()) contador.nextLine(); 
            
            // laço para contar quantas linhas de dados existem
            while (contador.hasNextLine()) {
                String linhaAtual = contador.nextLine();
                // conta apenas se a linha não for vazia (evita posições nulas no arranjo)
                if (linhaAtual.trim().length() > 0) {
                    quantidadeLinhas++;
                }
            }
            contador.close(); // fecha o leitor após contar
            
            // define o tamanho da coleção e cria o arranjo com o tamanho exato
            this.tamanho = quantidadeLinhas;
            this.restaurantes = new Restaurante[this.tamanho];
            
            // --- segundo passo: ler os dados e preencher o arranjo ---
            Scanner leitor = new Scanner(arquivo);
            
            // pula o cabeçalho novamente na segunda leitura
            if (leitor.hasNextLine()) leitor.nextLine(); 
            
            int i = 0; // controlador da posição no arranjo
            
            // laço para percorrer os dados
            while (leitor.hasNextLine()) {
                String linhaAtual = leitor.nextLine();
                
                // validação simples para ignorar linhas vazias e evitar erros
                if (linhaAtual.trim().length() > 0) {
                    Restaurante r = new Restaurante(); // instancia novo restaurante
                    r.parseRestaurante(linhaAtual); // utiliza sua função para formatar o objeto
                    this.restaurantes[i] = r; // guarda no arranjo
                    i++; // avança a posição
                }
            }
            leitor.close(); // fecha o leitor de dados
            
        } catch (Exception e) {
            // envia mensagem caso ocorra algum erro (ex: arquivo não encontrado)
            System.out.println("Erro na leitura do arquivo: " + e.getMessage());
        }
    }

    // lê o dataset do arquivo CSV padrão e retorna a coleção com os restaurantes
    public static ColecaoRestaurantes lerCsv() {
        ColecaoRestaurantes colecao = new ColecaoRestaurantes();
        colecao.lerCsv("/tmp/restaurantes.csv"); // chama o método da instância apontando para o arquivo no verde
        return colecao; // retorna a coleção preenchida
    }
} // fim ColecaoRestaurantes

/*
 * classe principal para execução do programa e leitura das entradas
*/
public class Main {
    // variáveis globais para registro de métricas de desempenho
    public static int comparacoes = 0;
    public static int movimentacoes = 0;

    /*
     * algoritmo de ordenação por inserção
     * ordena o arranjo de restaurantes pelo atributo cidade
    */
    public static void insercao(Restaurante[] arr, int n) {
        for (int i = 1; i < n; i++) {
            Restaurante tmp = arr[i];
            int j = i - 1;
            boolean continuar = true; // flag para controle do laço sem usar break

            // laço para encontrar a posição correta de inserção
            while (j >= 0 && continuar) {
                comparacoes++; // incrementar contador de comparações entre chaves (cidade)
                
                if (arr[j].getCidade().compareTo(tmp.getCidade()) > 0) {
                    arr[j + 1] = arr[j]; // movimentar o elemento para a direita
                    movimentacoes++;
                    j--;
                } else {
                    continuar = false; // encerra o laço preservando o valor correto de 'j'
                }
            }
            arr[j + 1] = tmp; // inserir o elemento na posição correta
            movimentacoes += 2; // contabiliza a ida para tmp e o retorno para o arranjo
        }
    }

    /*
     * método principal para execução do programa e leitura das entradas
    */
    public static void main(String[] args) {
        // carregar os dados do arquivo csv para a memória
        ColecaoRestaurantes colecaoTotal = ColecaoRestaurantes.lerCsv();
        Restaurante[] restaurantesCsv = colecaoTotal.getRestaurantes();
        int tamanhoTotal = colecaoTotal.getTamanho();

        Restaurante[] selecionados = new Restaurante[1000]; // arranjo para armazenar os pesquisados
        int n = 0;
        Scanner sc = new Scanner(System.in);
        boolean continuarLeitura = true; // flag para controle do laço principal

        // laço para ler os IDs da entrada padrão e filtrar do dataset original
        while (continuarLeitura && sc.hasNextLine()) {
            String linhaEntrada = sc.nextLine().trim();

            if (linhaEntrada.length() > 0) {
                int idBuscado = Utils.toInt(linhaEntrada);

                if (idBuscado == -1) {
                    continuarLeitura = false; // flag para encerrar a leitura conforme pub.in
                } else {
                    boolean encontrou = false; // flag para controle da busca linear

                    // busca linear para selecionar o restaurante e colocar no array de ordenação
                    for (int i = 0; i < tamanhoTotal && !encontrou; i++) {
                        if (restaurantesCsv[i] != null && restaurantesCsv[i].getId() == idBuscado) {
                            selecionados[n++] = restaurantesCsv[i];
                            encontrou = true; // encerra a iteração do for graças a flag
                        }
                    }
                }
            }
        }

        // marcar o tempo inicial para o log
        long inicio = System.currentTimeMillis();

        // executar a ordenação por inserção utilizando o atributo cidade
        insercao(selecionados, n);

        // marcar o tempo final
        long fim = System.currentTimeMillis();
        long tempoTotal = fim - inicio;

        // exibir os registros ordenados na saída padrão conforme o formato
        for (int i = 0; i < n; i++) {
            System.out.println(selecionados[i].formatar());
        }

        // geração do arquivo de log com as métricas da execução
        try {
            PrintWriter log = new PrintWriter(new File("897498_insercao.txt"));
            // formato: matrícula [tab] comparações [tab] movimentações [tab] tempo
            log.printf("897498\t%d\t%d\t%d\n", comparacoes, movimentacoes, tempoTotal);
            log.close(); // fechar o escritor
        } catch (Exception e) {
            System.out.println("Erro ao criar arquivo de log.");
        }

        sc.close(); // fechar scanner
    }
}
