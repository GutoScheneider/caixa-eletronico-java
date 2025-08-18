package src;

import src.enums.Cedula;
import src.model.EstoqueCedulas;
import src.interfaces.NotificacaoConsole;
import src.interfaces.NotificacaoArquivo;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EstoqueCedulas estoque = new EstoqueCedulas();
        NotificacaoConsole notConsole = new NotificacaoConsole();
        NotificacaoArquivo notArquivo = new NotificacaoArquivo("logs/log-caixa.txt");
        estoque.addNotificador(notConsole);
        estoque.addNotificador(notArquivo);
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- MENU CAIXA ELETRÔNICO ---");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito");
            System.out.println("99 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    estoque.notificarTodos("Saldo disponível: R$ " + estoque.getValorTotal());
                    break;
                case 2:
                    System.out.print("Digite o valor para saque: ");
                    int valorSaque = scanner.nextInt();
                    if (estoque.podeSacar(valorSaque)) {
                        Map<Cedula, Integer> cedulasParaSaque = estoque.composicaoSaque(valorSaque);
                        int valorRestante = valorSaque;
                        for (Cedula cedula : Cedula.values()) {
                            int qtd = cedulasParaSaque.getOrDefault(cedula, 0);
                            valorRestante -= cedula.getValor() * qtd;
                        }
                        if (valorRestante == 0) {
                            // Realiza o saque removendo as cédulas
                            for (Cedula cedula : Cedula.values()) {
                                int qtd = cedulasParaSaque.getOrDefault(cedula, 0);
                                if (qtd > 0) {
                                    estoque.removeCedula(cedula, qtd);
                                }
                            }
                            estoque.notificarTodos("Saque realizado: R$ " + valorSaque);
                        } else {
                            estoque.notificarTodos("Não há quantidade suficiente de cédulas para o valor solicitado.");
                            System.out.println("Cédulas disponíveis no caixa:");
                            for (Cedula cedula : Cedula.values()) {
                                int qtd = estoque.getMap().getOrDefault(cedula, 0);
                                if (qtd > 0) {
                                    System.out.println("R$ " + cedula.getValor() + ": " + qtd + " cédulas");
                                }
                            }
                            System.out.print("Deseja tentar outro valor? (s/n): ");
                            String resposta = scanner.next();
                            if (resposta.equalsIgnoreCase("s")) {
                                System.out.print("Digite o novo valor para saque: ");
                                int novoValor = scanner.nextInt();
                                // Repete a lógica de saque para o novo valor
                                if (estoque.podeSacar(novoValor)) {
                                    Map<Cedula, Integer> cedulasNovoSaque = estoque.composicaoSaque(novoValor);
                                    int valorRestanteNovo = novoValor;
                                    for (Cedula cedula : Cedula.values()) {
                                        int qtdNovo = cedulasNovoSaque.getOrDefault(cedula, 0);
                                        valorRestanteNovo -= cedula.getValor() * qtdNovo;
                                    }
                                    if (valorRestanteNovo == 0) {
                                        for (Cedula cedula : Cedula.values()) {
                                            int qtdNovo = cedulasNovoSaque.getOrDefault(cedula, 0);
                                            if (qtdNovo > 0) {
                                                estoque.removeCedula(cedula, qtdNovo);
                                            }
                                        }
                                        estoque.notificarTodos("Saque realizado: R$ " + novoValor);
                                    } else {
                                        estoque.notificarTodos("Não há quantidade suficiente de cédulas para o valor solicitado.");
                                    }
                                } else {
                                    estoque.notificarTodos("Saldo insuficiente para saque.");
                                    estoque.notificarTodos("Tentativa de saque sem saldo suficiente: R$ " + novoValor);
                                }
                            }
                        }
                    } else {
                        estoque.notificarTodos("Saldo insuficiente para saque.");
                        estoque.notificarTodos("Tentativa de saque sem saldo suficiente: R$ " + valorSaque);
                    }
                    break;
                case 3:
                    System.out.println("Escolha a cédula para depósito:");
                    Cedula[] cedulas = Cedula.values();
                    for (int i = 0; i < cedulas.length; i++) {
                        estoque.notificarTodos(((i+1) + " - R$ " + cedulas[i].getValor()));
                    }
                    int escolhaCedula = scanner.nextInt();
                    if (escolhaCedula < 1 || escolhaCedula > cedulas.length) {
                        System.out.println("Opção inválida.");
                        break;
                    }
                    Cedula cedulaSelecionada = cedulas[escolhaCedula-1];
                    System.out.print("Quantidade de cédulas: ");
                    int qtdCedulas = scanner.nextInt();
                    estoque.depositar(cedulaSelecionada, qtdCedulas);
                    estoque.notificarTodos("Depósito: " + qtdCedulas + " cédulas de R$ " + cedulaSelecionada.getValor());
                    break;
                case 99:
                    System.out.println("Saindo...");
                    estoque.notificarTodos("Caixa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 99);
        scanner.close();
    }
}

