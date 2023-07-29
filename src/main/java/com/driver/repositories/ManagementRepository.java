package com.driver.repositories;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.*;

public class ManagementRepository {

    HashMap<Integer,User> userMap;
    HashMap<String ,Hotel> hotelMap;
    HashMap<String,Booking> bookingMap;

    public ManagementRepository(){
        userMap = new HashMap<>();
        hotelMap = new HashMap<>();
        bookingMap = new HashMap<>();
    }
    public int booARoom(Booking booking) {
        String id = booking.getBookingId();
        Hotel hotel = hotelMap.get(booking.getHotelName());
        booking.setBookingId(String.valueOf(UUID.randomUUID()));
        if(hotel==null || hotel.getAvailableRooms()<booking.getNoOfRooms()){
            return -1;
        }
        int amount = booking.getNoOfRooms()*hotel.getPricePerNight();
        bookingMap.put(booking.getBookingId(),booking);
        booking.setAmountToBePaid(amount);
        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());
        return amount;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelMap.get(hotelName);
        List<Facility> list = hotel.getFacilities();
        for(Facility facility:newFacilities){
            if(list.contains(facility)==false){
                list.add(facility);
            }
        }
        return hotel;
    }

    public int getBooking(Integer id) {
        int count = 0;
        for(String bookingId:bookingMap.keySet()){
            if(bookingMap.get(bookingId).getBookingAadharCard()==id){
                count++;
            }
        }
        return count;
    }

    public String addHotel(Hotel hotel) {
        String id = hotel.getHotelName();
        if(hotelMap.containsKey(id) || id==null){
            return "FAILURE";
        }
        hotelMap.put(id,hotel);
        return "SUCCESS";
    }

    public Integer addUser(User user) {
        Integer id = user.getaadharCardNo();
        userMap.put(id,user);
        return id;
    }

    public String getHotelWithMostFacilities() {
        int count = 0;
        for(Hotel hotel : hotelMap.values()){
            count = Math.max(hotel.getFacilities().size() , count);
        }
        if(count == 0)
            return "";
        String curr = "";
        for(Hotel hotel : hotelMap.values()){
            if(hotel.getFacilities().size() == count){
                if(curr.length()==0){
                    curr = hotel.getHotelName();
                }
                else{
                    if(curr.compareTo(hotel.getHotelName())>0){
                        curr = hotel.getHotelName();
                    }
                }
            }
        }


    }
}
