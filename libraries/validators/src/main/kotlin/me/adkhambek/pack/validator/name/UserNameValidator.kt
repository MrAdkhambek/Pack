package me.adkhambek.pack.validator.name

import java.util.regex.Pattern
import me.adkhambek.pack.validator.Validator

public class UserNameValidator(
    private val pattern: Pattern = USERNAME_PATTERN,
) : Validator<String?> {

    public object KEY : Validator.Key
    private companion object {
        // Minimum three characters:
        private val USERNAME_PATTERN: Pattern = Pattern.compile("^[a-zA-Z\\d]{3,}\$")
    }

    override fun isValid(value: String?): Set<Validator.Key> {
        return if (isValidUserName(value)) emptySet()
        else setOf(KEY)
    }

    private fun isValidUserName(s: String?): Boolean {
        return !s.isNullOrEmpty() && pattern.matcher(s).matches()
    }
}