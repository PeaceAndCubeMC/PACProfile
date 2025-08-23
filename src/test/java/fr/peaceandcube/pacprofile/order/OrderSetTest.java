package fr.peaceandcube.pacprofile.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderSetTest {

    @ParameterizedTest
    @CsvSource({
            "DEFAULT,NAME_AZ",
            "NAME_AZ,NAME_ZA",
            "NAME_ZA,AREA_ASC",
            "AREA_ASC,AREA_DESC",
            "AREA_DESC,CATEGORY_AZ",
            "CATEGORY_AZ,CATEGORY_ZA",
            "CATEGORY_ZA,COLOR",
            "COLOR,DEFAULT"
    })
    void testOrderSetNextWithAll(String currentOrder, String nextOrder) {
        OrderSet orderSet = new OrderSet(Order.valueOf(currentOrder), Order.DEFAULT,
                Order.NAME_AZ, Order.NAME_ZA,
                Order.AREA_ASC, Order.AREA_DESC,
                Order.CATEGORY_AZ, Order.CATEGORY_ZA,
                Order.COLOR
        );

        orderSet.next();

        Assertions.assertEquals(nextOrder, orderSet.currentOrder().name());
    }

    @ParameterizedTest
    @CsvSource({
            "DEFAULT,NAME_AZ",
            "NAME_AZ,NAME_ZA",
            "NAME_ZA,COLOR",
            "COLOR,DEFAULT"
    })
    void testOrderSetNextWithNotAll(String currentOrder, String nextOrder) {
        OrderSet orderSet = new OrderSet(Order.valueOf(currentOrder), Order.DEFAULT,
                Order.NAME_AZ, Order.NAME_ZA,
                Order.COLOR
        );

        orderSet.next();

        Assertions.assertEquals(nextOrder, orderSet.currentOrder().name());
    }

    @ParameterizedTest
    @CsvSource({
            "DEFAULT,DEFAULT",
            "AREA_DESC,AREA_DESC"
    })
    void testOrderSetNextWithOne(String currentOrder, String nextOrder) {
        OrderSet orderSet = new OrderSet(Order.valueOf(currentOrder), Order.valueOf(currentOrder));

        orderSet.next();

        Assertions.assertEquals(nextOrder, orderSet.currentOrder().name());
    }

    @ParameterizedTest
    @ValueSource(strings = {"DEFAULT", "COLOR"})
    void testOrderSetNextWithZero(String currentOrder) {
        OrderSet orderSet = new OrderSet(Order.valueOf(currentOrder));

        orderSet.next();

        Assertions.assertEquals(currentOrder, orderSet.currentOrder().name());
    }
}
