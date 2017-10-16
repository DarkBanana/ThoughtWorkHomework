package main;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户输入信息表实体类
 * 用于转化成结构化数据
 * 包含提交日期、出租车列表
 */
class InfoTable {
    private Date submitDate;/* 提交日期 */
    private List<Taxi> taxis;/* 出租车列表 */

    private InfoTable() {

    }

    static InfoTable fromString(String tableString) {
        InfoTable infoTable = new InfoTable();
        infoTable.submitDate = getSubmitDateFromTableString(tableString);
        infoTable.taxis = getTaxisFromTableString(tableString);
        return infoTable;
    }

    private static Date getSubmitDateFromTableString(String tableString) {
        Date submitDate = null;
        String prefix = "SubmitDate: ";

        try {
            if (tableString.startsWith(prefix)) {
                submitDate = new SimpleDateFormat("yyyy/MM/dd").parse(tableString, new ParsePosition(prefix.length()));
                assert submitDate != null;
            } else {
                assert false;
            }
        } catch (AssertionError e) {
            throw new RuntimeException("输入文本格式不正确！");
        }

        return submitDate;
    }

    private static List<Taxi> getTaxisFromTableString(String inputText) {
        String[] strings = inputText.split("\n");

        List<Taxi> taxis = new ArrayList<>();
        for (String item : strings
                ) {
            if (item.startsWith("CAR")) {
                String[] itemStrings = item.split("\\|");
                Taxi taxi = new Taxi();
                taxi.setCarNumber(itemStrings[0]);
                taxi.setPurchaseTime(itemStrings[1]);
                taxi.setBrand(itemStrings[2]);
                taxi.setMileage(Integer.valueOf(itemStrings[3]));
                if (itemStrings[4].equals("T")) {
                    taxi.setOverHaul(true);
                } else {
                    taxi.setOverHaul(false);
                }

                taxis.add(taxi);
            }
        }

        return taxis;
    }

    Date getSubmitDate() {
        return submitDate;
    }

    List<Taxi> getTaxis() {
        return taxis;
    }

}
