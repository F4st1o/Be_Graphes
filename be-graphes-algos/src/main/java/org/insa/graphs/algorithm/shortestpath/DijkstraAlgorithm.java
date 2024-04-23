package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {


    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        int nbNode = data.getGraph().size();
        Label[] tab = new Label[nbNode];
        int cptMarque = 0;
        double coutRealise = 0;
        BinaryHeap<Label> tas = new BinaryHeap<Label>();

        int i;
        Label x;
        for(i=0;i<tab.length;i++)
        {
            tab[i] = new Label(data.getGraph().get(i), null, false);
        }
        tab[data.getOrigin().getId()].modifCoutRealise(coutRealise);
        tas.insert(tab[data.getOrigin().getId()]);
        while(cptMarque < nbNode)
        {
            x = tas.deleteMin();
            x.setMarquetrue();
            //si x == destinaton alors nbmarque=nbNode "boost performances"
            cptMarque++;
            coutRealise = x.getCoutRealise();
            for(Arc elem : x.getCourant().getSuccessors())
            {
                Label y = tab[elem.getDestination().getId()];
                if(!y.getMarque())
                {
                    double coutActuel = coutRealise+elem.getMinimumTravelTime();
                    if(coutActuel<y.getCost())
                    {
                        y.modifCoutRealise(coutActuel);
                        tas.insert(y);
                        y.modifPere(elem);
                    }
                }
                
            }
        }

        if (tab[data.getDestination().getId()].getPere() == null) 
        {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else 
        {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = tab[data.getDestination().getId()].getPere();
            while (arc != null) {
                arcs.add(arc);
                arc = tab[arc.getOrigin().getId()].getPere();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));
        }

        return solution;
    }

}
