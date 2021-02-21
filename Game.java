/*
   Air is Throw, loses to Fire
   Earth is Block, Loses to Throws
   Fire is Attack, Loses to Blocks
*/


import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
   private Scanner in = new Scanner(System.in);
   private ArrayList<Card> playedCards = new ArrayList<>();
   private Player player1;
   private Player player2;
   private int roundResult;

   public Game()
   {
       player1= new Player();
       player2= new Player();
   }


   public void prompt()
   {
       System.out.println("Welcome to the game!");
       System.out.println("Will you be playing with someone (Enter 1) or against computer (Enter 0) ?");
       int input= in.nextInt();
       while (input != 1 && input != 0)
       {
           System.out.println("Please input a recognized value");
           input = in.nextInt();
       }
       if (input == 1)
       {
           twoPlayerGame();
       }
       else
       {
           onePlayerGame();
       }
   }

   public void onePlayerGame()
   {
       player1.buildDeck();
       player1.buildHand();

       player2.buildDeck();
       player2.buildHand();

       while(player1.getLives() > 0 && player2.getLives() > 0)
       {
          //First two print lines are for checking that draw works properly for tempHand and computer
           System.out.println("Com hand: " + player2.getHand());
           System.out.println("Com temphand: " + player2.getTempHand());
           playedCards.add(player1.play());
           playedCards.add(player2.randPlay());

           if(checkValidPlays())
           {
               System.out.println("\nYou played: " + playedCards.get(0));
               System.out.println("The computer played: " + playedCards.get(1));

               playEffects();
               printComRound();
               playedCards.clear();

               if(player1.getDeck().size() > 0)     //If deck isn’t empty, continue to draw from it
               {
                   player1.drawCard();
               }
               if(player2.getDeck().size() > 0)
               {
                   player2.drawCard();
               }
           }
           else                        //Else if there aren’t any valid plays, end the game
           {
               break;
           }
       }
       if(player1.getLives() > player2.getLives())
       {
           System.out.println("You won!");
       }
       else
       {
           System.out.println("You lost!");
       }
   }


   public void playEffects()
   {
       if(checkDraw())       //Check for when the played cards are of the same type
       {
           roundResult=0;
           player1.getHand().add(playedCards.get(0));
           player2.getHand().add(playedCards.get(0));
       }
       else if(playedCards.get(0) instanceof Air)
       {
           if(playedCards.get(1) instanceof Earth)
           {
               roundResult=1;
               playedCards.get(0).playCard(player2, player1);
           }
           else
           {
               roundResult=-1;
               playedCards.get(1).playCard(player1, player2);
           }
       }
       else if(playedCards.get(0) instanceof Earth)
       {
           if(playedCards.get(1) instanceof Fire)
           {
               roundResult=1;
               /*if(playedCards.get(1) instanceof FireBreath)
               {
                   playedCards.get(0).playCard(player2, player1);
                   playedCards.get(1).playCard(player1, player2);
               }
               FireBreath is Attack Special 3, where in "all cases" hand is discarded for damage
               In case "all cases" means even when losing a round, but FireBreath is played, want this ready
               */
               playedCards.get(0).playCard(player2, player1);
           }
           else
           {
               roundResult=-1;
               playedCards.get(1).playCard(player1, player2);
           }
       }
       else                                                        //Else when player 1 plays attack card
       {
           if(playedCards.get(1) instanceof Air)
           {
               roundResult=1;
               playedCards.get(0).playCard(player2, player1);
           }
           else
           {
               roundResult=-1;
               playedCards.get(1).playCard(player1, player2);
           }
       }
   }
   
   
   
  
   public void twoPlayerGame()
   {
       
       player1.buildDeck();
       player1.buildHand();

       player2.buildDeck();
       player2.buildHand();
       
       while(player1.getLives() > 0 && player2.getLives() > 0)
       {
           
               System.out.println("Player 1:");
               playedCards.add(player1.play());
               System.out.println("Player 2:");
               playedCards.add(player2.play());
               if(checkValidPlays())
               {
                           System.out.println("\nPlayer 1 played: " + playedCards.get(0));
                           System.out.println("Player 2 played: " + playedCards.get(1));
            
                           playEffects();
                           printMultiRound();
                           playedCards.clear();
            
                        if(player1.getDeck().size() > 0)     //If deck isn’t empty, continue to draw from it
                       {
                           player1.drawCard();
                       }
                        else                        //Else if there aren’t any valid plays, end the game
                       {
                               break;
                       } 
                       
                       if(player2.getDeck().size() > 0)
                       {
                           player2.drawCard();
                       }
                     
                        else                        //Else if there aren’t any valid plays, end the game
                       {
                               break;
                       }
               }
           
       }
   
           if(player1.getLives() > player2.getLives())
               {
                   System.out.println("Player 1 won!");
               }
               else
               {
                   System.out.println("Player 2 won!");
               }
    }    

  
   public void printComRound()          //Print method for results of each round vs computer
   {
       if(roundResult == 0)
       {
           System.out.println("It's a draw! No one wins this round, two of the same card types were played!");
       }
       else if(roundResult == 1)
       {
           System.out.println("You win this round!");
       }
       else
       {
           System.out.println("The computer wins this round!");
       }
       System.out.println("\n Your health: " + player1.getLives());
       System.out.println("Computer health: " + player2.getLives() + "\n");
   }
   
   public void printMultiRound()          //Print method for results of each round for multiplayer
   {
       if(roundResult == 0)
       {
           System.out.println("It's a draw! No one wins this round, two of the same card types were played!");
       }
       else if(roundResult == 1)
       {
           System.out.println("Player 1 wins this round!");
       }
       else
       {
           System.out.println("Player 2 wins this round!");
       }
       System.out.println("\n Player 1 health: " + player1.getLives());
       System.out.println("Player 2 health: " + player2.getLives() + "\n");
   }

   public boolean checkValidPlays()//Method to check whether both players can make a move
   {
       if(playedCards.get(0) == null && playedCards.get(1) == null)
       {
           return false;
       }
       return true;
   }

   public boolean checkDraw()//Method to check for a draw
   {
      if(playedCards.get(0) instanceof Fire && playedCards.get(1) instanceof Fire)
   {
       return true;
   }
   else if(playedCards.get(0) instanceof Air && playedCards.get(1) instanceof Air)
   {
       return true;
   }
   else if(playedCards.get(0) instanceof Earth && playedCards.get(1) instanceof Earth)
   {
       return true;
   }
   return false;
   }  
}
