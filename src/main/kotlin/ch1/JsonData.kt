package ch1

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Play(
    val name: String,
    val type: String
)

@Serializable
data class Performance(
    val playID: String,
    val audience: Int
)

@Serializable
data class Invoice(
    val customer: String,
    val performances: List<Performance>,
)

private val playsJson =
    """
    {
        "hamlet": {"name": "Hamlet", "type": "tragedy"},
        "as-like": {"name": "As You Like It", "type": "comedy"},
        "othello": {"name": "Othello", "type": "tragedy"}
    }
    """.trimIndent()

private val invoicesJson =
    """
    [
        {
            "customer": "BigCo",
            "performances": [
                {
                    "playID": "hamlet",
                    "audience": 55
                },
                {
                    "playID": "as-like",
                    "audience": 35
                },
                {
                    "playID": "othello",
                    "audience": 40
                }
            ]
        }
    ]
    """.trimIndent()

val format = Json {
    ignoreUnknownKeys = true
}

val plays = format.decodeFromString<Map<String, Play>>(playsJson)
val invoices = format.decodeFromString<List<Invoice>>(invoicesJson)
