import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Q2c {

  // Comparator to sort by number of connections (desc), then name (asc)
  private static final Comparator<Station> stationComparator =
      Comparator.comparingInt((Station s) -> s.getBusConnections().size())
          .reversed()
          .thenComparing(Station::getName);

  // 2c. Top 3 most connected stations (by bus)
  public static List<String> getTopConnectedStations(List<Station> stations) {
//     List<Station> sortedStations = new ArrayList<>(stations);
//     sortedStations.sort(stationComparator);
// 
//     List<String> topStations = new ArrayList<>();
//     for (int i = 0; i < Math.min(3, sortedStations.size()); i++) {
//       topStations.add(sortedStations.get(i).getName());
//     }
//     return topStations;

    return stations.stream()
                .sorted(stationComparator)
                .limit(Math.min(3, stations.size()))
                .map(x -> x.getName())
                .toList();
  }
}
