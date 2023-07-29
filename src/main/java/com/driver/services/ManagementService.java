package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repositories.ManagementRepository;

import java.util.List;

public class ManagementService {

    ManagementRepository managementRepository = new ManagementRepository();
    public int bookARoom(Booking booking) {
        return managementRepository.booARoom(booking);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return managementRepository.updateFacilities(newFacilities,hotelName);
    }

    public int getBookings(Integer id) {
        return managementRepository.getBooking(id);
    }

    public String addHotel(Hotel hotel) {
        return managementRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return managementRepository.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return managementRepository.getHotelWithMostFacilities();
    }
}
