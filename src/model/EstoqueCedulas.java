package src.model;

import src.interfaces.Notificacao;
import src.enums.Cedula;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;

// If Notification does not exist, create the Notification class in src/model/Notification.java:

public class EstoqueCedulas {
    private int quantidade;
    private Notificacao notification;
    private Map<Cedula, Integer> map = new HashMap<>();
    private Map<Cedula, Integer> composicao = new HashMap<>();

    public EstoqueCedulas(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Notificacao getNotification() {
        return notification;
    }

    public void setNotification(Notificacao notification) {
        this.notification = notification;
    }

    Map<Cedula, Integer> getMap() {
        return map;
    }

    EnumMap<Cedula, Integer> getEnumMap() {
        EnumMap<Cedula, Integer> enumMap = new EnumMap<>(Cedula.class);
        enumMap.putAll(map);
        return enumMap;
    }

    //Adicionar cédulas ao estoque
    public void depositar(Cedula cedula, int quantidade) {
        if (map.containsKey(cedula)) {
            map.put(cedula, map.get(cedula) + quantidade);
        } else {
            map.put(cedula, quantidade);
        }
    }

    //Remover cédulas do estoque 
    public void removeCedula(Cedula cedula, int quantidade) {
        if (map.containsKey(cedula)) {
            int novaQuantidade = map.get(cedula) - quantidade;
            int disponivel = map.get(cedula);
            if (novaQuantidade > 0) {
                map.put(cedula, novaQuantidade);
            } else {
                map.remove(cedula);
            }

            if (novaQuantidade == disponivel) {
                notification.notificar("Saldo está zerado");
            } else if (novaQuantidade > disponivel) {
                // Notificar que não há cédulas suficientes
                notification.notificar("Não há cédulas suficientes para a operação.");
            }
        }
    }

    //Retornar o valor total disponível no caixa
    public int getValorTotal() {
        return (int) map.entrySet().stream().mapToDouble(entry -> entry.getKey().getValor() * entry.getValue()).sum();
    }

    //Verificar se é possível realizar um saque de determinado valor
    public boolean podeSacar(int valor) {
        return getValorTotal() >= valor;
    }

    //Calcular a composição de cédulas para um saque
    public Map<Cedula, Integer> composicaoSaque(int valor) {
        for (Map.Entry<Cedula, Integer> entry : map.entrySet()) {
            Cedula cedula = entry.getKey();
            int quantidade = entry.getValue();
            while (valor >= cedula.getValor() && quantidade > 0) {
                composicao.put(cedula, composicao.getOrDefault(cedula, 0) + 1);
                valor -= cedula.getValor();
                quantidade--;
            }
        }
        return composicao;
    }

}
