# Elasticsearch - System Design & Architecture

## Core Concepts

### What is Elasticsearch?
- Distributed search and analytics engine built on Apache Lucene
- Part of the Elastic Stack (ELK Stack)
- Designed for horizontal scalability and real-time search
- Built on JVM with focus on high performance and reliability
- RESTful API for operations and management

### Architecture Components
1. **Nodes**
   - Master Node: Controls cluster operations
   - Data Node: Stores and manages data
   - Ingest Node: Pre-processes documents
   - Coordinating Node: Routes requests
   - Machine Learning Node: Handles ML jobs
   - Transform Node: Handles transforms
   - Voting-only Node: Participates in master elections

2. **Indices & Shards**
   - Index: Logical namespace for documents
   - Primary Shards: Original data copies
   - Replica Shards: Redundant copies
   - Segments: Immutable Lucene indices
   - Translog: Write-ahead log
   - Refresh: Near real-time search

3. **Documents & Mapping**
   - Document: JSON-based data unit
   - Field types: text, keyword, numeric, date, etc.
   - Dynamic mapping
   - Index templates
   - Field data types
   - Analysis & tokenization

4. **Cluster State**
   - Cluster health
   - Node discovery
   - Shard allocation
   - Metadata management
   - Recovery process

## High-Level Design Considerations

### Scalability
1. **Horizontal Scaling**
   - Add nodes to cluster
   - Increase shards per index
   - Cross-cluster replication
   - Cross-cluster search
   - Shard allocation filtering

2. **Performance Optimization**
   - Query optimization
   - Indexing optimization
   - Caching strategies
   - Bulk operations
   - Circuit breakers

### Search & Querying
1. **Query Types**
   - Match query
   - Term-level queries
   - Compound queries
   - Full-text queries
   - Geo queries
   - Aggregations

2. **Relevance Scoring**
   - TF/IDF
   - BM25
   - Script scoring
   - Function score
   - Field normalization

### Data Management
1. **Index Lifecycle**
   - Hot-warm-cold architecture
   - Index rollover
   - Index aliases
   - Snapshot/restore
   - Index templates

2. **Data Retention**
   - Time-based indices
   - Size-based rollover
   - ILM policies
   - Snapshot lifecycle
   - Data streams

## Advanced Features

### Security
1. **Authentication**
   - Native realm
   - LDAP/Active Directory
   - SSO integration
   - API keys
   - PKI certificates

2. **Authorization**
   - Role-based access
   - Field-level security
   - Document-level security
   - Encrypted communications
   - Audit logging

### Monitoring & Management
1. **Cluster Monitoring**
   - Cluster stats
   - Node stats
   - Index stats
   - Thread pool stats
   - Circuit breaker stats

2. **Performance Analysis**
   - Search profiling
   - Index profiling
   - Slow log analysis
   - Hot threads analysis
   - Task management

## Production Configuration Best Practices

### Node Settings
```yaml
# JVM Settings
-Xms31g
-Xmx31g

# Network Settings
network.host: _site_
discovery.seed_hosts: ["host1", "host2"]
cluster.initial_master_nodes: ["node1"]

# Memory Settings
indices.memory.index_buffer_size: 30%
indices.queries.cache.size: 20%
```

### Index Settings
```json
{
  "settings": {
    "number_of_shards": 5,
    "number_of_replicas": 1,
    "refresh_interval": "30s",
    "index.routing.allocation.total_shards_per_node": 3,
    "index.queries.cache.enabled": true
  }
}
```

## Common Interview Questions

1. **Architecture**
   - How does Elasticsearch handle distributed search?
   - What happens during node failure?
   - How does shard allocation work?

2. **Performance**
   - How to optimize search performance?
   - What affects query latency?
   - How to handle hot spots?

3. **Scalability**
   - How to scale for high query load?
   - What are the replication strategies?
   - How to handle large indices?

4. **Data Management**
   - How to handle time-series data?
   - What's the best mapping strategy?
   - How to manage index lifecycle?

## System Design Considerations

### Capacity Planning
1. **Hardware Requirements**
   - Memory sizing
   - CPU requirements
   - Storage capacity
   - Network bandwidth
   - JVM configuration

2. **Cluster Sizing**
   - Number of nodes
   - Shards per node
   - Documents per shard
   - Segment merging
   - Memory pressure

3. **Growth Strategy**
   - Data growth rate
   - Query growth rate
   - Retention requirements
   - Backup strategy
   - Recovery time objectives

### Common Pitfalls
1. **Performance Issues**
   - Too many shards
   - Large shard sizes
   - Memory pressure
   - Slow queries
   - Mapping explosion

2. **Operational Issues**
   - Split-brain scenarios
   - Network partitions
   - JVM memory issues
   - Disk space issues
   - Cluster state size

3. **Design Issues**
   - Poor mapping design
   - Inefficient queries
   - Wrong shard allocation
   - Incorrect aliases
   - Index template conflicts 