package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Table class.
 */
public class Table extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Align enum class.
     */
    public enum Align {
        ALIGN_RIGHT, ALIGN_LEFT, ALIGN_DEFAULT, ALIGN_CENTER
    }

    private final Align[] aligns;
    private final ArrayList<Object[]> rows;
    private final int setRow;

    private Table(Builder builder) {
        this.setRow = builder.setRow;

        if (builder.align.length >= setRow) {
            this.aligns = (Arrays.stream(builder.align).limit(setRow).toArray(Align[]::new));
        } else {
            this.aligns = new Align[setRow];

            System.arraycopy(builder.align, 0, this.aligns, 0, builder.align.length);

            for (int i = builder.align.length; i < setRow; i++) {
                this.aligns[i] = Align.ALIGN_DEFAULT;
            }
        }

        this.rows = builder.rows;
    }

    public Align[] getAligns() {
        return aligns;
    }

    public List<Object[]> getRows() {
        return rows;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Table table = (Table) obj;

        if (table.rows.size() != this.rows.size()
                || table.aligns.length != this.aligns.length
                || table.setRow != this.setRow) {
            return false;
        }

        for (int i = 0; i < this.aligns.length; i++) {
            if (this.aligns[i] != table.aligns[i]) {
                return false;
            }
        }

        for (int i = 0; i < this.rows.size(); i++) {
            if (!Arrays.equals(this.rows.get(i), table.rows.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(aligns);
        result = 31 * result + (rows != null ? rows.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        int[] rowsLength = new int[setRow];

        for (int i = 0; i < rowsLength.length; i++) {
            int max = -1;
            for (Object[] row : rows) {
                if (i < row.length && max < row[i].toString().length()) {
                    max = row[i].toString().length();
                }
            }

            rowsLength[i] = max;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rows.size(); i++) {
            stringBuilder.append("|");

            for (int j = 0; j < setRow; j++) {
                if (j >= rows.get(i).length) {
                    stringBuilder.append(" ".repeat(rowsLength[j]));
                } else {
                    int leftIndentLength = 0;
                    int rightIndentLength = 0;

                    switch (aligns[j]) {
                        case ALIGN_LEFT, ALIGN_DEFAULT:
                            rightIndentLength = rowsLength[j]
                                    - rows.get(i)[j].toString().length();

                            break;
                        case ALIGN_RIGHT:
                            leftIndentLength = rowsLength[j]
                                    - rows.get(i)[j].toString().length();

                            break;
                        case ALIGN_CENTER:
                            leftIndentLength = (rowsLength[j]
                                    - rows.get(i)[j].toString().length()) / 2;
                            rightIndentLength = leftIndentLength
                                    + (rowsLength[j] - rows.get(i)[j].toString().length()) % 2;

                            break;
                        default:
                            break;
                    }

                    stringBuilder
                            .append(" ".repeat(leftIndentLength))
                            .append(rows.get(i)[j])
                            .append(" ".repeat(rightIndentLength))
                            .append("|");
                }
            }

            stringBuilder.append("|".repeat(Math.max(0, setRow - rows.get(i).length)));

            stringBuilder.append("\n");

            if (i == 0) {
                stringBuilder.append("|");
                for (int j = 0; j < rows.get(i).length; j++) {
                    switch (aligns[j]) {
                        case ALIGN_LEFT:
                            stringBuilder
                                    .append(":")
                                    .append("-".repeat(rowsLength[j] - 1))
                                    .append("|");

                            break;
                        case ALIGN_RIGHT:
                            stringBuilder
                                    .append("-".repeat(rowsLength[j] - 1))
                                    .append(":|");

                            break;
                        case ALIGN_CENTER:
                            stringBuilder
                                    .append(":")
                                    .append("-".repeat(Math.max(rowsLength[j] - 2, 1)))
                                    .append(":|");

                            break;
                        case ALIGN_DEFAULT:
                            stringBuilder
                                    .append("-".repeat(rowsLength[j]))
                                    .append("|");

                            break;
                        default:
                            break;
                    }
                }

                stringBuilder.append("\n");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    /**
     * Table builder class.
     */
    public static class Builder {
        private Align[] align;
        private final ArrayList<Object[]> rows;
        private int setRow = -1;

        public Builder() {
            rows = new ArrayList<>();
            align = new Align[]{};
        }

        /**
         * Set alignments method.
         */
        public Builder withAlignments(Align... align) {
            this.align = align;

            return this;
        }

        /**
         * Add row method.
         */
        public Builder addRow(Object... rowName) {
            if (setRow == -1) {
                setRow = rowName.length;
            }

            for (Object o : rowName) {
                if (o instanceof Header || o instanceof Table || o instanceof List
                        || o instanceof Quote || o instanceof CodeBlock || o instanceof Task) {
                    throw new IllegalArgumentException("Impossible to add such element in table");
                }
            }

            if (rowName.length != setRow) {
                rows.add(Arrays.stream(rowName).limit(setRow).toArray(Object[]::new));
            } else {
                rows.add(Arrays.stream(rowName).toArray(Object[]::new));
            }

            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }
}
