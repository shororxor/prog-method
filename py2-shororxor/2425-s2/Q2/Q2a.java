import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2a {

  // 2a. All distinct connected bus station names (no HashSet)
  public static List<String> getAllBusConnections(List<Station> stations) {
//     List<String> connections = new ArrayList<>();
//     for (Station station : stations) {
//       for (String conn : station.getBusConnections()) {
//         if (!connections.contains(conn)) {
//           connections.add(conn);
//         }
//       }
//     }
//     Collections.sort(connections);
//     return connections;
     return stations.stream()
              .flatMap(x -> x.getBusConnections().stream())
              .distinct()
              .sorted()
              .toList();
  }
}
