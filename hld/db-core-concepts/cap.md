# 🧠 CAP Theorem — FAANG/MAANG Senior Engineer Level Notes

## 📌 What is CAP Theorem?

CAP Theorem (aka Brewer’s Theorem) states:

> A distributed system **can only guarantee two of the following three** in the presence of a network partition:
- **C**onsistency
- **A**vailability
- **P**artition Tolerance

---

## 🔺 Definitions

| Property | Meaning |
|----------|---------|
| **Consistency (C)** | All nodes see the same data at the same time. Every read gets the latest write (linearizability). |
| **Availability (A)** | Every request gets a (non-error) response, regardless of whether it is the latest data. |
| **Partition Tolerance (P)** | The system continues to function despite network partitions (i.e., lost/delayed messages between nodes). |

> ⚠️ In real distributed systems, **partition tolerance is a must**, so you must **choose between C and A** during a partition.

---

## ⚖️ CAP Trade-offs

| Type | Guarantees | Sacrifices | When to Use |
|------|------------|------------|-------------|
| **CP** | Consistency + Partition Tolerance | Availability | Systems where correctness > uptime (e.g., banking, metadata stores) |
| **AP** | Availability + Partition Tolerance | Consistency | Systems where uptime > strong correctness (e.g., shopping cart, feeds) |
| **CA** | Consistency + Availability | ❌ Cannot be achieved in distributed systems (requires no partitions) |

---

## 📂 Real-World System Examples

| System | Type | Notes |
|--------|------|-------|
| **Zookeeper, Etcd** | CP | Prioritize consistency, used for metadata/config |
| **MongoDB (default)** | CP | Blocks during partitions |
| **HBase** | CP | Strong consistency, master-slave |
| **Cassandra** | AP | Eventually consistent, tunable per query |
| **DynamoDB** | AP | Highly available, resolves conflicts on read |
| **Kafka** | Configurable | CP with `acks=all`; AP with `acks=1` |

---

## 📘 Interview Deep Dive Q&A

### ❓ Q1: Can a distributed system be CA?
**No.** Because **network partitions are inevitable**, and CAP requires picking C or A during partitions.

### ❓ Q2: What does CAP mean practically?
It means **you must design systems to tolerate partitions** and then decide if your system should:
- Return stale/possibly incorrect data (**Availability**),
- Or deny the request until consistent (**Consistency**).

### ❓ Q3: What does Cassandra prioritize?
Cassandra is **AP**. It uses **eventual consistency** with **tunable consistency levels** like ONE, QUORUM, ALL.

### ❓ Q4: What kind of system would pick CP?
- **Banking systems**
- **Configuration stores (Zookeeper/Etcd)**
  Consistency is critical. They may deny requests to maintain correctness.

---

## 🔧 Related Concepts

| Term | Description |
|------|-------------|
| **BASE** | Basically Available, Soft state, Eventually consistent — common in AP systems |
| **ACID** | Atomicity, Consistency, Isolation, Durability — strong DB guarantees |
| **Quorum** | Read/write majority mechanism used to balance C & A |
| **Eventual Consistency** | Writes may not be immediately visible but eventually all nodes sync |
| **Strong Consistency** | Guarantees latest data always (linearizability) |

---

## 🧠 Real-World Analogy

Imagine two ATMs in different cities:
- A network failure disconnects them.
- A user withdraws from ATM A.
- Another user checks balance from ATM B.
    - If B allows response → You prioritize **Availability**, risk stale data (AP)
    - If B blocks the request → You prioritize **Consistency**, sacrifice uptime (CP)

---

## ✅ Summary

- **Network partitions will happen**, even rarely.
- **CAP forces you to choose C or A under partition**.
- Most practical systems pick **CP or AP**, depending on business requirements.

---

> 🏆 For senior roles: Always explain *how you’d tune consistency vs availability per use case*. Mention real systems, e.g., Kafka, Cassandra, MongoDB, and discuss trade-offs clearly.