package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;

public class Label implements Comparable<Label>{
    
    private final Node sommetCourant;
    private boolean marque = false;
    private double coutChemin = Double.POSITIVE_INFINITY;
    private Arc arcPere;

    public Label(Node sommet, Arc pere, Boolean marqueBool)
    {
        this.sommetCourant = sommet;
        this.arcPere = pere;
        this.marque = marqueBool;
    }

    public Node getCourant()
    {
        return this.sommetCourant;
    }

    public boolean getMarque()
    {
        return this.marque;
    }

    public double getCoutRealise()
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

    public int compareTo(Label autre)
    {
        return (int)(this.getCost() - autre.getCost());
    }

    public void setMarquetrue()
    {
        this.marque = true;
    }

    public void modifCoutRealise(double a)
    {
        this.coutChemin = a;
    }

    public void modifPere(Arc e)
    {
        this.arcPere = e;
    }
}
