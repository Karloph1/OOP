package ru.nsu.fit.labusov.markdown;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * MarkDown tests.
 */
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

        Assertions.assertEquals("```\nSystem.out.println('Hello world')\n```",
                codeBlock.toString());
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
    void imageToStringTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals(
                "![123](https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg)",
                image.toString());
    }

    @Test
    void imageGetNameTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("123", image.getName());
    }

    @Test
    void imageGetUrlTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("https://img.freepik.com/free-photo/"
                + "view-beautiful-persian-domestic-cat_23-2151773826.jpg", image.getUrl());
    }

    @Test
    void imageEqualsTest() {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Image image1 = new Image(123,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals(image, image1);
    }

    @Test
    void imageSerializeTest() throws IOException, ClassNotFoundException {
        Image image = new Image(123,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");
        String file = "file1.txt";

        image.serialize(file);

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Image image1 = (Image) objectInputStream.readObject();

        objectInputStream.close();

        Assertions.assertEquals(image, image1);
    }

    @Test
    void imageExceptionTest() {
        try {
            Image image = new Image(123,
                    "3826");

            System.out.println(image);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("URI is not absolute"));
        }
    }

    /**
     * Link tests.
     */
    @Test
    void linkToStringTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("[0.9](https://img.freepik.com/free-photo/"
                + "view-beautiful-persian-domestic-cat_23-2151773826.jpg)", link.toString());
    }

    @Test
    void linkGetNamTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("0.9", link.getName());
    }

    @Test
    void linkGetUrlTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals("https://img.freepik.com/free-photo/"
                + "view-beautiful-persian-domestic-cat_23-2151773826.jpg", link.getUrl());
    }

    @Test
    void linkExceptionTest() {
        try {
            Link link = new Link(123,
                    "3826");

            System.out.println(link);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(e.getMessage().contains("URI is not absolute"));
        }
    }

    @Test
    void linkEqualsTest() {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");
        Link link1 = new Link(0.9,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");

        Assertions.assertEquals(link, link1);
    }

    @Test
    void linkSerializeTest() throws IOException, ClassNotFoundException {
        Link link = new Link(0.9,
                "https://img.freepik.com/free-photo/"
                        + "view-beautiful-persian-domestic-cat_23-2151773826.jpg");
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
    void listOrderToStringTest() {
        List.Builder builder = new List.Builder().setOrder(true);

        for (int i = 0; i < 4; i++) {
            builder.append(i);
        }

        Assertions.assertEquals("""
                1.  0
                2.  1
                3.  2
                4.  3""", builder.build().toString());
    }

    @Test
    void listNotOrderToStringTest() {
        List.Builder builder = new List.Builder();

        for (int i = 0; i < 4; i++) {
            builder.append(i);
        }

        Assertions.assertEquals("""
                -  0
                -  1
                -  2
                -  3""", builder.build().toString());
    }

    @Test
    void listGetTextsTest() {
        List.Builder builder = new List.Builder().setOrder(true);

        for (int i = 0; i < 4; i++) {
            builder.append(i);
        }

        Assertions.assertArrayEquals(new Object[]{0, 1, 2, 3}, builder.build().getTexts());
    }

    @Test
    void listGetHasOrder() {
        List list = new List.Builder().append(2).setOrder(true).build();

        Assertions.assertTrue(list.getHasOrder());
    }

    @Test
    void listEqualsTest() {
        List list = new List.Builder()
                .append(1)
                .append("Hello")
                .append(false)
                .append(new Link(0.9,
                        "https://img.freepik.com/free-photo/"
                                + "view-beautiful-persian-domestic-cat_23-2151773826.jpg"))
                .append(new Text.Builder("Class").bold().italic().build())
                .build();

        List list1 = new List.Builder()
                .append(1)
                .append("Hello")
                .append(false)
                .append(new Link(0.9,
                        "https://img.freepik.com/free-photo/"
                                + "view-beautiful-persian-domestic-cat_23-2151773826.jpg"))
                .append(new Text.Builder("Class").bold().italic().build())
                .build();


        Assertions.assertEquals(list, list1);
    }

    @Test
    void listSerializeTest() throws IOException, ClassNotFoundException {
        List list = new List.Builder()
                .append(1)
                .append("Hello")
                .append(false)
                .append(new Link(0.9,
                        "https://img.freepik.com/free-photo/"
                                + "view-beautiful-persian-domestic-cat_23-2151773826.jpg"))
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
    void quoteToStringTest() {
        Quote quote = new Quote("Without changing anything, nothing will change");

        Assertions.assertEquals("> Without changing anything, nothing will change",
                quote.toString());
    }

    @Test
    void quoteGetTextTest() {
        Quote quote = new Quote("Without changing anything, nothing will change");

        Assertions.assertEquals("Without changing anything, nothing will change",
                quote.getText());
    }

    @Test
    void quoteEqualsTest() {
        Quote quote = new Quote("Without changing anything, nothing will change");

        Quote quote1 = new Quote("Without changing anything, nothing will change");

        Assertions.assertEquals(quote, quote1);
    }

    @Test
    void quoteSerializeTest() throws IOException, ClassNotFoundException {
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
    void taskCompletedToStringTest() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertEquals("- [x] Wake up", task.toString());
    }

    @Test
    void taskNotCompletedToStringTest() {
        Task task = new Task.Builder("Wake up").isCompleted(false).build();

        Assertions.assertEquals("- [ ] Wake up", task.toString());
    }

    @Test
    void taskGetTextTest() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertEquals("Wake up", task.getText());
    }

    @Test
    void taskGetHasCompleted() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertTrue(task.getHasCompleted());
    }

    @Test
    void taskEqualsTest() {
        Task task = new Task.Builder("Wake up").isCompleted(true).build();

        Task task1 = new Task.Builder("Wake up").isCompleted(true).build();

        Assertions.assertEquals(task, task1);
    }

    @Test
    void taskSerializeTest() throws IOException, ClassNotFoundException {
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
    void tableToStringTest() {
        Table.Builder table = new Table.Builder()
                .withAlignments(Table.Align.ALIGN_CENTER, Table.Align.ALIGN_LEFT,
                        Table.Align.ALIGN_RIGHT, Table.Align.ALIGN_CENTER)
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
    void tableGetAlignsTest() {
        Table table = new Table.Builder()
                .withAlignments(Table.Align.ALIGN_CENTER, Table.Align.ALIGN_LEFT,
                        Table.Align.ALIGN_RIGHT, Table.Align.ALIGN_CENTER)
                .addRow("Number", "Bold", "Italic", "Boolean")
                .build();

        Assertions.assertArrayEquals(new Table.Align[]{Table.Align.ALIGN_CENTER,
            Table.Align.ALIGN_LEFT, Table.Align.ALIGN_RIGHT, Table.Align.ALIGN_CENTER},
                table.getAligns());
    }

    @Test
    void tableGetRowsTest() {
        Table table = new Table.Builder()
                .withAlignments(Table.Align.ALIGN_CENTER, Table.Align.ALIGN_LEFT,
                        Table.Align.ALIGN_RIGHT, Table.Align.ALIGN_CENTER)
                .addRow("Number", "Bold", "Italic", "Boolean", "Empty")
                .addRow(true, false, false, false, true)
                .build();

        ArrayList<Object[]> a = new ArrayList<>();
        a.add(new Object[]{"Number", "Bold", "Italic", "Boolean", "Empty"});
        a.add(new Object[]{true, false, false, false, true});

        Assertions.assertArrayEquals(a.toArray(), table.getRows().toArray());
    }

    @Test
    void tableEqualsTest() {
        Table.Builder table = new Table.Builder()
                .withAlignments(Table.Align.ALIGN_LEFT, Table.Align.ALIGN_LEFT)
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
    void tableSerializeTest() throws IOException, ClassNotFoundException {
        Table.Builder table = new Table.Builder()
                .withAlignments(Table.Align.ALIGN_CENTER, Table.Align.ALIGN_LEFT,
                        Table.Align.ALIGN_RIGHT, Table.Align.ALIGN_CENTER)
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