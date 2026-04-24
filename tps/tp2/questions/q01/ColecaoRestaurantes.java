// Biblioteca padrão para leitura
import java.util.Scanner;

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

    /*ADICIONAR UM METODO SPLIT PARA SEPARAR AS LINHAS DO CSV!!!*/

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
    public Data setDataAbertura() { return this.dataAbertura; }
    public void setDataAbertura(Data dataAbertura) {
        if (dataAbertura == null) throw new IllegalArgumentException("Data de abertura não pode ser nula.");
        this.dataAbertura = dataAbertura;
    }

    // get e set para status
    public boolean getStatus() { return this.aberto; }
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    // método para ler a string e adaptar a estrutura da classe
    public void parseRestaurante(String restauranteStr) {
        int tamanho = restauranteStr.length();
        int i = 0;
        while (i < tamanho) {

        }
    }
}

public class ColecaoRestaurantes {
    public static void main(String[] args) {
        Data data = new Data(19, 11, 2006);
        String tmp = data.formatar();
        System.out.println(tmp);

        Hora hora = new Hora(04, 30);
        String temp = hora.formatar();
        System.out.println(temp);

        Restaurante restaurante = new Restaurante();
        
    }
} // Fim main
