import java.util.Scanner;

public class InputHelper 
{
    public static int readDifficultyChoice(Scanner scanner)
    {
        while(true)
        {
            System.out.println("Enter your choice: ");

            String input = scanner.nextLine().trim();

            try
            {
                int choice = Integer.parseInt(input);

                if(choice >= 1 && choice <= 3)
                {
                    return choice;
                }

                System.out.println("Invalid choice. Please enter 1 , 2 , or 3.");
            }

            catch (NumberFormatException exception)
            {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static String readGuessOrHint(Scanner scanner , String message)
    {
        while(true)
        {
            System.out.println(message);

            String input = scanner.nextLine().trim();

            if(input.equalsIgnoreCase("hint"))
            {
                return "hint";
            }

            try{
                int guess = Integer.parseInt(input);

                if(guess >= 1 && guess <= 100)
                {
                    return String.valueOf(guess);
                }

                System.out.println("Please enter a number between 1 and 100.");
            }

            catch(NumberFormatException exception)
            {
                System.out.println("Invalid input. Enter a number or type hint.");
            }
        }
    }

    public static boolean readYesOrNo(Scanner scanner , String message)
    {
        while(true)
        {
            System.out.println(message);
            String answer = scanner.nextLine().trim();

            if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))
            {
                return true;
            }
            else if(answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n"))
            {
                return false;
            }
            else 
            {
                System.out.println("Invalid input. Please enter yes or no.");
            }
        }
    }
}
