# Storage and Retrieval in Databases
> Databases ideally does two simple things, they store the data you give and give it back when asked for


SQL, No SQL, MapReduce, Graph cypher language all the languages are interface to the devs, but internally.
They store in some format   

- It can be as simple as an append only csv file. Faster to write, but O(n) to read.
- So we use **indexes** as a supporting data structure for faster reads. 
- Index slows down the writes coz it has to manage that data additionally. 
- So, the DB gives use the control to choose our index based on our application's

## Indexes
### Hash Indexes
