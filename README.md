# PathFinding
Simple Java Swing application that allows the user to draw a maze, set the initial and the objective positions and watch how different search algorithms find the way to the target.



## Reservations and possible improvements
Right now the components in the UI are not been updated and disabled in the middle of a search, the user can't cancel an animation or a search if he wanted.

____
## AIToolkit
You may reuse the search algorithms by including the "``AIToolkit``" packet to your application. To use any of the search algorithms, you must implement and provide it with an instance of the ``SearchRules`` interface, which specifies how to generate the next steps and what constitutes the end of search. In your instance you may implement any methods necessary to define your search problem.

### Algorithms
#### Non-informed search
Non informed search algorithms are those that don't use any kind of guide function to find the target, but rather, scans the tree and navigates using a defined order.

##### Depth-First Search
The most basic search algorithm: it scans the tree and always chooses the first element to navigate to. It doesn't ensures to find the best result, but rather the first most left side result on the tree. This implementation makes sure we don't repeat previously tried steps, ensuring that at least a result can be found, if it exists.  
<center>
<img src="https://i.imgur.com/KY8VthS.gif" width="300" title="Depth-First Search Example">
</center>

##### Breath-First Search
BFS is the most memory and time consuming search algorithm, but also the only one capable of always finding the best result, if it can be founded.
<center>
<img src="https://i.imgur.com/F7XEEId.gif" width="300" title="Breath-First Search Example">
</center>

____
#### Informed search
Those are the search algorithms that use some kind of "direction function" for deciding which node of the tree is the best to navigate to when searching for the objective. This can be done by assigning or informing a cost to the next state, which the algorithm will try to minimize. They also may use a "heuristic" to determinate what is the best path to the objective.

##### Uniform Cost Search
This search algorithm only takes into consideration the cost of the steps to navigate the tree. Its' objective is to minimize the overall cost of the path to the objective, however, because the cost in this particular case is simple the distance from the start position, it behaves exactly like a Breach-First Search. The cost function can be specified when implementing the ``SearchRules`` interface.

<center>
<img src="https://i.imgur.com/BoxmVSF.gif" width="300" title="Uniform Search Example">
</center>

##### Greedy Search
Greedy Search is an informed search algorithm that uses an "heuristic" function and always navigates to the element which has the best chance to find the objective state. Although this seems to be the most effective at quickly solving the problem, it can sometimes leads to not that effective results. The effect of this algorithm is pretty clear in this example application, as it doesn't take into consideration any path but the one with highest heuristics and most times it gets trapped within a wall when another path, that although isn't the closest, may lead to the objective faster.
<center>
<img src="https://i.imgur.com/NhdK7mZ.gif" width="300" title="Greedy Search Example">
</center>


##### A* Search
The A* algorithm uses the combination of a heuristic and a cost function. Its' point is to direct the search to towards the object state but also give a chance to less costly (with worst heuristics) paths to consider. In practice, the A* will consider more ways to the object which might help find a new better path than the Greedy Search. Although it may not generate results as good as the Breath-First search, memory and time complexity is definitely lower, as it never consider states which may not be used to generate a path to the objective state.
<center>
<img src="https://i.imgur.com/iO4Chwg.gif" width="300" title="A* Search Example">
</center>

____
## Licence
[MIT License](https://opensource.org/licenses/MIT).