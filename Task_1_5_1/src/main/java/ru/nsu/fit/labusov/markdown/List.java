package ru.nsu.fit.labusov.markdown;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * List class.
 */
public class List extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ArrayList<Object> texts;
    private final boolean hasOrder;

    private List(Builder builder) {
        this.texts = builder.texts;
        this.hasOrder = builder.hasOrder;
    }

    public Object[] getTexts() {
        return texts.toArray();
    }

    public boolean getHasOrder() {
        return hasOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        List list = (List) o;

        if (hasOrder != list.hasOrder) {
            return false;
        }

        return Objects.equals(texts, list.texts);
    }

    @Override
    public int hashCode() {
        int result = texts != null ? texts.hashCode() : 0;
        result = 31 * result + (hasOrder ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (hasOrder) {
            for (int i = 0; i < texts.size(); i++) {
                stringBuilder
                        .append(i + 1)
                        .append(". ")
                        .append(texts.get(i))
                        .append("\n");
            }
        } else {
            for (Object text : texts) {
                stringBuilder
                        .append("- ")
                        .append(text)
                        .append("\n");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    /**
     * List builder class.
     */
    public static class Builder {
        private final ArrayList<Object> texts;
        private boolean hasOrder;

        public Builder() {
            texts = new ArrayList<>();
            hasOrder = false;
        }

        public Builder setOrder(boolean hasOrder) {
            this.hasOrder = hasOrder;

            return this;
        }

        /**
         * Text append method.
         */
        public Builder append(Object text) {
            if (text instanceof Header || text instanceof Table || text instanceof List
                    || text instanceof Quote || text instanceof CodeBlock) {
                throw new IllegalArgumentException("Impossible to add such element in table");
            }

            texts.add(text);

            return this;
        }

        public List build() {
            return new List(this);
        }
    }
}
