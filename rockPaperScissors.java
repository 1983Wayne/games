import textio.TextIO;

public class ComputerMove {
    public static void main (String[] args) {
        int humanScore = 0;
        int computerScore = 0;
        boolean again = true;
        int selection;                              // 1 = rock, 2 = paper, 3 = scissors
        int computerSelection;                      // 1 = rock, 2 = paper, 3 = scissors

        System.out.println("This program simulates rock, paper, scissors.");
        while (again) {

            // Select randomly what the computer is going to do
            computerSelection = (int)(Math.random() * 3 + 1);
            System.out.println("Computer selection is: " + computerSelection);


            // Find out what the player is going to do
            System.out.println("Which do you choose? Select 1, 2, or 3 below:");
            System.out.println("1 - Rock");
            System.out.println("2 - Paper");
            System.out.println("3 - Scissors");
            System.out.println();
            System.out.print("What is your selection? > ");
            selection = TextIO.getlnInt();

            // Find out what happens
            switch (selection) {
                case 1:
                    System.out.println("You chose rock");
                    if (computerSelection == 1) {
                        System.out.println("The computer also picked rock, so nobody gets the point!");
                    } else if (computerSelection == 2) {
                        System.out.println("The computer selected paper, and wins the point!");
                        computerScore++;
                    } else {
                        System.out.println("The computer chose scissors, you get the point!");
                        humanScore++;
                    }
                    break;

                case 2:
                    System.out.println("You chose paper");
                    if (computerSelection == 2) {
                        System.out.println("The computer also picked paper, so nobody gets the point!");
                    } else if (computerSelection == 3) {
                        System.out.println("The computer selected scissors, and wins the point!");
                        computerScore++;
                    } else {
                        System.out.println("The computer chose rock, you get the point!");
                        humanScore++;
                    }
                    break;

                case 3:
                    System.out.println("You chose scissors");
                    if (computerSelection == 3) {
                        System.out.println("The computer also picked scissors, so nobody gets the point!");
                    } else if (computerSelection == 1) {
                        System.out.println("The computer selected rock, and wins the point!");
                        computerScore++;
                    } else {
                        System.out.println("The computer chose paper, you get the point!");
                        humanScore++;
                    }
                    break;

                default:
                    System.out.println("That was an incorrect selection.");
                    System.out.println("We may have to start again.");
            }

            System.out.println("That means the player has " + humanScore + " points and the computer has " +
                    computerScore + " points.");

            System.out.println();
            System.out.print("Do you want to play again? ");
            again = TextIO.getlnBoolean();
            System.out.println();
        }

        System.out.println("The final score for the human player = " + humanScore);
        System.out.println("The final score for the computer player = " + computerScore);

        if (humanScore < computerScore) {
            System.out.println("Better luck next time.");
        } else {
            System.out.println("That was a close game, do better than you did this time please.");
        }

    }
}
