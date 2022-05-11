package com.editay.bsps.service;

import com.editay.bsps.models.Car;
import com.editay.bsps.payload.request.CarRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CarService {
   List<Car> findAll();

   Car findOneById(UUID id);

   List<Car> findAllByMake(String make);

   List<Car> findAllByPrice(BigDecimal price);

   List<Car> findAllByMileage(Integer mileage);

   List<Car> findAllByPriceAndMileage(BigDecimal price, Integer mileage);

   List<Car> findAllByFuel(String fuel);

   List<Car> findAllByPriceAndFuel(BigDecimal price, String fuel);

   List<Car> findAllByMakeAndModel(String make, String model);

   List<Car> findAllByMakeAndFuel(String make, String fuel);

   List<Car> findAllByMakeAndPrice(String make, BigDecimal price);

   List<Car> findAllByMakeAndMileage(String make, Integer mileage);

   List<Car> findAllByMakeAndPower(String make, String power);

   List<Car> findAllByMakeAndModelAndFuel(String make, String model, String fuel);

   List<Car> findAllByMakeAndModelAndPrice(String make, String model, BigDecimal price);

   List<Car> findAllByMakeAndModelAndMileage(String make, String model, Integer mileage);

   List<Car> findAllByMakeAndModelAndPower(String make, String model, String power);

   List<Car> findAllByMakeAndModelAndFuelAndPower(String make, String model, String fuel, String power);

   List<Car> findAllByMakeAndModelAndFuelAndPrice(String make, String model, String fuel, BigDecimal price);

   List<Car> findAllByMakeAndModelAndFuelAndMileage(String make, String model, String fuel, Integer mileage);

   List<Car> findAllByMakeAndModelAndPriceAndPower(String make, String model, BigDecimal price, String power);

   List<Car> findAllByMakeAndModelAndMileageAndPower(String make, String model, Integer mileage, String power);

   List<Car> findAllByMakeAndModelAndFuelAndPowerAndPrice(String make, String model, String fuel, String power, BigDecimal price);

   List<Car> findAllByMakeAndModelAndFuelAndPowerAndMileage(String make, String model, String fuel, String power, Integer Mileage);

   List<Car> findAllByMakeAndModelAndPriceAndPowerAndMileage(String make, String model, BigDecimal price, String power, Integer Mileage);

   List<Car> findAllByMakeAndModelAndFuelAndPriceAndMileage(String make, String model, String fuel, BigDecimal price, Integer mileage);

   Car save(CarRequest carRequest);

   Car update(UUID id, CarRequest carRequest);

   void deleteCar(UUID id);
}
