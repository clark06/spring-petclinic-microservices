package org.springframework.samples.petclinic.api.application;

import lombok.Data;

/**
 * @author Maciej Szarlinski
 */
@Data
public class OwnerDescriptor {

    private final String firstName;

    private final String lastName;
}
