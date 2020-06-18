package martintrollip.task3;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Martin Trollip
 * @since 2020/06/18 21:19
 */
public class DistanceTableTest {

    private static final String amsterdam = "Amsterdam";
    private static final String berlin = "Berlin    ";
    private static final String copenhagen = "Copenhagen";
    private static final String london = "London";
    private static final String moscow = "Moscow";
    private static final String rome = "Rome";
    private static final String warsaw = "Warsaw";

    @Test
    public void test_DistanceTable() {
        DistanceTable distanceTable = init();

        //Same city
        Assert.assertEquals(0, distanceTable.distanceBetween(amsterdam, amsterdam));
        Assert.assertEquals(0, distanceTable.distanceBetween(berlin, berlin));
        Assert.assertEquals(0, distanceTable.distanceBetween(copenhagen, copenhagen));
        Assert.assertEquals(0, distanceTable.distanceBetween(london, london));
        Assert.assertEquals(0, distanceTable.distanceBetween(moscow, moscow));
        Assert.assertEquals(0, distanceTable.distanceBetween(rome, rome));
        Assert.assertEquals(0, distanceTable.distanceBetween(warsaw, warsaw));

        //Amsterdam
        Assert.assertEquals(365, distanceTable.distanceBetween(amsterdam, berlin));
        Assert.assertEquals(381, distanceTable.distanceBetween(amsterdam, copenhagen));
        Assert.assertEquals(220, distanceTable.distanceBetween(amsterdam, london));
        Assert.assertEquals(1325, distanceTable.distanceBetween(amsterdam, moscow));
        Assert.assertEquals(808, distanceTable.distanceBetween(amsterdam, rome));
        Assert.assertEquals(673, distanceTable.distanceBetween(amsterdam, warsaw));
        Assert.assertEquals(365, distanceTable.distanceBetween(berlin, amsterdam));
        Assert.assertEquals(381, distanceTable.distanceBetween(copenhagen, amsterdam));
        Assert.assertEquals(220, distanceTable.distanceBetween(london, amsterdam));
        Assert.assertEquals(1325, distanceTable.distanceBetween(moscow, amsterdam));
        Assert.assertEquals(808, distanceTable.distanceBetween(rome, amsterdam));
        Assert.assertEquals(673, distanceTable.distanceBetween(warsaw, amsterdam));

        //Berlin
        Assert.assertEquals(225, distanceTable.distanceBetween(berlin, copenhagen));
        Assert.assertEquals(575, distanceTable.distanceBetween(berlin, london));
        Assert.assertEquals(995, distanceTable.distanceBetween(berlin, moscow));
        Assert.assertEquals(730, distanceTable.distanceBetween(berlin, rome));
        Assert.assertEquals(320, distanceTable.distanceBetween(berlin, warsaw));
        Assert.assertEquals(225, distanceTable.distanceBetween(copenhagen, berlin));
        Assert.assertEquals(575, distanceTable.distanceBetween(london, berlin));
        Assert.assertEquals(995, distanceTable.distanceBetween(moscow, berlin));
        Assert.assertEquals(730, distanceTable.distanceBetween(rome, berlin));
        Assert.assertEquals(320, distanceTable.distanceBetween(warsaw, berlin));

        //Copenhagen
        Assert.assertEquals(590, distanceTable.distanceBetween(copenhagen, london));
        Assert.assertEquals(970, distanceTable.distanceBetween(copenhagen, moscow));
        Assert.assertEquals(948, distanceTable.distanceBetween(copenhagen, rome));
        Assert.assertEquals(415, distanceTable.distanceBetween(copenhagen, warsaw));
        Assert.assertEquals(590, distanceTable.distanceBetween(london, copenhagen));
        Assert.assertEquals(970, distanceTable.distanceBetween(moscow, copenhagen));
        Assert.assertEquals(948, distanceTable.distanceBetween(rome, copenhagen));
        Assert.assertEquals(415, distanceTable.distanceBetween(warsaw, copenhagen));

        //London
        Assert.assertEquals(1540, distanceTable.distanceBetween(london, moscow));
        Assert.assertEquals(890, distanceTable.distanceBetween(london, rome));
        Assert.assertEquals(890, distanceTable.distanceBetween(london, warsaw));
        Assert.assertEquals(1540, distanceTable.distanceBetween(moscow, london));
        Assert.assertEquals(890, distanceTable.distanceBetween(rome, london));
        Assert.assertEquals(890, distanceTable.distanceBetween(warsaw, london));

        //Moscow
        Assert.assertEquals(1462, distanceTable.distanceBetween(moscow, rome));
        Assert.assertEquals(710, distanceTable.distanceBetween(moscow, warsaw));
        Assert.assertEquals(1462, distanceTable.distanceBetween(rome, moscow));
        Assert.assertEquals(710, distanceTable.distanceBetween(moscow, warsaw));

        //Rome
        Assert.assertEquals(810, distanceTable.distanceBetween(rome, warsaw));
    }

    private DistanceTable init() {
        DistanceTable distanceTable = new DistanceTable();

        distanceTable.put(amsterdam, amsterdam, 0);
        distanceTable.put(amsterdam, berlin, 365);
        distanceTable.put(amsterdam, copenhagen, 381);
        distanceTable.put(amsterdam, london, 220);
        distanceTable.put(amsterdam, moscow, 1325);
        distanceTable.put(amsterdam, rome, 808);
        distanceTable.put(amsterdam, warsaw, 673);
        distanceTable.put(berlin, berlin, 0);
        distanceTable.put(berlin, copenhagen, 225);
        distanceTable.put(berlin, london, 575);
        distanceTable.put(berlin, moscow, 995);
        distanceTable.put(berlin, rome, 730);
        distanceTable.put(berlin, warsaw, 320);
        distanceTable.put(copenhagen, copenhagen, 0);
        distanceTable.put(copenhagen, london, 590);
        distanceTable.put(copenhagen, moscow, 970);
        distanceTable.put(copenhagen, rome, 948);
        distanceTable.put(copenhagen, warsaw, 415);
        distanceTable.put(london, london, 0);
        distanceTable.put(london, moscow, 1540);
        distanceTable.put(london, rome, 890);
        distanceTable.put(london, warsaw, 890);
        distanceTable.put(moscow, moscow, 0);
        distanceTable.put(moscow, rome, 1462);
        distanceTable.put(moscow, warsaw, 710);
        distanceTable.put(rome, rome, 0);
        distanceTable.put(rome, warsaw, 810);
        distanceTable.put(warsaw, warsaw, 0);

        return distanceTable;
    }
}
