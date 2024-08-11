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
  <img src="../../images/sample-system.png" alt="Sublime's custom image"/>
<br>
Sample System
</p>  

## Three Important Factors For a Good System  
 
 ### [Reliability](reliability.md#reliability)  
 ### [Scalability](scalability.md#scalability)  
 ### [Maintainability](maintainability.md#maintainability)
