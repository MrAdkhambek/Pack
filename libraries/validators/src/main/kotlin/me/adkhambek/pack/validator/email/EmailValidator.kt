package me.adkhambek.pack.validator.email

import java.util.regex.Pattern
import me.adkhambek.pack.validator.Validator


public class EmailValidator : Validator<String?> {

    public object KEY : Validator.Key

    private companion object {
        private val EMAIL_PATTERN: Pattern =
            Pattern.compile("[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})")
    }

    override fun isValid(value: String?): Set<Validator.Key> {
        return if (isValidPassword(value)) emptySet()
        else setOf(KEY)
    }

    private fun isValidPassword(s: String?): Boolean {
        return !s.isNullOrEmpty() && EMAIL_PATTERN.matcher(s).matches()
    }
}
