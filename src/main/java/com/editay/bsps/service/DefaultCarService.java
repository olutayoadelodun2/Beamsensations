package com.editay.bsps.service;

import com.editay.bsps.models.Car;
import com.editay.bsps.payload.request.CarRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultCarService implements CarService {
   @Autowired
   private CarRepository carRepository;

   public List<Car> findAll() {
      return this.carRepository.findAll();
   }

   public Car findOneById(UUID id) {
      return this.carRepository.findOneById(id);
   }

   public List<Car> findAllByMake(String make) {
      return this.carRepository.findAllByMake(make);
   }

   public List<Car> findAllByPrice(BigDecimal price) {
      return this.carRepository.findAllByPrice(price);
   }

   public List<Car> findAllByMileage(Integer mileage) {
      return this.carRepository.findAllByMileage(mileage);
   }

   public List<Car> findAllByPriceAndMileage(BigDecimal price, Integer mileage) {
      return this.carRepository.findAllByPriceAndMileage(price, mileage);
   }

   public List<Car> findAllByFuel(String fuel) {
      return this.carRepository.findAllByFuel(fuel);
   }

   public List<Car> findAllByPriceAndFuel(BigDecimal price, String fuel) {
      return this.carRepository.findAllByPriceAndFuel(price, fuel);
   }

   public List<Car> findAllByMakeAndModel(String make, String model) {
      return this.carRepository.findAllByMakeAndModel(make, model);
   }

   public List<Car> findAllByMakeAndFuel(String make, String fuel) {
      return this.carRepository.findAllByMakeAndFuel(make, fuel);
   }

   public List<Car> findAllByMakeAndPrice(String make, BigDecimal price) {
      return this.carRepository.findAllByMakeAndPrice(make, price);
   }

   public List<Car> findAllByMakeAndMileage(String make, Integer mileage) {
      return this.carRepository.findAllByMakeAndMileage(make, mileage);
   }

   public List<Car> findAllByMakeAndPower(String make, String power) {
      return this.carRepository.findAllByMakeAndPower(make, power);
   }

   public List<Car> findAllByMakeAndModelAndFuel(String make, String model, String fuel) {
      return this.carRepository.findAllByMakeAndModelAndFuel(make, model, fuel);
   }

   public List<Car> findAllByMakeAndModelAndPrice(String make, String model, BigDecimal price) {
      return this.carRepository.findAllByMakeAndModelAndPrice(make, model, price);
   }

   public List<Car> findAllByMakeAndModelAndMileage(String make, String model, Integer mileage) {
      return this.carRepository.findAllByMakeAndModelAndMileage(make, model, mileage);
   }

   public List<Car> findAllByMakeAndModelAndPower(String make, String model, String power) {
      return this.carRepository.findAllByMakeAndModelAndPower(make, model, power);
   }

   public List<Car> findAllByMakeAndModelAndFuelAndPower(String make, String model, String fuel, String power) {
      return this.carRepository.findAllByMakeAndModelAndFuelAndPower(make, model, fuel, power);
   }

   public List<Car> findAllByMakeAndModelAndFuelAndPrice(String make, String model, String fuel, BigDecimal price) {
      return this.carRepository.findAllByMakeAndModelAndFuelAndPrice(make, model, fuel, price);
   }

   public List<Car> findAllByMakeAndModelAndFuelAndMileage(String make, String model, String fuel, Integer mileage) {
      return this.carRepository.findAllByMakeAndModelAndFuelAndMileage(make, model, fuel, mileage);
   }

   public List<Car> findAllByMakeAndModelAndPriceAndPower(String make, String model, BigDecimal price, String power) {
      return this.carRepository.findAllByMakeAndModelAndPriceAndPower(make, model, price, power);
   }

   public List<Car> findAllByMakeAndModelAndMileageAndPower(String make, String model, Integer mileage, String power) {
      return this.carRepository.findAllByMakeAndModelAndMileageAndPower(make, model, mileage, power);
   }

   public List<Car> findAllByMakeAndModelAndFuelAndPowerAndPrice(String make, String model, String fuel, String power, BigDecimal price) {
      return this.carRepository.findAllByMakeAndModelAndFuelAndPowerAndPrice(make, model, fuel, power, price);
   }

   public List<Car> findAllByMakeAndModelAndFuelAndPowerAndMileage(String make, String model, String fuel, String power, Integer mileage) {
      return this.carRepository.findAllByMakeAndModelAndFuelAndPowerAndMileage(make, model, fuel, power, mileage);
   }

   public List<Car> findAllByMakeAndModelAndPriceAndPowerAndMileage(String make, String model, BigDecimal price, String power, Integer mileage) {
      return this.carRepository.findAllByMakeAndModelAndPriceAndPowerAndMileage(make, model, price, power, mileage);
   }

   public List<Car> findAllByMakeAndModelAndFuelAndPriceAndMileage(String make, String model, String fuel, BigDecimal price, Integer mileage) {
      return this.carRepository.findAllByMakeAndModelAndFuelAndPriceAndMileage(make, model, fuel, price, mileage);
   }

   public Car save(CarRequest carRequest) {
      Car newCar = new Car();
      newCar.setId(carRequest.getId());
      newCar.setMake(carRequest.getMake());
      newCar.setModel(carRequest.getModel());
      newCar.setDescription(carRequest.getDescription());
      newCar.setFuel(carRequest.getFuel());
      newCar.setImage(carRequest.getImage());
      newCar.setPrice(carRequest.getPrice());
      newCar.setPower(carRequest.getPower());
      newCar.setMileage(carRequest.getMileage());
      newCar.setDate(carRequest.getDate());
      newCar.setUsername(carRequest.getUsername());
      newCar.setSubscriptionType(carRequest.getSubscriptionType());
      return this.carRepository.save(newCar);
   }

   public Car update(UUID id, CarRequest carRequest) {
      Car carUpdate = this.carRepository.findOneById(id);
      if (carUpdate == null) {
         return null;
      } else {
         this.carRepository.delete(carUpdate);
         return this.save(carRequest);
      }
   }

   public void deleteCar(UUID id) {
      Car car = this.carRepository.findOneById(id);
      if (car != null) {
         this.carRepository.delete(car);
      }

   }
}
