# Foundations
Nowadays CPU is barely a limiting factor for most of the apps, it the memory


A data-intensive application is typically built from standard building blocks that provide commonly
needed functionality. For example, many applications need to:

- Store data so that they, or another application, can find it again later (databases)
- Remember the result of an expensive operation, to speed up reads (caches)
- Allow users to search data by keyword or filter it in various ways (search indexes)
- Send a message to another process, to be handled asynchronously (stream processing)
- Periodically crunch a large amount of accumulated data (batch processing)”

> Increasingly many applications now have such demanding or wide-ranging requirements that a
single tool can no longer meet all of its data processing and storage needs. Instead, the work is
broken down into tasks that can be performed efficiently on a single tool, and those different
tools are stitched together using application code

<p align="center">
  <img src="../images/sample-system.png" alt="Sublime's custom image"/>
<br>
Sample System
</p>

# Reliability
System should be 
- fault-tolerant to user inputs 
- function well under normal load conditions
- should perform the expected functionality

## How Important It Is
- Reliability is not just for nuclear power stations and air traffic control software—more mundane
applications are also expected to work reliably. Bugs in business applications cause lost
productivity (and legal risks if figures are reported incorrectly), and outages of ecommerce sites
can have huge costs in terms of lost revenue and damage to reputation.

- Even in “noncritical” applications we have a responsibility to our users. Consider a parent who
stores all their pictures and videos of their children in your photo application
[15]. How would they feel if that database was suddenly corrupted?
Would they know how to restore it from a backup?

- There are situations in which we may choose to sacrifice reliability in order to reduce development
cost (e.g., when developing a prototype product for an unproven market) or operational cost (e.g., for
a service with a very narrow profit margin)—but we should be very conscious of when we are
cutting corners.”

  >  “reliability as
  meaning, roughly, “continuing to work correctly, even when things go wrong.”
  
## Hardware faults
### Problems
- Disk can fail after sometime like in years
- Network outages
- Natural calamities in datacenters
- RAM becomes faulty
- The power grid has a blackout
- Someone unplugs the wrong network cable
- Moreover, in
  some cloud platforms such as Amazon Web Services (AWS) it is fairly common for virtual machine instances
  to become unavailable without warning, as the platforms are designed to
  prioritize flexibility and elasticity
  over single-machine reliability.
- Even for any OS/Software upgrades, downtimes on your own (Not a fault)
### Solutions
- Adding Redundancy to machines (Availability Zones)
- Keeping Backups in different datacenters
- Rolling Upgrade
## Software faults

### Problems
- A software bug that causes every instance of an application server to crash when given a
  particular bad input. For example, consider the leap second on June 30, 2012, that caused many
  applications to hang simultaneously due to a bug in the Linux kernel
- A runaway process that uses up some shared resource—CPU time, memory, disk space, or network
  bandwidth
- A service that the system depends on that slows down, becomes unresponsive, or starts returning
  corrupted responses
- Cascading failures, where a small fault in one component triggers a fault in another component,
  which in turn triggers further faults
> The bugs that cause these kinds of software faults often lie dormant for a long time until they are
triggered by an unusual set of circumstances. In those circumstances, it is revealed that the
software is making some kind of assumption about its environment—and while that assumption is
usually true, it eventually stops being true for some reason
### Solutions
- There is no quick solution to the problem of systematic faults in software
- carefully thinking about assumptions and interactions in the system
- thorough testing
- process isolation
- allowing processes to crash and restart
- measuring, monitoring, and analyzing system
  behavior in production

## Human error
Ahh.... here we go. This is the one which can go wrong easily as there can be continuous development and deployment on the systems.  
Also many people are involved in this.
> Even when they have the best intentions, humans are known to be unreliable. For example, one
study of large internet services found that configuration errors by operators were the leading cause
of outages, whereas hardware faults (servers or network) played a role in only 10–25% of outages.
### Problems
- You can just write bad code or bad architecture or managed something bad
### Solutions
- Design systems in a way that minimizes opportunities for error
- Decouple the places where people make the most mistakes from the places where they can cause
  failures
- Provide fully featured non-production sandbox environments where
  people can explore and experiment safely, using real data, without affecting real users
- Test thoroughly at all levels, from unit tests to whole-system integration tests and manual tests
- Allow quick and easy recovery from human errors, to minimize the impact in the case of a failure.
  For example, make it fast to roll back configuration changes, roll out new code gradually (so that
  any unexpected bugs affect only a small subset of users), and provide tools to recompute data (in
  case it turns out that the old computation was incorrect)”
- Set up detailed and clear monitoring, such as performance metrics and error rates.
- Implement good management practices and training - Code Review, Design Review, Infra Review, Mostly Reviews and Feedback


## Monitoring / Can be caught by
- Adding Application metrics Error Metrics, RPS Metrics, Latency Metrics
- Adding Open tracing to find where stuff is going bad
- Adding Hardware metrics like CPU usage, Disk IO, Memory metrics, Network metrics
- Adding Software metrics like JVM Threads, GC, Heap memory etc
- Alerts on all above within desired/assumed thresholds


# Scalability
You know what it is. The app which worked in your dev environment should be able to support real traffic without performance degrading
## Possible Reasons
- Number of requests have increased from the client, A server can handle only some max concurrency (Network, CPU)
- Volume of data has increased (Memory, Storage, CPU)
- When you increase a load parameter and keep the system resources (CPU, memory, network bandwidth,
  etc.) unchanged, how is the performance of your system affected?
- When you increase a load parameter, how much do you need to increase the resources if you want to
keep performance unchanged?

> If a single service is slow in a system of orchestration, that alone is enough to fuck people
<p align="center">
  <img src="../images/latency-issue.png" alt="Sublime's custom image"/>
<br>
When several backend calls are needed to serve a request, it takes just a single slow backend request to slow down the entire end-user request
</p>


> In a batch processing system such as Hadoop, we usually care about throughput—the number of
records we can process per second, or the total time it takes to run a job on a dataset of a certain
size.iii In online systems, what’s usually more important is the service’s
response time—that is, the time between a client sending a request and receiving a response.

> Latency and response time are often used synonymously, but they are not the same. The response
time is what the client sees: besides the actual time to process the request (the service time),
it includes network delays and queueing delays. Latency is the duration that a request is waiting to
be handled—during which it is latent, awaiting service

p50, p90, p99, p999 -> You know already what these are. But you didn't realize how important p99 is.

### p99 is lesser bcoz of many how following reasons
 - That users/requests are performing high throughput operations
 - A system even happened around that time GC pause, Deployment etc.
 - could be introduced by a context switch to a background
   process
 - the loss of a network packet and TCP retransmission
 - a page fault forcing a read from disk
 - mechanical vibrations in the server rack
 - Or bitch, you might have done some bad decisions on the dev or arch
>High percentiles of response times, also known as tail latencies  

> For example, Amazon describes response time
requirements for internal services in terms of the 99.9th percentile, even though it only affects 1
in 1,000 requests. This is because the customers with the slowest requests are often those who have
the most data on their accounts because they have made many purchases—that is, they’re the most
valuable customers
[19].
It’s important to keep those customers happy by ensuring the website is fast for them: Amazon has
also observed that a 100 ms increase in response time reduces sales by 1%
[20],
and others report that a 1-second slowdown reduces a customer satisfaction metric by 16%

> “On the other hand, optimizing the 99.99th percentile (the slowest 1 in 10,000 requests) was deemed
too expensive and to not yield enough benefit for Amazon’s purposes. Reducing response times at very
high percentiles is difficult because they are easily affected by random events outside of your
control, and the benefits are diminishing”

> “percentiles are often used in service level objectives (SLOs) and service level
agreements (SLAs), “These metrics set
expectations for clients of the service and allow customers to demand a refund if the SLA is not
met”

> “When generating load artificially in order to test the scalability of a system, the load-generating
client needs to keep sending requests independently of the response time. If the client waits for
the previous request to complete before sending the next one, that behavior has the effect of
artificially keeping the queues shorter in the test than they would be in reality, which skews the
measurements”
## Solutions
> Scaling Stateless systems are easy, just spin up more machine.  
> But Stateful machines like DB, Kafka etc needs some engineering
- Vertical Scaling (Big Machine)
- Horizontal Scaling (More Machine)  

# Maintainability