# Data models and Query Languages
> “The limits of my language mean the limits of my world.  
Ludwig Wittgenstein, Tractatus Logico-Philosophicus (1922)”

Data models exist so that we provide an simpler abstraction to the end user -> to view, modify or create data with ease.

- At one end the hardware stores electrical currents, magnetic fields, pulses of light ets.
- Then on other end we have JSON, XML, relation DB or graph model
- We may use LSM trees, SSTs to store on disk
## Relational models(SQL)
- Until 2010, This was dominating.
- All most all use case can be fit into it
- Transactions (reservations, banking, book-keeping) and Batch Processing(invoicing, payroll processing)  
- **Use when you need strong consistency, relational store**
## Document Model(No-SQL) (Not Only SQL)
- Need for greater scalability (Large Datasets, High throughput etc) **TODO:** Add more details on how
- Specialized query operations that are not well-supported by SQL
- To get rid of restrictions from relational schema and have free, dynamic, expressive data models
- #### Document Database
  - Stores self contained documents
  - Use when no relationship needed **why**
- #### Graph Database
  - Stores vertices, edges
  - Use Relational data querying is high **when**
- #### Full text search Database
  - Can search documents in any manner
- #### Time series Database
  - Can search and query across time


## Object relational Mismatch
- Mostly we use object-oriented programming languages. No to store this data to SQL there is an awkward translation
layer (**impedance mismatch**)
- ORM Object-relational mapping are used for this. But they are not so great. 