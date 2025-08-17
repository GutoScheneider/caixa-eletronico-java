package src.interfaces;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificacaoArquivo implements Notificacao {
    private String caminhoArquivo;

    public NotificacaoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public void notificar(String mensagem) {
        try (FileWriter fw = new FileWriter(caminhoArquivo, true)) {
            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            fw.write("[" + dataHora + "] " + mensagem + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
