package com.example.nodemanager.help

import java.security.MessageDigest
import java.util.UUID

fun generateNodeName(input: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val hash = digest.digest(input.toByteArray())
    return "0x" + hash.takeLast(20).joinToString("") { "%02x".format(it) }
}