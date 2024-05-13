package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.*;

public class LabelStar extends Label
{

    private Node nodeFinal = null;


    public LabelStar(Node sommet, Arc pere, Boolean marqueBool, Node nodeArrivee)
    {
        super(sommet, pere, marqueBool);
        this.nodeFinal = nodeArrivee;
    }

    public double getNorme()
    {
        return Math.sqrt(Math.pow((this.getCourant().getPoint().getLatitude() - this.nodeFinal.getPoint().getLatitude()), 2) + Math.pow(this.getCourant().getPoint().getLongitude() - this.nodeFinal.getPoint().getLongitude(), 2));
    }

    @Override
    public double getTotalCost()
    {
        return (this.getCost() + getNorme());
    }
}