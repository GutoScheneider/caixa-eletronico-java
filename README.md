# Sistema de Caixa Eletrônico (Java - Orientado a Objetos)
# Descrição
Este projeto simula um sistema de caixa eletrônico, desenvolvido em Java puro, seguindo os princípios de orientação a objetos. O sistema permite gerenciar o estoque de cédulas, realizar depósitos, saques e registrar eventos em log, conforme solicitado no teste técnico.

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

# Sugestão Alternativa (Opcional):
Se o valor solicitado não puder ser composto, o sistema sugere o maior valor possível para saque.

# Simulação no Console:
O sistema possui um menu interativo para facilitar testes e avaliação.

# Exemplo de Log
Como Executar
Certifique-se de ter o Java instalado (Java 17 ou superior).
Compile o projeto:
Execute o sistema:
Estrutura do Projeto
src - Código fonte
logs - Arquivo de log dos eventos
README.md - Este arquivo
Observações
Não foram utilizados frameworks ou bibliotecas externas.
O código está limpo, estruturado e comentado.
Nomes de classes, métodos e variáveis são claros e autoexplicativos.