package martintrollip.task1;

import martintrollip.task1.csv.Reader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test the {@link Reader} by stepping through a smaller test CSV with numbered entries.
 *
 * @author Martin Trollip
 * @since 2020/06/16 18:24
 */
public class FileReaderTest {

    @Test
    public void test_reader_paginationNext() throws Exception {
        String file = "\\docs\\test_sales.csv";
        Reader reader = new Reader(file, 100);

        Assert.assertTrue(reader.hasNext());

        List<String> next = reader.next();
        Assert.assertEquals(100, next.size());
        Assert.assertTrue(next.get(0).startsWith("0"));
        Assert.assertTrue(next.get(99).startsWith("99"));

        Assert.assertTrue(reader.hasNext());

        next = reader.next();
        Assert.assertEquals(100, next.size());
        Assert.assertTrue(next.get(0).startsWith("100"));
        Assert.assertTrue(next.get(99).startsWith("199"));

        Assert.assertTrue(reader.hasNext());

        next = reader.next();
        Assert.assertEquals(100, next.size());
        Assert.assertTrue(next.get(0).startsWith("200"));
        Assert.assertTrue(next.get(99).startsWith("299"));

        Assert.assertTrue(reader.hasNext());

        next = reader.next();
        Assert.assertEquals(25, next.size());
        Assert.assertTrue(next.get(0).startsWith("300"));
        Assert.assertTrue(next.get(24).startsWith("324"));

        Assert.assertFalse(reader.hasNext());

        next = reader.next();
        Assert.assertEquals(0, next.size());

        Assert.assertFalse(reader.hasNext());
    }
}
