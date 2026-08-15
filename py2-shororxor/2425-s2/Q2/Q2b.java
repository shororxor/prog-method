import java.util.ArrayList;
import java.util.List;

public class Q2b {

  // 2b. Stations opened before the given year
  public static List<String> getOldStations(List<Station> stations, int year) {
//     List<String> oldStations = new ArrayList<>();
//     for (Station station : stations) {
//       if (station.getOpeningYear() < year) {
//         oldStations.add(station.getName());
//       }
//     }
//     return oldStations;

    return stations.stream()
              .filter(x -> x.getOpeningYear() < year)
              .map(x -> x.getName())
              .toList();
  }
}
