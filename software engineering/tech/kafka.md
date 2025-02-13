# Kafka
- Distributed Event Streaming platform - Highly Scalable, Fault Tolerant
- Publish Subscribe System on a topic
- TCP network protocol
- There will be servers and clients
- Brokers are the servers, They have the storage layer
- We pass events/Messages
- Clients are either producers or consumers
- Decoupled Clients
- Message Delivery Settings
  - At most once—Messages may be lost but are never redelivered.
  - At least once—Messages are never lost but may be redelivered. 
  - Exactly once—this is what people actually want, each message is delivered once and only once.
- Events are organized and stored in topics
- Topics are partitioned into buckets, Topic can be distributed over brokers for scalability
- Topics can be replicated for fault tolerance  even across geo-regions or datacenters
- Events get appended to a partition
- Event Retention can be done
- Rewind and replay can be done with offsets
- Ordering can be promised on a partition in a topic
- Built on JVM
- Its on some page cache / uses disk 
- Append only data for O(1) and BTree for metadata
- Load balancing through selective picking the partition
- Async send with batches
- You can add compression
- Main APIs
  - Admin API
  - Producer API
  - Consumer API
  - Streaming API (See this)
  - Connect API
- Push based producers
- Pull based consumers, so that the consumers can safely consume to its rate. avoiding DoS
- Consumer ACK is there. Learn about this. retrys rewinds are possible
- Consumer groups, a group if processed if shared same groupid, they all process the message once
- No of Consumers < no.of.partitions

# Kafka Producer and Consumer Settings

## Producer Settings

| **Setting**                | **Description**                                                                                     | **Recommended Value**                   |
|----------------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------|
| **acks**                   | Controls message durability:                                                                        | `acks=all` for high reliability         |
|                            | - `0`: No acknowledgment                                                                           |                                         |
|                            | - `1`: Leader acknowledgment                                                                       |                                         |
|                            | - `all` (or `-1`): All in-sync replicas acknowledge                                                |                                         |
| **retries**                | Number of retries for transient errors                                                             | `Integer.MAX_VALUE` for critical apps   |
| **enable.idempotence**     | Ensures retries do not produce duplicates (exactly-once)                                           | `true` for critical apps                |
| **compression.type**       | Compresses messages to reduce payload                                                              | `gzip` or `lz4`                         |
| **batch.size**             | Maximum size of a batch of messages (in bytes)                                                     | `16384` (16 KB)                         |
| **linger.ms**              | Time to wait before sending a batch                                                                | `5` (ms)                                |
| **delivery.timeout.ms**    | Total time for message delivery, including retries                                                 | `120000` (2 minutes)                    |
| **request.timeout.ms**     | Maximum time to wait for a broker acknowledgment                                                   | Typically less than `delivery.timeout.ms` |
| **retry.backoff.ms**       | Time to wait between retries                                                                       | `100`                                   |

---

## Consumer Settings

| **Setting**                | **Description**                                                                                     | **Recommended Value**                   |
|----------------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------|
| **enable.auto.commit**     | Automatically commits offsets after a specified interval                                           | `false` for manual commit               |
| **auto.commit.interval.ms**| Interval for auto-commit of offsets (if auto-commit is enabled)                                     | `5000` (default, adjust as needed)      |
| **auto.offset.reset**      | Controls where to start consuming when no offset is committed:                                      | `earliest` for new consumers            |
|                            | - `earliest`: Start from the beginning                                                             |                                         |
|                            | - `latest`: Start from the latest                                                                  |                                         |
|                            | - `none`: Throws error if no offset is available                                                   |                                         |
| **max.poll.records**       | Maximum number of messages fetched in a single poll                                                | `500`                                   |
| **max.poll.interval.ms**   | Maximum time allowed between polls before marking consumer as failed                               | `300000` (5 minutes)                    |
| **session.timeout.ms**     | Time to detect a failed consumer                                                                   | `10000`                                 |
| **heartbeat.interval.ms**  | Frequency of heartbeat messages to the broker                                                     | `3000`                                  |
| **manual commit**          | Use `commitSync()` or `commitAsync()` for offset commits                                           | Commit **after processing** for reliability |

---

## Delivery Semantics

| **Delivery Guarantee** | **Producer Settings**                       | **Consumer Settings**                                   | **Description**                                                                                          |
|-------------------------|--------------------------------------------|-------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| **At-Most-Once**        | `acks=0` or `acks=1`                      | Commit offsets **before processing**                  | Messages may be lost if processing fails after commit.                                                   |
| **At-Least-Once**       | `acks=all`, `retries > 0`, `enable.idempotence=true` | Commit offsets **after processing**                   | Ensures no messages are lost but may result in duplicates during retries.                                |
| **Exactly-Once**        | `enable.idempotence=true`, transactional producer | Transactions or frameworks like Kafka Streams         | Guarantees no duplicates or message loss, but requires additional setup and overhead.                    |
## Use cases
