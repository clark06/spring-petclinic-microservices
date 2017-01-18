package org.springframework.samples.petclinic.api.application;

import lombok.Data;
import java.util.List;

/**
 * @author mszarlinski on 2017-01-17.
 */
@Data
public class OwnersList {

    private final List<OwnerDetails> owners;

    private final boolean failure;

    private OwnersList(List<OwnerDetails> owners, boolean failure) {
        this.owners = owners;
        this.failure = failure;
    }

    public static OwnersList failure() {
        return new OwnersList(null, true);
    }

    public static OwnersList of(List<OwnerDetails> owners) {
        return new OwnersList(owners, false);
    }
}
