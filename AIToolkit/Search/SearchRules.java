/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIToolkit.Search;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author luan
 */
public interface SearchRules<T> {

    ArrayList<T> generateNextStates(T currentState);
    
    Comparator<T> getPriorityByCost();
    Comparator<T> getPriorityByHeuristic();
    Comparator<T> getPriorityByHeuristicAndCost();

    boolean hasFinished(T currentState);

}
