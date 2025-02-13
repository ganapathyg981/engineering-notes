# Backtracking

## Problem
### Subsets
#### Intuition and approach
- Go across each element
- Either include that element or discard it
- Pass an empty list
- Add the element to list and do next recursion call
- Without the element in the list, do the next recursion call. 
- You can do that above by removing the last element from the list
### Combination Sum
- You have to pass another parameter target sum
- You can pass the target sum and reduce it by chosen element
- Pass the same index in one call to include the same in the next call too
- Pass the next index to skip that index
- Sort the array before for optimal stuff