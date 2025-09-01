## gRPC vs REST vs GraphQL – FAANG-Level System Design Notes

### ⚡ Overview

| Feature               | gRPC                                    | REST                              | GraphQL                                  |
| --------------------- | --------------------------------------- | --------------------------------- | ---------------------------------------- |
| **Protocol**          | HTTP/2 (binary)                         | HTTP/1.1 (text)                   | HTTP/1.1 or HTTP/2                       |
| **Data Format**       | Protobuf (compact, efficient)           | JSON (human-readable)             | JSON (query-based)                       |
| **Contract-first?**   | Yes (Protobuf schema)                   | No (ad-hoc with OpenAPI optional) | Optional (Schema is mandatory though)    |
| **Speed**             | 🔥 Fastest                              | 🐢 Slower                         | ⚡ Fast, but depends on query             |
| **Streaming Support** | Bi-directional, server/client streaming | One-shot request-response         | One-shot request-response (no streaming) |
| **Strong Typing**     | Yes                                     | Weak                              | Strong (via GraphQL schema)              |
| **Browser-friendly?** | No (needs proxy or gRPC-web)            | Yes                               | Yes                                      |
| **Caching**           | Harder                                  | Easy (HTTP headers)               | Harder (depends on query & structure)    |
| **Tooling**           | Good (especially for microservices)     | Excellent                         | Good (Apollo, Relay, etc.)               |
| **Learning Curve**    | Steep                                   | Easy                              | Medium                                   |

---

### 🛠 Use Case Comparison

| Use Case                             | Best Option        | Why?                                                           |
| ------------------------------------ | ------------------ | -------------------------------------------------------------- |
| Microservices internal communication | **gRPC**           | Fast, efficient, strongly typed, streaming support             |
| Public APIs (3rd party integration)  | **REST**           | Easy to consume, widely supported, browser-native              |
| Client needs only partial data       | **GraphQL**        | Flexible querying, avoids over-fetching                        |
| Mobile clients with bandwidth limits | **gRPC / GraphQL** | gRPC for compact payloads, GraphQL for selective data fetching |
| Real-time communication / Streaming  | **gRPC**           | Native support for streaming                                   |
| Legacy or broad 3rd party ecosystem  | **REST**           | Broad support & familiarity                                    |

---

### 🧠 FAANG Interview Q\&A

**1. When would you prefer gRPC over REST?**

* Internal service-to-service calls
* Performance is critical
* Need bi-directional streaming
* Enforce strong typing via `.proto` definitions

**2. When is GraphQL a bad idea?**

* If you have simple CRUD APIs (adds complexity)
* If you rely heavily on HTTP-level caching
* Complex authorization on a per-field basis

**3. Does gRPC support browser clients?**

* Not directly. Use **gRPC-Web** or **proxy (e.g., Envoy)**

**4. Is GraphQL more efficient than REST?**

* **Yes**, when:

    * You want to avoid over-fetching/under-fetching
    * You combine multiple resources in one query
* **No**, if:

    * Server already offers well-structured endpoints
    * You rely on HTTP caching/CDNs

---

### 🔐 Security & Monitoring

| Feature       | gRPC                                      | REST                        | GraphQL                         |
| ------------- | ----------------------------------------- | --------------------------- | ------------------------------- |
| Auth Methods  | TLS + token-based                         | OAuth, JWT, API keys        | Same as REST (middleware)       |
| Monitoring    | Needs custom tooling (e.g., interceptors) | Native tools/logs available | Apollo Studio, custom logs      |
| Rate Limiting | Harder (custom interceptors)              | Easy (API Gateway, nginx)   | Medium (query complexity-based) |

---

### 🧪 Testing

|         | gRPC                                 | REST                         | GraphQL                    |
| ------- | ------------------------------------ | ---------------------------- | -------------------------- |
| Tools   | Postman (limited), BloomRPC, grpcurl | Postman, curl, Swagger       | GraphiQL, Postman, Apollo  |
| Mocking | More effort                          | Easy with tools like Mockoon | Medium with schema tooling |

---

### 🚀 Summary

| Tech    | Best For                                             |
| ------- | ---------------------------------------------------- |
| gRPC    | Internal microservices, performance-critical systems |
| REST    | Public APIs, browser clients, simplicity             |
| GraphQL | Rich frontend clients, mobile apps, flexible queries |
