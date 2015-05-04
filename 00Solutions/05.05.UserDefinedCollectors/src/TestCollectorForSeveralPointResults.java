import java.awt.Point;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

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

    // sum up the x-coordinates of all points
    // sum up the distance from origin for all points with a positive x-coordinate
    // build a String representing all points


    private interface PointsResults {
        int getXCoordinate();
        double getDfo();
        String pointsAsString();
    }

    private static abstract class PointsResultsImpl implements PointsResults {
        protected int xCoordinate = 0;
        protected double dfo = 0.0;
        protected StringBuilder pointsAsString = new StringBuilder();


        public int getXCoordinate() {
            return xCoordinate;
        }

        public double getDfo() {
            return dfo;
        }

        public String pointsAsString() {
            return pointsAsString.toString();
        }
    }

    private static final class PointResultCollectorHelper extends PointsResultsImpl {
        public void accept(Point p) {
            xCoordinate += p.x;

            if (p.x > 0)
                dfo += Math.sqrt(p.x * p.x + p.y * p.y);

            pointsAsString.append(p.toString()).append("  ");
        }

        public PointResultCollectorHelper combine(PointResultCollectorHelper ps) {
            xCoordinate += ps.xCoordinate;
            dfo += ps.dfo;
            pointsAsString.append(ps.pointsAsString);
            return this;
        }
    }

    public static void main(String[] args) {
        PointsResults ps = points.stream().parallel()
                .collect(PointResultCollectorHelper::new,
                        PointResultCollectorHelper::accept,
                        PointResultCollectorHelper::combine);

        System.out.println("sum x coordinate: " + ps.getXCoordinate());
        System.out.println("sum dfo where x > 0: " + ps.getDfo());
        System.out.println(ps.pointsAsString());
    }
}

