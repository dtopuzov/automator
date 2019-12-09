# Performance Tests 

Set of dummy web tests that are used to measure performance of parallel test execution.

## Parallel Testing

Parallelism can be achieved by following lines in gradle test tasks:

```
    maxHeapSize = '1G'
    int cores = Runtime.runtime.availableProcessors().intdiv(2) ?: 1
    systemProperties = [
            'junit.jupiter.execution.parallel.enabled'                 : true,
            'junit.jupiter.execution.parallel.mode.default'            : 'same_thread',
            'junit.jupiter.execution.parallel.mode.classes.default'    : 'concurrent',
            'junit.jupiter.execution.parallel.config.strategy'         : 'fixed',
            'junit.jupiter.execution.parallel.config.fixed.parallelism': cores
    ]
    maxParallelForks = cores
    forkEvery = 1
```
