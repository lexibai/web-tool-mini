# web-tool-mini

A mini Spring Boot server providing common web development tools via REST API.

## Getting Started

```bash
mvn spring-boot:run
```

The server starts on port **8080** by default.

## Available Tools

### JSON Tools
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/json/format` | Format/pretty-print a JSON string |
| POST | `/api/json/validate` | Validate a JSON string |

### URL Tools
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/url/encode` | URL-encode a string |
| POST | `/api/url/decode` | URL-decode a string |

### Base64 Tools
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/base64/encode` | Base64-encode a string |
| POST | `/api/base64/decode` | Base64-decode a string |

### UUID Generator
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/uuid` | Generate a random UUID |
| GET | `/api/uuid/batch?count=N` | Generate N UUIDs (1–100) |

### Timestamp Tools
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/timestamp/now` | Get current timestamp |
| POST | `/api/timestamp/convert` | Convert a Unix timestamp to a readable date |

### Hash Generator
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/hash` | Generate MD5, SHA-1, and SHA-256 hashes |

## Examples

**Format JSON:**
```bash
curl -X POST http://localhost:8080/api/json/format \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","age":30}'
```

**URL encode:**
```bash
curl -X POST http://localhost:8080/api/url/encode \
  -H "Content-Type: application/json" \
  -d '{"value":"hello world"}'
```

**Base64 encode:**
```bash
curl -X POST http://localhost:8080/api/base64/encode \
  -H "Content-Type: application/json" \
  -d '{"value":"hello"}'
```

**Generate UUID:**
```bash
curl http://localhost:8080/api/uuid
```

**Get current timestamp:**
```bash
curl http://localhost:8080/api/timestamp/now
```

**Hash a string:**
```bash
curl -X POST http://localhost:8080/api/hash \
  -H "Content-Type: application/json" \
  -d '{"value":"hello"}'
```

## Build & Test

```bash
mvn clean test
```
