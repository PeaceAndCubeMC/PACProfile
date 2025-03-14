package fr.peaceandcube.pacprofile.order;

import java.util.List;

public class OrderSet {
    private Order currentOrder;
    private final List<Order> orders;

    public OrderSet(Order currentOrder, Order... orders) {
        this.currentOrder = currentOrder;
        this.orders = List.of(orders);
    }

    public Order currentOrder() {
        return currentOrder;
    }

    public void next() {
        int currentIndex = orders.indexOf(currentOrder);
        if (currentIndex > -1) {
            if (currentIndex == orders.size() - 1) {
                this.currentOrder = orders.getFirst();
            } else {
                this.currentOrder = orders.get(currentIndex + 1);
            }
        }
    }
}
