package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public String run(String tableString) {
        InfoTable infoTable = InfoTable.fromString(tableString);
        return parse(infoTable);
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

    private String parse(InfoTable table) {
        Date submitDate = table.getSubmitDate();
        List<Taxi> taxis = table.getTaxis();

        Map<String, List<Taxi>> timeRelated = new ResultMap<>("Time-related maintenance");//TODO
        Map<String, List<Taxi>> distanceRelated = new ResultMap<>("Distance-related maintenance");
        Map<String, List<Taxi>> writeOff = new ResultMap<>("Write-off");

        for (Taxi taxi : taxis
                ) {
            if (taxi.isBelongToWriteOffNotice(submitDate)) { //报废提醒
                addToResultMap(writeOff, taxi);
            } else if (taxi.isBelongToDistanceRelatedNotice(submitDate)) { //每1万公里保养提醒
                addToResultMap(distanceRelated, taxi);
            } else if (taxi.isBelongToTimeRelatedNotice(submitDate)) { //定期保养提醒
                addToResultMap(timeRelated, taxi);
            }
        }

        String result = resultToString(timeRelated, distanceRelated, writeOff);
        System.out.println(result);

        return result;
    }

    private String resultToString(Map<String, List<Taxi>> timeRelatedMap, Map<String, List<Taxi>> distanceRelatedMap, Map<String, List<Taxi>> writeOffMap) {
        return "Reminder\n" +
                "==================\n\n" +
                timeRelatedMap.toString() + "\n" +
                distanceRelatedMap.toString() + "\n" +
                writeOffMap.toString();
    }

}
