import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AvaliacaoTest {

    // Casos validos -- IDs iguais aos da tabela de casos (Parte 3)
    @ParameterizedTest(name = "média {0}, frequência {1}%: {2}")
    @CsvSource({
        "0.0,  90,  REPROVADO",            // V1  - media minima valida
        "3.9,  90,  REPROVADO",            // V2  - ultimo valor de REPROVADO
        "4.0,  90,  RECUPERACAO",          // V3  - primeiro valor de RECUPERACAO
        "6.9,  90,  RECUPERACAO",          // V4  - ultimo valor de RECUPERACAO
        "7.0,  90,  APROVADO",             // V5  - primeiro valor de APROVADO
        "10.0, 90,  APROVADO",             // V6  - media maxima valida
        "0.0,  0,   REPROVADO_POR_FALTA",  // V7  - frequencia minima valida, media minima
        "10.0, 74,  REPROVADO_POR_FALTA",  // V8  - ultima frequencia com falta, media maxima
        "8.0,  75,  APROVADO",             // V9  - primeira frequencia suficiente
        "8.0,  100, APROVADO"              // V10 - frequencia maxima valida
    })
    void casosValidos(double media, int frequencia, String esperado) {
        assertEquals(esperado, Avaliacao.situacao(media, frequencia));
    }

    // Casos invalidos: uma unica entrada invalida por teste

    @Test
    void mediaNegativa() {                 // I1
        assertThrows(IllegalArgumentException.class, () -> Avaliacao.situacao(-0.1, 90));
    }

    @Test
    void mediaAcimaDeDez() {               // I2
        assertThrows(IllegalArgumentException.class, () -> Avaliacao.situacao(10.1, 90));
    }

    @Test
    void frequenciaNegativa() {            // I3
        assertThrows(IllegalArgumentException.class, () -> Avaliacao.situacao(8.0, -1));
    }

    @Test
    void frequenciaAcimaDeCem() {          // I4
        assertThrows(IllegalArgumentException.class, () -> Avaliacao.situacao(8.0, 101));
    }
}
