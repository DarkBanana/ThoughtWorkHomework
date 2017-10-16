package main;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResultMap<K, V> extends TreeMap<K, V> {
    private String typeMessage;

    ResultMap(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("* ").append(typeMessage).append(" coming soon...\n");

        for (Map.Entry<K, V> e : entrySet()
                ) {
            String key = (String) e.getKey();
            @SuppressWarnings("unchecked")
            List<Taxi> value = (List<Taxi>) e.getValue();
            sb.append(key).append(": ").append(value.size()).append(" (");
            for (Taxi item : value) {
                sb.append(item.getCarNumber()).append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(')').append("\n");
        }

        return sb.toString();
    }
}
