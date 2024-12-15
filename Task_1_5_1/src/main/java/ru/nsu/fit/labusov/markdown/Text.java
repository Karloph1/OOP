package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Text class.
 */
public class Text extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String text;
    private final boolean hasBold;
    private final boolean hasItalic;
    private final boolean hasStrikeThrough;

    private Text(Builder builder) {
        text = builder.text;
        hasBold = builder.hasBold;
        hasItalic = builder.hasItalic;
        hasStrikeThrough = builder.hasStrikeThrough;
    }

    public String getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text.toString();
    }

    public boolean isBold() {
        return hasBold;
    }

    public boolean isItalic() {
        return hasItalic;
    }

    public boolean isStrikeThrough() {
        return hasStrikeThrough;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(text);
        if (hasBold) {
            stringBuilder.insert(0, "**").append("**");
        }

        if (hasItalic) {
            stringBuilder.insert(0, "*").append("*");
        }

        if (hasStrikeThrough) {
            stringBuilder.insert(0, "~~").append("~~");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Text text1 = (Text) o;

        if (hasBold != text1.hasBold) {
            return false;
        }
        if (hasItalic != text1.hasItalic) {
            return false;
        }
        if (hasStrikeThrough != text1.hasStrikeThrough) {
            return false;
        }

        return Objects.equals(text, text1.text);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (hasBold ? 1 : 0);
        result = 31 * result + (hasItalic ? 1 : 0);
        result = 31 * result + (hasStrikeThrough ? 1 : 0);
        return result;
    }

    /**
     * Text builder class.
     */
    public static class Builder {
        private final String text;
        private boolean hasBold;
        private boolean hasItalic;
        private boolean hasStrikeThrough;

        public Builder(Object text) {
            this.text = text.toString();
            hasBold = false;
            hasItalic = false;
            hasStrikeThrough = false;
        }

        public Builder bold() {
            hasBold = true;

            return this;
        }

        public Builder italic() {
            hasItalic = true;

            return this;
        }

        public Builder strikeThrough() {
            hasStrikeThrough = true;

            return this;
        }

        public Text build() {
            return new Text(this);
        }
    }
}