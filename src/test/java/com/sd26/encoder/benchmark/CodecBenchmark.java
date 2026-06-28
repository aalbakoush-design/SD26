package com.sd26.encoder.benchmark;

import com.sd26.encoder.service.CodecService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * JMH microbenchmarks for {@link CodecService} encoding/decoding operations.
 * <p>
 * Measures throughput (ops/sec) for Hex and Base64 encoding/decoding
 * with various input sizes.
 * </p>
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 0)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class CodecBenchmark {

    private CodecService codecService;

    private String smallInput;
    private String mediumInput;
    private String largeInput;

    private String smallHex;
    private String mediumHex;
    private String largeHex;

    private String smallB64;
    private String mediumB64;
    private String largeB64;

    @Setup
    public void setup() {
        codecService = new CodecService();

        smallInput = "Hello, SD26!";
        mediumInput = "The quick brown fox jumps over the lazy dog. "
                + "This is a medium length input string for JMH benchmarks. "
                + "Encoding and decoding should handle this efficiently.";
        largeInput = generateLargeString(10000);

        smallHex = codecService.encodeHex(smallInput);
        mediumHex = codecService.encodeHex(mediumInput);
        largeHex = codecService.encodeHex(largeInput);

        smallB64 = codecService.encodeBase64(smallInput);
        mediumB64 = codecService.encodeBase64(mediumInput);
        largeB64 = codecService.encodeBase64(largeInput);
    }

    private String generateLargeString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('A' + (i % 26)));
        }
        return sb.toString();
    }

    // ========================================================
    // Hex Encoding Benchmarks
    // ========================================================
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void encodeHex_smallInput(Blackhole bh) {
        bh.consume(codecService.encodeHex(smallInput));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void encodeHex_mediumInput(Blackhole bh) {
        bh.consume(codecService.encodeHex(mediumInput));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void encodeHex_largeInput(Blackhole bh) {
        bh.consume(codecService.encodeHex(largeInput));
    }

    // ========================================================
    // Hex Decoding Benchmarks
    // ========================================================
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void decodeHex_smallInput(Blackhole bh) {
        bh.consume(codecService.decodeHex(smallHex));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void decodeHex_mediumInput(Blackhole bh) {
        bh.consume(codecService.decodeHex(mediumHex));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void decodeHex_largeInput(Blackhole bh) {
        bh.consume(codecService.decodeHex(largeHex));
    }

    // ========================================================
    // Base64 Encoding Benchmarks
    // ========================================================
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void encodeBase64_smallInput(Blackhole bh) {
        bh.consume(codecService.encodeBase64(smallInput));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void encodeBase64_mediumInput(Blackhole bh) {
        bh.consume(codecService.encodeBase64(mediumInput));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void encodeBase64_largeInput(Blackhole bh) {
        bh.consume(codecService.encodeBase64(largeInput));
    }

    // ========================================================
    // Base64 Decoding Benchmarks
    // ========================================================
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void decodeBase64_smallInput(Blackhole bh) {
        bh.consume(codecService.decodeBase64(smallB64));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void decodeBase64_mediumInput(Blackhole bh) {
        bh.consume(codecService.decodeBase64(mediumB64));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1)
    public void decodeBase64_largeInput(Blackhole bh) {
        bh.consume(codecService.decodeBase64(largeB64));
    }
}
