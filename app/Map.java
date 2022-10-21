import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**Rendering map for the simulations and capturing calculation operations
 */
public class Map {
	
	/**Checking if the map is rendered*/
    private static boolean isRendered = false;
    /**Cost of path*/
    private static double[][] cost;
    /**path from location to location*/
    private static int[][] path;

	//Zjištění nejbližšího skladu
    /**Finding the nearest storage on the map
     * @param to current position on the map
     * @throws if the map does not yet exist
     * @throws if there is no storage yet on the map
     * @return best storage for the most effective run
     */
	public static Storage getNearestStorage(Location to) throws Exception {
		if (!isRendered) {
			throw new Exception("Map has not been rendered yet.");
		}

		double bestDistance = Double.MAX_VALUE;
		Storage bestStorage = null;
		for (Storage storage : Storage.getStorages()) {
			double distance = getTotalDistance(storage, to);
			if (distance < bestDistance) {
				bestDistance = distance;
				bestStorage = storage;
			}			
		}

		if (bestStorage == null) throw new Exception("There is probably no storage on map.");
		return bestStorage;
	}
	/**Browsing the whole path from point A to point B and getting all locations on this path
	 * @param from starting point
	 * @param to final point
	 * @throws if the map does not yet exist
	 * @return all found locations
	 */
	public static ArrayList<Location> getLocationsBetween(Location from, Location to) throws Exception
	{
        if (!isRendered) {
            throw new Exception("Map has not been rendered yet.");
        }

        ArrayList<Location> locations = new ArrayList<>();
        int u = from.getId() - 1;
        int v = to.getId() - 1;
        
        if (u != v && path[v][u] != -1)
        {
            List<Integer> route = new ArrayList<>();
            route.add(u);
            setLocation(u, v, route);
            route.add(v);

            for (Integer lId : route) {
                locations.add(Location.getLocationById(lId+1));
            }

            return locations;
        }

        throw new Exception("Could not find path between these locations.");
	}

    // Recursive function to print path of given vertex `u` from source vertex `v`
	/**Adding location to route
	 * @param v vortex V (coordinate)
	 * @param u vortex U (coordinate)
	 * @param route list where locations will be set using vertices (coordinates)
	 * @throws if the map does not yet exist
	 */
	private static void setLocation(int v, int u, List<Integer> route) throws Exception
	{
        if (!isRendered) {
            throw new Exception("Map has not been rendered yet.");
        }
            
		if (path[v][u] == v) {
			return;
		}
		setLocation(v, path[v][u], route);
		route.add(path[v][u]);
	}

	//Getting total distance between two locations
	/**Getting total cost of the path from point to point
	 * @param from starting point
	 * @param to final point
	 * @throws if the map does not yet exist
	 * return cost of the path
	 */
	public static double getTotalDistance(Location from, Location to) throws Exception {
		if (!isRendered) {
			throw new Exception("Map has not been rendered yet.");
		}

		return cost[from.getId() - 1][to.getId() - 1];
	}
	/**Calculation the distance of the path
	 * @return adj ?
	 */
    private static double[][] calculateLightDistances() throws Exception {
        double[][] adj = new double[Location.getLocations().size()][Location.getLocations().size()];
        double inf = Double.MAX_VALUE;

        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                if (i == j) {
                    adj[i][j] = 0;
                }
                else {
                    adj[i][j] = inf;
                }
            }
        }

        //Getting the distance
        for (int i = 0; i < Path.getPaths().size(); i++) {
            Location from = Location.getLocationById(Path.getPaths().get(i).getFrom().getId());
            Location to = Location.getLocationById(Path.getPaths().get(i).getTo().getId());
            double distance = Calculator.directDistance(from, to);
			
			if (Settings.isTestMode()) {
				distance = 20;
			}

            adj[Path.getPaths().get(i).getFrom().getId()-1][Path.getPaths().get(i).getTo().getId()-1] = Math.min(adj[Path.getPaths().get(i).getFrom().getId()-1][Path.getPaths().get(i).getTo().getId()-1], distance);
            adj[Path.getPaths().get(i).getTo().getId()-1][Path.getPaths().get(i).getFrom().getId()-1] = Math.min(adj[Path.getPaths().get(i).getTo().getId()-1][Path.getPaths().get(i).getFrom().getId()-1], distance);
        }

        return adj;
    }

	// Function to run the Floyd–Warshall algorithm
    /** Rendering map by using Floay-Warshall algoritm (graph) to find the most effective way from point to point
  	 * using new coordinates v,u
     * @throws if cost is negative
     */
	public static void render() throws Exception
	{
        cost = new double[Location.getLocations().size()][Location.getLocations().size()];
        path = new int[Location.getLocations().size()][Location.getLocations().size()];

        double[][] adjMatrix = calculateLightDistances();
        
		// base case
		if (adjMatrix == null || adjMatrix.length == 0) {
			return;
		}

		int n = adjMatrix.length;

		// initialize cost[] and path[]
		for (int v = 0; v < n; v++)
		{
			for (int u = 0; u < n; u++)
			{
				// initially, cost would be the same as the weight of the edge
				cost[v][u] = adjMatrix[v][u];

				if (v == u) {
					path[v][u] = 0;
				}
				else if (cost[v][u] != Double.MAX_VALUE) {
					path[v][u] = v;
				}
				else {
					path[v][u] = -1;
				}
			}
		}

		// run Floyd–Warshall
		for (int k = 0; k < n; k++)
		{
			for (int v = 0; v < n; v++)
			{
				for (int u = 0; u < n; u++)
				{
					// If vertex `k` is on the shortest path from `v` to `u`,
					// then update the value of cost[v][u] and path[v][u]

					if (cost[v][k] != Double.MAX_VALUE
							&& cost[k][u] != Double.MAX_VALUE
							&& (cost[v][k] + cost[k][u] < cost[v][u]))
					{
						cost[v][u] = cost[v][k] + cost[k][u];
						path[v][u] = path[k][u];
					}
				}

				// if diagonal elements become negative, the
				// graph contains a negative-weight cycle
				if (cost[v][v] < 0)
					throw new Exception("Negative-weight cycle found!");
			}
		}

        isRendered = true;
	}

	public static boolean isRendered() {
		return isRendered;
	}

}
