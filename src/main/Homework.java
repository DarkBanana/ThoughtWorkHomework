package main;

import java.util.*;

public class Homework {

    public String run(String tableString) {
        InfoTable infoTable = InfoTable.fromString(tableString);
        String result = parse(infoTable);
        System.out.println(result);
        return result;
    }

    private void addToResultMap(Map<String, List<Taxi>> map, Taxi taxi) {
        String brand = taxi.getBrand();
        List<Taxi> list = map.get(brand);
        if (list == null) {
            list = new ArrayList<>();
            list.add(taxi);
            map.put(brand, list);
        } else {
            map.get(brand).add(taxi);
        }
    }

    private ResultReport createResultReport(String typeMessage) {
        return new ResultReport(typeMessage, new TreeMap<>());
    }

    private String parse(InfoTable table) {
        Date submitDate = table.getSubmitDate();
        List<Taxi> taxis = table.getTaxis();

        ResultReport timeRelated = createResultReport("Time-related maintenance");
        ResultReport distanceRelated = createResultReport("Distance-related maintenance");
        ResultReport writeOff = createResultReport("Write-off");

        for (Taxi taxi : taxis
                ) {
            if (taxi.isBelongToWriteOffNotice(submitDate)) { //报废提醒
                addToResultMap(writeOff.getResultMap(), taxi);
            } else if (taxi.isBelongToDistanceRelatedNotice(submitDate)) { //每1万公里保养提醒
                addToResultMap(distanceRelated.getResultMap(), taxi);
            } else if (taxi.isBelongToTimeRelatedNotice(submitDate)) { //定期保养提醒
                addToResultMap(timeRelated.getResultMap(), taxi);
            }
        }

        return resultToString(timeRelated, distanceRelated, writeOff);
    }

    private String resultToString(ResultReport timeRelated, ResultReport distanceRelated, ResultReport writeOffMap) {
        return "Reminder\n" +
                "==================\n\n" +
                timeRelated.toString() + "\n" +
                distanceRelated.toString() + "\n" +
                writeOffMap.toString();
    }

}
