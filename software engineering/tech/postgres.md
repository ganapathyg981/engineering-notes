# PostgreSQL Database Notes for Staff Engineer Interview

## Core Architecture

### Process & Memory Architecture
- PostgreSQL uses a multi-process architecture (not multi-threaded) where each client connection spawns a new server process
- Shared memory is used for caching and inter-process communication
- The shared buffer pool caches frequently accessed data pages to minimize disk I/O
- Background processes handle various tasks:
    - Writer process flushes dirty pages to disk
    - Checkpointer process ensures data durability
    - Autovacuum handles dead tuple cleanup and table statistics
    - WAL writer manages write-ahead logging

### Storage
- Data is stored in tables which are divided into 8KB pages
- Heap files contain the actual table data
- TOAST (The Oversized-Attribute Storage Technique) handles large field values
- Table spaces allow data to be stored on different physical locations
- Data files are managed by the storage manager layer

## Transaction Management

### MVCC (Multi-Version Concurrency Control)
- Each transaction sees a snapshot of the database at the start
- Multiple versions of rows exist simultaneously
- Transaction ID wraparound prevention is critical for long-term maintenance
- Dead tuple cleanup is handled by VACUUM process
- Isolation levels: Read Committed (default), Repeatable Read, Serializable

### Write-Ahead Logging (WAL)
- All changes are written to WAL before being applied to data files
- Ensures durability and enables point-in-time recovery
- WAL segments are typically 16MB
- Archive logging enables continuous backup and replication
- WAL compression can reduce storage and I/O requirements

## Performance Optimization

### Query Planning
- Cost-based optimizer uses statistics to generate execution plans
- EXPLAIN and EXPLAIN ANALYZE are crucial debugging tools
- Statistics are collected by ANALYZE command
- Plan caching improves prepared statement performance
- Query rewriting can optimize complex queries

### Indexing Strategies
- B-tree is the default index type, optimal for equality and range queries
- GiST supports geometric data and custom data types
- GIN is optimized for composite values (arrays, jsonb)
- BRIN works well for naturally ordered data
- Partial indexes can reduce index size and maintenance overhead
- Covering indexes can improve query performance by including all needed columns

## High Availability & Replication

### Streaming Replication
- Based on WAL shipping
- Synchronous or asynchronous modes
- Hot standby allows read queries on replicas
- Replication slots prevent WAL cleanup before replica consumption
- Cascading replication reduces primary server load

### Failover & Recovery
- Promote standby to primary during failures
- Point-in-Time Recovery (PITR) enables precise recovery states
- Recovery targets can be timestamp, transaction ID, or named restore point
- pg_rewind allows failed primary to rejoin cluster

## Maintenance & Monitoring

### Routine Maintenance
- VACUUM removes dead tuples and updates statistics
- ANALYZE updates statistics for query planning
- Reindex operations may be needed for index corruption
- Table/index bloat monitoring and management
- Regular backup strategy implementation

### Performance Monitoring
- pg_stat_statements tracks query execution statistics
- pg_stat_activity shows current session state
- System catalogs provide database metadata
- Custom monitoring using extensions like pg_stat_statements
- Wait event analysis for bottleneck identification

## Advanced Features

### Partitioning
- Table partitioning by range, list, or hash
- Partition pruning improves query performance
- Declarative partitioning simplifies management
- Dynamic partition routing for INSERTs
- Partition-wise joins and aggregation

### Extensions
- Foreign Data Wrappers (FDW) for external data access
- PostGIS for spatial data management
- TimescaleDB for time-series workloads
- PL/pgSQL for stored procedures
- Custom extensions using C language

## Security

### Authentication & Authorization
- Client authentication methods (md5, scram-sha-256, cert)
- Role-based access control
- Row-level security policies
- SSL/TLS configuration
- Password encryption and management

### Audit & Compliance
- Statement logging
- Object access logging
- Custom audit triggers
- System catalog security
- Regulatory compliance considerations

## Performance Tuning Parameters

### Memory Configuration
- shared_buffers (typically 25% of RAM)
- effective_cache_size (estimate of disk cache)
- work_mem for complex sorts
- maintenance_work_mem for maintenance operations
- huge_pages for large installations

### Checkpoint Configuration
- checkpoint_timeout
- max_wal_size
- checkpoint_completion_target
- wal_buffers
- wal_compression