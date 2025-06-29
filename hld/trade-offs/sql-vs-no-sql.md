# SQL vs NO-SQL


## Use SQL when
- You need strong consistency, ACID. Transactions etc (Banking software)
- Data is structured (won't change much), relational data needs multiple tables and joins
- You have to perform complex queries. Joins Aggregation



## Use NoSQL when
- Eventual consistency is okay, writes will be faster and can take high loads
- Excellent horizontal scaling with shards
- Schema changes are often, Can have rapid development


# SQL vs NoSQL + Sharding & Replication

---

## 📘 SQL (Relational Databases)

### ✅ Use When:
- Strong consistency (ACID transactions) is required
- Schema is well-defined and doesn’t change frequently
- Relationships and joins across tables are needed
- Complex queries and aggregations are necessary

### Examples:
- **PostgreSQL**, **MySQL**, **Oracle**, **SQL Server**

### Strengths:
- ACID compliance
- Joins & relationships
- Structured Query Language (SQL)
- Mature tooling and ecosystem

### Weaknesses:
- Scaling is **vertical** by default (can be expensive)
- Schema changes can be painful
- Limited flexibility for unstructured data

---

## 📕 NoSQL (Non-relational Databases)

### ✅ Use When:
- High write/read throughput required
- Schema-less, evolving data model
- Horizontal scaling (web-scale apps)
- Eventual consistency is acceptable

### Types:
- **Key-Value** (Redis, DynamoDB)
- **Document** (MongoDB, Couchbase)
- **Wide-column** (Cassandra, HBase)
- **Graph** (Neo4j)

### Strengths:
- Horizontal scalability via sharding
- Schema flexibility
- High availability and partition tolerance
- Suited for real-time analytics and distributed systems

### Weaknesses:
- Weaker consistency guarantees (CAP: CP or AP)
- Complex joins/transactions are hard or unsupported
- Querying flexibility varies by type

---

## ⚖️ SQL vs NoSQL Comparison

| Feature             | SQL                         | NoSQL                          |
|---------------------|------------------------------|----------------------------------|
| Schema              | Fixed schema                | Flexible schema                 |
| Transactions        | ACID                        | BASE (eventual consistency)     |
| Scaling             | Vertical (scale-up)         | Horizontal (scale-out)          |
| Joins               | Supported                   | Rarely supported                |
| Consistency         | Strong                      | Eventual                        |
| Use Case            | Banking, ERP, traditional   | Social media, IoT, analytics    |
| Tools               | Postgres, MySQL, Oracle     | MongoDB, Cassandra, DynamoDB    |

---

## 🧬 Replication

### ✅ What:
Replication is the process of **copying data** from one database node to one or more replicas to improve **availability** and **fault tolerance**.

### 🔁 Types:
- **Master-Slave (Primary-Replica)**:
    - Writes go to master, reads can be from replicas
- **Multi-Master**:
    - Multiple nodes accept writes (complex conflict resolution)
- **Synchronous**:
    - Writes are acknowledged only when all replicas confirm
    - Stronger consistency, higher latency
- **Asynchronous**:
    - Faster writes, but risk of lag and stale reads

### 📌 Use Cases:
- Fault tolerance
- Read scalability
- Geo-distributed reads (global apps)

---

## 🧩 Sharding

### ✅ What:
Sharding is the technique of **splitting data horizontally** across multiple nodes to enable **scalability**.

Each shard holds a subset of the data.

### 🧱 Approaches:
- **Hash-based Sharding**:
    - Uniform distribution, but hard to re-shard
- **Range-based Sharding**:
    - Easy to query range data but can be skewed
- **Directory-based Sharding**:
    - Lookup table to find shard location

### ⚠️ Challenges:
- Cross-shard joins/transactions
- Hotspots if data distribution is uneven
- Rebalancing shards during scale-out
- Increased operational complexity

### 🧮 Use Cases:
- High write throughput systems
- Large user base
- Partitioning by customer ID, region, etc.

---

## 🔺 Sharding vs Replication

| Feature         | Sharding                      | Replication                        |
|------------------|-------------------------------|-------------------------------------|
| Purpose          | Scale horizontally            | Improve availability                |
| Data Copy        | Different data per shard      | Same data on all replicas           |
| Failover         | Limited to affected shard     | Replicas take over if primary fails |
| Complexity       | Higher query logic, rebalancing| Conflict handling, sync delays      |

---

## ❓ Interview Questions

### SQL vs NoSQL
- When would you choose SQL over NoSQL?
- What trade-offs do you consider when designing a schema?
- Can NoSQL provide ACID guarantees?

### Replication
- How does asynchronous replication affect consistency?
- How do you handle replica lag in production?

### Sharding
- How would you shard a large user table?
- What issues arise with range-based sharding?
- How do you reshard a live system without downtime?

---

## 🛠 Best Practices
- Prefer **replication** for read scalability and availability.
- Use **sharding** for write scalability or huge datasets.
- Combine both in high-scale systems (e.g., Twitter, Instagram).
- Monitor replication lag, shard skew, and query performance.

# 🧱 When Do You Need Sharding? (SQL + NoSQL)

---

## ⚠️ What is Sharding?

Sharding is **horizontal partitioning** of data across multiple machines/nodes to support **scalability**, especially write-heavy or large-volume datasets.

---

## ✅ How Much Data Can You Handle *Without* Sharding?

> No hard limit — it depends on database type, hardware, and query patterns.

### ✅ Approximate Guidelines

| Database     | Max Rows / Size Before Sharding | Notes |
|--------------|----------------------------------|-------|
| PostgreSQL   | ~100M rows or 1–2 TB per table   | Use table partitioning, good indexes |
| MySQL        | ~50–100M rows/table              | Use InnoDB, optimize indexes |
| MongoDB      | ~50M–100M docs or 1–2 TB working set | Watch RAM usage & working set fit |
| Cassandra    | Auto-sharded via partition keys  | Scales horizontally from the start |
| DynamoDB     | No sharding needed by user       | Handles scale with partition keys & throughput config |

---

## 📊 Factors That Affect When You Need Sharding

| Factor                | Impact                                            |
|------------------------|--------------------------------------------------|
| **RAM/CPU/SSD**        | Bigger servers can delay need for sharding       |
| **Query pattern**      | Heavy joins, full scans trigger scaling early    |
| **Indexes**            | Proper indexing = less stress                   |
| **Write/Read load**    | >1K QPS writes or 10K QPS reads may bottleneck   |
| **Data size**          | >1–2 TB per table/node = potential performance risk |
| **High cardinality**   | Larger indexes → more memory, slower queries     |

---

## 🚧 Warning Signs That You Need Sharding

1. 📉 Query latency is increasing as data grows
2. 🔁 Write throughput is maxing out per node
3. 🧠 RAM no longer holds working set
4. 💾 Disk I/O becomes the bottleneck
5. 📊 Indexes are bloated
6. 🔥 Single table > 100M–500M rows (depending on ops)
7. ❌ Replication lags, failovers slow down

---

## 🧪 What To Try Before Sharding

- ✅ Add **read replicas** (scale reads)
- ✅ Use **caching** (Redis, CDN)
- ✅ Apply **table partitioning** (Postgres, MySQL)
- ✅ Use **materialized views** or summary tables
- ✅ Compress large data (e.g., JSON blobs)
- ✅ Archive cold data to separate table or storage
- ✅ Optimize queries & batch writes

---

## ❓ Interview Q&A

### Q: How many records can you store in a DB before sharding?
**A:** Depends on DB type, but typically:
- Postgres: ~100M rows/table
- MongoDB: ~2TB working set
- Beyond that, you risk query slowdowns and should shard.

### Q: What would you monitor to decide when to shard?
- Query latency
- Storage growth per node
- Index size
- Replication lag
- CPU/memory I/O saturation

### Q: How do you shard a user table?
- Use user_id modulo N
- Or hash(user_id) → consistent hashing
- Avoid range-based sharding if user distribution is skewed

---

## 🧠 Final Tip

> "Don’t shard too early. Don’t shard too late."

Start with good indexing, replication, and caching. Monitor your system. Introduce sharding only when vertical scaling + partitioning + read replicas are no longer enough.