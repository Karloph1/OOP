package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Code line class.
 */
public class CodeLine extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Text code;

    public CodeLine(Object code) {
        this.code = new Text.Builder(code).build();
    }

    public String getText() {
        return code.getText();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CodeLine codeLine = (CodeLine) o;

        return Objects.equals(code, codeLine.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "`" + code + "`";
    }
}
