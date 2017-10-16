# ThoughtWorkHomework

## 解题过程

#### 1)建模

- Taxi实体类建模

    ```
    class Taxi {
        private String carNumber;/* 车牌号 */
        private Date purchaseTime;/* 购买日期 */
        private String brand;/* 品牌名称 */
        private int mileage;/* 行驶公里数 */
        private boolean overHaul;/* 有无大修 */
    }   
    ``` 
- InfoTable用户输入的信息表建模

    ```
    class InfoTable {
        private Date submitDate;/* 提交日期 */
        private List<Taxi> taxis;/* 出租车列表 */
    }   
    ```
- ResultMap输出的结果集建模，采用ResultMap<String,List<Taxi>>存储三类提醒（报废提醒、每1万公里保养提醒、定期保养提醒），并重写toString()方法。

#### 2）业务功能实现

- 读取输入字符串，并解析成结构化数据，包括提交时间、taxi列表

- 遍历分析每个taxi，并放置到相应ResultMap中

- 打印结果，进行比对，验证正确性

#### 3）代码重构

- 提取公共代码、模块细分、提取常量。

#### 4）代码提交

- 建立本地和远程仓库，提交代码到github上，[链接](https://github.com/Scofield123111/ThoughtWorkHomework)



## 单元测试

>运用Junit5完成单元测试，验证程序正确性

- 将输入文本tableString作为参数传入，运行得到分析后的字符串myResult，再与正确的输出trueResult做equals()，结果为true则程序正常运行。


- 测试用例一：
    ```
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
            String myResult = new Homework().run(tableString);
    
            System.out.println(testIsTrue(trueResult, myResult));
        }
    ```
    打印结果：`true`
    
- 测试用例二：
    ```  
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
            String myResult = new Homework().run(tableString);
    
            System.out.println(testIsTrue(trueResult, myResult));
        }
    
        private boolean testIsTrue(String a, String b) {
            return a.equals(b);
        }

    ```
    打印结果：`true`
    