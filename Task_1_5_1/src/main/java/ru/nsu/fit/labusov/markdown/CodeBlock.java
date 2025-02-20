package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * CodeBlock class.
 */
public class CodeBlock extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Text code;

    public CodeBlock(Object code) {
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

        CodeBlock codeBlock = (CodeBlock) o;

        return Objects.equals(code, codeBlock.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "```\n" + code + "\n```";
    }
}
