package com.drople.utilClasses;

import com.drople.Models.Address;
import com.drople.Models.Garment;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderManagerTest {


    @Test
    public void isAddressValid() {
        Address address = new Address();
        address.hostel = "Hostel 1";
        address.type = "Home";
        address.room = "121";
        address.id = "jdafaf_d";
        address.name = "Rakesh Pandey";
        address.phone = "43892582394";
        assertTrue(OrderManager.isAddressValid(address));
    }

    @Test
    public void isValidInputs() {
        Address address = new Address();
        address.hostel = "Hostel 1";
        address.type = "Home";
        address.room = "121";
        address.id = "jdafaf_d";
        address.name = "Rakesh Pandey";
        address.phone = "43892582394";
        ArrayList<Garment> garments = new ArrayList<>();
        garments.add(new Garment());
        assertTrue(OrderManager.isValidInputs(address,"15 Jul 10:00 AM",garments));
    }
}