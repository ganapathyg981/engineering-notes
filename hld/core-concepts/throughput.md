
# 🚀 System Design: Throughput — FAANG Interview Notes

---

## 📌 What is Throughput?

**Throughput** is the number of requests (or operations) a system can handle per unit time.

- Measured in:
    - Requests per second (RPS)
    - Transactions per second (TPS)
    - MB/sec (for data pipelines)

---

## 🎯 Throughput vs Latency

| Concept     | Latency                       | Throughput                      |
|-------------|-------------------------------|----------------------------------|
| Measures    | Time per request               | Requests per second              |
| Goal        | Minimize                       | Maximize                         |
| Unit        | ms / µs                        | RPS, TPS, MB/sec                 |
| Analogy     | Time to serve 1 customer       | Number of customers served/hour  |

---

## 🧠 Formula: How to Estimate Throughput per Pod

```
Throughput (RPS) = (1000 / Avg Latency in ms) × Concurrent Workers
```

- Example: 100ms latency × 20 threads = 200 RPS
- Adjust for CPU, I/O, DB contention, GC, etc.

---

## 🧪 Real-world Caveats

- Thread contention, DB bottlenecks, network latency, GC pauses
- Use only 60–70% of theoretical max as safe estimate
- Benchmark using tools: `wrk`, `k6`, `Locust`, `JMeter`

---

## ✅ How to Answer Interview Questions

### ❓ Q1: How do you calculate throughput per pod?

> “Assuming avg latency of 100ms and 20 threads, we can handle ~200 RPS:
>
> `1000/100 * 20 = 200 RPS`
>
> I’d validate this with load testing and keep a headroom for CPU/memory.”

---

### ❓ Q2: How do you increase throughput?

| Bottleneck        | Optimization Strategy                        |
|-------------------|-----------------------------------------------|
| CPU               | Efficient algorithms, parallelism             |
| DB                | Caching, batching, read replicas, sharding    |
| Network           | Compression, persistent connections           |
| App Threads       | Use async/non-blocking frameworks             |
| External APIs     | Async handling, retries, circuit breakers     |

> “I'd reduce DB calls, move heavy logic to background jobs, and use caching to offload pressure.”

---

### ❓ Q3: How do you handle burst traffic?

- Buffer with **Kafka/SQS**
- Use **autoscaling policies**
- Apply **rate limiting & backpressure**
- Defer heavy work with **asynchronous processing**

---

### ❓ Q4: How does throughput affect system design?

> “Throughput determines scalability strategy. High-throughput systems need load balancers, partitioned DBs, async queues, and stateless services to scale horizontally.”

---

### ❓ Q5: Throughput vs QPS vs TPS?

- **Throughput** is a generic term
- **QPS** = API queries/sec
- **TPS** = Business transactions/sec (often span multiple queries)

---

## 📈 Throughput Targets by System Type

| System Type                  | Typical Throughput Target     |
|------------------------------|-------------------------------|
| REST API                    | 100–2,000 RPS                 |
| Kafka Message Pipeline      | 10,000+ messages/sec          |
| High-scale Backend (Search) | 10,000+ RPS                   |
| File Transfer Service       | 100MB/sec – 10GB/sec          |
| Chat System                 | 1,000+ messages/sec           |

---

## 🧠 Pro Tip for Interviews

> “Throughput is not just about code — it’s about identifying bottlenecks in DB, network, or external dependencies, and designing for parallelism, buffering, and elasticity.”

---

## 🛠️ Related Concepts

- **Latency**: Time taken for one request
- **Throughput**: Number of requests per second
- **Capacity**: Max sustainable throughput under SLA
- **Concurrency**: Number of simultaneous in-flight requests

---

## 🏁 Summary

- Estimate with formula, validate with load tests
- Scale with caching, async, replicas, queues
- Tailor answers based on latency SLA and system architecture
