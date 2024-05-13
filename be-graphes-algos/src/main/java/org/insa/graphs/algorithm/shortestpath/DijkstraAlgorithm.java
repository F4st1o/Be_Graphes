package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {


    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    public Label initLabel(Node som)
    {
        return new Label(som, null, false);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        final int nbNode = data.getGraph().size();
        Label[] tab = new Label[nbNode];
        int cptMarque = 0;
        double coutRealise = 0;
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        int i;
        Label x;

        for(i=0;i<tab.length;i++)
        {
            tab[i] = initLabel(data.getGraph().get(i));
        }
        tab[data.getOrigin().getId()].modifCoutRealise(coutRealise);
        tas.insert(tab[data.getOrigin().getId()]);
        notifyOriginProcessed(data.getOrigin());
        while(cptMarque < nbNode && !tas.isEmpty())
        {
            x = tas.deleteMin();
            x.setMarquetrue();
            notifyNodeMarked(x.getCourant());
            if(x.getCourant() == data.getDestination())
            {
                cptMarque = nbNode;
            }

            else
            {
                cptMarque++;
                coutRealise = x.getCoutRealise();
                for(Arc elem : x.getCourant().getSuccessors())
                {
                    if(data.isAllowed(elem))
                    {
                        Label y = tab[elem.getDestination().getId()];
                        if(!y.getMarque())
                        {
                            notifyNodeReached(y.getCourant());
                            double coutActuel = coutRealise+data.getCost(elem);
                            if(y.getCost() == Double.POSITIVE_INFINITY)
                            {
                                y.modifCoutRealise(coutActuel);
                                y.modifPere(elem);
                                tas.insert(y);
                            }

                            else if(coutActuel < y.getCost())
                            {
                                tas.remove(y);
                                y.modifCoutRealise(coutActuel);
                                y.modifPere(elem);
                                tas.insert(y);
                            }
                        }
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
