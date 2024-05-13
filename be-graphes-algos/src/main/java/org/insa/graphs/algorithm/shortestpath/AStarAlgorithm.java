package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) 
    {
        super(data);
    }

    @Override
    public Label initLabel(Node som)
    {
        return new LabelStar(som, null, false, getInputData().getDestination());
    }
    
}
