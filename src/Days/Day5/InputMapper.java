package Days.Day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputMapper {
    private final List<Integer[]> preformattedInput;
    private final Map<Integer, List<Integer[]>> orderingRules;
    private final List<Integer[]> producePages;

    public InputMapper() throws IOException {
        preformattedInput = new InputFormatter().getConvertedInput();
        orderingRules = loadMap();
        producePages = loadList();
    }

    private List<Integer[]> loadList() {
        List<Integer[]> result = new ArrayList<>();
        for (Integer[] number : preformattedInput) {
            result.add(number);
        }
        return result;
    }

    private Map<Integer, List<Integer[]>> loadMap() {
        Map<Integer, List<Integer[]>> rules = new HashMap<>();
        while (preformattedInput.getFirst().length == 2) {
            if (rules.containsKey(preformattedInput.getFirst()[0])) {
                rules.get(preformattedInput.getFirst()[0]).add(preformattedInput.getFirst());
                preformattedInput.removeFirst();
                continue;
            }
            List<Integer[]> newUpdateList = new ArrayList<>();
            newUpdateList.add(preformattedInput.getFirst());
            rules.put(preformattedInput.getFirst()[0], newUpdateList);
            preformattedInput.removeFirst();
        }
        return rules;
    }

    public List<Integer[]> getProducePages() {
        return producePages;
    }

    public Map<Integer, List<Integer[]>> getOrderingRules() {
        return orderingRules;
    }

    public static void main(String[] args) throws IOException {
        InputMapper inputMapper = new InputMapper();
        System.out.println("Numbers for Updates");
        for (Integer[] numbers : inputMapper.producePages) {
            for (Integer number : numbers) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Numbers for Ordering Rules");
        for (Integer key : inputMapper.orderingRules.keySet()) {
            System.out.println(key + " ");
            for (Integer[] numbers : inputMapper.orderingRules.get(key)) {
                for (Integer number : numbers) {
                    System.out.print(number + " ");
                }
                System.out.println();
            }
        }
    }
}
