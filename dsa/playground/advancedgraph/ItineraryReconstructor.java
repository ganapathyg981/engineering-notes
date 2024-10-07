package advancedgraph;

import java.util.*;

public class ItineraryReconstructor {

    // This is the method you will implement
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String,PriorityQueue<String>> map = new HashMap<>();

        for (List<String> edge: tickets) {
            String src = edge.get(0);
            String dest = edge.get(1);
            map.putIfAbsent(src,new PriorityQueue<>());
            map.get(src).add(dest);
        }
        Stack<String> stack = new Stack<>();
        dfs("JFK", map, stack);
       return stackToArray(stack);
    }

    private void dfs(String terminal, Map<String, PriorityQueue<String>> map, Stack<String> out) {

        PriorityQueue<String> nextOnes = map.get(terminal);
       while (nextOnes!=null && !nextOnes.isEmpty()) {
            String nextOne = nextOnes.poll();
            dfs(nextOne, map, out);
        }
        out.add(terminal);
    }

    public static void main(String[] args) {
        ItineraryReconstructor reconstructor = new ItineraryReconstructor();

        // Example test cases
        List<List<String>> tickets1 = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );
        System.out.println(reconstructor.findItinerary(tickets1)); // Expected: ["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]

        List<List<String>> tickets2 = Arrays.asList(
                Arrays.asList("JFK", "KUL"),
                Arrays.asList("JFK", "NRT"),
                Arrays.asList("NRT", "JFK")
        );
        System.out.println(reconstructor.findItinerary(tickets2)); // Expected: ["JFK", "NRT", "JFK", "KUL"]
    }

    public static List<String> stackToArray(Stack<String> stack) {
        List<String> out = new ArrayList<>();
        while(!stack.empty()){
            out.add(stack.pop());
        }
        return out;
    }

}