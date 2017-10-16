package main;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResultReport {
    private String typeMessage;
    private TreeMap<String, List<Taxi>> resultMap;

    ResultReport(String typeMessage, TreeMap<String, List<Taxi>> resultMap) {
        this.typeMessage = typeMessage;
        this.resultMap = resultMap;
    }

    TreeMap<String, List<Taxi>> getResultMap() {
        return resultMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("* ").append(typeMessage).append(" coming soon...\n");

        for (Map.Entry<String, List<Taxi>> pair : resultMap.entrySet()
                ) {
            String brand = pair.getKey();
            List<Taxi> taxis = pair.getValue();
            sb.append(brand).append(": ").append(taxis.size()).append(" (");
            for (Taxi item : taxis) {
                sb.append(item.getCarNumber()).append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(')').append("\n");
        }

        return sb.toString();
    }
}
