package validation;

public sealed interface Error permits ComposeError, ValueError {
}
