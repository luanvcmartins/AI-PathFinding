/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIToolkit.Search.NonInformedSearch;

import java.util.Stack;
import AIToolkit.Search.Search;
import AIToolkit.Search.SearchRules;

/**
 *
 * @author luan
 */
public class DepthFirstSearch<T> extends Search<T> {

    private final Stack<T> stateList;

    public DepthFirstSearch(SearchRules rules) {
        super(rules);
        stateList = new Stack<>();
    }

    @Override
    public void setOnSearchEvents(OnSearchEvents onSearchEvents) {
        this.onSearchEvents = onSearchEvents;
    }

    @Override
    public T getNextState() {
        return stateList.pop();
    }

    @Override
    public void addNextState(T state) {
        stateList.push(state);
    }

    @Override
    public boolean hasSearchEnded() {
        return stateList.isEmpty();
    }

}
