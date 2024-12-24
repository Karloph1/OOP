package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Quote class.
 */
public class Quote extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Object text;

    public Quote(Object text) {
        if (text instanceof Table || text instanceof Task) {
            throw new IllegalArgumentException("Impossible to add such element in table");
        }

        this.text = text;
    }

    public String getText() {
        return text.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Quote quote = (Quote) o;

        return Objects.equals(text, quote.text);
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "> " + text;
    }
}
