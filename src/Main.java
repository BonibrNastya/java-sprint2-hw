import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static List<MonthlyReport> monthlyReports = new ArrayList<>();
    static YearlyReport yearlyReport = null;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    for (int i = 1; i < 4; i++) {
                        String monthName = (i + " месяц");
                        String data = FileReader.readMonthReport(i);
                        MonthlyReport monthlyReport = new MonthlyReport(data, monthName);
                        monthlyReports.add(monthlyReport);
                    }
                    System.out.println("Операция завершена.\n");
                    break;
                case "2":
                    String data = FileReader.readYearReport();
                    yearlyReport = new YearlyReport(data);
                    System.out.println("Операция завершена.\n");
                    break;
                case "3":
                    if (!checkMonthlyData() || !checkYearData()) {
                        System.out.println("Сначала необходимо считать отчёты.\n");
                    } else {
                        checkReports();
                    }
                    break;
                case "4":
                    if (!checkMonthlyData()) {
                        System.out.println("Необходимо считать месячные отчёты.\n");
                    } else {
                        for (MonthlyReport report : monthlyReports) {
                            report.printMonthStatistics();
                        }
                    }
                    break;
                case "5":
                    if (!checkYearData()) {
                        System.out.println("Необходимо считать годовой отчёт.\n");
                    } else {
                        yearlyReport.printYearStatistics();
                    }
                    break;
                case "0":
                    System.out.println("Выход");
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет.\n");
            }
        }
    }


    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }

    private static void checkReports() {
        boolean isSuccessfull = true;
        int i = 1;
        for (MonthlyReport report : monthlyReports) {
            Integer monthExpense = report.getMonthReport(true);
            Integer monthExpenseFromYear = yearlyReport.getResultByMonth(i, true);
            if (!monthExpense.equals(monthExpenseFromYear)) {
                System.out.println("Расходы за " + report.monthName + " не сходятся с годовым отчётом.\n");
                isSuccessfull = false;
            }
            Integer monthIncome = report.getMonthReport(false);
            Integer monthIncomeFromYear = yearlyReport.getResultByMonth(i, false);
            if (!monthIncome.equals(monthIncomeFromYear)) {
                System.out.println("Доходы за " + report.monthName + " не сходятся с годовым отчётом.\n");
                isSuccessfull = false;
            }
            i++;
        }
        if (isSuccessfull) {
            System.out.println("Операция успешно завершена. Отчеты сходятся.\n");
        }
    }

    private static boolean checkMonthlyData() {
        if (monthlyReports.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkYearData() {
        if (Objects.isNull(yearlyReport)) {
            return false;
        } else {
            return true;
        }
    }
}

