package controller;

import domain.*;
import view.InputViewer;
import view.OutputViewer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static WinningLottoTicket getWinningLottoTicket() {
        try {
            LottoTicket winningLottoTicket = getWinningLottoTicketNumbers();
            LottoNumber bonusBall = getBonusBall();
            return new WinningLottoTicket(winningLottoTicket, bonusBall);
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getWinningLottoTicket();
        }
    }

    private static LottoTicket getWinningLottoTicketNumbers() {
        try {
            String lottoNumber = InputViewer.inputWinningLottoTicketNumber();
            List<LottoNumber> lottoNumbers = LottoNumberSplit.initializeLottoNumbers(lottoNumber);
            LottoTicket winningLottoTicket = new LottoTicket(lottoNumbers);
            return winningLottoTicket;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getWinningLottoTicketNumbers();
        }
    }

    private static LottoNumber getBonusBall() {
        try {
            int bonusNumber = InputViewer.inputBonusBallNumber();
            LottoNumber bonusBall = LottoNumbers.getLottoNumber(bonusNumber);
            return bonusBall;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getBonusBall();
        }
    }

    public static ManualLottoTicketCount getManualLottoTicketCount(int allLottoTicketCount, int inputManualLottoCount) {
        try {
            ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount(allLottoTicketCount,
                    inputManualLottoCount);
            return manualLottoTicketCount;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getManualLottoTicketCount(allLottoTicketCount, InputViewer.inputManualLottoCount());
        }
    }

    public static LottoTickets getLottoTickets(int manualLottoTicketCounts, int autoLottoTicketCount) {
        Tickets manualLottoTickets = getManualLottoTickets(manualLottoTicketCounts);
        Tickets autoLottoTickets = TicketsFactory.getTickets(autoLottoTicketCount);
        LottoTickets lottoTickets = new LottoTickets(
                concatManualTicketsWithAutoTickets(manualLottoTickets, autoLottoTickets));
        return lottoTickets;
    }

    private static Tickets getManualLottoTickets(int manualLottoTicketCounts) {
        try {
            List<String> manualLottoTicketsNumbers = InputViewer.inputManualLottoTicketNumber(manualLottoTicketCounts);
            Tickets manualLottoTickets = TicketsFactory.getTickets(manualLottoTicketsNumbers);
            return manualLottoTickets;
        } catch (IllegalArgumentException e) {
            OutputViewer.printErrorMessage(e.getMessage());
            return getManualLottoTickets(manualLottoTicketCounts);
        }
    }

    private static List<LottoTicket> concatManualTicketsWithAutoTickets(Tickets manualLottoTickets,
                                                                        Tickets autoLottoTickets) {
        List<LottoTicket> manualLottoTicketList = manualLottoTickets.getTickets();
        List<LottoTicket> autoLottoTicketList = autoLottoTickets.getTickets();
        List<LottoTicket> lottoTickets = Stream.concat(manualLottoTicketList.stream(), autoLottoTicketList.stream())
                .collect(Collectors.toList());
        return lottoTickets;
    }
}
