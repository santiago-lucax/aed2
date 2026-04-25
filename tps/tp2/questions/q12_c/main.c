/*
 * Nome: Lucas Santiago Pereira
 * Matricula: 897498
*/

// bibliotecas padrao permitidas
#include <stdio.h>
#include <stdlib.h> // necessario para malloc/free e substituicao do 'new'
#include <stdbool.h>
#include <string.h> // necessario para strcmp e strcat

/*
 * funcoes para armazenar metodos uteis para o projeto
 * (equivalente a classe utils em java)
*/

// função para identificar se caractere é um inteiro
bool is_num(char c) {
    bool ok = false;
    if (c >= '0' && c <= '9') ok = true; // verificar se e um numero
    return ok;
}

// função auxiliar para remover espacos em branco das pontas (equivalente ao .trim())
void trim(char *str) {
    int inicio = 0;
    while (str[inicio] == ' ' || str[inicio] == '\t' || str[inicio] == '\r' || str[inicio] == '\n') {
        inicio++;
    }
    int fim = strlen(str) - 1;
    while (fim >= inicio && (str[fim] == ' ' || str[fim] == '\t' || str[fim] == '\r' || str[fim] == '\n')) {
        fim--;
    }
    int i;
    for (i = 0; i <= fim - inicio; i++) {
        str[i] = str[inicio + i];
    }
    str[i] = '\0';
}

// função para tranformar string em um inteiro
int to_int(char *s) {
    if (s == NULL || strlen(s) == 0) return 0; // fallback para c

    bool is_negative = false; // variavel para identificar se numero e ou nao negativo
    int acumulado = 0; // variavel do valor numerico da string
    int i = 0;

    if (s[0] == '-') { // identificar se e positivo ou negativo
        is_negative = true;
        i = 1; // comeca a ler depois do sinal
    }

    // laco para transformar string em um numero inteiro
    for (; i < strlen(s); i++) {
        char c = s[i];
        if (is_num(c)) {
            acumulado = (acumulado * 10) + (c - '0'); // transformar caractere em seu valor inteiro através da tabela ascii
        }
    }

    if (is_negative) acumulado = (-1) * acumulado; // se for negativo, multiplicar por -1

    return acumulado; // retornar valor inteiro
}

// função para transformar string em um double
double to_double(char *s) {
    if (s == NULL || strlen(s) == 0) return 0.0;

    bool is_negative = false;
    double acumulado = 0.0; // variavel do valor numerico da string
    double divisor = 1.0; // usado para calcular as casas decimais
    bool passou_ponto = false; // flag para rastrear se ja encontramos o separador
    int i = 0;

    if (s[0] == '-') { // identificar se e positivo ou negativo
        is_negative = true;
        i = 1; // comeca a ler depois do sinal
    }

    // laco para transformar string em um numero decimal
    for (; i < strlen(s); i++) {
        char c = s[i];

        // e aceito tanto ponto quanto virgula como separador
        if (c == '.' || c == ',') {
            passou_ponto = true; // ativa a leitura de casas decimais
        } 
        else if (is_num(c)) {
            if (!passou_ponto) {
                // se ainda nao passou do ponto, e a parte inteira
                acumulado = (acumulado * 10) + (c - '0');
            } else {
                // se ja passou do ponto, e a parte fracionaria
                divisor *= 10; // o divisor cresce: 10, 100, 1000...
                acumulado = acumulado + ((c - '0') / divisor);
            }
        }
    }

    if (is_negative) acumulado = (-1) * acumulado;

    return acumulado;
}

// função para contar quantidade de ocorrencia de determinado caractere na string
int contador_caractere(char *str, char c) {
    int cnt = 0;
    for (int i = 0; i < strlen(str); i++) {
        if (str[i] == c) cnt++;
    }
    return cnt;
}

// função para dividir uma string em substrings preenchendo uma matriz previamente criada
int split_line(char *texto, char delimitador, char partes[][300]) {
    int cnt = 0;
    int j = 0;
    for (int i = 0; i < strlen(texto); i++) {
        if (texto[i] == delimitador) {
            partes[cnt][j] = '\0'; // finaliza a string atual
            cnt++;
            j = 0; // zera o indice da proxima string
        } else {
            partes[cnt][j++] = texto[i];
        }
    }
    partes[cnt][j] = '\0'; // finaliza a ultima substring
    return cnt + 1; // retorna a quantidade de partes
}

// função para converter string em boolean (utilizando strcmp permitido)
bool to_bool(char *str) {
    char copia[100];
    strcpy(copia, str);
    trim(copia);
    if (strcmp(copia, "true") == 0) return true;
    return false;
}

/*
 * struct para definir formato adequado de data
*/ 
typedef struct {
    int ano;
    int mes;
    int dia;
} Data;

// metodo para ler a string e adaptar a estrutura da struct
void parse_data(Data *d, char *data_str) {
    int tamanho = strlen(data_str);
    int i = 0;
    int parte_atual = 0; // 0 = ano, 1 = mes, 2 = dia
    int acumulado = 0; // variavel que monta o numero direto

    // enquanto existir caracteres
    while (i < tamanho) {
        char c = data_str[i]; // copia temporaria

        if (is_num(c)) {
            acumulado = (acumulado * 10) + (c - '0'); // transformar caractere numerico atraves da tabela ascii
        } else if (c == '-') {
            // divisao entre ano, mes e dia
            if (parte_atual == 0) {
                d->ano = acumulado;
            } else if (parte_atual == 1) {
                d->mes = acumulado;
            }
            
            acumulado = 0; // zera o acumulador para a proxima parte
            parte_atual++;
        }
        
        i++;
    }

    // definir dia com o valor que restou no acumulador no fim da string
    if (parte_atual == 2) {
        d->dia = acumulado;
    }
}

// função que formata data para dd/mm/yyyy em um buffer
void formatar_data(Data *d, char *buffer) {
    sprintf(buffer, "%02d/%02d/%04d", d->dia, d->mes, d->ano); // string formatada
} // fim data

/*
 * struct para definir formato adequado de horas e minutos
*/ 
typedef struct {
    int hora;
    int minuto;
} Hora;

// metodo para ler a string e adaptar a estrutura da struct
void parse_hora(Hora *h, char *hora_str) {
    int tamanho = strlen(hora_str);
    int i = 0;
    int parte_atual = 0; // 0 = horas, 1 = minutos
    int acumulado = 0; // variavel que monta o numero direto

    while (i < tamanho) {
        char c = hora_str[i];

        if (is_num(c)) {
            acumulado = (acumulado * 10) + (c - '0');
        } else if (c == ':') {
            if (parte_atual == 0) {
                h->hora = acumulado;
                acumulado = 0; // zera o acumulador para ler os minutos
                parte_atual++;
            }
        }
        i++;
    }
    // salvar o que sobrou no acumulador apos o fim do laco
    if (parte_atual == 0) {
        h->hora = acumulado; // caso a string tenha apenas a hora (ex: "11")
        h->minuto = 0; 
    } else {
        h->minuto = acumulado;
    }
}

// função que formata hora para HH:mm em um buffer
void formatar_hora(Hora *h, char *buffer) {
    sprintf(buffer, "%02d:%02d", h->hora, h->minuto); // string formatada
} // fim hora

/*
 * struct para definir restaurante
*/
typedef struct {
    int id; // identificador
    char nome[300]; // nome do restaurante
    char cidade[300]; // cidade onde se localiza o restaurante
    int capacidade; // capacidade do restaurante
    double avaliacao; // avaliacao
    char tipos_cozinha[20][300]; // CORRECAO: o tamanho [300] garante a aritmetica correta de ponteiros na funcao split_line
    int num_tipos; // quantidade de tipos
    int faixa_preco; // faixa de precos
    Hora horario_abertura; // abertura
    Hora horario_fechamento; // fechamento
    Data data_abertura; // data de abertura
    bool aberto; // status de aberto ou fechado
} Restaurante;

// metodo para ler a string e adaptar a estrutura da struct
void parse_restaurante(Restaurante *r, char *restaurante_str) {
    char partes[10][300]; // vetor de strings local
    
    // dividir string lida em substrings e atribui-las
    split_line(restaurante_str, ',', partes); 
    
    // horario de abertura e fechamento
    char tmp[2][300]; 
    split_line(partes[7], '-', tmp); 
    
    r->id = to_int(partes[0]); // posicao 0: id
    strcpy(r->nome, partes[1]); // posicao 1: nome
    strcpy(r->cidade, partes[2]); // posicao 2: cidade
    r->capacidade = to_int(partes[3]); // posicao 3: capacidade
    r->avaliacao = to_double(partes[4]); // posicao 4: avaliacao
    
    // posicao 5: tipos (separados por ponto e virgula)
    r->num_tipos = split_line(partes[5], ';', r->tipos_cozinha); 
    
    r->faixa_preco = contador_caractere(partes[6], '$'); // posicao 6: quantidade de cifroes
    
    parse_hora(&(r->horario_abertura), tmp[0]); // primeira metade do horario
    parse_hora(&(r->horario_fechamento), tmp[1]); // segunda metade do horario
    
    parse_data(&(r->data_abertura), partes[8]); // posicao 8: data
    r->aberto = to_bool(partes[9]); // posicao 9: status booleano
}

// função para formatar e exibir dados do restaurante em uma string
void formatar_restaurante(Restaurante *r, char *buffer) {
    // concatenar tipos de cozinha manualmente
    char tipos_str[1000] = ""; // buffer aumentado para garantir seguranca
    for (int i = 0; i < r->num_tipos; i++) {
        strcat(tipos_str, r->tipos_cozinha[i]);
        if (i < r->num_tipos - 1) {
            strcat(tipos_str, ","); // ultimo elemento nao recebe virgula no final
        }
    }

    // contar quantos '$' com base na faixa de preco do restaurante
    char cifroes[5] = "";
    for (int i = 0; i < r->faixa_preco; i++) cifroes[i] = '$';

    // variaveis temporarias para formatar dependencias
    char hora_abertura_str[20];
    char hora_fechamento_str[20];
    char data_abertura_str[20];

    formatar_hora(&(r->horario_abertura), hora_abertura_str);
    formatar_hora(&(r->horario_fechamento), hora_fechamento_str);
    formatar_data(&(r->data_abertura), data_abertura_str);

    // formatar de acordo com a saida esperada via sprintf autorizado
    sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## [%s] ## %s ## %s-%s ## %s ## %s]",
        r->id,
        r->nome,
        r->cidade,
        r->capacidade,
        r->avaliacao,
        tipos_str,
        cifroes,
        hora_abertura_str,
        hora_fechamento_str,
        data_abertura_str,
        r->aberto ? "true" : "false"
    );
} // fim restaurante

/*
 * struct para gerenciar um arranjo de restaurantes lidos de um arquivo csv
*/
typedef struct {
    int tamanho; // quantidade de restaurantes da colecao
    Restaurante *restaurantes; // arranjo dinamico com os restaurantes
} ColecaoRestaurantes;

// lê o arquivo csv, cria os restaurantes e configura a colecao
void ler_csv(ColecaoRestaurantes *colecao, const char *path) {
    FILE *arquivo = fopen(path, "r");
    if (arquivo == NULL) {
        printf("Erro na leitura do arquivo.\n");
        return;
    }

    // --- primeiro passo: contar as linhas validas para redimensionar o arranjo ---
    int quantidade_linhas = 0;
    char linha[2000];

    // pula a primeira linha (cabecalho) se ela existir
    if (fgets(linha, sizeof(linha), arquivo) != NULL) {
        // laco para contar quantas linhas de dados existem
        while (fgets(linha, sizeof(linha), arquivo) != NULL) {
            trim(linha);
            // conta apenas se a linha nao for vazia
            if (strlen(linha) > 0) {
                quantidade_linhas++;
            }
        }
    }

    // define o tamanho da colecao e cria o arranjo com o tamanho exato usando malloc
    colecao->tamanho = quantidade_linhas;
    colecao->restaurantes = (Restaurante *) malloc(quantidade_linhas * sizeof(Restaurante));

    // --- segundo passo: ler os dados e preencher o arranjo ---
    rewind(arquivo); // volta ao inicio do arquivo para segunda leitura

    // pula o cabecalho novamente na segunda leitura
    if (fgets(linha, sizeof(linha), arquivo) != NULL) {
        int i = 0; // controlador da posicao no arranjo
        
        // laco para percorrer os dados
        while (fgets(linha, sizeof(linha), arquivo) != NULL) {
            trim(linha);
            // validacao simples para ignorar linhas vazias e evitar erros
            if (strlen(linha) > 0) {
                // utiliza sua função para formatar o objeto direto na memoria
                parse_restaurante(&(colecao->restaurantes[i]), linha);
                i++; // avanca a posicao
            }
        }
    }

    fclose(arquivo); // fecha o ponteiro de arquivo
} // fim colecao_restaurantes

// definindo o tamanho máximo da pilha
#define MAX_TAMANHO 200

/*
 * estrutura da Pilha Sequencial
*/
typedef struct {
    Restaurante array[MAX_TAMANHO];
    int topo;
} Pilha;

// inicializa a pilha
void iniciar_pilha(Pilha *p) {
    p->topo = 0;
}

// empilha um restaurante no topo
void empilhar(Pilha *p, Restaurante r) {
    if (p->topo < MAX_TAMANHO) {
        p->array[p->topo] = r;
        p->topo++;
    }
}

// desempilha e retorna o restaurante do topo
Restaurante desempilhar(Pilha *p) {
    p->topo--;
    return p->array[p->topo];
}

// mostra os elementos da pilha a partir do topo
void mostrar(Pilha *p) {
    for (int i = p->topo - 1; i >= 0; i--) {
        char buffer_saida[2000];
        formatar_restaurante(&(p->array[i]), buffer_saida);
        printf("%s\n", buffer_saida);
    }
}

/*
 * função auxiliar para buscar e retornar o objeto restaurante por ID
 * de forma segura utilizando a coleção
*/
Restaurante buscar_por_id(ColecaoRestaurantes *colecao, int id_buscado, bool *encontrou_ref) {
    Restaurante r;
    *encontrou_ref = false;
    for (int i = 0; i < colecao->tamanho && !(*encontrou_ref); i++) {
        if (colecao->restaurantes[i].id == id_buscado) {
            r = colecao->restaurantes[i];
            *encontrou_ref = true;
        }
    }
    return r;
}

/*
 * função principal para execução do programa e leitura das entradas
*/
int main() {
    ColecaoRestaurantes colecao_total;
    ler_csv(&colecao_total, "/tmp/restaurantes.csv");

    Pilha pilha;
    iniciar_pilha(&pilha);
    
    char linha_entrada[300];
    bool continuar_leitura = true;

    // --- PRIMEIRA PARTE: popular a pilha ---
    while (continuar_leitura && fgets(linha_entrada, sizeof(linha_entrada), stdin) != NULL) {
        trim(linha_entrada);
        if (strlen(linha_entrada) > 0) {
            if (strcmp(linha_entrada, "FIM") == 0 || to_int(linha_entrada) == -1) {
                continuar_leitura = false; // encerra primeira parte
            } else {
                int id_buscado = to_int(linha_entrada);
                bool encontrou;
                Restaurante r = buscar_por_id(&colecao_total, id_buscado, &encontrou);
                if (encontrou) {
                    empilhar(&pilha, r);
                }
            }
        }
    }

    // --- SEGUNDA PARTE: processamento de comandos de operações ---
    if (fgets(linha_entrada, sizeof(linha_entrada), stdin) != NULL) {
        trim(linha_entrada);
        int qtd_comandos = to_int(linha_entrada);

        for (int k = 0; k < qtd_comandos; k++) {
            if (fgets(linha_entrada, sizeof(linha_entrada), stdin) != NULL) {
                trim(linha_entrada);
                char partes[3][300];
                split_line(linha_entrada, ' ', partes); // separando o comando do id (se houver)

                if (strcmp(partes[0], "I") == 0) {
                    int id_buscado = to_int(partes[1]);
                    bool encontrou;
                    Restaurante r = buscar_por_id(&colecao_total, id_buscado, &encontrou);
                    if (encontrou) {
                        empilhar(&pilha, r);
                    }
                } 
                else if (strcmp(partes[0], "R") == 0) {
                    if (pilha.topo > 0) {
                        Restaurante r = desempilhar(&pilha);
                        printf("(R)%s\n", r.nome);
                    }
                }
            }
        }
    }

    // exibir resultado final mostrando a partir do topo
    mostrar(&pilha);

    free(colecao_total.restaurantes);
    return 0;
}
