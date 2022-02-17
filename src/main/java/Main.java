import validation.ApiValidation;
import validation.ComposeError;
import validation.Error;
import validation.ValueError;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Valid validation ===");
        System.out.println();
        var valid = ApiValidation.validatePerson(new api.Person()
                .setFirstName("Francois")
                .setLastName("Teychene")
                .setMail("francois@startree.ai")
                .setAddress(new api.Address()
                        .setLine1("18 rue de la batte")
                        .setZipCode("34000")
                        .setCity("Montpellier")));
        System.out.println(valid);

        System.out.println();

        System.out.println("=== Invalid validation ===");
        var invalid = ApiValidation.validatePerson(new api.Person()
                .setLastName("Teychene")
                .setMail("francoisstartree.ai")
                .setAddress(new api.Address()
                        .setZipCode("abcd")));
        System.out.println(invalid);

        System.out.println();

        System.out.println("=== Fold the validation ===");
        var validated = ApiValidation.validatePerson(new api.Person()
                .setLastName("Teychene")
                .setMail("francoisstartree.ai")
                .setAddress(new api.Address()
                        .setZipCode("abcd")));

        var stringFolded = validated.fold(
                errors -> errors.map(Main::prettyDisplay).mkString("\n"),
                v -> "User created with mail "+v.email()
        );
        System.out.println(stringFolded);
    }

    public static String prettyDisplay(Error validationError) {
        return switch (validationError) {
            case ValueError e -> "Error on field "+ e.getPath()+" [" + e.getValue()+ "] => "+ e.getReason();
            case ComposeError e -> "Error on "+ e.getPath()+" : \n"+e.getReasons().map(Main::prettyDisplay).map(s -> "\t"+s).mkString("\n");
        };
    }
}
