package com.jgerardo.fromzeroapi.iam.infrastructure.hashing.bcrypt;

import com.jgerardo.fromzeroapi.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
