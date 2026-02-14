package com.ganesh.LogCollector.dto;


import java.time.Instant;


public record LogRequest (
     Instant timestamp,
     String serviceName,
     String environment,
     String logLevel,
     String message,
     String traceId,
     Long latencyMs
) {}
