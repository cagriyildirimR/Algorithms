With single stack we cannot calculate shortest path using BFS but we can create queue structure using two stacks and use BFS.

`stack1` and `stack2`

when `stack2` is empty we transfer `stack1` to `stack2`. `pop()` in `stack2` is equivalent of dequeue of a queue. 