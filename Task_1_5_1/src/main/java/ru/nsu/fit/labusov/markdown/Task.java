package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Task class.
 */
public class Task extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Text text;
    private final boolean hasCompleted;

    private Task(Builder builder) {
        this.text = builder.text;
        this.hasCompleted = builder.hasCompleted;
    }

    public String getText() {
        return text.getText();
    }

    public boolean getHasCompleted() {
        return hasCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (hasCompleted != task.hasCompleted) {
            return false;
        }

        return Objects.equals(text, task.text);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (hasCompleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("- [ ] " + text);

        if (hasCompleted) {
            stringBuilder.replace(3, 4, "x");
        }

        return stringBuilder.toString();
    }

    /**
     * Task builder class.
     */
    public static class Builder {
        private final Text text;
        private boolean hasCompleted;

        public Builder(Object text) {
            this.text = new Text.Builder(text).build();
        }

        public Builder isCompleted(boolean hasCompleted) {
            this.hasCompleted = hasCompleted;

            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
