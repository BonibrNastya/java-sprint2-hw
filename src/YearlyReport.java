import java.util.HashMap;
import java.util.Map;

public class YearlyReport {
    String year = "Отчёт за 2021 год";
    Map<Integer, Integer> incomeMap = new HashMap<>();
    Map<Integer, Integer> expenseMap = new HashMap<>();

    public YearlyReport(String rawData) {
        String[] lines = rawData.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] lineContents = lines[i].split(",");
            if (lineContents.length == 3) {
                if (Boolean.parseBoolean(lineContents[2])) {
                    expenseMap.put(Integer.parseInt(lineContents[0]), Integer.parseInt(lineContents[1]));
                } else {
                    incomeMap.put(Integer.parseInt(lineContents[0]), Integer.parseInt(lineContents[1]));
                }
            } else {
                System.out.println("Данные в файле за " + i + " месяц за 2021 год некорректны.\n");
            }
        }
    }

    public void printYearStatistics() {
        System.out.println(year);
        for (int i = 1; i < 4; i++) {
            System.out.println("Прибыль " + i + " месяца составила " + calculateIncomeForMonth(i) + " руб.");
        }
        System.out.println("Средний расход за все месяцы составил: " + averageExpense() + " руб.");
        System.out.println("Средний доход за все месяцы составил: " + averageIncome() + " руб.\n");
    }

    private Integer averageIncome() {
        Integer result = 0;
        for (Map.Entry<Integer, Integer> entry : incomeMap.entrySet()) {
            result = result + entry.getValue();
        }
        return result / 3;
    }

    private Integer averageExpense() {
        Integer result = 0;
        for (Map.Entry<Integer, Integer> entry : expenseMap.entrySet()) {
            result = result + entry.getValue();
        }
        return result / 3;

    }

    private Integer calculateIncomeForMonth(Integer monthNumber) {
        return incomeMap.get(monthNumber) - expenseMap.get(monthNumber);
    }

    public Integer getResultByMonth(Integer monthNumber, boolean isExpense) {
        if (isExpense) {
            return expenseMap.get(monthNumber);
        } else {
            return incomeMap.get(monthNumber);
        }
    }

}
