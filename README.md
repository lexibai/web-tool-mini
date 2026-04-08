# 🛠️ Web Tool Mini

> A local toolbox for web developers, providing commonly used utilities such as encoding/decoding, hashing, JSON processing, JWT tools, date/time conversion, and much more.

[中文文档](./README-cn.md)

## Tech Stack

- **Frontend**: Vue 3 + TypeScript + Vite
- **State Management**: Pinia
- **Routing**: Vue Router
- **Linting**: ESLint

## Getting Started

```bash
# Install dependencies
npm install

# Start development server
npm run dev

# Type-check and build for production
npm run build

# Preview production build
npm run preview

# Lint the code
npm run lint
```

## Roadmap

### I. Encoding / Decoding

| Feature | Status |
|---------|--------|
| Base64 Encode / Decode | 📋 Planned |
| URL Encode / Decode | 📋 Planned |
| HTML Entity Encode / Decode | 📋 Planned |
| Unicode Escape / Unescape | 📋 Planned |
| Hex Encode / Decode | 📋 Planned |
| ASCII ↔ Character | 📋 Planned |
| Base58 / Base32 Encode / Decode | 📋 Planned |
| Morse Code Encode / Decode | 📋 Planned |

### II. Hash / Encryption

| Feature | Status |
|---------|--------|
| MD5 | 📋 Planned |
| SHA-1 / SHA-256 / SHA-512 | 📋 Planned |
| HMAC (MD5 / SHA series) | 📋 Planned |
| bcrypt Hash Generate / Verify | 📋 Planned |
| CRC32 | 📋 Planned |
| GOST / SM3 (Chinese National Standard) | 📋 Planned |
| AES Symmetric Encrypt / Decrypt | 📋 Planned |
| RSA Asymmetric Encrypt / Decrypt | 📋 Planned |
| File MD5 / SHA Checksum | 📋 Planned |

### III. JSON Tools

| Feature | Status |
|---------|--------|
| JSON Format / Prettify | 📋 Planned |
| JSON Minify | 📋 Planned |
| JSON Validate / Syntax Highlight | 📋 Planned |
| JSON to XML | 📋 Planned |
| JSON to YAML | 📋 Planned |
| JSON to TOML | 📋 Planned |
| JSONPath Query | 📋 Planned |
| JSON Diff / Compare | 📋 Planned |
| JSON Schema Generate / Validate | 📋 Planned |
| JSON to CSV | 📋 Planned |
| JSON to TypeScript Interface | 📋 Planned |

### IV. JWT Tools

| Feature | Status |
|---------|--------|
| JWT Decode (Header + Payload) | 📋 Planned |
| JWT Signature Verify (HS256 / RS256 / ES256) | 📋 Planned |
| JWT Generate (Custom Payload + Secret) | 📋 Planned |
| JWT Expiration Check | 📋 Planned |
| JWT Weak Secret Brute-force (Security Tool) | 📋 Planned |

### V. Timestamp / Date

| Feature | Status |
|---------|--------|
| Unix Timestamp ↔ DateTime | 📋 Planned |
| Relative Time (e.g. "2 days ago") | 📋 Planned |
| Timezone Conversion | 📋 Planned |
| Cron Expression Parser | 📋 Planned |
| Millisecond / Microsecond / Nanosecond Conversion | 📋 Planned |
| Date Arithmetic | 📋 Planned |
| Business Day Calculator | 📋 Planned |

### VI. Regular Expressions

| Feature | Status |
|---------|--------|
| Regex Tester & Visualizer | 📋 Planned |
| Common Regex Library (Email, URL, Phone, IP, etc.) | 📋 Planned |
| Regex Generator (from text samples) | 📋 Planned |
| Regex Syntax Cheatsheet | 📋 Planned |

### VII. Text / String Processing

| Feature | Status |
|---------|--------|
| String Length Counter (bytes / chars) | 📋 Planned |
| Case Converter (camelCase / snake_case / CONSTANT / Title) | 📋 Planned |
| Placeholder Replacer (`{name}` → value) | 📋 Planned |
| Random String Generator (Password / Token) | 📋 Planned |
| Text Deduplicate / Sort / Reverse | 📋 Planned |
| Character / Word / Line Counter | 📋 Planned |
| Markdown to HTML | 📋 Planned |
| Levenshtein Edit Distance | 📋 Planned |
| Chinese Simplified ↔ Traditional | 📋 Planned |
| Text Diff | 📋 Planned |

### VIII. Color / Image Tools

| Feature | Status |
|---------|--------|
| Color Converter (HEX ↔ RGB ↔ HSL ↔ HSV) | 📋 Planned |
| Image to Base64 | 📋 Planned |
| Base64 Preview (Image / PDF) | 📋 Planned |
| QR Code Generator | 📋 Planned |
| QR Code Decoder | 📋 Planned |
| Image Compress / Crop | 📋 Planned |
| CSS Gradient Generator | 📋 Planned |
| Color Palette Generator | 📋 Planned |

### IX. Network / Request Tools

| Feature | Status |
|---------|--------|
| HTTP Request Tester (GET / POST / PUT / DELETE) | 📋 Planned |
| IP Info Lookup (Local IP, Geolocation) | 📋 Planned |
| User-Agent Parser | 📋 Planned |
| DNS Lookup | 📋 Planned |
| URL Parser (scheme / host / path / query) | 📋 Planned |
| HTTP Status Code Reference | 📋 Planned |
| CORS Diagnostics | 📋 Planned |

### X. Developer Utilities

| Feature | Status |
|---------|--------|
| Code Prettify / Minify (JS / CSS / HTML) | 📋 Planned |
| UUID / GUID Generator (v1 / v3 / v4 / v5) | 📋 Planned |
| Lorem Ipsum Generator | 📋 Planned |
| Mock Data Generator (Name, Phone, Email, Address, etc.) | 📋 Planned |
| SQL Formatter | 📋 Planned |
| CSS Unit Converter (px / rem / em / vw) | 📋 Planned |
| Number Base Converter (Binary / Octal / Decimal / Hex) | 📋 Planned |
| Keyboard Event Code Lookup (KeyCode) | 📋 Planned |
| File Size Unit Converter | 📋 Planned |
| Language Code Reference (ISO 639) | 📋 Planned |

---

Status: 📋 Planned · 🚧 In Progress · ✅ Done

## Project Structure

```
web-tool-mini/
├── src/
│   ├── assets/          # Static assets
│   ├── components/      # Shared components
│   ├── router/          # Route definitions
│   ├── stores/          # Pinia stores
│   ├── views/           # Page views
│   └── App.vue
├── public/
├── index.html
└── vite.config.ts
```

## License

[MIT](./LICENSE)
