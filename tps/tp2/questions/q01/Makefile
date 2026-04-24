# COMO RODAR
# C: make run_c PROG=nome_do_seu_arquivo_c_sem_extensao
# JAVA: make run_java MAIN=NomeDaSuaClassePrincipal

# Variáveis de Compiladores
CC = gcc
CFLAGS = -Wall -g
JC = javac
JVM = java

# Diretórios
BIN_DIR = bin
SRC_DIR = src

# Arquivos C
C_SOURCES = $(wildcard $(SRC_DIR)/*.c)
C_EXECUTABLES = $(patsubst $(SRC_DIR)/%.c, $(BIN_DIR)/%, $(C_SOURCES))

# Arquivos Java
JAVA_SOURCES = $(wildcard $(SRC_DIR)/*.java)
JAVA_CLASSES = $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(JAVA_SOURCES))

# Alvo principal (compila tudo)
all: create_bin $(C_EXECUTABLES) $(JAVA_CLASSES)

# Cria a pasta bin se não existir
create_bin:
	mkdir -p $(BIN_DIR)

# Regra para compilar C
$(BIN_DIR)/%: $(SRC_DIR)/%.c
	$(CC) $(CFLAGS) $< -o $@

# Regra para compilar Java
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JC) -d $(BIN_DIR) $<

# Limpeza
clean:
	rm -rf $(BIN_DIR)

# Ajuda para rodar (Exemplo: make run_java MAIN=Main)
run_java:
	$(JVM) -cp $(BIN_DIR) $(MAIN)

run_c:
	./$(BIN_DIR)/$(PROG)
