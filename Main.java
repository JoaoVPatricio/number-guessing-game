import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void clearConsole() {
    try {
      if (System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
      } else {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    clearConsole();

    System.out.println("Welcome to the Number Guessing Game!");
    System.out.print("I'm thinking of a number between 1 and 100.");
    System.out.println(" Can you guess it?");
    System.out.println("Your chances are based on the difficulty you choose\n");

    System.out.println("Please select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");
    

    int difficulty = 0;

    do {
      try {
        System.out.print("Enter your choice: ");
        difficulty = sc.nextInt();

        if (difficulty < 1 || difficulty > 3) {
          clearConsole();
          System.out.println("Invalid value. Please try again.\n");
          System.out.println("Please select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");
        }
      } catch (InputMismatchException e) {
        clearConsole();
        System.out.println("Invalid value! Enter a numeric number.\n");
        System.out.println("Please select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");
        sc.nextLine();
        continue;
      }
    } while (difficulty < 1 || difficulty > 3);

    clearConsole();

    String choiceInitialString = "\nGreat. You have selected the ";
    String choiceEndString = " difficulty level.\n";

    switch (difficulty) {
      case 1:
        System.out.println(choiceInitialString + "Easy" + choiceEndString);
        break;
      case 2:
        System.out.println(choiceInitialString + "Medium" + choiceEndString);
        break;
      case 3:
        System.out.println(choiceInitialString + "Hard" + choiceEndString);
      default:
        break;
    }

    int chances;
    int tries = 1;
    int guess;
    String playAgain = "";
    long finalTimeSeconds = 0;

    Logic logic = new Logic();
    boolean guessed = false;

    long startTime = 0;

    do {

      double randomNumber = Math.random() * 100;
      int randomInt = (int) randomNumber;

      if (difficulty == 1) {
        chances = 10;

        startTime = System.nanoTime();

        while (chances > 0) {
          try {
            System.out.print("Enter your guess: ");
            guess = sc.nextInt();
            guessed = logic.guessLogic(guess, randomInt, tries);
          } catch (InputMismatchException e) {
            clearConsole();
            System.out.println("Invalid value! Enter a numeric number.\n");
            sc.nextLine();
            continue;
          }


          if (guessed) {
            break;
          }

          chances--;
          tries++;
        }

      } else if (difficulty == 2) {
        chances = 5;

        startTime = System.nanoTime();

        while (chances > 0) {
          try {
            System.out.print("Enter your guess: ");
            guess = sc.nextInt();
            guessed = logic.guessLogic(guess, randomInt, tries);
          } catch (InputMismatchException e) {
            clearConsole();
            System.out.println("Invalid value! Enter a numeric number.\n");
            sc.nextLine();
            continue;
          }

          if (guessed) {
            break;
          }

          chances--;
          tries++;
        }

      } else if (difficulty == 3) {
        chances = 3;

        startTime = System.nanoTime();

        while (chances > 0) {
          try {
            System.out.print("Enter your guess: ");
            guess = sc.nextInt();
            guessed = logic.guessLogic(guess, randomInt, tries);
          } catch (InputMismatchException e) {
            clearConsole();
            System.out.println("Invalid value! Enter a numeric number.\n");
            sc.nextLine();
            continue;
          }

          if (guessed) {
            break;
          }

          chances--;
          tries++;
        }
      }

      if (!guessed) {
        System.out.println("Waah you didn't guessed it. Better luck next time.");
        System.out.println("The random number was " + randomInt);
      }

      if (guessed) {
        long endTime = System.nanoTime();
        finalTimeSeconds = (endTime - startTime) / 1000000000;

        if (finalTimeSeconds > 60) {
          int finalTimeMinutes = (int) finalTimeSeconds / 60;
          finalTimeSeconds = (int) finalTimeSeconds % 60;
          System.out.println("You guessed it in " + finalTimeMinutes + " minute and " + finalTimeSeconds + " seconds.");
        } else {
          System.out.println("You guessed it in " + finalTimeSeconds + " seconds.");
        }
      }

      do {
        System.out.print("\nDo you want to play again? (yes/no) ");

        playAgain = sc.next();
      } while ((!playAgain.equalsIgnoreCase("yes")) && (!playAgain.equalsIgnoreCase("no")));

      if (playAgain.equalsIgnoreCase("yes")) {
        tries = 0;
        difficulty = 0;

        clearConsole();

        System.out.println("\nPlease select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");

        do {
          try {
            System.out.print("Enter your choice: ");
            difficulty = sc.nextInt();
            System.out.println();

            if (difficulty < 1 || difficulty > 3) {
              clearConsole();
              System.out.println("Invalid value! Please try again.");
              System.out.println("\nPlease select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");
            }

          } catch (InputMismatchException e) {
            clearConsole();
            System.out.println("Invalid value! Enter a numeric number.");
            System.out.println("\nPlease select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");
            sc.nextLine();
            continue;
          }
        } while (difficulty < 1 || difficulty > 3);

        clearConsole();

        switch (difficulty) {
          case 1:
            System.out.println(choiceInitialString + "Easy" + choiceEndString);
            break;
          case 2:
            System.out.println(choiceInitialString + "Medium" + choiceEndString);
            break;
          case 3:
            System.out.println(choiceInitialString + "Hard" + choiceEndString);
          default:
          break;
        }
      }
    } while (!playAgain.equalsIgnoreCase("no"));
    sc.close();
  }
}

class Logic {

  public boolean guessLogic(int guess, int randomInt, int tries) {
    if (guess == randomInt) {
      System.out.println("Congratulations. You guessed the correct number in " + tries + " attempts");
      return true;
    } else if (guess > randomInt) {
      Main.clearConsole();
      System.out.println("Incorrect. The number is less than " + guess + "\n");
    } else {
      Main.clearConsole();
      System.out.println("Incorrect. The number is more than " + guess + "\n");
    }

    return false;
  }
}