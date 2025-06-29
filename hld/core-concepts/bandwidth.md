
# 🌐 System Design: Bandwidth — FAANG Interview Notes

---

## 📌 What is Bandwidth?

**Bandwidth** is the **maximum amount of data** that can be transferred over a network or connection **per unit of time**.

- Measured in:
    - **Mbps** (megabits per second)
    - **Gbps** (gigabits per second)
    - Sometimes **MBps / GBps** (bytes per second)

> Think of it as: 🛣️ **“The width of the road”** — how much traffic can flow at once, not how fast each car goes.

---

## 🧠 Bandwidth vs Latency vs Throughput

| Metric        | Meaning                              | Unit        | Analogy                        |
|---------------|---------------------------------------|-------------|--------------------------------|
| **Latency**   | Delay for a single packet/request     | ms / µs     | Travel time of one car        |
| **Throughput**| Actual work done per second           | RPS / MBps  | Cars passing per second       |
| **Bandwidth** | Max data transfer rate possible       | Mbps / Gbps | Number of lanes on the road   |

---

## 📈 Bandwidth in System Design

### Where it matters:
- Transferring large files (e.g., media, backups)
- Streaming services (audio/video)
- Cross-data center replication
- APIs with large payloads
- File upload/download

### Example:
If a response is 5MB and client has 10 Mbps bandwidth:
```
Download time = (5 * 8) / 10 = 4 seconds
```

---

## 🛠️ How to Optimize Bandwidth Usage

| Problem                        | Solution                                 |
|--------------------------------|------------------------------------------|
| Large payloads                 | Compression (gzip, Brotli)               |
| Over-fetching from APIs        | Field filtering, GraphQL                 |
| Frequent duplicate transfers   | Use CDNs and caching                     |
| Network congestion             | Load balancing, regional replication     |
| Low bandwidth clients          | Use pagination, lower-quality assets     |

---

## ☁️ Cloud Bandwidth Basics (AWS/GCP)

| **Instance Type**              | **Typical Bandwidth**   |
|-------------------------------|--------------------------|
| AWS m5.large (≤16 vCPU)       | Up to 10 Gbps (burstable)|
| AWS c6in / metal              | 100–200 Gbps             |
| GCP n2-standard-4             | ~10 Gbps                 |
| GCP C2 (CPU optimized)        | ~50–100 Gbps             |
| GCP A3 Ultra                  | 400+ Gbps (some up to 3.6 Tbps internally)|

> Bandwidth depends on instance size (vCPUs) and network interface hardware.

---

## ❓ Why is Bandwidth Tied to Machine Type?

- Because each instance has a **specific network interface card (NIC)** with hardware limits
- Small instances share NICs → lower max bandwidth
- High-end VMs use dedicated NICs → higher throughput

---

## ❓ If I Mix Instance Types, Does the Smallest Cap the System?

- Not globally. It **limits its own traffic**, not the entire system.
- If a critical service runs on a low-bandwidth node → it becomes a bottleneck
- Horizontal scale helps distribute the load across stronger nodes

---

## ❓ Shouldn't Bandwidth Be Controlled by VPC?

- VPCs define **network topology**, routes, and security
- But **bandwidth is enforced at the NIC level** (per VM or container)
- VPC peering/VPNs have their own limits — but individual VM caps still apply first

---

## ✅ What to Say in Interviews:

> “Bandwidth is tied to the machine’s NIC, not just the VPC. So different instance types have different transfer caps.  
In mixed deployments, each node handles data at its own max rate — bottlenecks only emerge if low-bandwidth nodes sit on the critical path.  
To optimize usage, I’d compress payloads, use pagination, and offload large data via async or CDN delivery.”

---

## 🏁 Summary

- Bandwidth = how much data you *can* transfer per second
- It’s capped by **instance NIC hardware**
- Don’t overfetch, compress data, and use CDNs
- In interviews, show you know how to manage bandwidth constraints gracefully

---
