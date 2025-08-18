package src.interfaces;

public class NotificacaoConsole implements Notificacao {
    @Override
    public void notificar(String mensagem) {
        System.out.println(mensagem);
    }
}
