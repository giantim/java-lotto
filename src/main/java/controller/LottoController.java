package controller;

import domain.*;
import view.InputViewer;
import view.OutputViewer;

public class LottoController {
    private LottoController() {
    }

    public static Money getMoney(int inputMoney) {
        try {
            Money money = new Money(inputMoney);
            return money;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getMoney(InputViewer.inputMoney());
        }
    }

    public static WinningLottoTicket getWinningLottoTicket(String winningLottoNumbers) {
        try {
            WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningLottoNumbers);
            return winningLottoTicket;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getWinningLottoTicket(InputViewer.inputWinningLottoTicketNumber());
        }
    }

    public static void getBonusBall(WinningLottoTicket winningLottoTicket, int bonusNumber) {
        try {
            LottoNumber bonusBall = new LottoNumber(bonusNumber);
            winningLottoTicket.initializeBonusBall(bonusBall);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            getBonusBall(winningLottoTicket, InputViewer.inputBonusBallNumber());
        }
    }

    public static LottoResult calculateLottoResult(LottoTickets lottoTickets,
                                                   WinningLottoTicket winningLottoTicket) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.calculateWinningCount(lottoTickets, winningLottoTicket);
        return lottoResult;
    }

    public static void calculateProfit(Money money, LottoResult lottoResult) {
        lottoResult.calculateProfitPercent(money);
    }
}
