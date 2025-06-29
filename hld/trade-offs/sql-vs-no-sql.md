# SQL vs NO-SQL


## Use SQL when
- You need strong consistency, ACID. Transactions etc (Banking software)
- Data is structured (won't change much), relational data needs multiple tables and joins
- You have to perform complex queries. Joins Aggregation



## Use NoSQL when
- Eventual consistency is okay, writes will be faster and can take high loads
- Excellent horizontal scaling with shards
- Schema changes are often, Can have rapid development