package ru.nsu.fit.labusov.markdown;

import java.io.Serial;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

/**
 * Link class.
 */
public class Link extends Element implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Text name;
    private URL url;

    /**
     * Link builder method.
     */
    public Link(Object name, String url) {
        this.name = new Text.Builder(name).build();

        try {
            URI uri = new URI(url);
            this.url = uri.toURL();
        } catch (MalformedURLException e) {
            System.out.println("Incorrect url");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name.getText();
    }

    public String getUrl() {
        return String.valueOf(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Link link = (Link) o;

        if (!Objects.equals(name, link.name)) {
            return false;
        }

        return Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.toString().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + name + "](" + url + ")";
    }
}
