package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int INITIAL_COUNT = 0;
    private static final int HAS_BONUS = 5;

    private Map<PrizeType, Integer> prizeInfo;
    private ProfitPercent profitPercent;

    public LottoResult() {
        initializePrizeInfo();
    }

    private void initializePrizeInfo() {
        this.prizeInfo = new HashMap<>();
        this.prizeInfo.put(PrizeType.THREE, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.FOUR, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.FIVE, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.FIVE_WITH_BONUS, INITIAL_COUNT);
        this.prizeInfo.put(PrizeType.SIX, INITIAL_COUNT);
    }

    public void calculateWinningCount(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        countWinningLottoNumber(lottoTickets, winningLottoTicket);
    }

    private void countWinningLottoNumber(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            PrizeType prizeType = compareNumberWithWinningLottoNumber(lottoTicket, winningLottoTicket);
            addPrizeInfoValue(prizeType);
        }
    }

    private PrizeType compareNumberWithWinningLottoNumber(LottoTicket lottoTicket,
                                                          WinningLottoTicket winningLottoTicket) {
        int winningCount = 0;
        for (LottoNumber lottoNumber : winningLottoTicket.getWinningLottoTicket()) {
            winningCount = checkLottoTicketHasWinningNumber(lottoTicket, winningCount, lottoNumber);
        }
        if (winningCount == HAS_BONUS) {
            return PrizeType.getPrizeTypeForWinningCount(winningCount,
                    isLottoTicketHasBonusNumber(winningLottoTicket, lottoTicket));
        }
        return PrizeType.getPrizeTypeForWinningCount(winningCount, false);
    }

    private int checkLottoTicketHasWinningNumber(LottoTicket lottoTicket, int winningCount, LottoNumber lottoNumber) {
        if (lottoTicket.containLottoNumber(lottoNumber)) {
            winningCount = winningCount + 1;
        }
        return winningCount;
    }

    private boolean isLottoTicketHasBonusNumber(WinningLottoTicket winningLottoTicket, LottoTicket lottoTicket) {
        return lottoTicket.containLottoNumber(winningLottoTicket.getBonusNumber());
    }

    private void addPrizeInfoValue(PrizeType prizeType) {
        if (prizeType == null) {
            return;
        }
        int originalPrizeCount = prizeInfo.get(prizeType);
        this.prizeInfo.put(prizeType, originalPrizeCount + 1);
    }

    public void calculateProfitPercent(Money money) {
        int totalPrizeMoney = 0;
        for (PrizeType prizeType : PrizeType.values()) {
            totalPrizeMoney = totalPrizeMoney + prizeType.calculatePrizeMoney(getPrizeTypeValue(prizeType));
        }
        this.profitPercent = new ProfitPercent(money, totalPrizeMoney);
    }

    public int getPrizeTypeValue(PrizeType prizeType) {
        return this.prizeInfo.get(prizeType);
    }

    public int getProfitPercent() {
        return this.profitPercent.getProfitPercent();
    }
}
