package org.insa.graphs.algorithm.ShortestPath;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.insa.graphs.algorithm.*;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.*;
import org.insa.graphs.model.RoadInformation.RoadType;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestsDijkstra {

    private static String mapName;
    private static GraphReader reader;
    private static Graph graph;
    private static ShortestPathSolution solutionDijLength, solutionDijOrigineEqDest, solutionDijTime, solutionDijNoPath, solutionAStarLength, solutionAStarOrigineEqDest, solutionAStarTime, solutionAStarNoPath;
    private static ShortestPathSolution solutionBellLength, solutionBellTime;

    @BeforeClass
    public static void initAll() throws IOException 
    {
        mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";
        reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph = reader.read();  

        List<ArcInspector> filters = ArcInspectorFactory.getAllFilters();
        ShortestPathData dataLength = new ShortestPathData(graph, graph.getNodes().get(8490), graph.getNodes().get(7680), filters.get(0));
        ShortestPathData dataOrigineEqDest = new ShortestPathData(graph, graph.getNodes().get(8490), graph.getNodes().get(8490), filters.get(0));
        ShortestPathData dataTime = new ShortestPathData(graph, graph.getNodes().get(6049), graph.getNodes().get(4710), filters.get(2));
        ShortestPathData dataNoPath = new ShortestPathData(graph, graph.getNodes().get(502), graph.getNodes().get(9315), filters.get(2));
        DijkstraAlgorithm dijkstraLength = new DijkstraAlgorithm(dataLength);
        DijkstraAlgorithm dijkstraOrigineEqDest = new DijkstraAlgorithm(dataOrigineEqDest);
        DijkstraAlgorithm dijkstraTime = new DijkstraAlgorithm(dataTime);
        DijkstraAlgorithm dijkstraNoPath = new DijkstraAlgorithm(dataNoPath);
        AStarAlgorithm AStarAlgorithmLength = new AStarAlgorithm(dataLength);
        AStarAlgorithm AStarAlgorithmOrigineEqDest = new AStarAlgorithm(dataOrigineEqDest);
        AStarAlgorithm AStarAlgorithmTime = new AStarAlgorithm(dataTime);
        AStarAlgorithm AStarAlgorithmNoPath = new AStarAlgorithm(dataNoPath);
        BellmanFordAlgorithm bellmanLength = new BellmanFordAlgorithm(dataLength);
        BellmanFordAlgorithm bellmanTime = new BellmanFordAlgorithm(dataTime);
        solutionDijLength = dijkstraLength.run();
        solutionDijOrigineEqDest = dijkstraOrigineEqDest.run();
        solutionBellLength = bellmanLength.run();
        solutionDijTime = dijkstraTime.run();
        solutionDijNoPath = dijkstraNoPath.run();
        solutionAStarLength = AStarAlgorithmLength.run();
        solutionAStarOrigineEqDest = AStarAlgorithmOrigineEqDest.run();
        solutionAStarTime = AStarAlgorithmTime.run();
        solutionAStarNoPath = AStarAlgorithmNoPath.run();
        solutionBellTime = bellmanTime.run();
    }

    @Test
    public void testDikstraLength() 
    {
        assertTrue(solutionDijLength.getPath().isValid());
        assertEquals(solutionBellLength.getPath().getLength(), solutionDijLength.getPath().getLength(), 0.001);
    }

    @Test
    public void testDijkstraOrigineEqualsDestination()
    {
        assertTrue(solutionDijOrigineEqDest.getStatus() == Status.INFEASIBLE);
    }

    @Test
    public void testDikstraTime()
    {
        assertTrue(solutionDijTime.getPath().isValid());
        assertEquals(solutionBellTime.getPath().getMinimumTravelTime(), solutionDijTime.getPath().getMinimumTravelTime() ,0.001);
    }

    @Test
    public void testDijkstraNoPath()
    {
        assertTrue(solutionDijNoPath.getStatus() == Status.INFEASIBLE);
    }

    @Test
    public void testAStarLength() 
    {
        assertTrue(solutionAStarLength.getPath().isValid());
        assertEquals(solutionBellLength.getPath().getLength(), solutionAStarLength.getPath().getLength(), 0.001);
    }

    @Test
    public void testAStarOrigineEqDestination()
    {
        assertTrue(solutionAStarOrigineEqDest.getStatus() == Status.INFEASIBLE);
    }

    @Test
    public void testAStarTime()
    {
        assertTrue(solutionAStarTime.getPath().isValid());
        assertEquals(solutionBellTime.getPath().getMinimumTravelTime(), solutionAStarTime.getPath().getMinimumTravelTime() ,0.001);
    }

    @Test
    public void testAStarNoPath()
    {
        assertTrue(solutionAStarNoPath.getStatus() == Status.INFEASIBLE);
    }

}
