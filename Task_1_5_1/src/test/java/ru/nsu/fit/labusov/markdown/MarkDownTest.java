package ru.nsu.fit.labusov.markdown;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkDownTest {

    /**
     * Text tests.
     */
    @Test
    void textBuildTest() {
        Text textBuilder = new Text.Builder("Hello").build();
        Text textBuilder1 = new Text.Builder("hello").build();

        Assertions.assertNotEquals(textBuilder, textBuilder1);
    }

    @Test
    void textToStringTest() {
        Text.Builder textBuilder = new Text.Builder("Hello");

        Assertions.assertEquals("Hello", textBuilder.build().toString());
    }

    @Test
    void textBoldTest() {
        Text textBuilder = new Text.Builder("Hello").bold().build();

        Assertions.assertTrue(textBuilder.isBold());
    }

    @Test
    void textBoldTextTest() {
        Text textBuilder = new Text.Builder("Hello").bold().build();

        Assertions.assertEquals("**Hello**", textBuilder.toString());
    }

    @Test
    void textItalicTest() {
        Text textBuilder = new Text.Builder("Hello").italic().build();

        Assertions.assertTrue(textBuilder.isItalic());
    }

    @Test
    void textItalicTextTest() {
        Text textBuilder = new Text.Builder("Hello").italic().build();

        Assertions.assertEquals("*Hello*", textBuilder.toString());
    }

    @Test
    void textStrikeThroughTest() {
        Text textBuilder = new Text.Builder("Hello").strikeThrough().build();

        Assertions.assertTrue(textBuilder.isStrikeThrough());
    }

    @Test
    void textStrikeThroughTextTest() {
        Text textBuilder = new Text.Builder("Hello").strikeThrough().build();

        Assertions.assertEquals("~~Hello~~", textBuilder.toString());
    }

    @Test
    void textBuilderGetTextTest() {
        Text textBuilder = new Text.Builder("Hello").bold().build();

        Assertions.assertEquals("Hello", textBuilder.getText());
    }

    @Test
    void textSetTextTest() {
        Text textBuilder = new Text.Builder(123).bold().build();

        Assertions.assertEquals("123", textBuilder.getText());

        textBuilder.setText(false);

        Assertions.assertEquals("false", textBuilder.getText());
    }

    @Test
    void textEqualsTest() {
        Text text = new Text.Builder(123).bold().italic().build();
        Text text1 = new Text.Builder("123").italic().bold().build();

        Assertions.assertEquals(text, text1);
    }

    @Test
    void textSerializeTest() throws IOException, ClassNotFoundException {
        Text text = new Text.Builder("Hello").bold().italic().build();
        String file = "file1.txt";

        text.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Text text1 = (Text) objectInputStream.readObject();

        objectInputStream.close();

        Assertions.assertEquals(text, text1);
    }

    /**
     * CodeBlock tests.
     */
    @Test
    void codeBlockToStringTest() {
        CodeBlock codeBlock = new CodeBlock("System.out.println('Hello world')");

        Assertions.assertEquals("```\nSystem.out.println('Hello world')\n```"
                , codeBlock.toString());
    }

    @Test
    void codeBlockGetTextTest() {
        CodeBlock codeBlock = new CodeBlock("System.out.println('Hello world')");

        Assertions.assertEquals("System.out.println('Hello world')", codeBlock.getText());
    }

    @Test
    void codeBlockEqualsTest() {
        CodeBlock codeBlock = new CodeBlock("System.out.println('Hello world')");

        CodeBlock codeBlock1 = new CodeBlock("System.out.println('Hello world')");

        Assertions.assertEquals(codeBlock, codeBlock1);
    }

    @Test
    void codeBlockSerializeTest() throws IOException, ClassNotFoundException {
        CodeBlock codeBlock = new CodeBlock("System.out.println('Hello world')");
        String file = "file1.txt";

        codeBlock.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        CodeBlock codeBlock1 = (CodeBlock) objectInputStream.readObject();

        objectInputStream.close();

        Assertions.assertEquals(codeBlock, codeBlock1);
    }

    /**
     * Header tests.
     */
    @Test
    void headerToStringTest() {
        Header header = new Header("Hello").titleDegree(1);

        Assertions.assertEquals("# Hello", header.toString());
    }

    @Test
    void headerGetTextTest() {
        Header header = new Header("Hello").titleDegree(1);

        Assertions.assertEquals("Hello", header.getText());
    }

    @Test
    void headerGetTitleDegreeTest() {
        Header header = new Header("Hello").titleDegree(2);

        Assertions.assertEquals(2, header.getTitleDegree());
    }

    @Test
    void headerGetStringTest() {
        Header header = new Header("Hello").titleDegree(2);

        Assertions.assertEquals("Hello", header.getText());
    }

    @Test
    void headerEqualsTest() {
        Header header = new Header("Hello").titleDegree(2);
        Header header1 = new Header("Hell").titleDegree(2);

        Assertions.assertNotEquals(header, header1);
    }

    @Test
    void headerSerializeTest() throws IOException, ClassNotFoundException {
        Header header = new Header("Hello").titleDegree(6);
        String file = "file1.txt";

        header.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Header header1 = (Header) objectInputStream.readObject();

        objectInputStream.close();

        Assertions.assertEquals(header, header1);
    }

    /**
     * Image tests.
     */
    @Test
    void ImageToStringTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals(
                "![123](https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg)",
                image.toString());
    }

    @Test
    void ImageGetNameTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("123", image.getName());
    }

    @Test
    void ImageGetUrlTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("https://img.freepik.com/free-photo/" +
                "view-beautiful-persian-domestic-cat_23-2151773826.jpg", image.getUrl());
    }

    @Test
    void ImageEqualsTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Image image1 = new Image(123,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals(image, image1);
    }

    @Test
    void ImageSerializeTest() throws IOException, ClassNotFoundException {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");
        String file = "file1.txt";

        image.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Image image1 = (Image) objectInputStream.readObject();

        objectInputStream.close();

        Assertions.assertEquals(image, image1);
    }

    /**
     * Link tests.
     */
    @Test
    void LinkToStringTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("[0.9](https://img.freepik.com/free-photo/" +
                "view-beautiful-persian-domestic-cat_23-2151773826.jpg)", link.toString());
    }

    @Test
    void LinkGetNamTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("0.9", link.getName());
    }

    @Test
    void LinkGetUrlTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("https://img.freepik.com/free-photo/" +
                "view-beautiful-persian-domestic-cat_23-2151773826.jpg", link.getUrl());
    }

    @Test
    void LinkEqualsTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");
        Link link1 = new Link(0.9,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals(link, link1);
    }

    @Test
    void LinkSerializeTest() throws IOException, ClassNotFoundException {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/" +
                        "view-beautiful-persian-domestic-cat_23-2151773826.jpg");
        String file = "file1.txt";

        link.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Link link1 = (Link) objectInputStream.readObject();

        Assertions.assertEquals(link, link1);
    }

    /**
     * List tests.
     */
    @Test
    void ListOrderToStringTest() {
        List.Builder builder = new List.Builder().setOrder(true);

        for (int i = 0; i < 4; i++) {
            builder.append(i);
        }

        Assertions.assertEquals("""
                1. 0
                2. 1
                3. 2
                4. 3""", builder.build().toString());
    }

    @Test
    void ListNotOrderToStringTest() {
        List.Builder builder = new List.Builder();

        for (int i = 0; i < 4; i++) {
            builder.append(i);
        }

        Assertions.assertEquals("""
                - 0
                - 1
                - 2
                - 3""", builder.build().toString());
    }

    @Test
    void ListGetTextsTest() {
        List.Builder builder = new List.Builder().setOrder(true);

        for (int i = 0; i < 4; i++) {
            builder.append(i);
        }

        Assertions.assertArrayEquals(new Object[]{0, 1, 2, 3}, builder.build().getTexts());
    }

    @Test
    void ListGetHasOrder() {
        List list = new List.Builder().append(2).setOrder(true).build();

        Assertions.assertTrue(list.getHasOrder());
    }

    @Test
    void ListEqualsTest() {
        List list = new List.Builder()
                .append(1)
                .append("Hello")
                .append(false)
                .append(new Link(0.9,
                        "https://img.freepik.com/free-photo/" +
                                "view-beautiful-persian-domestic-cat_23-2151773826.jpg"))
                .append(new Text.Builder("Class").bold().italic().build())
                .build();

        List list1 = new List.Builder()
                .append(1)
                .append("Hello")
                .append(false)
                .append(new Link(0.9,
                        "https://img.freepik.com/free-photo/" +
                                "view-beautiful-persian-domestic-cat_23-2151773826.jpg"))
                .append(new Text.Builder("Class").bold().italic().build())
                .build();


        Assertions.assertEquals(list, list1);
    }

    @Test
    void ListSerializeTest() throws IOException, ClassNotFoundException {
        List list = new List.Builder()
                .append(1)
                .append("Hello")
                .append(false)
                .append(new Link(0.9,
                        "https://img.freepik.com/free-photo/" +
                                "view-beautiful-persian-domestic-cat_23-2151773826.jpg"))
                .append(new Text.Builder("Class").bold().italic().build())
                .build();

        String file = "file1.txt";

        list.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        List list1 = (List) objectInputStream.readObject();

        Assertions.assertEquals(list, list1);
    }

    /**
     * Quote tests.
     */
    @Test
    void QuoteToStringTest() {
        Quote quote = new Quote("Without changing anything, nothing will change");

        Assertions.assertEquals("> Without changing anything, nothing will change",
                quote.toString());
    }

    @Test
    void QuoteGetTextTest() {
        Quote quote = new Quote("Without changing anything, nothing will change");

        Assertions.assertEquals("Without changing anything, nothing will change",
                quote.getText());
    }

    @Test
    void QuoteEqualsTest() {
        Quote quote = new Quote("Without changing anything, nothing will change");

        Quote quote1 = new Quote("Without changing anything, nothing will change");

        Assertions.assertEquals(quote, quote1);
    }

    @Test
    void QuoteSerializeTest() throws IOException, ClassNotFoundException {
        Quote quote = new Quote("Without changing anything, nothing will change");

        String file = "file1.txt";

        quote.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Quote quote1 = (Quote) objectInputStream.readObject();

        Assertions.assertEquals(quote, quote1);
    }

    /**
     * Task tests.
     */
    @Test
    void TaskCompletedToStringTest() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertEquals("- [x] Wake up", task.toString());
    }

    @Test
    void TaskNotCompletedToStringTest() {
        Task task = new Task.Builder("Wake up").isCompleted(false).build();

        Assertions.assertEquals("- [ ] Wake up", task.toString());
    }

    @Test
    void TaskGetTextTest() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertEquals("Wake up", task.getText());
    }

    @Test
    void TaskGetHasCompleted() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertTrue(task.getHasCompleted());
    }

    @Test
    void TaskEqualsTest() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Task task1 = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertEquals(task, task1);
    }

    @Test
    void TaskSerializeTest() throws IOException, ClassNotFoundException {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();
        String file = "file1.txt";
        task.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Task task1 = (Task) objectInputStream.readObject();

        Assertions.assertEquals(task, task1);
    }

    /**
     * Table tests.
     */
    @Test
    void TableToStringTest() {
        Table.Builder table = new Table.Builder()
                .withAlignments(Table.ALIGN.ALIGN_CENTER, Table.ALIGN.ALIGN_LEFT,
                        Table.ALIGN.ALIGN_RIGHT, Table.ALIGN.ALIGN_CENTER)
                .addRow("Number", "Bold", "Italic", "Boolean", "Empty");

        for (int i = 0; i < 5; i++) {
            table.addRow(i, new Text.Builder(i + 8).bold().build(),
                    new Text.Builder(i * 100).italic().strikeThrough().build(), true);
        }

        Assertions.assertEquals("""
                |Number|Bold  |   Italic|Boolean|Empty|
                |:----:|:-----|--------:|:-----:|-----|
                |  0   |**8** |  ~~*0*~~| true  |     |
                |  1   |**9** |~~*100*~~| true  |     |
                |  2   |**10**|~~*200*~~| true  |     |
                |  3   |**11**|~~*300*~~| true  |     |
                |  4   |**12**|~~*400*~~| true  |     |""", table.build().toString());
    }


    @Test
    void TableGetAlignsTest() {
        Table table = new Table.Builder()
                .withAlignments(Table.ALIGN.ALIGN_CENTER, Table.ALIGN.ALIGN_LEFT,
                        Table.ALIGN.ALIGN_RIGHT, Table.ALIGN.ALIGN_CENTER)
                .addRow("Number", "Bold", "Italic", "Boolean")
                .build();

        Assertions.assertArrayEquals(new Table.ALIGN[]{Table.ALIGN.ALIGN_CENTER,
                Table.ALIGN.ALIGN_LEFT, Table.ALIGN.ALIGN_RIGHT, Table.ALIGN.ALIGN_CENTER},
                table.getAligns());
    }

    @Test
    void TableGetRowsTest() {
        Table table = new Table.Builder()
                .withAlignments(Table.ALIGN.ALIGN_CENTER, Table.ALIGN.ALIGN_LEFT,
                        Table.ALIGN.ALIGN_RIGHT, Table.ALIGN.ALIGN_CENTER)
                .addRow("Number", "Bold", "Italic", "Boolean", "Empty")
                .addRow(true, false, false, false, true)
                .build();

        ArrayList<Object[]> a = new ArrayList<>();
        a.add(new Object[]{"Number", "Bold", "Italic", "Boolean", "Empty"});
        a.add(new Object[]{true, false, false, false, true});

        Assertions.assertArrayEquals(a.toArray(), table.getRows().toArray());
    }

    @Test
    void TableEqualsTest() {
        Table.Builder table = new Table.Builder()
                .withAlignments(Table.ALIGN.ALIGN_LEFT, Table.ALIGN.ALIGN_LEFT)
                .addRow("Number", "Bold");

        for (int i = 0; i < 5; i++) {
            table.addRow(i, new Text.Builder(i + 8).bold().build());
        }

        Table.Builder table1 = new Table.Builder().addRow("Number", "Bold");

        for (int i = 0; i < 5; i++) {
            table1.addRow(i, new Text.Builder(i + 8).bold().build());
        }

        Assertions.assertNotEquals(table.build(), table1.build());
    }

    @Test
    void TableSerializeTest() throws IOException, ClassNotFoundException {
        Table.Builder table = new Table.Builder()
                .withAlignments(Table.ALIGN.ALIGN_CENTER, Table.ALIGN.ALIGN_LEFT,
                        Table.ALIGN.ALIGN_RIGHT, Table.ALIGN.ALIGN_CENTER)
                .addRow("Number", "Bold", "Italic", "Boolean", "Empty");

        for (int i = 0; i < 5; i++) {
            table.addRow(i, new Text.Builder(i + 8).bold().build(),
                    new Text.Builder(i * 100).italic().strikeThrough().build(), true);
        }

        String file = "file1.txt";

        table.build().serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Table table1 = (Table) objectInputStream.readObject();

        Assertions.assertEquals(table.build(), table1);
    }
}