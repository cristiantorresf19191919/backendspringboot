package com.opttimusdev.springbootapirest.auth;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "miclavesevreta";
    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAszcp7V5iG7Lr7sR2VPF1scY0T2aiLyHpsB7J9a+YOgXrfOQY\n" +
            "ylZqCQ8+DPgBr+G/Ow1XU5JYAR8+LoyZPuZAgYgUlftOqjWI5xKYY43Q3D5W7WaG\n" +
            "Keqt3K/iQYvJ7Y4q/2tQjarp6JUGCQLnQkq/GVSmTojp3cJyaT7ClwLYjRa9Uj8A\n" +
            "vDzb0m6HzfjhsWLN8hW2PO+eHIDrLZ4ZUErZtKlBmb8w7QtBWv5+vRTs7s76rEbp\n" +
            "plTI9h388LCRq066I20CSFFQCx490S4P97XYLPJjIS3hPA5WViIVLjUdENDYbF3N\n" +
            "7umAcYcyvM7nfrAs313CEq9DQ2X78R3cpqcEnwIDAQABAoIBAHjhs5EdQZWnWMaD\n" +
            "k8F14MoZg/0uoCSkfkjCa70hRM+WYKGsBSCtqvYTZuo+enzVCzTcqdN/+YZAOaQS\n" +
            "R+eKTWZRkHkdupDKbV5XR5yW8pMsJVwZ9FLfrYgWxskpbfmjFFOWYg48g2vS4PBB\n" +
            "GhW37zA5zbdZWE2xM5TTtVNAoyp+Un0L0aIxZdUUBMBdyNHNEx+KZP8495FfVdQ0\n" +
            "KvJP0xM+ycqTCPHLP7EZ1cEdoIHZonWrJ54tajf4xc7JAJOIdnMmat2fL0aUJuBQ\n" +
            "JLDMcvnKlczQE79dN8aBfxN9H4vh3qrLmz32FOMASF1Pvixj8UfgEOUjXxJB9dLs\n" +
            "8W/H41kCgYEA7jFlx1HiH5Q9GyVwC0f1Ap6YnNJAVVUqMudiamC8elzDg07LsMhM\n" +
            "hjO1/pBAI2hwS6HqbLlFj994x6AeGmnnv743Lq+cElisvvyMl62ndu95b5UzhnUB\n" +
            "iX8RvJsSnOgQY6wdAL3gtWrmRF5FiBcnWi9ByK1qjbvzw6UF8iyfvU0CgYEAwJ0J\n" +
            "/mug2WeYYCck+ReJQDHcqZ0yHVBRjYszV9hMzV61a91RqBwOn6ehae/sRdAXTLRC\n" +
            "jCwdJgcCV/dOw74XpQGgJCzP1DkpW4xDh+KkU6/0mIkoiULNZzBScYGFx2oBLl3e\n" +
            "4jpJbHpwqMvQ4S281HMS5RoeyARJcY+wjW/Vg5sCgYEA6w9elKPvFiDxaK+6cbeS\n" +
            "SAKBMwmeKVgBa/jCO565gbu3ygZhp1UTh8DSUqTCVu8DGvBv6tmvlzr+82nFK2Xm\n" +
            "KWgrBuyQhd5yo4uRp0HdKNdNLNu93Gvtl2YJ9+nFJYkyW1qHyKqZbg9PrJqn3ZST\n" +
            "5kEKl2Lj63lXJ3TceFCnV2UCgYBp16G/QgtwjCHKbJg/nPRZ50x3lB8Oxq0Z161O\n" +
            "D1kc3XdaH5MYitpqVyY23ZV7Q38aMlADUNZvqBhcmFSa8aFylfMXDdzipMqaOPh+\n" +
            "+kcMibsh+vTggGn40y/qmZ8W1qRxgznQHPFt/9Zamy1cH9MM93Mhm/ngNaLaGXiO\n" +
            "MC8nEwKBgQCnCTYReCoMlMYqC6gFxcdTdB9Mhw/x9jCcoBVpZI8W3WVvjhT6NBrj\n" +
            "1wj1sfpcDcY5GX+VR7hL6fgA1/xh/T23rsPxAyJqIYxHnj/0cZ+c+FkZTo5Z37qL\n" +
            "+/EFSm+yTxc34B8joI0ToieeyOpF6s6dux0NKSz5OC9YAoPT7sDoHg==\n" +
            "-----END RSA PRIVATE KEY-----";


    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAszcp7V5iG7Lr7sR2VPF1\n" +
            "scY0T2aiLyHpsB7J9a+YOgXrfOQYylZqCQ8+DPgBr+G/Ow1XU5JYAR8+LoyZPuZA\n" +
            "gYgUlftOqjWI5xKYY43Q3D5W7WaGKeqt3K/iQYvJ7Y4q/2tQjarp6JUGCQLnQkq/\n" +
            "GVSmTojp3cJyaT7ClwLYjRa9Uj8AvDzb0m6HzfjhsWLN8hW2PO+eHIDrLZ4ZUErZ\n" +
            "tKlBmb8w7QtBWv5+vRTs7s76rEbpplTI9h388LCRq066I20CSFFQCx490S4P97XY\n" +
            "LPJjIS3hPA5WViIVLjUdENDYbF3N7umAcYcyvM7nfrAs313CEq9DQ2X78R3cpqcE\n" +
            "nwIDAQAB\n" +
            "-----END PUBLIC KEY-----";


}
