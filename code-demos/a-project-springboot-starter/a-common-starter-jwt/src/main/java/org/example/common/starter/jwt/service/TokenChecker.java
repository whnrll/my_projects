package org.example.common.starter.jwt.service;

public interface TokenChecker {
    boolean verify(String token) throws Exception;
}
