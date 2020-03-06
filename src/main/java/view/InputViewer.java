package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputViewer {
    private static Scanner scanner = new Scanner(System.in);

    private InputViewer() {
    }

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            OutputViewer.printErrorMessage("숫자를 입력해주세요.");
            return inputMoney();
        }
    }

    public static String inputWinningLottoTicketNumber() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int inputBonusBallNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            OutputViewer.printErrorMessage("숫자를 입력해주세요.");
            return inputBonusBallNumber();
        }
    }

    public static int inputManualLottoCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            OutputViewer.printErrorMessage("숫자를 입력해주세요.");
            return inputManualLottoCount();
        }
    }

    public static List<String> inputManualLottoTicketNumber(int manualLottoTicketCount) {
        List<String> manualLottoNumbers = new ArrayList<>();
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualLottoTicketCount; i++) {
            String lottoNumber = scanner.nextLine();
            manualLottoNumbers.add(lottoNumber);
        }
        return manualLottoNumbers;
    }
}
