package com.example.homework5

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

sealed class EmailCheckResult {
    object Available : EmailCheckResult()
    object Unavailable : EmailCheckResult()
    data class Invalid(val reason: String) : EmailCheckResult()
}

class CheckEmailAvailabilityUseCase {
    fun execute(email: String): EmailCheckResult {
        if (!email.contains("@") || !email.contains(".")) {
            return EmailCheckResult.Invalid("Email is not valid")
        }
        return if (isTaken(email)) {
            EmailCheckResult.Unavailable
        } else {
            EmailCheckResult.Available
        }
    }

    private fun isTaken(email: String): Boolean {
        return email == "taken@pookiemail.com"
    }
}

class LoginValidator {
    fun isValid(email: String): Boolean = email.contains("@") && email.contains(".")
}

class CheckEmailAvailabilityUseCaseTest {

    private val useCase = CheckEmailAvailabilityUseCase()
    private val validator = LoginValidator()

    @Test
    fun email_isTaken_isUnavailable() = runBlocking {
        assertEquals(EmailCheckResult.Unavailable, useCase.execute("taken@pookiemail.com"))
    }

    @Test
    fun email_isFree_isAvailable() = runBlocking {
        assertEquals(EmailCheckResult.Available, useCase.execute("available@pookiemail.com"))
    }

    @Test
    fun email_without_atSymbol_isInvalid() = runBlocking {
        assertTrue(useCase.execute("withoutsobachka.com") is EmailCheckResult.Invalid)
    }

    @Test
    fun email_without_dotSymbol_isInvalid() = runBlocking {
        assertTrue(useCase.execute("withoutkrapka@pookiemail") is EmailCheckResult.Invalid)
    }

    @Test
    fun email_with_at_and_dot_isValidByValidator() {
        assertTrue(validator.isValid("valid@pookiemail.com"))
    }
}
