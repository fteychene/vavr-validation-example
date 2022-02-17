package model;

import io.vavr.control.Option;

public record Address(
        String line1,
        Option<String> line2,
        ZipCode zip,
        String city
){}