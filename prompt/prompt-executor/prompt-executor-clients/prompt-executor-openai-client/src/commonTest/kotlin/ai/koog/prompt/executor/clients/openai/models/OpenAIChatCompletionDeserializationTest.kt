package ai.koog.prompt.executor.clients.openai.models

import ai.koog.test.utils.verifyDeserialization
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenAIChatCompletionDeserializationTest {

    private val jsonSnakeCase: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
        encodeDefaults = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }

    @Test
    fun `should deserialize chat completion response when object is not null`() {
        // language=JSON
        val payload = """
            {
              "choices": [
                {
                  "index": 0,
                  "message": {
                    "role": "assistant",
                    "content": "The capital of France is **Paris**."
                  },
                  "finish_reason": "stop"
                }
              ],
              "created": 1716920000,
              "id": "chatcmpl-123",
              "model": "gpt-4o-2024-11-20",
              "object": "chat.completion",
              "usage": {
                "prompt_tokens": 9,
                "completion_tokens": 9,
                "total_tokens": 18
              }
            }
        """.trimIndent()

        val response: OpenAIChatCompletionResponse = verifyDeserialization(payload, jsonSnakeCase)

        assertEquals("chat.completion", response.objectType)
        assertEquals("chatcmpl-123", response.id)
        assertEquals("gpt-4o-2024-11-20", response.model)
        assertEquals(1, response.choices.size)
        assertEquals("stop", response.choices[0].finishReason)
        assertEquals("The capital of France is **Paris**.", response.choices[0].message.content?.text())
    }

    @Test
    fun `should deserialize chat completion response  when object is null`() {
        // language=JSON
        val payload = """
            {
              "choices": [
                {
                  "index": 0,
                  "message": {
                    "role": "assistant",
                    "content": "The capital of France is **Paris**."
                  },
                  "finish_reason": "stop"
                }
              ],
              "created": 1716920000,
              "id": "chatcmpl-123",
              "model": "gpt-4o-2024-11-20",
              "usage": {
                "prompt_tokens": 9,
                "completion_tokens": 9,
                "total_tokens": 18
              }
            }
        """.trimIndent()

        val response: OpenAIChatCompletionResponse = verifyDeserialization(payload, jsonSnakeCase)

        assertEquals(null, response.objectType)
        assertEquals("chatcmpl-123", response.id)
        assertEquals("gpt-4o-2024-11-20", response.model)
        assertEquals(1, response.choices.size)
        assertEquals("stop", response.choices[0].finishReason)
        assertEquals("The capital of France is **Paris**.", response.choices[0].message.content?.text())
    }

    @Test
    fun `should deserialize chat stream completion response when object is not null`() {
        // language=JSON
        val payload = """
            {
              "choices": [
                {
                  "index": 0,
                  "message": {
                    "role": "assistant",
                    "content": "The capital of France is **Paris**."
                  },
                  "finish_reason": "stop"
                }
              ],
              "created": 1716920000,
              "id": "chatcmpl-123",
              "model": "gpt-4o-2024-11-20",
              "object": "chat.completion",
              "usage": {
                "prompt_tokens": 9,
                "completion_tokens": 9,
                "total_tokens": 18
              }
            }
        """.trimIndent()

        val response: OpenAIChatCompletionStreamResponse = verifyDeserialization(payload, jsonSnakeCase)

        assertEquals("chat.completion", response.objectType)
        assertEquals("chatcmpl-123", response.id)
        assertEquals("gpt-4o-2024-11-20", response.model)
        assertEquals(1, response.choices.size)
        assertEquals("stop", response.choices[0].finishReason)
        assertEquals("The capital of France is **Paris**.", response.choices[0].delta.content)
    }

    @Test
    fun `should deserialize chat stream completion response  when object is null`() {
        // language=JSON
        val payload = """
            {
              "choices": [
                {
                  "index": 0,
                  "message": {
                    "role": "assistant",
                    "content": "The capital of France is **Paris**."
                  },
                  "finish_reason": "stop"
                }
              ],
              "created": 1716920000,
              "id": "chatcmpl-123",
              "model": "gpt-4o-2024-11-20",
              "usage": {
                "prompt_tokens": 9,
                "completion_tokens": 9,
                "total_tokens": 18
              }
            }
        """.trimIndent()

        val response: OpenAIChatCompletionStreamResponse = verifyDeserialization(payload, jsonSnakeCase)

        assertEquals(null, response.objectType)
        assertEquals("chatcmpl-123", response.id)
        assertEquals("gpt-4o-2024-11-20", response.model)
        assertEquals(1, response.choices.size)
        assertEquals("stop", response.choices[0].finishReason)
        assertEquals("The capital of France is **Paris**.", response.choices[0].delta.content)
    }
}


