package com.adkhambek.pack.validator.password

import com.adkhambek.pack.validator.Validator
import java.util.regex.Pattern

public class PasswordValidator(
    private val pattern: Pattern = PASSWORD_PATTERN,
) : Validator<String?> {

    public object KEY : Validator.Key

    private companion object {
        // Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:
        private val PASSWORD_PATTERN: Pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}\$")
    }

    override fun isValid(value: String?): Set<Validator.Key> {
        return if (isValidPassword(value)) {
            emptySet()
        } else {
            setOf(KEY)
        }
    }

    private fun isValidPassword(s: String?): Boolean {
        return !s.isNullOrEmpty() && pattern.matcher(s).matches()
    }
}
