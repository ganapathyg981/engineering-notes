# Microservices - FAANG/MAANG Senior SDE Interview Notes

---

## 🔹 What are Microservices?

Microservices is an architectural style that structures an application as a collection of small, **autonomous services** modeled around a **business domain**.

Each service:
- Is independently deployable
- Has its own database (Database per service pattern)
- Communicates via APIs (HTTP/gRPC/WebSockets/message brokers)

---

## 🔹 Why Microservices?

- **Scalability**: Scale parts of the system independently
- **Agility**: Faster feature development, smaller teams per service
- **Resilience**: Failure in one service doesn't bring down the whole system
- **Deployability**: CI/CD becomes easier; deploy changes without affecting entire system
- **Polyglot persistence**: Use different DBs per service (SQL/NoSQL as needed)

---

## 🔹 When to Use Microservices?

✅ When:
- Your system is growing fast (team size + traffic)
- You need independent deployments
- Domain boundaries are well understood
- You want to scale features/modules independently

❌ Avoid when:
- Team size is small
- You don’t have DevOps maturity (need infra, monitoring, observability)
- Application is not complex enough to justify the overhead

---

## 🔹 Core Concepts

| Concept                      | Description |
|-----------------------------|-------------|
| **Service Discovery**        | Mechanism to dynamically discover service instances (e.g., Eureka, Consul, DNS-based) |
| **API Gateway**              | Entry point for all clients; handles routing, auth, rate-limiting (e.g., Kong, Zuul, NGINX) |
| **Load Balancing**           | Distributes traffic among service instances (e.g., L4/L7 LB) |
| **Inter-service Communication** | REST (synchronous), gRPC (fast), or message queues (asynchronous) |
| **Event-Driven Architecture** | Services emit/consume events using Kafka, RabbitMQ |
| **Circuit Breakers**         | Prevent cascading failures (e.g., Netflix Hystrix, Resilience4j) |
| **Centralized Logging**      | E.g., ELK/EFK stack |
| **Distributed Tracing**      | E.g., Jaeger, Zipkin |
| **Service Mesh**             | Istio/Linkerd for traffic routing, retries, encryption, etc. |
| **CI/CD Pipelines**          | Required for rapid deployment |

---

## 🔹 Microservices vs Monolith

| Feature            | Monolith                            | Microservices                          |
|--------------------|--------------------------------------|----------------------------------------|
| Deployment         | Single unit                          | Multiple independent services          |
| Scaling            | Whole app                            | Individual services                    |
| Tech stack         | Single                               | Polyglot (language/db/tools)           |
| Fault isolation    | Low                                  | High                                   |
| Dev speed          | Slower over time                     | Faster (small teams/clear boundaries)  |
| Complexity         | Easier initially                     | Complex (infra, orchestration)         |

---

## 🔹 Challenges

- **Distributed systems complexity**
- **Data consistency** across services
- **Debugging & tracing** across network boundaries
- **DevOps overhead** (infra, pipelines, secrets, configs)
- **Service versioning & compatibility**
- **Latency due to network hops**

---

## 🔹 Design Patterns for Microservices

1. **Strangler Fig Pattern** – Gradually replace parts of monolith with microservices
2. **Saga Pattern** – For distributed transactions
3. **Database per service** – Prevent shared state
4. **Bulkhead** – Isolate failures
5. **Sidecar Pattern** – Use sidecar containers for cross-cutting concerns
6. **CQRS** – Separate read/write models
7. **Event Sourcing** – Store state as events

---

## 🔹 Communication Strategies

| Type         | Pros                        | Cons                             |
|--------------|-----------------------------|----------------------------------|
| REST         | Simple, standardized        | Synchronous, can cause tight coupling |
| gRPC         | Fast, typed contracts       | Requires protobuf, less human-readable |
| Message Queue| Decouples producer/consumer | Adds delivery guarantees & retries |
| Event Streams| Great for reactive systems  | Complexity in ordering & idempotency |

---

## 🔹 Database in Microservices

- **Each service should own its own DB**
- Use **event-driven approach** for syncing data across services
- Use **Change Data Capture (CDC)** for syncing changes
- Avoid joins across service boundaries → denormalize and sync via events

---

## 🔹 Deployment Strategies

- **Docker + Kubernetes**
- **Helm charts** for templating
- **Canary / Blue-Green deployments**
- **Istio** for service mesh and rollout control
- **Feature toggles** for gradual feature exposure

---

## 🔹 Observability

- **Logging**: Correlate logs with `trace_id`
- **Metrics**: Prometheus + Grafana
- **Tracing**: OpenTelemetry, Jaeger
- **Health Checks**: Liveness and Readiness probes

---

## 🔹 Service Mesh

### What is a Service Mesh?

A **service mesh** is a dedicated infrastructure layer that controls **service-to-service (S2S) communication** in a microservices architecture.  
It handles traffic logic like retries, TLS/mTLS, tracing, and routing using **sidecar proxies** (e.g., Envoy).

### Architecture Overview
### Core Components

| Component        | Description |
|------------------|-------------|
| **Sidecar Proxy** | Intercepts and manages service traffic (e.g., Envoy) |
| **Control Plane** | Manages config/policy (e.g., Istio Pilot) |
| **Data Plane**    | All sidecars collectively routing real traffic |

### Features

| Category           | Features |
|--------------------|----------|
| Traffic Mgmt       | Routing, retries, circuit breakers |
| Security           | mTLS, access policies |
| Observability      | Tracing, metrics, logging |
| Reliability        | Failovers, rate limiting |
| Policy Control     | Quotas, ACLs, fault injection |

### Popular Tools

- Istio (powerful, feature-rich)
- Linkerd (lightweight, Kubernetes-native)
- Consul (service discovery + mesh)
- Kuma (multi-platform, from Kong)
- AWS App Mesh (managed on AWS)

### When to Use vs Avoid

✅ Use when:
- You have many services
- You need secure & reliable S2S communication
- You want full observability

❌ Avoid when:
- System is simple/small
- You lack DevOps expertise
- You want minimal overhead

### Pros vs Cons

| Pros | Cons |
|------|------|
| Handles retries, mTLS, observability out-of-the-box | Added latency & infra complexity |
| Improves security & control | Steep learning curve |
| Decouples traffic logic from code | Debugging can be tricky |

### Interview Questions

1. What is a service mesh and how is it different from an API Gateway?
2. What problems does a service mesh solve?
3. How does mTLS work in Istio?
4. How would you use a service mesh to implement circuit breaking?
5. How do you monitor and trace traffic in a service mesh?
6. Compare Istio vs Linkerd.
7. Would you use a service mesh in a small-scale microservice system?

---

## 🔹 Microservices System Design Example

**Problem**: Design a Food Delivery System

**Services**:
- User Service
- Restaurant Service
- Menu Service
- Order Service
- Payment Service
- Delivery Service
- Notification Service

**Communication**:
- Orders → async Kafka → Delivery, Notification
- Payments → synchronous REST call
- Notifications → FCM/Email/SMS via queue

**Storage**:
- Each service has its own DB
- Kafka for event bus
- Redis for caching menus

**Orchestration**:
- Kubernetes
- CI/CD with GitHub Actions or Jenkins

---

## 🔹 Interview Questions

1. What is a microservice and how is it different from monolith?
2. When would you use gRPC over REST?
3. How do you ensure eventual consistency across services?
4. How would you implement distributed transactions?
5. How do you scale a microservices architecture?
6. What observability tools would you set up for production?
7. Describe the architecture of your current microservices system.
8. How do you handle versioning in microservices?
9. How do you debug failures in a distributed microservices environment?
10. How does a service mesh improve microservices reliability?

---