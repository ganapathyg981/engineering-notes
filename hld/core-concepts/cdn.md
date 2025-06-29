
# 🌐 CDN (Content Delivery Network) — System Design Notes

## 🔹 What is a CDN?

A Content Delivery Network (CDN) is a geographically distributed network of servers (edge locations or POPs) that cache and deliver static or dynamic content to users from the nearest edge server, reducing latency and load on origin servers.

---

## ⚙️ How a CDN Works

1. **User makes a request** (e.g., image, video, JS file).
2. DNS and Anycast route it to the **nearest CDN edge server**.
3. The edge server checks if it has the **cached version**:
    - ✅ **Cache Hit**: Serve directly from edge.
    - ❌ **Cache Miss**: Fetch from origin → cache → serve to user.
4. **Subsequent users** near that edge get it from cache.

---

## 🧱 Core Components

| Component         | Purpose |
|-------------------|---------|
| Edge Server (POP) | Caches and serves content close to the user |
| Origin Server     | Actual source of truth (e.g., S3, backend API) |
| Cache Layer       | Holds recently fetched responses |
| TTL / Cache-Control | Controls how long content stays cached |
| DNS / Anycast     | Routes users to nearest POP |
| Edge Functions    | Run JS/WASM to customize content per user at the edge |

---

## ✅ What Can Be Cached

| Content Type           | Cacheable? | Notes |
|------------------------|------------|-------|
| Images, CSS, JS        | ✅          | Static with long TTL |
| Videos                 | ✅          | With range request support |
| HTML Pages (static)    | ✅          | SSR/SSG sites |
| API Responses (GET)    | ✅          | If idempotent and cache headers set |
| Dynamic / Auth APIs    | ❌          | Must bypass CDN or use edge logic |

---

## 🔁 Cache Management

- **TTL (Time-To-Live)**: `Cache-Control: max-age=86400`
- **ETag / Last-Modified**: Helps conditional caching (304 Not Modified)
- **Stale-While-Revalidate**: Serve stale while refreshing in background
- **Manual Invalidation**: Purge APIs, versioned URLs, cache-busting

---

## 🚀 When to Use a CDN

| Use Case                          | Use CDN? | Why |
|----------------------------------|----------|-----|
| Global users                     | ✅       | Serve content faster globally |
| Heavy static assets              | ✅       | Reduce backend load |
| Video/image streaming            | ✅       | Lowers latency, saves bandwidth |
| Personalized content / Auth APIs | ❌       | Needs dynamic origin handling |
| SSR/SSG sites                    | ✅       | Use ISR or pre-rendered caching |

---

## 🛠️ Popular CDN Providers

| Provider         | Highlights |
|------------------|------------|
| Cloudflare       | Free tier, WAF, edge compute |
| AWS CloudFront   | Deep S3 + Lambda@Edge integration |
| Fastly           | Real-time purging, low latency |
| Akamai           | Enterprise CDN with deep global presence |
| Google Cloud CDN | Integrated with GCP, Anycast support |

---

## 💡 Dynamic Content Handling with CDN

| Technique                   | Description |
|-----------------------------|-------------|
| Bypass CDN                  | Route dynamic paths directly to backend |
| Edge Compute (e.g. Workers) | Customize request/response at edge |
| Stale-While-Revalidate      | Serve old data, revalidate in background |
| Fragment Caching / ESI      | Cache parts of page (e.g., footer, sidebar) |
| Pre-rendered Pages (ISR)    | Use frameworks like Next.js to push dynamic pages to edge |

---

## 🧠 Interview Q&A

**Q: How does a CDN reduce latency?**  
A: It caches content at edge locations closer to the user, reducing round-trip time and load on origin servers.

**Q: What happens on a cache miss?**  
A: The edge pulls the content from the origin server, caches it, and serves it. Future requests hit the cache.

**Q: How can a CDN handle dynamic content?**  
A: Use edge compute, bypass paths, or revalidate techniques like stale-while-revalidate and conditional headers (ETag).

**Q: How to prevent stale content?**  
A: Use versioned URLs, short TTLs for frequently changing content, or manual purge APIs.

---

## 💸 Is a CDN Costly?

| Cost Component       | Notes |
|----------------------|-------|
| Data Transfer (egress) | Main cost factor (₹5–₹10/GB in India) |
| Request Count         | Minor unless millions of reqs/day |
| Edge Logic (optional) | Charged per execution (e.g., Lambda@Edge) |

**How to Reduce Costs:**
- High cache hit ratio
- Long TTLs
- Asset compression
- Avoid unnecessary cache invalidations

---

## 📦 Real-World Example: Instagram

| Component             | Served From     |
|-----------------------|-----------------|
| Feed images           | ✅ CDN           |
| Profile pictures      | ✅ CDN           |
| Feed data (personal)  | ❌ Backend API   |
| Comments (POST)       | ❌ Backend API   |
| JS/CSS                | ✅ CDN           |

---

## 📢 Why Media & Static Files Use CDN

### ✅ Because file size is large
- Images, videos, JS/CSS bundles, and PDFs are often several MBs.
- Serving them directly from origin servers would:
    - Consume excessive bandwidth
    - Slow down user experience
    - Increase latency for global users
    - Strain backend and storage resources

### ✅ Static assets don’t change frequently
- Ideal for long-term caching (`Cache-Control: max-age=31536000`)
- Often versioned (e.g., `/app.v123.js`) so changes are cache-safe

### ✅ Reused across millions of users
- Same asset downloaded by many → cache it once at the edge → serve at scale

### ✅ Improves latency and throughput
- Files are served from edge caches near users
- No need to round-trip to central origin server

### ✅ Saves cost
- Avoids repeated origin egress charges (CDN serves after first fetch)
- Greatly reduces backend bandwidth usage

### ✅ Summary
> _Static and media files are perfect for CDNs because they’re large, frequently reused, and rarely change. CDNs make them load faster, cheaper, and more reliably at scale._




## 🔢 CDN Versioning — Why It Helps and How It Works

### 🎯 Why Use Versioning in CDN?

| Problem                         | Solution via Versioning             |
|----------------------------------|--------------------------------------|
| Users receive stale content      | New URL forces fresh fetch from origin |
| Cache invalidation is complex    | Versioning eliminates need to purge |
| Users load outdated CSS/JS       | Versioned filenames ensure updates |
| Long TTLs cause old assets to stick | New filename triggers cache bypass |

---

### ⚙️ How Versioning Works

#### 1. ✅ File Name Versioning (Best Practice)

- Example:
    - `main.css` → `main.v2.css`
    - `app.js` → `app.9f02a1b.js` (hash-based)
- CDN sees the filename as a **new resource** → fetches and caches it

#### 2. ⚠️ URL Query Versioning (Less Reliable)

- Example:
    - `main.css?v=2`
    - `logo.png?t=20250628`
- Some CDNs treat query strings inconsistently
- Use only if filename-based versioning isn’t possible

---

### 🧠 Real-World Examples

| Platform       | Strategy                     |
|----------------|------------------------------|
| Google Fonts   | Query param (`?v=3`)         |
| Facebook       | Hash-based file name         |
| Shopify        | Path versioning (`/v123/style.css`) |
| Next.js / Vite | Auto hash in file names      |

---

### ✅ Best Practices

| Do                                  | Don’t                                |
|-------------------------------------|--------------------------------------|
| ✅ Use hash-based filenames          | ❌ Rely solely on manual purges       |
| ✅ Set long TTLs for versioned files | ❌ Use same name for updated content |
| ✅ Automate via build tools (e.g. Webpack) | ❌ Serve unversioned static assets    |

---

### 🧠 Interview Summary Line

> _“Versioning in CDNs ensures cache busting by changing the file name whenever content changes. This avoids stale content issues and allows for long TTLs without worrying about users receiving outdated files.”_

