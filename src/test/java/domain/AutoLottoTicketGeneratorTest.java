package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoLottoTicketGeneratorTest {
    @DisplayName("생성된 두 LottoTicket 이 다른 티켓인지 테스트")
    @Test
    void equalsTest() {
        LottoTicket input = AutoLottoTicketGenerator.generateAutoLottoTicket();

        LottoTicket expected = AutoLottoTicketGenerator.generateAutoLottoTicket();
        Assertions.assertThat(input).isNotEqualTo(expected);
    }
}
