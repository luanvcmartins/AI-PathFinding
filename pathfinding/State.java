/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

import AIToolkit.Distances.Distance;

/**
 *
 * @author luan
 */
public class State {

    private final int X, Y;
    private int cost;

    public State(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public State(int X, int Y, int cost) {
        this.X = X;
        this.Y = Y;
        this.cost = cost;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public State goUp() {
        return new State(X, Y - 1);
    }

    public State goRight() {
        return new State(X, Y + 1);
    }

    public State goLeft() {
        return new State(X - 1, Y);
    }

    public State goDown() {
        return new State(X + 1, Y);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public double getH(State targetState, Distance distance) {
        return distance.getDistance(
                new double[]{getX(), getY()},
                new double[]{targetState.getX(), targetState.getY()}
        );
    }

    @Override
    public String toString() {
        return "{ x: " + X + ", y: " + Y + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            return X == ((State) obj).X && Y == ((State) obj).Y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.X;
        hash = 89 * hash + this.Y;
        return hash;
    }

}
