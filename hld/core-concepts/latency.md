# ⚡️ System Design Latency Cheat Sheet

Understand typical latency expectations across different system components to build responsive and scalable systems.

---

## ⏱️ What is Latency?

**Latency** is the time taken for a single request or operation to complete, usually measured in **milliseconds (ms)** or **microseconds (µs)**.

- It affects **responsiveness**
- Key metric for **user experience**
- Often measured as **p50 / p90 / p99** percentiles

---

## 🎯 Latency Targets by System Type

| 🧩 **System Type**                        | 🎯 **Target Latency**         | 📌 **Notes**                                                                 |
|------------------------------------------|-------------------------------|------------------------------------------------------------------------------|
| 💻 User-facing Web UI (click -> response) | < 100ms                       | < 50ms feels instant; > 100ms starts to feel sluggish                        |
| 🌐 REST API (standard)                    | 100–300ms                     | Includes business logic, DB, auth, etc.                                      |
| ⚙️ High-performance API (critical path)   | < 100ms                       | e.g., payments, product search                                               |
| 🔄 Microservice-to-Microservice Call      | 5–50ms                        | Lower is better; reduces end-to-end latency in service chains                |
| 📱 Mobile App Response                    | < 200ms                       | Combine network + app logic                                                  |
| 🧠 ML Model Inference (Real-time)         | < 100ms                       | Offline/Batch models can tolerate higher latency                             |
| 💬 Chat or Pub/Sub                        | < 50ms                        | Real-time interaction needs fast delivery                                    |
| 🕹️ Gaming / Video Conferencing           | < 20–30ms                     | Essential for smooth, real-time interaction                                  |
| 🧾 SQL/NoSQL Read (cached)                | < 5–10ms                      | Redis: sub-ms, SQL: 5–20ms good                                              |
| 📥 SQL/NoSQL Read (uncached)              | < 100ms                       | Depends on indexing, joins, etc.                                             |
| ✍️ DB Write                               | < 20–50ms                     | Write-ahead logs, replication can affect latency                             |
| 🛠️ Background Jobs / ETL                 | Seconds – Minutes             | Batch systems prioritize throughput over latency                             |
| 🔌 IoT / Sensor Systems                   | 1ms – 100ms                   | Especially important in control systems or automation                        |

---

## 🧠 Interview Strategy: When Asked to Design API Under 100ms or 200ms

### 🎯 How to Estimate Latency

Break down the latency into components:

| Component                  | Estimated Latency |
|---------------------------|-------------------|
| API Gateway + Auth        | 5–20ms            |
| Network Round Trip        | 10–30ms           |
| Business Logic            | 5–10ms            |
| Redis Cache               | 1–5ms             |
| SQL (cold read)           | 20–100ms          |
| External API              | 100–500ms         |

> ⚠️ Total target (no third-party): ~80–150ms

---

### 🗣️ How to Explain Estimates

> “Let me break down the latency budget: with Redis caching and minimal logic, we can keep it under 120ms. However, if we add third-party APIs or heavy DB joins, we may exceed the 100ms budget.”

---

### 🚨 If It's Not Possible

> “Meeting a 100ms budget would be difficult if a third-party API or DB joins are in the critical path. We can either relax the SLA, use async processing, or precompute the data.”

---

### 🔧 How to Improve Latency

| Area              | Optimization Strategy                                   |
|-------------------|----------------------------------------------------------|
| Caching           | Redis, CDN, query caching                                |
| DB Reads          | Denormalization, pre-aggregation                         |
| Service Chaining  | Reduce hops, use co-location                             |
| Third-party Calls | Make async, queue them, or use fallbacks                 |
| Payloads          | Compress responses, avoid over-fetching                  |
| Network           | Use gRPC, reduce DNS lookups, connection pooling         |
| Background Tasks  | Offload logging, auditing, analytics                     |

---

## 🔍 Tips for Latency-Sensitive Design

- Monitor **p99 latency** to avoid poor experience for edge cases
- Avoid N+1 DB queries, fetch only required fields
- Use connection pooling and keep-alive settings
- Prefer event-driven or async processing where latency isn't user-visible
- Design APIs to **fail fast** when needed

---

## ✅ Summary

- Target <100ms for real-time APIs; <300ms for standard APIs
- Use caching, reduce DB round-trips, avoid blocking calls
- Be ready to explain trade-offs in interviews clearly and confidently

---