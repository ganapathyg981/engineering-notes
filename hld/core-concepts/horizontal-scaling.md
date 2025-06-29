# 🚀 Horizontal Scaling — FAANG/MAANG Interview Notes

## 🧠 What is Horizontal Scaling?

Horizontal scaling (or scale-out) means adding more machines or nodes to a system to handle increased load, instead of upgrading a single machine.

---

## ✅ 1. Stateless Application Layer

- **Goal**: Any instance can serve any request.
- **How**:
    - Remove local state, use external session stores (e.g., Redis).
    - Avoid writing to disk — use shared storage if needed.
    - Use container orchestration (e.g., Kubernetes) to spin up/down replicas.
- **Benefits**:
    - Auto-scaling possible.
    - Fault tolerance improves.

---

## ✅ 2. Load Balancing

- **Purpose**: Distribute traffic across multiple servers.
- **Types**:
    - L4 (Transport-level): Load balances based on IP/Port.
    - L7 (Application-level): Uses URL, cookies, headers.
- **Tools**:
    - NGINX, HAProxy, AWS ELB, GCP Load Balancer, Cloudflare
- **Best Practices**:
    - Health checks for each node.
    - Sticky sessions if needed.

---

## ✅ 3. Database Sharding

- **Why**: Avoid write bottlenecks and DB growth limits.
- **Techniques**:
    - **Hash-based**: Consistent hashing on userId/orderId.
    - **Range-based**: Shard by ID ranges.
    - **Geo-based**: Shard by user location.
- **Challenges**:
    - Rebalancing shards.
    - Cross-shard joins.
    - Global unique ID generation (e.g., Snowflake, UUID).

---

## ✅ 4. Distributed Caching

- **Why**: Offload repeated DB reads and reduce latency.
- **Tools**: Redis Cluster, Memcached
- **Scaling Strategy**:
    - Partition cache keys via consistent hashing.
    - Use multiple cache nodes.
- **Avoid**:
    - Cache stampede → use TTL + locking.

---

## ✅ 5. Message Queues & Async Processing

- **Purpose**: Buffer spikes and decouple services.
- **Tools**: Kafka, RabbitMQ, SQS
- **Scaling**:
    - Multiple consumers across nodes.
    - Kafka partitions allow parallel processing.
    - Use Kubernetes HPA based on queue depth.

---

## ✅ 6. Blob/Object Storage

- **Why**: Offload static/media file storage from app servers.
- **Tools**: AWS S3, GCP Storage, Azure Blob Storage
- **Strategy**:
    - Store metadata in DB, binary in object store.
    - Use signed URLs for secure access.

---

## ✅ 7. Microservices Architecture

- **Why**: Scale individual components independently.
- **How**:
    - Containerize each service (Docker).
    - Use Kubernetes deployments with HPA/VPA.
    - API gateway + service mesh (e.g., Istio) for communication.

---

## ✅ 8. Auto-Scaling Infrastructure

- **Tools**:
    - Kubernetes Horizontal Pod Autoscaler (HPA)
    - AWS Auto Scaling Groups
    - GCP Instance Groups
- **Metrics to scale on**:
    - CPU/Memory
    - Requests per second
    - Custom application metrics (queue size, latency)

---

## ✅ 9. Content Delivery Network (CDN)

- **Why**: Serve static assets from edge servers, reduce origin load.
- **Examples**: Cloudflare, AWS CloudFront, Akamai
- **Use cases**:
    - Images, JS/CSS, videos
    - API caching with proper headers

---

## ✅ 10. Observability: Logging, Monitoring, Tracing

- **Why**: Track behavior across distributed nodes.
- **Tools**:
    - Logs: ELK Stack, Fluentd
    - Metrics: Prometheus + Grafana
    - Tracing: Jaeger, OpenTelemetry
- **Best Practices**:
    - Correlation IDs for tracing.
    - Set alerts on key performance thresholds.

---

## 🧠 Advanced Scaling Techniques

| Technique             | Purpose                               | Tools/Example                  |
|----------------------|---------------------------------------|--------------------------------|
| Service Discovery     | Dynamically find service endpoints     | Consul, etcd, Kubernetes DNS   |
| Circuit Breakers      | Prevent cascading failures             | Hystrix, Resilience4j          |
| Rate Limiting         | Protect services from overload         | Token/Leaky Bucket             |
| Blue-Green Deployment | Zero-downtime updates                  | Kubernetes, CI/CD              |

---

## 🎤 Sample Interview Response

> "To achieve horizontal scaling, I’d first make the application stateless and use load balancing. Then, I’d shard the database, use distributed caching, and containerize services using Kubernetes. I’d scale consumers for async processing using Kafka and offload media to S3 with CDN. With observability, autoscaling, and service discovery in place, the system can scale dynamically to handle millions of users with high availability."

---

## 📌 Summary Table

| Layer               | Horizontal Scaling Strategy                |
|--------------------|--------------------------------------------|
| App Layer          | Stateless + Load Balancer + Containers     |
| DB Layer           | Sharding + Read Replicas                   |
| Caching Layer      | Redis Cluster, Partitioned Keys            |
| File Storage       | S3 / Cloud Storage + CDN                   |
| Infra              | Auto-scaling (HPA, ASG)                    |
| Messaging          | Kafka/RabbitMQ with partitioned consumers  |
| Monitoring         | Prometheus, ELK, Jaeger                    |

---