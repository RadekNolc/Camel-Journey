package cz.radeknolc.cameljourney;
import java.util.ArrayList;
import java.util.List;

/**
 * Map class handles all the functions connected to imaginary map
 * @author Radek Nolč
 */
public class Map {
	
	/** If map is rendered */
    private static boolean isRendered = false;
    /** Cost (distance) of paths */
    private static double[][] cost;
    /** Paths between all locations */
    private static int[][] path;

	/**
	 * Function to get the whole path from location to another location
	 * @param from starting (origin) location
	 * @param to final location (destination)
	 * @throws NoSuchFieldException if the has not been rendered yet
	 * @return list of locations between origin location and destination
	 */
	public static List<Location> getLocationsBetween(Location from, Location to) throws NoSuchFieldException
	{
        if (!isRendered) {
            throw new NoSuchFieldException("Map has not been rendered yet.");
        }

        List<Location> locations = new ArrayList<>();
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

        throw new NoSuchFieldException("Could not find path between these locations.");
	}

	/**
	 * Function to add location to route
	 * @param v vertex V (location ID)
	 * @param u vertex U (location ID)
	 * @param route list where locations will be set using vertices (location ID)
	 * @throws NoSuchFieldException if the has not been rendered yet
	 */
	private static void setLocation(int v, int u, List<Integer> route) throws NoSuchFieldException
	{
        if (!isRendered) {
            throw new NoSuchFieldException("Map has not been rendered yet.");
        }
            
		if (path[v][u] == v) {
			return;
		}
		setLocation(v, path[v][u], route);
		route.add(path[v][u]);
	}

	/**
	 * Function to get total distance of the path from location to another location
	 * @param from starting (origin) location
	 * @param to final location (destination)
	 * @throws NoSuchFieldException if the has not been rendered yet
	 * @return distance between two locations
	 */
	public static double getTotalDistance(Location from, Location to) throws NoSuchFieldException {
		if (!isRendered) {
			throw new NoSuchFieldException("Map has not been rendered yet.");
		}

		return cost[from.getId() - 1][to.getId() - 1];
	}

	/**
	 * Function to calculate the light (direct) distances between all locations where is path
	 * @return adjacency matrix with distances
	 * @throws NullPointerException if the path is pointing to not existing location(s)
	 */
    private static double[][] calculateLightDistances() throws NullPointerException {
        double[][] adj = new double[Location.getLocations().size()][Location.getLocations().size()];
        double inf = Double.MAX_VALUE;

		/* Just initialization of the matrix */
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

        /* Getting distances for all paths */
        for (int i = 0; i < Path.getPaths().size(); i++) {
            Location from = Location.getLocationById(Path.getPaths().get(i).getFrom().getId());
            Location to = Location.getLocationById(Path.getPaths().get(i).getTo().getId());
            double distance = Calculator.directDistance(from, to);
			
			if (Settings.isTestMode()) { /* If the test mode is turned on, all distances is going to be 20 */
				distance = 20;
			}

            adj[Path.getPaths().get(i).getFrom().getId()-1][Path.getPaths().get(i).getTo().getId()-1] = Math.min(adj[Path.getPaths().get(i).getFrom().getId()-1][Path.getPaths().get(i).getTo().getId()-1], distance);
            adj[Path.getPaths().get(i).getTo().getId()-1][Path.getPaths().get(i).getFrom().getId()-1] = Math.min(adj[Path.getPaths().get(i).getTo().getId()-1][Path.getPaths().get(i).getFrom().getId()-1], distance);
        }

        return adj;
    }

    /** 
	 * Function to render map, getting all shortest paths and distances of all possible Locations
     */
	public static void render()
	{
        cost = new double[Location.getLocations().size()][Location.getLocations().size()];
        path = new int[Location.getLocations().size()][Location.getLocations().size()];

        double[][] adjMatrix = calculateLightDistances();
        
		/* base case */
		if (adjMatrix.length == 0) {
			return;
		}

		int n = adjMatrix.length;

		/* initialize cost[] and path[] */
		for (int v = 0; v < n; v++)
		{
			for (int u = 0; u < n; u++)
			{
				/* initially, cost would be the same as the weight of the edge */
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

		/* run Floyd–Warshall */
		for (int k = 0; k < n; k++)
		{
			for (int v = 0; v < n; v++)
			{
				for (int u = 0; u < n; u++)
				{
					/*
					* If vertex `k` is on the shortest path from `v` to `u`,
					* then update the value of cost[v][u] and path[v][u]
					*/

					if (cost[v][k] != Double.MAX_VALUE
							&& cost[k][u] != Double.MAX_VALUE
							&& (cost[v][k] + cost[k][u] < cost[v][u]))
					{
						cost[v][u] = cost[v][k] + cost[k][u];
						path[v][u] = path[k][u];
					}
				}

				/*
				* if diagonal elements become negative, the
				* graph contains a negative-weight cycle
				*/
				if (cost[v][v] < 0) {
					System.out.println("Negative-weight cycle found!");
					System.exit(3);
				}
			}
		}

        setRendered(true);
	}

	/**
	 * Function to get if the map has been rendered
	 * @return if the map has been rendered
	 */
	public static boolean isRendered() {
		return isRendered;
	}

	/**
	 * Function to set if the map has been rendered
	 * @param value if the map has been rendered
	 */
	private static void setRendered(boolean value) {
		isRendered = value;
	}
}
