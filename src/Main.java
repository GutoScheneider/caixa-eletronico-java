package src;

import src.interfaces.NotificacaoArquivo;
import src.interfaces.NotificacaoConsole;

public class Main {

    
    public static void main(String[] args) {
        NotificacaoConsole notificacao = new NotificacaoConsole();
        notificacao.notificar("Teste de notificação no console.");
        NotificacaoArquivo notificacaoArquivo = new NotificacaoArquivo("logs/log-caixa.txt");
    }

}

