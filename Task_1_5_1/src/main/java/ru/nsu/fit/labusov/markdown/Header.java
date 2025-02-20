package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Header class.
 */
public class Header extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Object text;
    private int titleDegree;

    /**
     * Header constructor method.
     */
    public Header(Object text) {
        if (text instanceof CodeBlock || text instanceof CodeLine || text instanceof Header
                || text instanceof List || text instanceof Quote || text instanceof Table
                || text instanceof Task) {
            throw new IllegalArgumentException("Impossible to add such element in table");
        }

        this.text = new Text.Builder(text).build();
    }

    /**
     * Set title method.
     */
    public Header titleDegree(int titleDegree) {
        if (titleDegree > 6) {
            throw new IllegalArgumentException("Title degree can be maximum 6");
        }

        this.titleDegree = titleDegree;
        return this;
    }

    public String getText() {
        return text.toString();
    }

    public int getTitleDegree() {
        return titleDegree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Header header = (Header) o;

        if (titleDegree != header.titleDegree) {
            return false;
        }

        return Objects.equals(text, header.text);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + titleDegree;
        return result;
    }

    @Override
    public String toString() {
        return "#".repeat(titleDegree) + " " + text;
    }
}
