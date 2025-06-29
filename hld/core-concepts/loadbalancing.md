
# 🧠 Load Balancing - System Design Interview Notes (FAANG/MAANG)

---

## ✅ What is Load Balancing?

Load balancing is the process of distributing incoming requests across multiple resources (servers, containers, databases, etc.) to ensure optimal performance, availability, fault tolerance, and scalability.

---

## 📌 When to Use Load Balancing?

Use it when you want to:
- Handle high request volume efficiently
- Build highly available and fault-tolerant systems
- Achieve horizontal scalability
- Avoid hotspots and single points of failure
- Manage geographically distributed users

---

## 🌐 Where is Load Balancing Used?

| Layer / Scope             | Example                                                                 |
|---------------------------|-------------------------------------------------------------------------|
| Client-side (L7)          | SDK load balancer (e.g., gRPC RoundRobin)                              |
| Reverse proxy (L7)        | NGINX, Envoy, AWS ALB                                                   |
| Transport layer (L4)      | HAProxy (TCP), AWS NLB                                                  |
| Global load balancing     | DNS + Anycast (e.g., Route53, Cloudflare)                              |
| Service Mesh              | Istio, Linkerd manage service-to-service load balancing                |
| Kubernetes                | kube-proxy, Ingress Controller, Service LB                             |

---

## ⚙️ How Does Load Balancing Work?

Receives a request and selects a backend instance based on routing algorithm and health status.

### 🚥 Routing Algorithms

| Algorithm               | How it Works                                                             |
|-------------------------|--------------------------------------------------------------------------|
| Round Robin             | Each request goes to the next server in order                           |
| Least Connections       | Picks server with the fewest active connections                         |
| Weighted Round Robin    | More traffic to powerful machines                                       |
| Hash-based              | Hash of IP/User ID → consistent backend (used for sticky sessions)      |
| Random                  | Random backend, often used in client-side balancing                     |
| EWMA / P2C              | Advanced latency-based algorithms (used by Netflix, Envoy)              |

---

## 🎯 Why Use Load Balancing?

| Benefit                 | Explanation                                                              |
|-------------------------|---------------------------------------------------------------------------|
| Scalability             | Add more instances as load grows                                         |
| High Availability       | Failing instances are bypassed                                           |
| Fault Tolerance         | Keeps service running even if nodes crash                                |
| Latency Optimization    | Routes to nearest/fastest server                                         |
| Security                | Acts as a protective layer (SSL termination, rate limiting, etc.)        |

---

## 🏗️ Load Balancing in System Design

```
               +----------------+
Client  --->   |  CDN (Static)  |
               +-------+--------+
                       |
               +-------v--------+
               | Global DNS LB  |
               +-------+--------+
                       |
     +-----------------+-----------------+
     |                                   |
+----v----+                       +------v-----+
| Regional LB |                |  Regional LB |
|  (AWS ALB)  |                |   (GCP LB)   |
+----+----+                       +------+-----+
     |                                   |
+----v----+      ...     +---------+   +--v-----+
| Service |              | Service |   |Service |
|   A     |              |   B     |   |   C    |
+---------+              +---------+   +--------+
```

---

## 🛠️ Types of Load Balancers

| Type             | Description                       | Pros                                   | Cons                          |
|------------------|-----------------------------------|----------------------------------------|-------------------------------|
| L4 (Transport)    | Operates at TCP/UDP level         | Fast, low overhead                     | Can't inspect HTTP headers    |
| L7 (App Layer)    | Routes via path, headers, etc.    | Smart, feature-rich                    | Slightly slower               |
| Global LB         | DNS + Geo-based routing           | Geo latency optimization               | DNS caching delays            |
| Client-side LB    | SDK-based                         | No infra cost                          | Less control, no health checks|
| Service Mesh LB   | Proxy-based sidecars              | Fine-grained policies + retries        | Operational complexity        |

---

## 📊 Health Checks

- Periodic health probes (e.g., `/healthz`, TCP ping)
- Unhealthy nodes are removed from the pool
- Includes latency/error-based thresholds

---

## 🧠 Common Interview Questions

### 🔸 1. Can Load Balancer Become a Bottleneck?
- Yes. Use horizontal scaling or DNS-level sharding
- Use distributed LBs (Envoy mesh, Cloudflare Anycast)

### 🔸 2. Sticky Sessions?
- Via IP hashing, cookies, session affinity
- Or externalize session state (e.g., Redis)

### 🔸 3. Scaling Load Balancer?
- Use managed scalable services (AWS, GCP)
- Deploy multiple LBs + DNS RR or Anycast

### 🔸 4. Kubernetes Load Balancing?
- `Service` = basic L4 LB (kube-proxy)
- Ingress = L7 routing
- Mesh (Istio) = internal load balancing & retries

---

## 🧪 Real-World Examples

| Company     | Load Balancing Practice                                                  |
|-------------|---------------------------------------------------------------------------|
| Netflix     | Ribbon + Eureka (client-side), Zuul (server-side)                        |
| Google      | Global Anycast + Maglev hashing                                          |
| AWS         | ALB (HTTP), NLB (TCP), Route53 (Geo)                                     |
| Airbnb      | NGINX + Envoy for smart traffic & A/B testing                            |

---

## ⚠️ Design Trade-offs

| Trade-off                     | Explanation                                                             |
|-------------------------------|-------------------------------------------------------------------------|
| SPOF                          | Solve via DNS failover, active-active setups                            |
| Latency vs Smart Routing      | L4 faster; L7 offers rich logic                                         |
| Stateless vs Sticky Sessions | Stickiness adds coupling; stateless is easier to scale                  |
| Cost                          | Managed LBs can be expensive (per GB/request pricing)                   |
| Health Check Granularity      | Too strict = false positives; too loose = poor UX                      |

---

## 🧰 Tools

- **NGINX / HAProxy** – Open-source LB
- **Envoy** – Modern proxy for L7 + metrics
- **AWS ALB / NLB / Route53**
- **Cloudflare / Akamai** – Edge + DNS
- **Kubernetes Ingress + Istio**

---
