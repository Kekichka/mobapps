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
        val result = useCase.execute("taken@pookiemail.com")
        assertEquals(EmailCheckResult.Unavailable, result)
    }

    @Test
    fun email_isFree_isAvailable() = runBlocking {
        val result = useCase.execute("available@pookiemail.com")
        assertEquals(EmailCheckResult.Available, result)
    }

    @Test
    fun email_without_atSymbol_isInvalid() = runBlocking {
        val result = useCase.execute("withoutsobachka.com")
        assertTrue(result is EmailCheckResult.Invalid)
    }

    @Test
    fun email_without_dotSymbol_isInvalid() = runBlocking {
        val result = useCase.execute("withoutkrapka@pookiemail")
        assertTrue(result is EmailCheckResult.Invalid)
    }

    @Test
    fun email_with_at_and_dot_isValidByValidator() {
        assertTrue(validator.isValid("valid@pookiemail.com"))
    }
}
