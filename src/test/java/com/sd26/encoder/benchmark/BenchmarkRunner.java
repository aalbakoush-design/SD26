package com.sd26.encoder.benchmark;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * Runner for JMH microbenchmarks.
 * <p>
 * Use this class to execute all encoding/decoding benchmarks
 * and generate results in both text and JSON formats.
 * </p>
 */
public class BenchmarkRunner {

    /**
     * Main entry point for running JMH benchmarks.
     *
     * @param args command-line arguments (not used)
     * @throws Exception if benchmark execution fails
     */
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include("com\\.sd26\\.encoder\\.benchmark\\..*")
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(1))
                .forks(0)
                .resultFormat(ResultFormatType.JSON)
                .result("target/jmh-results.json")
                .shouldFailOnError(true)
                .build();

        new Runner(opt).run();
    }
}
