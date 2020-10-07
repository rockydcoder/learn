package sarangi.rakesh.dcp;

import java.util.*;

public class Problem81Easy {

    private static final Map<String, List<String>> MAPPING = new HashMap<>();

    public static void main(String[] args) {
        prepareTestData();
        solution("2");
        solution("345");
    }

    private static void prepareTestData() {
        MAPPING.put("2", Arrays.asList("a", "b", "c"));
        MAPPING.put("3", Arrays.asList("d", "e", "f"));
        MAPPING.put("4", Arrays.asList("g", "h", "i"));
        MAPPING.put("5", Arrays.asList("j", "k", "l"));
        MAPPING.put("6", Arrays.asList("m", "n", "o"));
        MAPPING.put("7", Arrays.asList("p", "q", "r", "s"));
        MAPPING.put("8", Arrays.asList("t", "u", "v"));
        MAPPING.put("9", Arrays.asList("w", "x", "y", "z"));
    }

    private static void solution(String query) {
        List<String> input = new ArrayList<>();
        for (int i = 0; i < query.length(); i++) {
            input.add(query.substring(i, i + 1));
        }
        printWords(input, 0, query.length(), new StringBuilder());
    }

    private static void printWords(List<String> input, int current, int length, StringBuilder output) {
        if (current == length) {
            System.out.println(output.toString());
        } else {
            for (int i = 0; i < MAPPING.get(input.get(current)).size(); i++) {
                output.append(MAPPING.get(input.get(current)).get(i));
                printWords(input, current + 1, length, output);
                output.deleteCharAt(output.length() - 1);
            }
        }
    }
}
