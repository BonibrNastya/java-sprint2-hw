
import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    String monthName;
    List<MonthlyRecord> recordList = new ArrayList<>();

    public MonthlyReport(String rawData, String name) {
        monthName = name;
        String[] lines = rawData.split("\n");

        for (int i = 1; i < lines.length; i++) {

            String[] lineContents = lines[i].split(",");
            if (lineContents.length == 4) {
                MonthlyRecord record = new MonthlyRecord(
                        lineContents[0],
                        Boolean.parseBoolean(lineContents[1]),
                        Integer.parseInt(lineContents[2]),
                        Integer.parseInt(lineContents[3])
                );
                recordList.add(record);

            } else {
                System.out.println("Данные в файле по " + i + " месяцу некорректны.");
                break;

            }
        }
    }

    public void printMonthStatistics() {
        System.out.println(monthName);
        MonthlyRecord bestItem = getBestRecord();
        System.out.println("Самый прибыльный товар: " + bestItem.itemName + " " + bestItem.sumOfOne * bestItem.quantity + " руб.");
        MonthlyRecord maxExpense = getMaxExpense();
        System.out.println("Самая большая трата: " + maxExpense.itemName + " " + maxExpense.sumOfOne * maxExpense.quantity + " руб.\n");

    }

    private MonthlyRecord getBestRecord() {
        MonthlyRecord result = null;
        int maxExpense = 0;
        for (MonthlyRecord record : recordList) {
            if (!record.isExpense) {
                int expenses = record.quantity * record.sumOfOne;
                if (maxExpense < expenses) {
                    maxExpense = expenses;
                    result = record;
                }
            }

        }
        return result;

    }

    private MonthlyRecord getMaxExpense() {
        MonthlyRecord result = null;
        int maxExpense = 0;
        for (MonthlyRecord record : recordList) {
            if (record.isExpense) {
                int expenses = record.quantity * record.sumOfOne;
                if (maxExpense < expenses) {
                    maxExpense = expenses;
                    result = record;
                }
            }

        }
        return result;

    }

    public Integer getMonthReport(boolean isExpense) {
        int result = 0;
        for (MonthlyRecord income : recordList) {
            if (income.isExpense == isExpense) {
                result = result + income.sumOfOne * income.quantity;
            }
        }
        return result;
    }

}




