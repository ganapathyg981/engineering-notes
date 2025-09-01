# 📑 Database Replication Systems — Staff Engineer Interview Notes

---

## 1. Fundamentals

**Replication** = keeping multiple database copies in sync across nodes/clusters.  
Used for:
- High Availability (HA)
- Disaster Recovery (DR)
- Read Scalability
- Geo-distribution

---

## 2. Replication Strategies

| Strategy              | Description | Pros | Cons | Use Cases |
|------------------------|-------------|------|------|-----------|
| **Synchronous**       | Primary waits for replicas to ACK before commit | No data loss (RPO=0) | Higher latency, stalls on slow replica | Banking, payments |
| **Asynchronous**      | Primary commits before replicas | Low latency, high throughput | Possible data loss (RPO > 0) | Social, analytics |
| **Semi-Synchronous**  | Commit after ≥1 replica ACK | Balance between safety & latency | Still risk of partial loss | E-commerce orders |

---

## 3. Topologies

### Single-Leader (Primary–Replica)
- Writes go to leader, replicas are read-only.
- Failover required if leader dies.
- Example: **Postgres streaming replication, MySQL binlog**.

### Multi-Leader (Active–Active)
- Multiple primaries accept writes.
- Requires conflict resolution (LWW, CRDT, app logic).
- Example: **Aurora Global Database, Active-Active MySQL**.

### Leaderless (Quorum-Based)
- Any node can take reads/writes.
- **Consistency ensured by quorum**: R + W > N.
- Example: **Cassandra, DynamoDB**.

---

## 4. Replication Granularity

- **Statement-based** → replicate SQL statements.
- **Row-based** → replicate changed rows (binlog row mode).
- **Logical** → replicate at record/transaction level (CDC, Debezium).
- **Physical** → block-level replication (WAL shipping).

---

## 5. Tradeoffs

- **Consistency vs Availability (CAP theorem)**
    - CP: strong consistency, lower availability (Spanner, ZK).
    - AP: eventual consistency, higher availability (Cassandra, DynamoDB).
- **Replication Lag**
    - Causes stale reads.
    - Mitigation: follower freshness checks, session consistency.
- **Conflict Resolution**
    - LWW, vector clocks, CRDTs, or app-defined.
- **Failover Risks**
    - Split-brain (two leaders).
    - Solve with consensus (Raft/Paxos), fencing tokens.

---

## 6. Real-World Examples

- **Postgres** → WAL streaming (physical) + logical replication.
- **MySQL** → Binlog (row/statement), semi-sync plugin.
- **Spanner** → TrueTime, global synchronous replication.
- **Cassandra/Dynamo** → Leaderless, gossip, vector clocks.

---

## 7. System Design Case Studies

### Case 1: Global E-commerce
- Need: writes across regions, fast reads.
- Solution: Multi-leader replication + eventual consistency.
- Conflict resolution: LWW for inventory, CRDT for cart.

### Case 2: Banking
- Need: strict consistency, no lost transactions.
- Solution: Single-leader, synchronous replicas.
- Failover via Raft/consensus.

### Case 3: Analytics
- Need: high ingest, stale reads OK.
- Solution: Asynchronous replication, fan-out to replicas.

---

## 8. Common Q&A

### Q1. How do you prevent data loss in replication?
- Use **synchronous replication** or **quorum writes**.
- Monitor replication lag.
- Apply fencing during failover.

### Q2. How do you handle split-brain?
- Consensus protocols (Raft/Paxos).
- External coordinator (Zookeeper/etcd).
- Fencing tokens to prevent double leaders.

### Q3. What’s replication lag? How to monitor it?
- Delay between commit on primary vs visibility on replica.
- Track LSN (Postgres), binlog position (MySQL), vector clock (Cassandra).
- Alert if exceeds SLO.

### Q4. How do you scale reads globally?
- Deploy geo-distributed replicas.
- Use read-your-writes/session consistency for user flows.
- Serve analytics from async replicas.

### Q5. How do you design CDC (Change Data Capture)?
- Use logical replication or log-based CDC (Debezium, Kafka Connect).
- Publish changes to Kafka for downstream consumers.
- Ensure idempotency and ordering.
# 📑 Database Replication Q&A — Staff Engineer Level

---

## Q1. What are the advantages, disadvantages, and use cases of **Single-Leader, Multi-Leader, and Leaderless Replication**?

### Single-Leader Replication
**Advantages**
- Simpler architecture (one source of truth).
- Strong consistency for writes.
- Easy conflict resolution (all writes go to leader).

**Disadvantages**
- Leader is a bottleneck for writes.
- Failover needed on leader crash → downtime risk.
- Read scalability limited to replicas only.

**When to Use**
- Banking, financial systems, order management — where **consistency > availability**.
- Small/medium-scale apps needing simpler HA setup.

---

### Multi-Leader Replication
**Advantages**
- Writes possible in multiple regions (low latency for distributed users).
- Higher availability (no single leader bottleneck).
- Resilient to regional failures.

**Disadvantages**
- Conflict resolution required (LWW, CRDT, app logic).
- Higher complexity in schema changes and operational overhead.
- Replication lag may cause divergent states.

**When to Use**
- Global SaaS apps with multi-region users.
- Collaborative apps (e.g., Google Docs, CRDT-based systems).
- Use when **availability + write locality > strict consistency**.

---

### Leaderless Replication
**Advantages**
- No single point of failure.
- High availability and fault tolerance.
- Any node can serve reads/writes (elastic scaling).

**Disadvantages**
- Application must handle eventual consistency.
- Complex read-repair, hinted handoff, quorum logic.
- Higher client-side complexity.

**When to Use**
- Large-scale distributed systems (Cassandra, DynamoDB).
- Analytics or social platforms where **eventual consistency is acceptable**.
- Systems needing to survive **network partitions gracefully**.

---

## Q2. What are the advantages, disadvantages, and use cases of **Synchronous, Asynchronous, and Semi-Synchronous Replication**?

### Synchronous Replication
**Advantages**
- Strong consistency, no data loss (RPO=0).
- Safe failover (replicas always up-to-date).

**Disadvantages**
- Higher latency (every commit waits for replica ACK).
- Throughput limited by slowest replica.
- Risk of stall during network partitions.

**When to Use**
- Banking, stock trading, mission-critical apps where **zero data loss** is mandatory.

---

### Asynchronous Replication
**Advantages**
- Very low latency for writes.
- High throughput (leader doesn’t block on replicas).
- Good for scaling read-heavy workloads.

**Disadvantages**
- Risk of data loss on failover (replication lag).
- Stale reads from replicas.
- Eventual consistency only.

**When to Use**
- Social media feeds, analytics, logging systems where **availability > consistency**.

---

### Semi-Synchronous Replication
**Advantages**
- Balance between safety & performance.
- At least one replica confirms → reduced risk of data loss.
- Faster than full synchronous, safer than full async.

**Disadvantages**
- Still possible to lose data if confirming replica also fails.
- Increased latency compared to async.

**When to Use**
- E-commerce, order systems where **most data must survive** but ultra-low latency is still important.

---

---
# 📑 Relationship Between Replication Modes & Topologies

---

## Q3. How are Synchronous/Asynchronous/Semi-Synchronous replication related to Single-Leader/Multi-Leader/Leaderless topologies?

Think of them as **two orthogonal axes**:

- **Topology axis** → *Who can accept writes?*
  - Single-Leader
  - Multi-Leader
  - Leaderless

- **Replication mode axis** → *How are writes propagated and acknowledged?*
  - Synchronous
  - Asynchronous
  - Semi-Synchronous

These combine to define the actual system behavior.

---

### 1. Single-Leader
- **Sync**: Strong consistency, RPO=0 (e.g., Postgres sync replicas, Spanner leader region).
- **Async**: Common default (e.g., MySQL async binlog, Postgres streaming replication).
- **Semi-Sync**: Middle ground (e.g., MySQL semi-sync plugin).

**Key Insight**: Mode here affects **RPO (data loss)** and **latency** of failover.

---

### 2. Multi-Leader
- **Sync**: Impractical across WAN (high latency). Used only in special global DBs (Spanner, Aurora Global DB).
- **Async**: Most common (conflict resolution required, e.g., Active-Active MySQL, CouchDB).
- **Semi-Sync**: Rare but possible in regional clusters.

**Key Insight**: Multi-leader almost always async → otherwise write latency becomes prohibitive across regions.

---

### 3. Leaderless
- **Sync** = Quorum Writes (W+R > N). Ensures strong consistency at higher latency.
- **Async** = Eventual consistency with low latency (reads may be stale until repaired).
- **Semi-Sync** = Configurable durability levels (e.g., Cassandra consistency levels: ONE, QUORUM, ALL).

**Key Insight**: Replication “mode” here is controlled by **consistency level settings** chosen per request.

---

## Summary Matrix

| Topology        | Sync Mode Example                  | Async Mode Example                        | Semi-Sync / Quorum Example                |
|-----------------|------------------------------------|-------------------------------------------|-------------------------------------------|
| **Single-Leader** | Postgres synchronous standby       | MySQL async binlog replication             | MySQL semi-sync plugin                     |
| **Multi-Leader**  | Spanner global strong consistency  | Active-Active MySQL / CouchDB              | Aurora regional semi-sync                  |
| **Leaderless**    | Cassandra QUORUM (R+W>N)          | Cassandra/Dynamo with ONE consistency      | Cassandra consistency = LOCAL_QUORUM       |

---

✅ **Staff Engineer Takeaway**:
- **Topology** decides *who can write*.
- **Replication mode** decides *how durable/consistent writes are across nodes*.
- Together they determine tradeoffs between **latency, consistency, fault tolerance, and conflict resolution**.

---

## Q4. What is RPO (Recovery Point Objective) in replication systems?

**Definition**  
RPO = *Recovery Point Objective*.  
It measures the **maximum amount of data (in time) that could be lost** if the system crashes and a failover or recovery occurs.

- Example:
  - RPO = 0 → No data loss. Every transaction committed on primary is also persisted on replicas.
  - RPO = 5 seconds → If primary crashes, replicas may be missing last 5 seconds of data.

---

### How it Relates to Replication Modes
- **Synchronous replication** → RPO = 0 (strong consistency, safe failover).
- **Asynchronous replication** → RPO > 0 (depends on replication lag).
- **Semi-Synchronous replication** → RPO close to 0, but still possible to lose the transactions that only reached the leader and not yet multiple replicas.

---

### Staff-Level Example
- Banking systems: **RPO = 0** (cannot lose money transfers).
- Analytics/logging systems: **RPO can be minutes** (eventual replay from logs acceptable).

---

✅ **Staff Engineer Takeaway**:
- RPO = *How much data loss can the business tolerate?*
- Replication strategy should be chosen to meet the RPO target of the system.

---

## Q5. Does synchronous replication reject a transaction if it cannot complete replication?

**Yes.**  
In synchronous replication, a transaction is considered **committed only after the leader and replica(s) acknowledge it**.  
If the replica(s) do not acknowledge within a timeout or are unavailable:

- The leader will **block the transaction commit** until timeout.
- After timeout, behavior depends on system configuration:
  - **Strict Sync Mode** → Transaction is **rejected/rolled back** (to guarantee RPO=0).
  - **Relaxed Sync Mode** → Transaction may fallback to async (risking some data loss).

---

### Examples
- **Postgres synchronous_commit=on** → Client waits until WAL is written on replica; if replica unreachable, commit fails.
- **MySQL Group Replication (sync)** → If quorum not reached, transaction is rejected.
- **Spanner** → Uses TrueTime + Paxos; if replicas unreachable, the write cannot commit until quorum is achieved.

---

### Staff-Level Insight
- Synchronous replication gives **safety (no data loss)** at the cost of **availability**.
- If replicas cannot confirm, the system must **choose**:
  - Reject writes (remain consistent, but risk downtime).
  - Allow writes anyway (become available, but risk data loss).

This is a classic **CAP theorem tradeoff**.

---

✅ **Takeaway**:
- In **pure synchronous systems** → **yes, transactions are rejected** if replication cannot complete.
- In **practical systems** → often configurable (e.g., failover to async, timeouts, or semi-sync plugins).
## 9. Diagrams (Mental Models)

### Single-Leader
```text
[Client] -> [Primary] -> [Replica1]
                        -> [Replica2]                    
```
### Multi-Leader
```text
[Region A Leader] <--> [Region B Leader]
        |                       |
     [Replicas]              [Replicas]
```
### Leaderless (Quorum)
```text
[Node1] <--> [Node2] <--> [Node3]
^   Quorum R/W ensures consistency
```