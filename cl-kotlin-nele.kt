import 'dart:io';

class Car {
  String name;
  double price;

  Car(this.name, this.price);

  void changePrice(double newPrice) {
    price = newPrice;
    print('The price of $name has been updated to \$$price.');
  }
}

class Person {
  String name;
  List<Car> ownedCars = [];
  double moneyLeft;

  Person(this.name, this.moneyLeft);

  void buyCar(Car car) {
    if (moneyLeft >= car.price) {
      ownedCars.add(car);
      moneyLeft -= car.price;
      print('Congrats, $name! You just bought a ${car.name} for \${car.price}. You now have \$$moneyLeft left.');
    } else {
      print('Sorry, $name! You don’t have enough money to buy a ${car.name}.');
    }
  }

  void sellCar(Car car) {
    if (ownedCars.contains(car)) {
      ownedCars.remove(car);
      moneyLeft += car.price;
      print('You sold your ${car.name} for \${car.price}. Now you have \$$moneyLeft.');
    } else {
      print('$name, you don’t own a ${car.name} to sell!');
    }
  }
}

void main() {
  Car car1 = Car('Toyota Camry', 25000);
  Car car2 = Car('Honda Civic', 20000);

  print(' Welcome to the Car Buying and Selling Platform!\n');
  print('What’s your name?');
  String userName = stdin.readLineSync()!;
  print('Hey $userName! How much money do you have?');
  double userMoney = double.parse(stdin.readLineSync()!);
  Person user = Person(userName, userMoney);

  while (true) {
    print('\nHere’s what you can do:');
    print('1. Buy a car');
    print('2. Change car price');
    print('3. Sell a car');
    print('4. Exit');
    print('Enter a number to choose an option:');

    int choice = int.parse(stdin.readLineSync()!);

    if (choice == 1) {
      print('\nAvailable Cars:');
      print('1. ${car1.name} - \$${car1.price}');
      print('2. ${car2.name} - \$${car2.price}');
      print('Which car would you like to buy? Enter the car number:');
      int carChoice = int.parse(stdin.readLineSync()!);
      
      switch (carChoice) {
        case 1:
          user.buyCar(car1);
          break;
        case 2:
          user.buyCar(car2);
          break;
        default:
          print('Oops! That’s not a valid option. Try again.');
      }

    } else if (choice == 2) {
      print('\nWhich car’s price do you want to change?');
      print('1. ${car1.name}');
      print('2. ${car2.name}');
      int carChoice = int.parse(stdin.readLineSync()!);
      print('Enter the new price:');
      double newPrice = double.parse(stdin.readLineSync()!);
      
      switch (carChoice) {
        case 1:
          car1.changePrice(newPrice);
          break;
        case 2:
          car2.changePrice(newPrice);
          break;
        default:
          print('Oops! That’s not a valid choice. Try again.');
      }

    } else if (choice == 3) {
      if (user.ownedCars.isEmpty) {
        print('\n$userName, you don’t have any cars to sell yet!');
      } else {
        print('\nHere are your cars, $userName:');
        for (int i = 0; i < user.ownedCars.length; i++) {
          print('${i + 1}. ${user.ownedCars[i].name}');
        }
        print('Which one would you like to sell? Enter the car number:');
        int sellChoice = int.parse(stdin.readLineSync()!);

        if (sellChoice >= 1 && sellChoice <= user.ownedCars.length) {
          user.sellCar(user.ownedCars[sellChoice - 1]);
        } else {
          print('Hmm… That’s not a valid choice. Try again.');
        }
      }

    } else if (choice == 4) {
      print('Thanks for visiting the Car Buying and Selling Platform, $userName! Have a great day!');
      break;
    } else {
      print('That’s not a valid option. Try again.');
    }
  }
}
