# Apache Kafka - System Design & Architecture

## Core Concepts

### What is Kafka?
- Distributed Event Streaming platform with high scalability and fault tolerance
- Publish-Subscribe messaging system built on TCP protocol
- Used for real-time data pipelines, streaming analytics, and data integration
- Built on JVM with focus on high throughput and horizontal scalability

### Architecture Components
1. **Brokers**
   - Servers that store and manage topics
   - Handle read/write operations
   - Manage replication and partition leadership
   - Use page cache and disk storage for persistence
   - Utilize append-only log structure for O(1) writes
   - Use B-Tree for metadata management

2. **Topics & Partitions**
   - Topics: Logical channels for message streams
   - Partitions: Physical division of topics for scalability
   - Each partition is an ordered, immutable sequence of messages
   - Messages within a partition have sequential IDs called offsets
   - Partitioning enables:
     - Horizontal scalability
     - Parallel processing
     - Data locality
     - Load balancing

3. **Producers**
   - Push-based architecture
   - Can choose partition assignment strategies:
     - Round-robin
     - Key-based
     - Custom partitioner
   - Support for async batching and compression
   - Idempotent writes possible with exactly-once semantics

4. **Consumers**
   - Pull-based architecture (prevents consumer overwhelm)
   - Organized into consumer groups
   - Each partition is consumed by only one consumer in a group
   - Automatic rebalancing when consumers join/leave
   - Track consumption progress via offsets
   - Rule: num_consumers ≤ num_partitions (per group)

## High-Level Design Considerations

### Scalability
1. **Horizontal Scaling**
   - Add brokers to cluster
   - Increase partitions for topics
   - Add consumers to consumer groups
   - Partition rebalancing happens automatically

2. **Performance Optimization**
   - Zero-copy data transfer
   - Batching of messages
   - Compression support (gzip, snappy, lz4)
   - Sequential I/O for high throughput
   - Leverages OS page cache

### Fault Tolerance & Reliability
1. **Replication**
   - Topics can be replicated across brokers
   - Each partition has one leader and multiple followers
   - ISR (In-Sync Replicas) concept ensures data durability
   - Support for rack-aware replica assignment

2. **Message Delivery Guarantees**
   - At-most-once: Fast but may lose messages
   - At-least-once: No message loss but possible duplicates
   - Exactly-once: Achieved through idempotent producers and transactions

### Message Ordering
- Strict ordering guaranteed only within a partition
- Global ordering requires single partition (limits scalability)
- Key-based ordering by sending related messages to same partition

## Advanced Features

### Security
1. **Authentication**
   - SASL/PLAIN, SASL/SCRAM, SASL/GSSAPI (Kerberos)
   - SSL/TLS support
   - ACL-based authorization

2. **Monitoring & Metrics**
   - JMX metrics
   - Prometheus integration
   - Consumer lag monitoring
   - Broker and topic metrics

### Data Retention
- Time-based retention
- Size-based retention
- Compacted topics for key-based retention
- Log compaction for maintaining latest value per key

## Production Configuration Best Practices

### Producer Settings
```properties
# Reliability Settings
acks=all
enable.idempotence=true
retries=Integer.MAX_VALUE
max.in.flight.requests.per.connection=5

# Performance Settings
compression.type=lz4
batch.size=32768
linger.ms=20
buffer.memory=33554432
```

### Consumer Settings
```properties
# Reliability Settings
enable.auto.commit=false
isolation.level=read_committed
auto.offset.reset=earliest

# Performance Settings
fetch.min.bytes=1024
fetch.max.wait.ms=500
max.poll.records=500
```

## Common Interview Questions

1. **Scalability**
   - How does Kafka handle millions of messages per second?
   - How to scale consumers effectively?
   - What happens when adding/removing consumers?

2. **Consistency**
   - How does Kafka maintain message ordering?
   - What happens if a broker fails?
   - How are replicas kept in sync?

3. **Design Decisions**
   - Why pull-based consumers instead of push?
   - Why use disk instead of keeping everything in memory?
   - How does Kafka achieve high throughput?

4. **Use Cases**
   - Event sourcing
   - Log aggregation
   - Stream processing
   - Metrics collection
   - Activity tracking

## System Design Considerations

### Capacity Planning
1. **Storage**
   - Message size × Messages per second × Retention period
   - Replication factor impact
   - Log compaction overhead

2. **Network**
   - Inbound/Outbound bandwidth per broker
   - Replication bandwidth
   - Client communication overhead

3. **Memory**
   - Page cache utilization
   - JVM heap requirements
   - Consumer memory footprint

### Common Pitfalls
1. **Producer**
   - Not handling retries properly
   - Incorrect partition key design
   - Ignoring batch size/linger time

2. **Consumer**
   - Long-running poll loops
   - Not committing offsets correctly
   - Inefficient message processing

3. **Operations**
   - Under-replicated partitions
   - Network bandwidth saturation
   - Disk space management
   - Monitoring gaps 