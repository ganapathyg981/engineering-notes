# Caching Strategies - FAANG/MAANG-Level System Design Notes

## 🧠 What is Caching?
Caching is the process of storing **frequently accessed data** in a **faster storage layer** to reduce access time, improve performance, and reduce load on primary systems.

---

## ✅ Why Use Caching?
- **Reduce latency** (memory > disk > DB > external APIs)
- **Reduce load** on backend systems (e.g., DB, services)
- **Improve throughput and scalability**
- **Handle spikes in traffic gracefully**
- **Avoid repeated computation (e.g., expensive DB joins, ML inferences)**

---

## 📍 Where to Use Caching?
1. **Client-side**: Browser cache, mobile cache (fastest, but can be stale)
2. **CDN/Edge cache**: For static assets, media, some API GETs
3. **API Gateway / Reverse Proxy**: e.g., Nginx, Envoy caching GET responses
4. **App/server-side in-memory**: e.g., Redis, Memcached
5. **Database query cache**: e.g., MySQL Query Cache (deprecated), or built yourself
6. **Computation Results**: e.g., ML inference, sorting, pagination cache

---

## 🕒 When to Use Caching?
Use caching when:
- Read-heavy traffic
- Data doesn't change often (or can tolerate slight staleness)
- Latency requirements are strict (<100ms)
- Backend systems are bottlenecked
- You want to offload external service usage (e.g., API rate limits)

Avoid or be cautious when:
- Data is highly dynamic
- Strong consistency is mandatory (e.g., banking balances)
- Cache invalidation is too complex

---

## 🔁 Caching Strategies

### 1. **Read-Through Cache**
- App asks cache → if miss, gets from DB and populates cache.
- 🔹 Safe for most use cases.
- 🔹 Cache is always populated with fresh reads.
- 🔹 Latency on cache miss can be high.

### 2. **Write-Through Cache**
- Write goes to cache **and** DB.
- 🔹 Ensures cache stays in sync.
- 🔹 Slightly slower writes due to dual-write.

### 3. **Write-Behind / Write-Back Cache**
- Writes go to cache first, async persisted to DB later.
- 🔹 High write throughput.
- ⚠️ Risk of data loss if cache crashes before persisting.

### 4. **Cache-Aside (Lazy Loading)**
- App directly queries DB and manually populates cache.
- Most common in Redis setups.
- 🔹 Flexible, decouples logic.
- ⚠️ Race conditions can occur if multiple instances populate at the same time.

### 5. **Refresh Ahead / Pre-Warm Cache**
- Periodically refresh hot keys in the background.
- Used in low-latency systems or ML inference caching.

---

## 📊 Eviction Policies
When cache is full, which entry to remove?
- **LRU** (Least Recently Used) 🔥 most common
- **LFU** (Least Frequently Used)
- **FIFO**
- **Random Replacement**
- **TTL-based expiration** (absolute or sliding expiry)

---

## 🔄 Cache Invalidation Strategies
> One of the hardest problems in CS.

- **TTL Expiry**: Set time limit (e.g., 5 mins)
- **Manual Invalidation**: On update/delete
- **Write-Through ensures sync** but is slower
- **Event-based invalidation**: Use pub/sub or CDC

---

## 🧩 Cache Consistency
- **Strong Consistency**: Hard to guarantee unless write-through
- **Eventual Consistency**: Acceptable in most real-time web apps
- **Read Repair**: On cache miss, update cache with fresh DB value
- **Background sync**: Periodic reconciliation

---

## ⚖️ Trade-offs

| Factor         | Cached Version                 | Fresh Source (DB/API)      |
|----------------|-------------------------------|-----------------------------|
| Latency        | Low (sub-ms to few ms)        | Higher (tens to hundreds ms)|
| Consistency    | Might be stale                | Always up-to-date           |
| Cost           | In-memory storage cost         | Higher compute/db cost      |
| Complexity     | Needs eviction & invalidation | Simpler                     |

---

## 🚨 Common Pitfalls
- Stale data
- Cache stampede (thundering herd on cache miss)
- Inconsistent cache and DB state
- Over-caching low-impact data
- Cache poisoning / security risks

---

## 🛠️ Mitigations

- **Locking/Single-flight** on cache miss to prevent stampede
- **Background refresh** of popular keys
- **Shard caches** by key
- **Use consistent hashing** for distributed caches
- **Monitoring** hit ratio, latency, TTL expiry metrics

---

## ❓ Interview Questions

### Conceptual
- What’s the difference between cache-aside and write-through?
- How do you handle cache invalidation?
- When would you avoid using a cache?

### Design
- How would you design a system with 1M QPS using cache?
- How do you cache paginated results or search results?
- How do you avoid stampede if your cache fails?

### Behavioral
- Tell me about a time you improved performance with caching.
- How did you monitor or debug cache issues in production?

---

## 🧪 Metrics to Monitor
- **Cache Hit Ratio**
- **Eviction Rate**
- **Latency of cache vs DB**
- **Memory usage**
- **Key churn rate**
- **Stale hit frequency**

---

## ⚙️ Tools/Tech
- **Redis / Memcached** (in-memory cache)
- **CDN** (Cloudflare, Akamai, Fastly)
- **Client Cache** (HTTP cache headers, Service workers)
- **Reverse Proxies** (Nginx, Envoy)

---

## 🔚 Final Advice
- Cache only what’s worth caching.
- Understand *how fresh* your data needs to be.
- Start simple, evolve with TTLs, then go deeper (write-through, pub/sub, etc.)