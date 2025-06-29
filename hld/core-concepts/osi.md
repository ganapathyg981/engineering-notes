# 🧠 OSI Model - FAANG/MAANG Senior Software Engineer Interview Notes

---

## 🔥 Why It Matters in System Design Interviews

- The OSI model **helps structure your understanding of how data flows across systems**, which directly maps to designing scalable, fault-tolerant services.
- **Debugging performance issues**? OSI helps isolate problems across layers.
- **Load balancer, TLS, CDN, rate-limiting** — all map to OSI layers.

---

## 🧱 The 7 OSI Layers — Explained With Examples

| Layer | Name         | Function                                                                 | Examples |
|-------|--------------|--------------------------------------------------------------------------|----------|
| 7     | Application  | Closest to end-user; enables interaction with software & services        | HTTP, FTP, DNS, gRPC, REST |
| 6     | Presentation | Data format translation, encryption/decryption, compression              | TLS, SSL, JSON ↔ Binary |
| 5     | Session      | Manages and maintains sessions between applications                      | WebSocket, NetBIOS, SSH sessions |
| 4     | Transport    | Provides reliable/unreliable delivery, segmentation, flow control        | TCP, UDP |
| 3     | Network      | Responsible for routing, IP addressing, and packet forwarding            | IP, ICMP, BGP, OSPF |
| 2     | Data Link    | MAC addressing, framing, physical addressing, error detection            | Ethernet, VLAN, ARP |
| 1     | Physical     | Transmits raw bits through a physical medium (electrical/optical/wireless)| Cables, fiber, Wi-Fi, NIC |

---

## 🧪 Interview-Focused Layer Mapping

| Component               | OSI Layer(s)            |
|------------------------|-------------------------|
| Load Balancer (L4)     | Transport               |
| Load Balancer (L7)     | Application             |
| CDN                    | Application + Network   |
| TLS Termination        | Presentation            |
| DNS                    | Application (uses UDP/TCP below) |
| Firewall               | Network / Transport     |
| Router                 | Network                 |
| Switch                 | Data Link               |
| Hub                    | Physical                |

---

## 🧠 Deep Layer-by-Layer Understanding

### 🔹 Layer 1 – Physical
- **Role**: Transfer bits (0s and 1s)
- **Concerns**: Signal strength, noise, bit timing
- **Example Issue**: Damaged cables causing packet loss

### 🔹 Layer 2 – Data Link
- **Role**: Node-to-node delivery; uses MAC address
- **Error Detection**: CRC checks
- **Example Issue**: MAC spoofing, packet collisions in LAN

### 🔹 Layer 3 – Network
- **Role**: Logical addressing and routing
- **Protocols**: IPv4/6, ICMP, BGP
- **Tools**: traceroute, ping

### 🔹 Layer 4 – Transport
- **Role**: Port addressing, reliability, flow control
- **TCP vs UDP**
    - TCP: Reliable (ACK, SYN), ordered
    - UDP: Fast, no ACK, used in video/voice

### 🔹 Layer 5 – Session
- **Role**: Session establishment, maintenance, teardown
- **Use Cases**: Secure connections, APIs using long polling

### 🔹 Layer 6 – Presentation
- **Role**: Encrypt/decrypt, compress/decompress, serialize
- **Examples**: JSON ↔ Protobuf, TLS handshake, gRPC compression

### 🔹 Layer 7 – Application
- **Role**: Interfaces with the app logic
- **Examples**: REST APIs, browsers, SMTP, HTTP headers

---

## 🧑‍💻 FAANG/MAANG Interview Questions & Answers

### ✅ OSI vs TCP/IP
> **Q: What’s the difference between OSI and TCP/IP models?**  
**A:** OSI has 7 layers; TCP/IP merges layers (Presentation, Session, Application into 1). OSI is theoretical; TCP/IP is practical.

---

### ✅ Application Layer (Layer 7)
> **Q: What happens at Layer 7 in HTTP request?**  
**A:** Constructs HTTP headers, cookies, handles URL routing, user input validation. It’s the layer APIs interact with.

> **Q: What’s the difference between L7 and L4 load balancing?**  
**A:** L7 inspects HTTP headers, allows routing by path, cookie. L4 routes by IP and port (faster but less flexible).

---

### ✅ Presentation Layer (Layer 6)
> **Q: Where does TLS/SSL encryption fit in OSI model?**  
**A:** Presentation Layer (6). It encrypts/decrypts content before transport begins.

> **Q: What is the difference between HTTPS and HTTP in OSI terms?**  
**A:** HTTPS = HTTP (Layer 7) + TLS (Layer 6) + TCP/IP (Layer 4/3)

---

### ✅ Session Layer (Layer 5)
> **Q: Is WebSocket at Layer 5 or 7?**  
**A:** Conceptually starts at Layer 5 (session management) but implemented via protocols at Layer 7 (via HTTP upgrade).

---

### ✅ Transport Layer (Layer 4)
> **Q: What happens if TCP fails?**  
**A:** Packet loss is detected; sender retries with ACK timeout. Congestion control via windowing and retransmission.

> **Q: UDP is unreliable — why use it at all?**  
**A:** Real-time performance (gaming, video calls) > reliability. App layer handles dropped packets if needed.

---

### ✅ Network Layer (Layer 3)
> **Q: Where does routing happen?**  
**A:** Layer 3 – IP, BGP, OSPF. Routers inspect destination IP and forward packets.

> **Q: What's ICMP and what layer is it in?**  
**A:** Internet Control Message Protocol – Layer 3. Used by `ping`, `traceroute`.

---

### ✅ Data Link Layer (Layer 2)
> **Q: What’s the role of MAC address?**  
**A:** MAC = physical address used in LAN for local delivery. Switches use MACs to forward data.

> **Q: What is ARP and which layer?**  
**A:** Address Resolution Protocol – maps IP to MAC (Layer 2)

---

### ✅ Physical Layer (Layer 1)
> **Q: What are common causes of issues at Layer 1?**  
**A:** Bad cables, interference, dead NICs, poor Wi-Fi signal, fiber breaks.

---

## 🛠️ Debugging by OSI Layer

| Problem                          | Possible OSI Layer |
|----------------------------------|--------------------|
| High latency on HTTP             | 4–7                |
| Packet drops                     | 1–3                |
| DNS not resolving                | 7 (uses L4/L3 below)|
| TLS handshake failure            | 6                  |
| Load balancer routing incorrectly| 4 or 7             |
| Router misconfigured             | 3                  |

---

## 🧠 FAANG-Level Summary

- You must **map every networking component you mention in a system design round to an OSI layer.**
- **Optimize latency or throughput?** Know where in OSI the bottleneck is.
- Expect to answer both **deep technical + abstraction-level questions**.

---

## 📌 Bonus Mnemonic

> 🔼 Top-to-Bottom (7→1):  
"All People Seem To Need Data Processing"

> 🔽 Bottom-to-Top (1→7):  
"Please Do Not Throw Sausage Pizza Away"

---