import java.util.ArrayList;
import java.util.List;

public class Q2d {

  // 2d. Returns all pairs of stations connected by at least one bus from the given list
  public static List<List<Station>> getConnectedStationPairs(
      List<Station> stations, List<String> busNames) {
//     List<List<Station>> connectedPairs = new ArrayList<>();
// 
//     for (int i = 0; i < stations.size(); i++) {
//       Station s1 = stations.get(i);
//       for (int j = i + 1; j < stations.size(); j++) {
//         Station s2 = stations.get(j);
// 
//         for (String bus : busNames) {
//           if (s1.getBusConnections().contains(bus) && s2.getBusConnections().contains(bus)) {
//             connectedPairs.add(List.of(s1, s2));
//             break; // Only need one shared bus to count as connected
//           }
//         }
//       }
//     }
// 
//     return connectedPairs;
     
      return stations.stream()
                  .flatMap(s1 -> stations.stream()
                                      .filter(s2 -> stations.indexOf(s1) < stations.indexOf(s2))
                                      .filter(s2 -> busNames.stream()
                                                        .anyMatch(bus -> s1.getBusConnection().contains(bus)
                                                                          && s2.getBusConnection().contains(bus)))
                          )

                  .map(s2 -> List.of(s1, s2))
                  .toList();
   }
}
