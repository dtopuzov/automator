# Performance Tests 

Set of dummy org.automator.web tests that are used to measure performance of parallel test execution.

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

## Actual Results

- 1 Core:   Executed 81 tests in 2m 10s (BUILD SUCCESSFUL in 2m 12s)

- 2 Cores:  Executed 81 tests in 1m 15s (BUILD SUCCESSFUL in 1m 18s)

- 3 Cores:  Executed 81 tests in 52.5s (BUILD SUCCESSFUL in 59s)

- 4 Cores:  Executed 81 tests in 47.7s (BUILD SUCCESSFUL in 50s)

- 5 Cores:  Executed 81 tests in 44s (BUILD SUCCESSFUL in 46s)

- 6 Cores:  Executed 81 tests in 40.7s (BUILD SUCCESSFUL in 43s)
