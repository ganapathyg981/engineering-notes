# Elasticsearch Detailed Notes

## Table of Contents
1. [Core Concepts](#1-core-concepts)
2. [Cluster Architecture](#2-cluster-architecture)
3. [Indexing](#3-indexing)
4. [Search](#4-search)
5. [Sharding and Replication](#5-sharding-and-replication)
6. [Data Structures](#6-data-structures)
7. [Fault Tolerance](#7-fault-tolerance)
8. [Performance Optimization](#8-performance-optimization)
9. [Real-Time Features](#9-real-time-features)
10. [Monitoring and Management](#10-monitoring-and-management)
11. [Scalability](#11-scalability)
12. [Security](#12-security)
13. [Sample Questions and Answers](#13-sample-questions-and-answers)

---

## 1. Core Concepts

### Key Terminologies
- **Index**: A collection of documents with similar characteristics, akin to a table in RDBMS.
- **Document**: The smallest unit of data in Elasticsearch, stored in JSON format.
- **Shard**: A physical division of an index, enabling parallel processing. It's a lucene index
- **Replica**: A copy of a shard for fault tolerance and high availability.
- **Cluster**: A group of nodes working together to handle data and search operations.
- **Node**: A single instance of Elasticsearch in a cluster. Nodes can have specific roles like master, data, or ingest.

---

## 2. Cluster Architecture

### Cluster Components
1. **Master Node**:
    - Manages cluster-wide operations like creating/deleting indices and assigning shards.
    - Manages cluster state and ensures fault tolerance during node failures.

2. **Data Node**:
    - Stores and manages the actual data.
    - Handles CRUD operations, search, and aggregations.

3. **Ingest Node**:
    - Preprocesses data before indexing (e.g., adding fields or parsing logs).

4. **Coordinating Node**:
    - Acts as a router for client requests and distributes them to the appropriate nodes.

---

## 3. Indexing

### Indexing Process
1. Data is sent to an **ingest node**, optionally passing through an ingest pipeline.
2. The primary shard receives the document.
3. Elasticsearch writes the document to the **translog** for durability.
4. Data is indexed in the **inverted index** within the shard.
5. Replicas are updated asynchronously.

### Translog
- Ensures durability by writing changes to disk before applying them to the Lucene index.
- Helps recover data during node failures.

---

## 4. Search

### Query Execution
1. **Query Phase**:
    - Queries are sent to the relevant primary or replica shards.
    - Each shard executes the query and sends back partial results.

2. **Fetch Phase**:
    - Documents are retrieved from the shards based on the query results.

### Relevance Scoring
- Elasticsearch uses **BM25** (an advanced version of TF-IDF) to rank documents.
- Factors include term frequency, inverse document frequency, and field-length normalization.

---

## 5. Sharding and Replication

### Shards
- **Primary Shard**: Stores a portion of the index.
- **Replica Shard**: A copy of the primary shard for high availability.

### Shard Allocation
- Elasticsearch balances shards across nodes to optimize query performance.
- Replicas are never stored on the same node as their primary shard.

### Dynamic Rebalancing
- Shards are redistributed automatically when nodes are added or removed.

---

## 6. Data Structures

### Key Data Structures
1. **Inverted Index**:
    - Maps terms to documents, enabling fast lookups.
    - Used for full-text search.

2. **Doc Values**:
    - On-disk structures for sorting and aggregations.

3. **BKD Trees**:
    - Optimized for numeric and geo-point data.

---

## 7. Fault Tolerance

### Cluster State Management
- The master node maintains the cluster state and uses a consensus mechanism for updates.

### High Availability
- Elasticsearch promotes replicas to primaries when a node fails.
- It rebalances shards to maintain cluster health.

---

## 8. Performance Optimization

### Caching
- **Query Cache**: Stores results of frequently executed queries.
- **Shard Request Cache**: Optimizes expensive operations like aggregations.

### Segment Merging
- Lucene creates immutable segments during indexing.
- Periodic merging reduces overhead and improves search performance.

---

## 9. Real-Time Features

### Near-Real-Time Search
- Documents are searchable within seconds after indexing.
- Controlled by the **refresh interval**.

### Bulk API
- Used for indexing large volumes of data efficiently.

---

## 10. Monitoring and Management

### Tools
- **Kibana**: Visualizes Elasticsearch data and monitors cluster health.
- **REST APIs**: Manage clusters, nodes, indices, and data.

### Metrics
- Cluster status: Green, Yellow, or Red.
- Node metrics: CPU, memory usage, and I/O statistics.

---

## 11. Scalability

### Horizontal Scaling
- Adding more nodes increases capacity.

### Dynamic Index Scaling
- Increase the number of shards or replicas as needed.

---

## 12. Security

### Authentication
- Supports Basic Auth, API tokens, LDAP, and OAuth.

### Authorization
- Role-Based Access Control (RBAC) defines user permissions.

### Encryption
- Data is encrypted in transit (TLS) and at rest.

---

## 13. Sample Questions and Answers

### Q1. **How does Elasticsearch handle high availability?**
**Answer**:
- By using replica shards.
- If a primary shard fails, one of its replicas is promoted to a primary shard.
- Dynamic shard allocation ensures data is redistributed when nodes fail or join.

### Q2. **Explain the role of the inverted index.**
**Answer**:
- The inverted index maps terms to the documents they appear in.
- It allows for efficient full-text searches.
- For example, the term "Elasticsearch" might map to document IDs [1, 3, 7].

### Q3. **How does Elasticsearch perform a search query?**
**Answer**:
1. **Query Phase**: Relevant shards (primary or replica) are searched, and results are aggregated.
2. **Fetch Phase**: The documents are fetched based on the aggregated results.

### Q4. **What is the purpose of the translog?**
**Answer**:
- Ensures durability by writing operations to disk before indexing.
- Helps recover data during crashes.

### Q5. **How does Elasticsearch scale with increasing data?**
**Answer**:
- By splitting data into shards and distributing them across multiple nodes.
- Adding nodes increases capacity and allows the cluster to handle more data.

---

This Markdown file covers Elasticsearch comprehensively and includes sample questions that are useful for interviews.