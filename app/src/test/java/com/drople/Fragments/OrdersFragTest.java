package com.drople.Fragments;

import com.drople.Models.Address;
import com.drople.Models.Order;
import com.google.firebase.database.DataSnapshot;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrdersFragTest {

    @Test
    public void isUidValid() {
        assertTrue(OrdersFrag.isUidValid("-Ldfsl_g132gfdsklglgdsl"));
    }

    @Test
    public void isOrderValid() {
        Order order = new Order();
        order.uid = "dghjkaergdjhakdsjthaewkj";
        order.status = "1";
        order.c_date = "1344353794134";
        order.address = new Address();
        order.address.phone = "8432432430";
        order.pickup_time = "10:00 PM";
        order.address.name = "Rakesh Pandey";
        order.address.room = "121";
        order.address.type = "Home";
        order.address.hostel = "Hostel 4";
        order.address.id = "fahghadsfdfajkdfsjk";
        order.pay_mode = "0";
        order.cost = "24";
        assertTrue(OrdersFrag.isOrderValid(order));
    }

    @Test
    public void isFirebaseResultValid() {
        Order order = new Order();
        order.uid = "dghjkaergdjhakdsjthaewkj";
        order.status = "1";
        order.c_date = "1344353794134";
        order.address = new Address();
        order.address.phone = "8432432430";
        order.pickup_time = "10:00 PM";
        order.address.name = "Rakesh Pandey";
        order.address.room = "121";
        order.address.type = "Home";
        order.address.hostel = "Hostel 4";
        order.address.id = "fahghadsfdfajkdfsjk";
        order.pay_mode = "0";
        order.cost = "24";
        assertTrue(OrdersFrag.isFirebaseResultValid(order));
    }
}