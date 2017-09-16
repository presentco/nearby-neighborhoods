package present;

import com.google.common.collect.Lists;

import java.util.*;

public class SortedNeighborhoods {

    private SortedNeighborhoods() {}

    public static final List<Neighborhood> BY_LATITUDE = new ArrayList<Neighborhood>(Neighborhoods.ALL);
    public static final List<Neighborhood> BY_LONGITUDE = new ArrayList<Neighborhood>(Neighborhoods.ALL);
    public static final List<Location> LOCATIONS_BY_LATITUDE;
    public static final List<Location> LOCATIONS_BY_LONGITUDE;


    static {
        BY_LATITUDE.sort(Comparator.comparingDouble(o -> o.location().latitude()));
        BY_LONGITUDE.sort(Comparator.comparingDouble(o -> o.location().longitude()));

        // Collections.binarySearch() wanted a List<Location); maybe there's a way to work around but let's just
        // make these here while we're pre-processing
        LOCATIONS_BY_LATITUDE = Lists.transform(SortedNeighborhoods.BY_LATITUDE, o -> o.location());
        LOCATIONS_BY_LONGITUDE = Lists.transform(SortedNeighborhoods.BY_LONGITUDE, o -> o.location());
    }
}
