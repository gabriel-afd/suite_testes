// Implementacao de referencia do requisito (substitua pela fornecida pelo professor).
public class Avaliacao {

    public static String situacao(double media, int frequencia) {
        if (media < 0.0 || media > 10.0 || frequencia < 0 || frequencia > 100) {
            throw new IllegalArgumentException("media ou frequencia fora da faixa");
        }
        if (frequencia < 75) {
            return "REPROVADO_POR_FALTA";
        }
        if (media >= 7.0) {
            return "APROVADO";
        }
        if (media >= 4.0) {
            return "RECUPERACAO";
        }
        return "REPROVADO";
    }
}
