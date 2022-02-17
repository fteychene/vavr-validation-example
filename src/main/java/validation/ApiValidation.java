package validation;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import model.Address;
import model.Email;
import model.Person;
import model.ZipCode;

public class ApiValidation {

    public static <T> Validation<Error, T> isNotNull(String path, T value) {
        if (value == null) {
            return Validation.invalid(new ValueError(path, null, "Should not be null"));
        }
        return Validation.valid(value);
    }

    public static Validation<Error, ZipCode> validateZipcode(String path, String zipCode) {
        if (zipCode == null) {
            return Validation.invalid(new ValueError(path, null, "Should not be null"));
        }
        if (!zipCode.matches("[0-9]{0,5}")) {
            return Validation.invalid(new ValueError(path, zipCode, "Not a valid zip code"));
        }
        return Validation.valid(new ZipCode(zipCode));
    }

    public static Validation<Error, Email> validateEmail(String path, String value) {
        if (!value.matches("^(.+)@(.+)$")) {
            return Validation.invalid(new ValueError(path, value, "Not a valid email"));
        }
        return Validation.valid(new Email(value));
    }

    public static Validation<Error, Address> validateAddress(String path, api.Address value) {
        return Validation.combine(
                        isNotNull("line1", value.getLine1()),
                        validateZipcode("zipCode", value.getZipCode()),
                        isNotNull("city", value.getCity())
                ).ap((line1, zipCode, city) -> new Address(line1, Option.of(value.getLine2()), zipCode, city))
                .mapError(errors -> new ComposeError(path, errors));
    }

    public static Validation<Seq<Error>, Person> validatePerson(api.Person value) {
        return Validation.combine(
                        isNotNull("firstName", value.getFirstName()),
                        isNotNull("lastName", value.getLastName()),
                        validateAddress("address", value.getAddress()),
                        validateEmail("mail", value.getMail())
                ).ap(Person::new);
    }

}
