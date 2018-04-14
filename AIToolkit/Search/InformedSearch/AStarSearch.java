/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIToolkit.Search.InformedSearch;

import AIToolkit.Search.Search;
import AIToolkit.Search.SearchRules;
import java.util.PriorityQueue;

/**
 *
 * @author luan
 */
public class AStarSearch<T> extends Search<T> {

    private final PriorityQueue<T> stateList;

    public AStarSearch(SearchRules<T> rules) {
        super(rules);
        stateList = new PriorityQueue<>(rules.getPriorityByHeuristicAndCost());
    }

    @Override
    public T getNextState() {
        return stateList.poll();
    }

    @Override
    public void addNextState(T state) {
        stateList.add(state);
    }

    @Override
    public boolean hasSearchEnded() {
        return stateList.isEmpty();
    }

}
