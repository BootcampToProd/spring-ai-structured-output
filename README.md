# Spring AI - Structured Output

This repository demonstrates how to use **Spring AI's Structured Output Converters** to transform responses from Large Language Models (LLMs) into clean, usable data formats for your Java applications.

🚀 **It covers several key ways to get structured data**:
*   Generating raw **JSON responses** directly from the AI model.
*   Converting AI output into specific **Java objects (Entities)** using `BeanOutputConverter`.
*   Obtaining a **List of Java objects (Entities)** for multiple structured items.
*   Getting flexible **Map-based output** (`Map<String, Object>`) for dynamic data structures.
*   Retrieving simple **Lists of Strings** using `ListOutputConverter`.

All examples are built around a fun "Social Media Analytics" theme.

📖 **Dive Deeper**: For a complete walkthrough, detailed explanations of each structured output type, and step-by-step instructions, read our blog post.<br>
👉 [Spring AI Structured Output: Converting AI Responses into Structured Data](https://bootcamptoprod.com/spring-ai-structured-output-guide/)

🎥 **Visual Learning**: Prefer video tutorials? Watch our step-by-step implementation guide on YouTube.<br>
👉 [Spring AI Structured Output: Convert AI Responses to Java Objects in Minutes](https://youtu.be/jlLGpd7bsoI)
---

## 📦 Environment Variables

Make sure to provide these Java environment variables when running the application:

- `GEMINI_API_KEY`: Your Google Gemini API key.
