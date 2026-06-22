import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Welcome to the Number Guessing Game!");
    System.out.print("I'm thinking of a number between 1 and 100.");
    System.out.println(" Can you guess it?");
    System.out.println("Your chances are based on the difficulty you choose\n");

    System.out.println(
        "Please select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");

    int difficulty;

    do {
      System.out.print("Enter your choice: ");
      difficulty = sc.nextInt();

      if (difficulty < 0 || difficulty > 3) {
        System.out.println("Invalid value. Please try again");
      }

    } while (difficulty < 0 || difficulty > 3);

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
    int tries = 0;
    int guess;
    Boolean pAgain = true;
    String playAgain = "";

    double randomNumber = Math.random() * 100;
    int randomInt = (int) randomNumber;

    Logic logic = new Logic();
    boolean guessed = false;

    do {

      if (difficulty == 1) {
        chances = 10;

        while (chances > 0) {
          System.out.print("Enter your guess: ");
          guess = sc.nextInt();
          sc.nextLine();

          guessed = logic.guessLogic(guess, randomInt, tries);

          if (guessed) {
            break;
          }

          chances--;
          tries++;
        }
        if (!guessed) {
          System.out.println("Waah you didn't guessed it. Better luck next time.");
          System.out.println("The random number was " + randomInt);
        }

      } else if (difficulty == 2) {
        chances = 5;

        while (chances > 0) {
          System.out.print("Enter your guess: ");
          guess = sc.nextInt();

          guessed = logic.guessLogic(guess, randomInt, tries);

          if (guessed) {
            break;
          }

          chances--;
          tries++;
        }
        if (!guessed) {
          System.out.println("Waah you didn't guessed it. Better luck next time.");
          System.out.println("The random number was " + randomInt);
        }

      } else if (difficulty == 3) {
        chances = 3;

        while (chances > 0) {
          System.out.print("Enter your guess: ");
          guess = sc.nextInt();

          guessed = logic.guessLogic(guess, randomInt, tries);

          if (guessed) {
            break;
          }

          chances--;
          tries++;
        }
        if (!guessed) {
          System.out.println("Waah you didn't guessed it. Better luck next time.");
          System.out.println("The random number was " + randomInt);
        }
      }
        System.out.print("\nDo you want to play again? (yes/no) ");
        playAgain = sc.next();

        if (playAgain.equalsIgnoreCase("yes")) {
          
          System.out.println(
          "\nPlease select the difficulty level:\n1. Easy (10 chances)\n2. Medium (5 chances)\n3. Hard (3 chances)\n");

          System.out.print("Enter your choice: ");
          difficulty = sc.nextInt();
          System.out.println();
        }

    } while (!playAgain.equalsIgnoreCase("no"));
    sc.close();
  }
}

class Logic {

  public boolean guessLogic(int guess, int randomInt, int tries) {
    if (guess == randomInt) {
      System.out.println("Congratulations. You guessed the correct number in " + tries + " attempts\n");
      return true;
    } else if (guess > randomInt) {
      System.out.println("Incorrect. The number is less than " + guess + "\n");
    } else {
      System.out.println("Incorrect. The number is more than " + guess + "\n");
    }

    return false;
  }
}