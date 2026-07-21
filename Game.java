import java.util.Random;
import java.util.Scanner;

public class Game 
{
    private Scanner scanner;
    private Random random;

    private int secNum;
    private int maxAttem;
    private int diffchoice;
    private String diffcultName;

    private int easyHighSco;
    private int mediHighSco;
    private int hardHighSco;

    public Game(Scanner scanner)
    {
        this.scanner = scanner;
        this.random = new Random();

        easyHighSco = 0;
        mediHighSco = 0;
        hardHighSco = 0;
    }

    public void start()
    {
        displayWelcome();

        boolean playagain = true;

        while(playagain)
        {
            playRound();

            playagain = InputHelper.readYesOrNo(scanner,"\nDo you want to play again? (yes/no): ");

        }
        display();
        System.out.println();
        System.out.println("Thank you for playing the Number Guessing Game!");
    }

    private  void displayWelcome()
    {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Rules:");
                System.out.println("1. The computer will select a number between 1 and 100.");
                System.out.println("2. You must guess the number within the available attempts.");
                System.out.println("3. After a wrong guess , you will receive a higher or lower clue.");
                System.out.println("4. You can type \"hint\" once during each round.");


    }

    private void playRound()
    {
        selectDiff();

        secNum = random.nextInt(100) + 1;

        int attemUse = 0;


        boolean guessCorrect = false;
        boolean hintUsed = false;

        System.out.println();
        System.out.println("You selected the " + diffcultName + " difficulty level.");
        System.out.println("You have " + maxAttem + " attempts to guess the number.");

        showCurrentHigh();
        System.out.println("Enter a number or type \"hint\" for a clue.");
        
        long startTime = System.currentTimeMillis();

        while(attemUse < maxAttem && !guessCorrect)
        {
            String input = InputHelper.readGuessOrHint(scanner,"\nEnter your guess or type hint: ");

            if(input.equalsIgnoreCase("hint"))
            {
                if(!hintUsed)
                {
                    showhint();
                    hintUsed = true;
                }
                else 
                {
                    System.out.println("You have already used your hint for this round.");
                }
                continue;
            }

            int guess = Integer.parseInt(input);

            attemUse++;

            if(guess == secNum)
            {
                guessCorrect = true;

                System.out.println("");

                System.out.println("Congratulations! You guessed the correct number in " + attemUse + " attempt(s).");

                updateHighScore(attemUse);
            }

            else if(guess < secNum)
            {
                System.out.println("Incorrect! The number is greater than " + guess + ".");

            }

            else 
            {
                System.out.println("Incorrect! The number is less than " + guess + ".");
            
            }


            if(!guessCorrect)
            {
                int remainAttempt = maxAttem - attemUse;

                if(remainAttempt > 0)
                {
                    System.out.println("Remaining attempts: " + remainAttempt);
                }

            }
        }

        if(!guessCorrect)
        {

            System.out.println();
            System.out.println("You have run out of attempts.");
            System.out.println("The correct number was " + secNum + ".");
        }
            long endTime = System.currentTimeMillis();
            double  totalSec = (endTime - startTime) / 1000.00;
            System.out.println("Time taken: " + totalSec + " second(s).");
    }
        private void selectDiff()
        {
            System.out.println();
            System.out.println("Please select the difficulty level:");
            System.out.println("1. Easy (10 attempts)");
            System.out.println("2. Medium (5 attempts)");
            System.out.println("3. Hard (3 attempts)");

            diffchoice = InputHelper.readDifficultyChoice(scanner);

            if(diffchoice == 1)
            {
                diffcultName = "Easy";
                maxAttem = 10;
            }
            else if(diffchoice == 2)
            {
                diffcultName = "Medium";
                maxAttem = 5;
            }
            else 
            {
                diffcultName = "Hard";
                maxAttem = 3;
            }
        }

        private void showhint()
        {
            int lolimit = secNum - 10;
            int uplimit = secNum + 10;

            if(lolimit < 1)
            {
                lolimit = 1;
            }
            if(uplimit > 100)
            {
                uplimit = 100;
            }

            System.out.println();
            System.out.println("Hint: The number is between " + lolimit + " and " + uplimit + ".");

            if(secNum % 2 == 0)
            {
                System.out.println("Hint: The number is even.");
            }
            else 
            {
                System.out.println("Hint: The number is odd.");
            }
        }

        private void updateHighScore(int attempts)
        {
            if(diffchoice == 1)
            {
                if(easyHighSco == 0 || attempts < easyHighSco)
                {
                    easyHighSco = attempts;

                    System.out.println("New high score for Easy difficulty!");
                }
                else 
                {
                    System.out.println("Easy high score: " + easyHighSco + " attempt(s).");
                }
            }
            else if(diffchoice == 2)
            {

                if(mediHighSco == 0 || attempts < mediHighSco)
                {
                    mediHighSco = attempts;

                    System.out.println("New high score for Medium difficulty!");
                }
                else 
                {
                    System.out.println("Medium high score: " + mediHighSco + " attempt(s).");

                }
            }
            else 
            {
                if(hardHighSco == 0 || attempts < hardHighSco)
                {
                    hardHighSco = attempts;

                    System.out.println("New high score for Hard difficulty!");
                }
                else 
                {
                    System.out.println("Hard high score: " + hardHighSco + " attempt(s).");
                }
            }
        }

        private void showCurrentHigh()
        {
            if(diffchoice == 1)
            {
                if(easyHighSco == 0)
                {
                    System.out.println("Easy high score: No score recorded yet.");
                }
                else 
                {
                    System.out.println("Easy high score: " + easyHighSco + " attempt(s).");
                }
            }

            else if(diffchoice == 2)
            {
                if(mediHighSco == 0)
                {
                    System.out.println("Medium high score: No score recorded yet.");
                }
                else 
                {
                    System.out.println("Medium high score: " + mediHighSco + " attempt(s).");
                }
            }

            else if(diffchoice == 3)
            {
                if(hardHighSco == 0)
                {
                    System.out.println("Hard high score: No score recorded yet.");
                }
                else 
                {
                    System.out.println("Hard high score: " + hardHighSco + " attempt(s).");
                }
            }
        }

        private void display()
        {
            System.out.println();

            if(easyHighSco == 0)
            {
                System.out.println("Easy: No score recorded");
            }

            else 
            {
                System.out.println("Easy: " + easyHighSco + " attempt(s)");
            }

            if(mediHighSco == 0)
            {
                System.out.println("Medium: No score recorded");
            }
            else
            {
                System.out.println("Medium: " + mediHighSco + " attempt(s)");
            }

            if(hardHighSco == 0)
            {
                System.out.println("Hard: No score recorded");
            }
            else 
            {
                System.out.println("Hard: " + hardHighSco + " attempt(s)");
            }
            System.out.println();
        }
}
