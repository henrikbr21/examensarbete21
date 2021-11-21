# Abstract

In making a chess engine using alpha-beta search there are many ways to reduce its execution time and thereby improve
overall performance. One way is by using move ordering heuristics, which are heuristics that reduce the search space by
attempting to make a rough estimation about which moves are most promising before starting a search of a board position.
Another example is using a transposition table, which can reduce redundant computations. A third example is
multithreading, which is an attempt to utilize the several cores found in most modern computer to improve the amount of
information found per unit of time.

The purpose of this thesis is to investigate four different commonly used algorithms and heuristics to see how much they
can reduce the execution time of a chess engine. These algorithms are the move-ordering heuristic known as MVV-LVA, a
transposition table, iterative deepening and Lazy SMP parallel search.

A Java chess engine named KLAS was implemented and used to measure the impact of these four algorithms and heuristics.
The largest impact came from the MVV-LVA move ordering heuristic which decreased average execution time by 68.5%. Second
most significant was the transposition table, which led to an average decrease of 39.8% execution time. Lazy SMP and
iterative deepening resulted in average execution time reduction of 33.1% and 28.7% respectively.

Keywords: chess engine, alpha/beta-search, minimax, move ordering, hash table, iterative deepening, Lazy SMP parallel
search. 
