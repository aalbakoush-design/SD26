# 🔐 SD26 Encoder Web Application

A secure Java web application for encoding and decoding text using **Hex** and **Base64** formats, powered by **Apache Commons Codec**.

## 📋 Table of Contents
- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [CI/CD Pipeline](#cicd-pipeline)
- [Testing Strategy](#testing-strategy)
- [Security Analysis](#security-analysis)
- [Docker](#docker)
- [Formal Verification](#formal-verification)
- [Links](#links)

## 📌 Project Overview

| Attribute | Value |
|-----------|-------|
| **Group** | `com.sd26` |
| **Artifact** | `sd26-encoder` |
| **Java Version** | 21 |
| **Spring Boot** | 3.4.5 |
| **Build Tool** | Maven |
| **Repository** | [GitHub](https://github.com/aalbakoush-design/SD26) |

## 🛠 Tech Stack

- **Spring Boot 3.4.5** - Web framework
- **Apache Commons Codec 1.17.1** - Encoding/Decoding
- **Thymeleaf** - Web UI templates
- **JaCoCo** - Code coverage
- **PiTest** - Mutation testing
- **JMH** - Performance benchmarking
- **OpenJML** - Formal verification
- **Docker** - Containerization
- **GitHub Actions** - CI/CD

## 🔄 CI/CD Pipeline

The pipeline is defined in `.github/workflows/maven.yml` and consists of **5 parallel jobs**:

| Job | Description |
|-----|-------------|
| **build** | Compiles code, runs 40 JUnit tests, generates JaCoCo report |
| **docker-push** | Builds & pushes Docker image to DockerHub (after successful build) |
| **snyk-security-scan** | Scans dependencies for known vulnerabilities |
| **gitguardian-scan** | Detects hardcoded secrets and API keys |
| **sonarcloud-analysis** | Analyzes code quality, bugs, and security hotspots |

## 🧪 Testing Strategy

### Unit Tests (JUnit 5)
- **40 tests** across 2 test classes
- `CodecServiceTest`: 30 tests (Hex encoding/decoding, Base64 encoding/decoding, Roundtrip integrity)
- `EncodeControllerTest`: 10 tests (GET/POST endpoints, error handling)

### Code Coverage (JaCoCo)
- Analyzes line coverage across all 3 main classes
- Generates HTML/XML reports

### Mutation Testing (PiTest)
- **18 mutations generated**, 9 killed (50%)
- **Test Strength: 100%** (no surviving mutants with coverage)
- Tests both `service` and `controller` packages

### Performance Benchmarking (JMH)
- **12 microbenchmarks** measuring throughput (ops/ms)
- Tests Hex and Base64 encode/decode with small, medium, and large inputs

### JMH Results Summary

| Operation | Small (ops/ms) | Medium (ops/ms) | Large (ops/ms) |
|-----------|:--------------:|:---------------:|:--------------:|
| Hex Encode | 15,406 ± 5,985 | 2,703 ± 3,355 | 23.7 ± 10.1 |
| Hex Decode | 6,652 ± 9,661 | 1,405 ± 524 | 20.1 ± 24.2 |
| Base64 Encode | 710 ± 239 | 406 ± 302 | 14.7 ± 6.8 |
| Base64 Decode | 969 ± 282 | 196 ± 295 | 10.2 ± 6.6 |

## 🔒 Security Analysis

### Snyk
- Scans `pom.xml` dependencies for CVEs
- Threshold: High severity
- Integrated in CI/CD pipeline

### GitGuardian
- Scans repository for hardcoded secrets
- Checks git history (`fetch-depth: 0`)
- Integrated in CI/CD pipeline

### SonarCloud
- Analyzes code quality and security hotspots
- Detects bugs, code smells, and vulnerabilities
- Available at: [SonarCloud Dashboard](https://sonarcloud.io)

## 🐳 Docker

- **DockerHub**: [aqeelomar/sd26-encoder](https://hub.docker.com/r/aqeelomar/sd26-encoder)
- **Multi-stage build** for minimal image size (~97 MB)
- Runs as **non-root user** (`sd26`)
- Includes **health check** endpoint

## ✅ Formal Verification (JML/OpenJML)

All 4 core methods in `CodecService.java` have **JML specifications**:
- `encodeHex()` - requires non-null input, ensures non-null result
- `decodeHex()` - requires non-null input, ensures non-null result
- `encodeBase64()` - requires non-null input, ensures non-null result
- `decodeBase64()` - requires non-null input, ensures non-null result

## 🔗 Links

- **GitHub Repository**: https://github.com/aalbakoush-design/SD26
- **GitHub Actions**: https://github.com/aalbakoush-design/SD26/actions
- **DockerHub**: https://hub.docker.com/r/aqeelomar/sd26-encoder
- **SonarCloud**: https://sonarcloud.io
- **Snyk Dashboard**: https://app.snyk.io
- **GitGuardian Dashboard**: https://dashboard.gitguardian.com