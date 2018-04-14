/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIToolkit.Search.NonInformedSearch;

import java.util.LinkedList;
import AIToolkit.Search.Search;
import AIToolkit.Search.SearchRules;

/**
 *
 * @author luan
 */
public class BreadthFirstSearch<T> extends Search<T> {

    private final LinkedList<T> stateList;

    public BreadthFirstSearch(SearchRules rules) {
        super(rules);
        stateList = new LinkedList<>();
    }

    @Override
    public T getNextState() {
        return stateList.removeLast();
    }

    @Override
    public void addNextState(T state) {
        stateList.addFirst(state);
    }

    @Override
    public boolean hasSearchEnded() {
        return stateList.isEmpty();
    }

}
