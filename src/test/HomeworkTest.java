package test;

import main.Main;
import org.junit.jupiter.api.Test;

class HomeworkTest {

    /**
     * 测试用例一
     */
    @Test
    void testA() {
        String tableString = "SubmitDate: 2030/09/01\n" +
                "CAR0001|2025/04/05|Porsche|10000|F\n" +
                "CAR0002|2029/10/14|Porsche|9000|F\n" +
                "CAR0003|2026/08/17|Porsche|13000|F\n" +
                "CAR0004|2027/11/01|BYD|23000|T\n" +
                "CAR0005|2027/01/11|BYD|19500|F\n" +
                "CAR0006|2029/07/01|Audi|10001|T\n" +
                "CAR0007|2028/04/19|Ford|9800|F\n" +
                "CAR0008|2027/07/10|Ford|15000|T\n" +
                "CAR0009|2024/10/22|Ford|90300|F";
        String trueResult = "Reminder\n" +
                "==================\n\n" +
                "* Time-related maintenance coming soon...\n" +
                "Audi: 1 (CAR0006)\n" +
                "Porsche: 1 (CAR0002)\n\n" +
                "* Distance-related maintenance coming soon...\n" +
                "BYD: 1 (CAR0005)\n" +
                "Ford: 1 (CAR0007)\n" +
                "Porsche: 1 (CAR0001)\n\n" +
                "* Write-off coming soon...\n" +
                "BYD: 1 (CAR0004)\n" +
                "Ford: 1 (CAR0009)\n";
        String myResult = new Main().run(tableString);

        System.out.println(testIsTrue(trueResult, myResult));
    }

    /**
     * 测试用例二
     */
    @Test
    void testB() {
        String tableString = "SubmitDate: 2050/05/01\n" +
                "CAR0001|2044/05/01|Volkswagen|65535|F\n" +
                "CAR0002|2044/05/03|BMW|100001|F\n" +
                "CAR0003|2047/05/02|Mercedes-Benz|37789|T\n" +
                "CAR0004|2047/05/03|Honda|59908|T\n" +
                "CAR0005|2049/12/10|Peugeot|49999|F\n" +
                "CAR0006|2046/11/15|Jeep|2000|F\n" +
                "CAR0007|2046/11/16|Jeep|5000|F";
        String trueResult = "Reminder\n" +
                "==================\n\n" +
                "* Time-related maintenance coming soon...\n" +
                "Jeep: 2 (CAR0006,CAR0007)\n\n" +
                "* Distance-related maintenance coming soon...\n" +
                "Peugeot: 1 (CAR0005)\n\n" +
                "* Write-off coming soon...\n" +
                "BMW: 1 (CAR0002)\n" +
                "Honda: 1 (CAR0004)\n";
        String myResult = new Main().run(tableString);

        System.out.println(testIsTrue(trueResult, myResult));
    }

    private boolean testIsTrue(String a, String b) {
        return a.equals(b);
    }

}
