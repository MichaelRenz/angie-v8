import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TestCollectorForSeveralPointResults {

    private final static List<Point> points = generatePoints();

    private static List<Point> generatePoints() {
        List<Point> result = new ArrayList<>();
        result.add(new Point(1,1));
        result.add(new Point(2,2));
        result.add(new Point(3,3));
        result.add(new Point(1,-1));
        result.add(new Point(2,-2));
        result.add(new Point(3,-3));
        result.add(new Point(-1,-1));
        result.add(new Point(-2,-2));
        result.add(new Point(-3,-3));
        result.add(new Point(-1,1));
        result.add(new Point(-2,2));
        result.add(new Point(-3,3));
        return result;
    }

    private interface PointsResults {
        int getXCoordinate();
        double getDfo();
        String pointsAsString();
    }

    // sum up the x-coordinates of all points
    // sum up the distance from origin for all points with a positive x-coordinate
    // build a String representing all points

    public static void main(String[] args) {
        PointsResults ps = points.stream().parallel()
                                 .collect(... to be done ...);

        System.out.println("sum x coordinate: " + ps.getXCoordinate());
        System.out.println("sum dfo where x > 0: " + ps.getDfo());
        System.out.println(ps.pointsAsString());
    }
}

