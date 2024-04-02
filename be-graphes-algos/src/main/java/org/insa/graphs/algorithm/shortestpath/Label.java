package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;

public class Label {
    
    private final Node sommetCourant;
    private boolean marque = false;
    private double coutChemin = Double.POSITIVE_INFINITY;
    private Arc arcPere;

    public Label(Node sommet, Arc pere)
    {
        this.sommetCourant = sommet;
        this.arcPere = pere;
    }

    public Node getCourant()
    {
        return this.sommetCourant;
    }

    public boolean getMarque()
    {
        return this.marque;
    }

    public double getCout()
    {
        return this.coutChemin;
    }

    public Arc getPere()
    {
        return this.arcPere;
    }

    public double getCost()
    {
        return this.coutChemin;
    }

}
