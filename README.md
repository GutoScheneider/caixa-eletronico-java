# Sistema de Caixa Eletrônico (Java - Orientado a Objetos)
# Descrição
Este projeto simula um sistema de caixa eletrônico, desenvolvido em Java puro, seguindo os princípios de orientação a objetos. O sistema permite gerenciar o estoque de cédulas, realizar depósitos, saques e registrar eventos em log.

# Funcionalidades
Estoque de Cédulas:
O sistema armazena o valor disponível no caixa e permite consultar o saldo total.

# Depósitos:
É possível realizar depósitos de cédulas, atualizando o estoque e registrando o evento no log.

# Saques:
O saque só é permitido se for possível compor o valor exato com as cédulas disponíveis, sempre utilizando a menor quantidade possível de cédulas.
Caso não seja possível realizar o saque, uma mensagem clara é exibida e registrada no log.

# Notificações e Log:
Eventos como depósito realizado, saque realizado com sucesso e saque recusado são registrados em um arquivo de texto (log-caixa.txt), contendo data, hora e mensagem detalhada.

# Simulação no Console:
O sistema possui um menu interativo para facilitar testes e avaliação.

# Como Executar

# Requisitos

- **Java JDK 17** ou superior
- Terminal (cmd, PowerShell, bash, etc.)
- Editor de texto ou IDE (VS Code, IntelliJ, Eclipse, etc.)

## Instalação do Java
Se não possuir o Java instalado, baixe e instale a versão mais recente do JDK em:
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- [OpenJDK](https://adoptium.net/pt/temurin/releases/)

Para verificar se o Java está instalado e a versão:
```bash
java -version
```
O resultado deve ser igual ou superior a `17`.


## Compilação
Abra o terminal na pasta raiz do projeto e execute:

```bash
javac -d bin src/**/*.java
```

## Execução
Após compilar, execute o sistema com:

```bash
java -cp bin src.Main
```