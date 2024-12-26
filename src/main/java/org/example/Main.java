package org.example;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private MapView mapView;

    private double globalOffset = 0.00035;

    public static void main(String[] args) {
        Application.launch(args);

        /*Main main = new Main();
        ArcGISRuntimeEnvironment.setApiKey(
                "AAPTxy8BH1VEsoebNVZXo8HurErUs89yc0SZaG6rzHAFBlc6LeYbhTCCU5gp23b3FEFVbsUicckVtLlfpjzJzwPBklHDf5tw-hmKRBh-o_gPJhBHS8Qh3cJPMaA6qZoq-_PYTNlHyCas2i-tXO-EzlusJt2Vjtgikv9DwjthXfKNahKXhv5phnf3Jc9i2bFmuqZNHw9vG2uqwI3ayTEQNtRes6UOzaP_AgK0Vt7BFhwir9M.AT1_jW2wL0Mv");

        main.calculateMappingWaylinePointList(new String[]{
                "118.014102598479,28.500967827075502",
                "118.013215434906,28.500791575530684",
                "118.01263709385604,28.5010921228371",
                "118.01251858058764,28.501973885705077",
                "118.01330530938101,28.502463303120102",
                "118.01418485872101,28.502351628912802",
                "118.01485820449903,28.502075878755903",
                "118.01537101040304,28.501707453921103",
                "118.014102598479,28.500967827075502"
        });
        main.stop();*/
    }

    @Override
    public void start(Stage stage) {
        // set the title and size of the stage and show it
        stage.setTitle("Display a map tutorial");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();

        // create a JavaFX scene with a stack pane as the root node, and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        ArcGISRuntimeEnvironment.setApiKey(
                "AAPTxy8BH1VEsoebNVZXo8HurErUs89yc0SZaG6rzHAFBlc6LeYbhTCCU5gp23b3FEFVbsUicckVtLlfpjzJzwPBklHDf5tw-hmKRBh-o_gPJhBHS8Qh3cJPMaA6qZoq-_PYTNlHyCas2i-tXO-EzlusJt2Vjtgikv9DwjthXfKNahKXhv5phnf3Jc9i2bFmuqZNHw9vG2uqwI3ayTEQNtRes6UOzaP_AgK0Vt7BFhwir9M.AT1_jW2wL0Mv");

        // create a map view to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);

        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC);
        // set the map on the map view
        mapView.setMap(map);

        mapView.setViewpoint(new Viewpoint(28.501338, 118.01259, 1444.638572));

        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        Point point1 = new Point(118.014102598479,28.500967827075502);
        Point point2 = new Point(118.013215434906,28.500791575530684);
        Point point3 = new Point(118.01263709385604,28.5010921228371);
        Point point4 = new Point(118.01251858058764,28.501973885705077);
        Point point5 = new Point(118.01330530938101,28.502463303120102);
        Point point6 = new Point(118.01418485872101,28.502351628912802);
        Point point7 = new Point(118.01485820449903,28.502075878755903);
        Point point8 = new Point(118.01537101040304,28.501707453921103);
        Point point9 = new Point(118.014102598479,28.500967827075502);

        // Add point graphics
        PointCollection points = new PointCollection(SpatialReferences.getWgs84());
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.add(point8);
        points.add(point9);

        // create a polygon geometry from the point collection
        Polygon polygon = new Polygon(points);

        SimpleLineSymbol blueOutlineSymbol =
                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2);

        // create an orange-red fill symbol with 20% transparency and the opaque blue simple line symbol
        SimpleFillSymbol polygonFillSymbol =
                new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.WHITE, blueOutlineSymbol);

        // create a polygon graphic from the polygon geometry and symbol
        Graphic polygonGraphic = new Graphic(polygon, polygonFillSymbol);

        // add the polygon graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(polygonGraphic);

        //获取多边形内最长的线，包括边和内部的线
        // Generate candidate polylines
//        List<Polyline> candidatePolylines = new ArrayList<>();
//        for (int i = 0; i < points.size(); i++) {
//            for (int j = i + 1; j < points.size(); j++) {
//                PointCollection pc = new PointCollection(SpatialReferences.getWgs84());
//                pc.add(points.get(i));
//                pc.add(points.get(j));
//                Polyline line = new Polyline(pc);
//                candidatePolylines.add(line);
//            }
//        }
//
//        List<Polyline> clippedPolylines = new ArrayList<>();
//        for (Polyline line : candidatePolylines) {
//            Geometry intersection = GeometryEngine.intersection(line, polygon);
//            if (intersection instanceof Polyline) {
//                clippedPolylines.add((Polyline) intersection);
//            }
//        }
//
//        double maxLength = 0;
//        Polyline longestPolyline = null;
//
//        for (Polyline line : clippedPolylines) {
//            double length = GeometryEngine.length(line); // Use geodesic for accurate length on Earth's surface
//            if (length > maxLength) {
//                maxLength = length;
//                longestPolyline = line;
//            }
//        }

        //获取多边形最长的边，不包含内部的连线
        double maxLength = 0;
        Polyline longestPolyline = null;

        // Get all rings (parts) of the polygon
        for (int i = 0; i < polygon.getParts().get(0).size(); i++) {
            // Get two consecutive points
            Point startPoint = polygon.getParts().get(0).get(i).getStartPoint();
            Point endPoint = polygon.getParts().get(0).get(i).getEndPoint();
            PointCollection pointCollection = new PointCollection(SpatialReferences.getWgs84());
            pointCollection.add(startPoint);
            pointCollection.add(endPoint);
            Polyline polyline = new Polyline(pointCollection);
            // Calculate distance between startPoint and endPoint
            double length = GeometryEngine.length(polyline);
            if (length > maxLength) {
                maxLength = length; // Update maxLength if current length is greater
                longestPolyline = polyline;
            }
        }

        double slope = calculateSlope(longestPolyline);

        if (longestPolyline != null) {
            System.out.println("Longest polyline length: " + maxLength);
            System.out.println("Longest polyline: " + longestPolyline.toJson());
            System.out.println("Slope of the polyline: " + slope);
        } else {
            System.out.println("No valid polylines found within the polygon.");
        }

        Point startPoint = longestPolyline.getParts().get(0).getStartPoint();
        Point endPoint = longestPolyline.getParts().get(0).getEndPoint();

        double extendPoint1X = Math.max(startPoint.getX(), endPoint.getX()) + 0.01;
        double extendPoint1Y = calculateY(slope, startPoint, extendPoint1X);
        double extendPoint2X = Math.min(endPoint.getX(), startPoint.getX()) - 0.01;
        double extendPoint2Y = calculateY(slope, endPoint, extendPoint2X);

        //画多条直线，取交点作为航点
        double offset = globalOffset;

        List<Point> pointList = new ArrayList<>();
        int count = 0;

        while (true) {
            PointCollection extendPoints = new PointCollection(SpatialReferences.getWgs84());
            extendPoints.add(new Point(extendPoint1X, extendPoint1Y - offset));
            extendPoints.add(new Point(extendPoint2X, extendPoint2Y - offset));
            Polyline polyline = new Polyline(extendPoints);
            Geometry intersection = GeometryEngine.intersection(polyline, polygon);
            try {
                if (intersection instanceof Polyline) {
                    Point polyStart = ((Polyline) intersection).getParts().get(0).getStartPoint();
                    Point polyEnd = ((Polyline) intersection).getParts().get(0).getEndPoint();
                    if (count % 2 == 0) {
                        pointList.add(polyStart);
                        pointList.add(polyEnd);
                    } else {
                        pointList.add(polyEnd);
                        pointList.add(polyStart);
                    }
                    PointCollection pointCollection = new PointCollection(SpatialReferences.getWgs84());
                    pointCollection.add(polyStart);
                    pointCollection.add(polyEnd);
                    Polyline internalPolyline = new Polyline(pointCollection);
//                    displayPolyline(internalPolyline, graphicsOverlay, new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2));
                }
            } catch (Exception e) {
                break;
            }
            count++;
            offset += globalOffset;
        }

        if (pointList.size() == 0) {
            offset = globalOffset;
            while (true) {
                PointCollection extendPoints = new PointCollection(SpatialReferences.getWgs84());
                extendPoints.add(new Point(extendPoint1X, extendPoint1Y + offset));
                extendPoints.add(new Point(extendPoint2X, extendPoint2Y + offset));
                Polyline polyline = new Polyline(extendPoints);
                Geometry intersection = GeometryEngine.intersection(polyline, polygon);
                try {
                    if (intersection instanceof Polyline) {
                        Point polyStart = ((Polyline) intersection).getParts().get(0).getStartPoint();
                        Point polyEnd = ((Polyline) intersection).getParts().get(0).getEndPoint();
                        if (count % 2 == 0) {
                            pointList.add(polyStart);
                            pointList.add(polyEnd);
                        } else {
                            pointList.add(polyEnd);
                            pointList.add(polyStart);
                        }
                        PointCollection pointCollection = new PointCollection(SpatialReferences.getWgs84());
                        pointCollection.add(polyStart);
                        pointCollection.add(polyEnd);
                        Polyline internalPolyline = new Polyline(pointCollection);
//                        displayPolyline(internalPolyline, graphicsOverlay, new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2));
                    }
                } catch (Exception e) {
                    break;
                }
                count++;
                offset += globalOffset;
            }
        }

        for(int i = 0; i < pointList.size()-1; i++) {
            Point polyStart = pointList.get(i);
            Point polyEnd = pointList.get(i+1);
            PointCollection pointCollection = new PointCollection(SpatialReferences.getWgs84());
            pointCollection.add(polyStart);
            pointCollection.add(polyEnd);
            Polyline internalPolyline = new Polyline(pointCollection);
            displayPolyline(internalPolyline, graphicsOverlay, new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.GREEN, 8));
        }

    }

    public String[] calculateMappingWaylinePointList(String[] paramPoint) {
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC);
//         set the map on the map view
        mapView = new MapView();
        mapView.setMap(map);

        mapView.setViewpoint(new Viewpoint(28.501338, 118.01259, 1444.638572));

        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        // Add point graphics
        PointCollection points = new PointCollection(SpatialReferences.getWgs84());
        for (String point : paramPoint) {
            String[] coodinate = point.split(",");
            points.add(new Point(Double.parseDouble(coodinate[0]), Double.parseDouble(coodinate[1])));
        }

        // create a polygon geometry from the point collection
        Polygon polygon = new Polygon(points);

//        SimpleLineSymbol blueOutlineSymbol =
//                new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 1);

        // create an orange-red fill symbol with 20% transparency and the opaque blue simple line symbol
//        SimpleFillSymbol polygonFillSymbol =
//                new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.WHITE, blueOutlineSymbol);

        // create a polygon graphic from the polygon geometry and symbol
//        Graphic polygonGraphic = new Graphic(polygon, polygonFillSymbol);

        // add the polygon graphic to the graphics overlay
//        graphicsOverlay.getGraphics().add(polygonGraphic);

        //获取多边形内最长的线，包括边和内部的线
        // Generate candidate polylines
//        List<Polyline> candidatePolylines = new ArrayList<>();
//        for (int i = 0; i < points.size(); i++) {
//            for (int j = i + 1; j < points.size(); j++) {
//                PointCollection pc = new PointCollection(SpatialReferences.getWgs84());
//                pc.add(points.get(i));
//                pc.add(points.get(j));
//                Polyline line = new Polyline(pc);
//                candidatePolylines.add(line);
//            }
//        }
//
//        List<Polyline> clippedPolylines = new ArrayList<>();
//        for (Polyline line : candidatePolylines) {
//            Geometry intersection = GeometryEngine.intersection(line, polygon);
//            if (intersection instanceof Polyline) {
//                clippedPolylines.add((Polyline) intersection);
//            }
//        }
//
//        double maxLength = 0;
//        Polyline longestPolyline = null;
//
//        for (Polyline line : clippedPolylines) {
//            double length = GeometryEngine.length(line); // Use geodesic for accurate length on Earth's surface
//            if (length > maxLength) {
//                maxLength = length;
//                longestPolyline = line;
//            }
//        }

        //获取多边形最长的边，不包含内部的连线
        double maxLength = 0;
        Polyline longestPolyline = null;

        // Get all rings (parts) of the polygon
        for (int i = 0; i < polygon.getParts().get(0).size(); i++) {
            // Get two consecutive points
            Point startPoint = polygon.getParts().get(0).get(i).getStartPoint();
            Point endPoint = polygon.getParts().get(0).get(i).getEndPoint();
            PointCollection pointCollection = new PointCollection(SpatialReferences.getWgs84());
            pointCollection.add(startPoint);
            pointCollection.add(endPoint);
            Polyline polyline = new Polyline(pointCollection);
            // Calculate distance between startPoint and endPoint
            double length = GeometryEngine.length(polyline);
            if (length > maxLength) {
                maxLength = length; // Update maxLength if current length is greater
                longestPolyline = polyline;
            }
        }

        double slope = calculateSlope(longestPolyline);

        if (longestPolyline != null) {
            System.out.println("Longest polyline length: " + maxLength);
            System.out.println("Longest polyline: " + longestPolyline.toJson());
            System.out.println("Slope of the polyline: " + slope);
        } else {
            System.out.println("No valid polylines found within the polygon.");
        }

        Point startPoint = longestPolyline.getParts().get(0).getStartPoint();
        Point endPoint = longestPolyline.getParts().get(0).getEndPoint();

        double extendPoint1X = Math.max(startPoint.getX(), endPoint.getX()) + 0.01;
        double extendPoint1Y = calculateY(slope, startPoint, extendPoint1X);
        double extendPoint2X = Math.min(endPoint.getX(), startPoint.getX()) - 0.01;
        double extendPoint2Y = calculateY(slope, endPoint, extendPoint2X);

        //画多条直线，取交点作为航点
        double offset = globalOffset;

        List<Point> pointList = new ArrayList<>();
        int count = 0;

        while (true) {
            PointCollection extendPoints = new PointCollection(SpatialReferences.getWgs84());
            extendPoints.add(new Point(extendPoint1X, extendPoint1Y - offset));
            extendPoints.add(new Point(extendPoint2X, extendPoint2Y - offset));
            Polyline polyline = new Polyline(extendPoints);
            Geometry intersection = GeometryEngine.intersection(polyline, polygon);
            try {
                if (intersection instanceof Polyline) {
                    Point polyStart = ((Polyline) intersection).getParts().get(0).getStartPoint();
                    Point polyEnd = ((Polyline) intersection).getParts().get(0).getEndPoint();
                    if (count % 2 == 0) {
                        pointList.add(polyStart);
                        pointList.add(polyEnd);
                    } else {
                        pointList.add(polyEnd);
                        pointList.add(polyStart);
                    }
                    PointCollection pointCollection = new PointCollection(SpatialReferences.getWgs84());
                    pointCollection.add(polyStart);
                    pointCollection.add(polyEnd);
                    Polyline internalPolyline = new Polyline(pointCollection);
                    displayPolyline(internalPolyline, graphicsOverlay, new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2));
                }
            } catch (Exception e) {
                break;
            }
            count++;
            offset += globalOffset;
        }

        if (pointList.size() == 0) {
            offset = globalOffset;
            while (true) {
                PointCollection extendPoints = new PointCollection(SpatialReferences.getWgs84());
                extendPoints.add(new Point(extendPoint1X, extendPoint1Y + offset));
                extendPoints.add(new Point(extendPoint2X, extendPoint2Y + offset));
                Polyline polyline = new Polyline(extendPoints);
                Geometry intersection = GeometryEngine.intersection(polyline, polygon);
                try {
                    if (intersection instanceof Polyline) {
                        Point polyStart = ((Polyline) intersection).getParts().get(0).getStartPoint();
                        Point polyEnd = ((Polyline) intersection).getParts().get(0).getEndPoint();
                        if (count % 2 == 0) {
                            pointList.add(polyStart);
                            pointList.add(polyEnd);
                        } else {
                            pointList.add(polyEnd);
                            pointList.add(polyStart);
                        }
                        PointCollection pointCollection = new PointCollection(SpatialReferences.getWgs84());
                        pointCollection.add(polyStart);
                        pointCollection.add(polyEnd);
                        Polyline internalPolyline = new Polyline(pointCollection);
                        displayPolyline(internalPolyline, graphicsOverlay, new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2));
                    }
                } catch (Exception e) {
                    break;
                }
                count++;
                offset += globalOffset;
            }
        }

        String[] res = new String[pointList.size()];
        int i = 0;
        for (Point point : pointList) {
            res[i++] = point.getX() + "," + point.getY();
            System.out.println(point.toJson());
        }

        return res;
    }

    /**
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {
        if (mapView != null) {
            mapView.dispose();
        }
    }

    public static double calculateSlope(Polyline polyline) {
        // Get start and end points
        Point startPoint = polyline.getParts().get(0).getStartPoint();
        Point endPoint = polyline.getParts().get(0).getEndPoint();

        double deltaX = endPoint.getX() - startPoint.getX();

        double deltaY = endPoint.getY() - startPoint.getY();

        // Calculate slope (rise/run)
        if (deltaX != 0) {
            return deltaY / deltaX; // Slope as a ratio
        } else {
            return Double.POSITIVE_INFINITY; // Vertical line case
        }
    }

    public static double calculateY(double slope, Point point, double X) {
        double deltaX = point.getX() - X;
        return point.getY() - slope * (deltaX);
    }

    public static void displayPolyline(Polyline polyline, GraphicsOverlay graphicsOverlay, SimpleLineSymbol polylineSymbol) {
        // create a polyline graphic with the polyline geometry and symbol
        Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);

        // add the polyline graphic to the graphics overlay
        graphicsOverlay.getGraphics().add(polylineGraphic);
    }

}
