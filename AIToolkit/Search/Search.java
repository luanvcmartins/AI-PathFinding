package AIToolkit.Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luan
 */
public abstract class Search<T> {

    protected OnSearchEvents onSearchEvents = null;

    protected HashSet<T> seenBefore;
    protected HashMap<T, T> treeAnswer;
    protected SearchRules rules;

    private Search() {
        seenBefore = new HashSet<>();
        treeAnswer = new HashMap<>();
    }

    protected Search(SearchRules<T> rules) {
        this();
        this.rules = rules;
    }

    protected abstract T getNextState();

    protected abstract void addNextState(T state);

    protected abstract boolean hasSearchEnded();

    /**
     * Discover the states we can go from the current one and select the next
     * one we should proceed to.
     *
     * @param currentState The current state.
     * @return The state we should go.
     */
    public T goToNextStep(T currentState) {
        return goToNextStep(currentState, null);
    }

    /**
     * Discover the states we can go from the current one and select the next
     * one we should proceed to.
     *
     * @param currentState The current state in the search.
     * @param discoveredTree The discovered states we can access thought this
     * one.
     * @return The next state to go.
     */
    public T goToNextStep(T currentState, ArrayList<T> discoveredTree) {
        // If searchAwnser is empty then the current state is the root of the tree
        // and it must be added as well:
        if (treeAnswer.isEmpty()) {
            seenBefore.add(currentState);
            treeAnswer.put(currentState, null);
        }

        // Now we will generate the next states from the current one:
        for (Object state : rules.generateNextStates(currentState)) {
            // We will ignore any states we have already seen imediately:
            if (!seenBefore.contains((T) state)) {
                // We haven't seen this state before, so we will add it to the list:
                addNextState((T) state);
                treeAnswer.put((T) state, (T) currentState);

                // If the user wants to see the generated states we will add it to
                // the answer. This will be used to render the orange blocks:
                if (discoveredTree != null) {
                    discoveredTree.add((T) state);
                }
            }
        }

        // Now we will select the next state to navigate:
        T nextState;
        do {
            // We will check if we still have states to go:
            if (hasSearchEnded()) {
                // We don't, we need to stop the search because there is no
                // result to be found.
                if (onSearchEvents != null) {
                    // If we have an interface to report events, we notify
                    // that the search has ended with no results?
                    onSearchEvents.onImpossibleToFinish();
                }
                return currentState;
            }

            nextState = getNextState();
            // We will repeat tyhe process of getting a new state until
            // we find one we haven't seen before. States we already checked
            // needs to be ignored.
        } while (seenBefore.contains(nextState));

        // We shouldn't go back to this state in the future, so we will add it
        // to the "seen states" list to remember it.
        seenBefore.add(nextState);

        // Now that have a new state, we need to verify if the search
        // has ended:
        verifyIfFinishedAndProceed(nextState);

        // Finally, we return the next state of the search:
        return nextState;
    }

    /**
     * This function verifies if the search has ended and if so, it reports the
     * results.
     *
     * @param currentState The current state.
     */
    protected void verifyIfFinishedAndProceed(T currentState) {
        if (rules.hasFinished(currentState)) {
            // The search has finished so we check to see if we got an interface to report to:
            if (onSearchEvents != null) {
                // We first create the path back to the tree's root:
                ArrayList path = new ArrayList();
                do {
                    path.add(currentState);
                    currentState = treeAnswer.get(currentState);
                } while (currentState != null);
                // After we got the path, we call the "onFinish" method:
                onSearchEvents.onFinished(path);
            }
        }
    }

    /**
     * Set an interface to be notified of new events in the search.
     *
     * @param onSearchEvents Your instance of the events interface.
     */
    public void setOnSearchEvents(OnSearchEvents onSearchEvents) {
        this.onSearchEvents = onSearchEvents;
    }

    /**
     * Interface to receive notification when new events happen.
     */
    public interface OnSearchEvents {

        void onFinished(ArrayList path);

        void onImpossibleToFinish();
    }
}
