/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

import AIToolkit.Distances.Distance;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import AIToolkit.Search.SearchRules;
import java.util.Comparator;

/**
 *
 * @author luan
 */
public class PathFindingSearchRules implements SearchRules<State> {

    private JPanel[][] world;
    private State targetPosition;
    private Distance heuristic;

    private PathFindingSearchRules() {
    }

    public PathFindingSearchRules(JPanel[][] world, State targetPosition, Distance heuristic) {
        this.world = world;
        this.heuristic = heuristic;
        this.targetPosition = targetPosition;
    }

    /**
     * Generate the next available steps from the current step. In this context,
     * the user may only go up, left, right or down.
     *
     * @param currentState The current state the user is in.
     * @return An ArrayList of the next states.
     */
    @Override
    public ArrayList<State> generateNextStates(State currentState) {
        ArrayList<State> nextStates = new ArrayList();

        // Let us check if we can go right:
        State rightState = currentState.goRight();
        if (isValidPosition(rightState) && world[rightState.getX()][rightState.getY()].getBackground() != Color.DARK_GRAY) {
            // The block above is free:
            rightState.setCost(currentState.getCost() + 1);
            nextStates.add(rightState);
        }

        // Check if we can go left:
        State leftState = currentState.goLeft();
        if (isValidPosition(leftState) && world[leftState.getX()][leftState.getY()].getBackground() != Color.DARK_GRAY) {
            // The block below is free:
            leftState.setCost(currentState.getCost() + 1);
            nextStates.add(leftState);
        }

        // Check if we can go up:
        State upState = currentState.goUp();
        if (isValidPosition(upState) && world[upState.getX()][upState.getY()].getBackground() != Color.DARK_GRAY) {
            // The block below is free:
            upState.setCost(currentState.getCost() + 1);
            nextStates.add(upState);
        }

        // Check if we can go down
        State downState = currentState.goDown();
        if (isValidPosition(downState) && world[downState.getX()][downState.getY()].getBackground() != Color.DARK_GRAY) {
            // The block below is free:
            downState.setCost(currentState.getCost() + 1);
            nextStates.add(downState);
        }

        return nextStates;
    }

    /**
     * This function returns false if the user stepped out of bounds.
     *
     * @param state The current position to test
     * @return True if the position is valid, false if the user is out of
     * bounds.
     */
    public boolean isValidPosition(State state) {
        return !(state.getX() < 0 || state.getY() < 0 || state.getX() >= world.length || state.getY() >= world[0].length);
    }

    @Override
    public boolean hasFinished(State currentState) {
        return currentState.equals(targetPosition);
    }

    @Override
    public Comparator<State> getPriorityByCost() {
        return (State o1, State o2) -> {
            return o1.getCost() - o2.getCost();
        };
    }

    @Override
    public Comparator<State> getPriorityByHeuristic() {
        return (State o1, State o2) -> {
            return (int) Math.round(o1.getH(targetPosition, heuristic)) - (int) Math.round(o2.getH(targetPosition, heuristic));
        };
    }

    @Override
    public Comparator<State> getPriorityByHeuristicAndCost() {
        return (State o1, State o2) -> {
            return (int) Math.round(o1.getH(targetPosition, heuristic) + o1.getCost()) - (int) Math.round(o2.getH(targetPosition, heuristic) + o2.getCost());
        };
    }

}
