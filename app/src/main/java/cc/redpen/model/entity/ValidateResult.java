package cc.redpen.model.entity;

import java.util.ArrayList;
import java.util.List;

public class ValidateResult {

    private List<Error> errors;

    public void setErrors(List<Error> errors) {
        this.errors = new ArrayList<>(errors);
    }

    public List<Error> getErrors() {
        return errors;
    }

}
