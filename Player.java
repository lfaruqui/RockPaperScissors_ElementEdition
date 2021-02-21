import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player
{
   private ArrayList<Card> deck= new ArrayList<>();
   private ArrayList<Card> hand= new ArrayList<>();
   private ArrayList<Card> tempHand= new ArrayList<>();
   private int lives;
   private Random rand;
   private Scanner in;
   private Card playedCard;
   private int check;

   public Player()
   {
       lives= 10;
       rand= new Random();
       in= new Scanner(System.in);
       check= 0;
   }

   public void setLives(int newLives)
   {
       lives= newLives;
   }

   public int getLives()
   {
       return lives;
   }

   public ArrayList<Card> getDeck()
   {
       return deck;
   }

   public ArrayList<Card> getHand()
   {
       return hand;
   }

   public ArrayList<Card> getTempHand()
   {
       return tempHand;
   }

   public void buildDeck()
   {
       for(int i =0; i < 27; i++)
       {
           if( i < 3)
           {
               deck.add(new AirBomb());
           }
           else if(i < 6)
           {
               deck.add(new AirShield());
           }
           else if(i < 9)
           {
               deck.add(new AirSwipe());
           }
           else if(i < 12)
           {
               deck.add(new EarthWall());
           }
           else if(i < 15)
           {
               deck.add(new EarthColumn());
           }
           else if(i < 18)
           {
               deck.add(new EarthGrip());
           }
           else if(i < 21)
           {
               deck.add(new FireStream());
           }
           else if(i < 24)
           {
               deck.add(new FireBall());
           }
           else
           {
               deck.add(new FireBreath());
           }
       }
   }

   public void buildHand()
   {
       while(hand.size() != 3)
       {
           Card randC= deck.get(rand.nextInt(deck.size()));
           hand.add(randC);
           deck.remove(randC);
       }
   }

   public void buildTempHand()                                //Method to build temporary hand for when a Throw card wins
   {
       tempHand.addAll(hand);                                  //Adds all of the elements in the hand to the temp array
       switch (check)
       {
           case 1:
               for(int i= 0; i < tempHand.size(); i++)
               {
                   if(tempHand.get(i) instanceof Earth)
                   {
                       tempHand.remove(i);
                   }
               }
           case 2:
               for(int i= 0; i < tempHand.size(); i++)
               {
                   if(tempHand.get(i) instanceof Fire)
                   {
                       tempHand.remove(i);
                   }
               }
           case 3:
               for(int i= 0; i < tempHand.size(); i++)
               {
                   if(tempHand.get(i) instanceof Air)
                   {
                       tempHand.remove(i);
                   }
               }
       }
   }

   public Card play()
   {
       int input;
       if(check != 0)
       {
           check=0;
           if(tempHand.size() > 0)
           {
               System.out.println("Which card do you want to play? (Enter the number next to the card)");
               printTempHand();
               input= in.nextInt();
           }
           else
           {
               System.out.println("You don't have anything to play from losing last round!");
               return null;
           }
       }
       else
       {
           System.out.println("Which card do you want to play? (Enter the number next to the card)");
           printHand();
           input= in.nextInt();
       }
       while(input < 0 || input > hand.size())
       {
           System.out.println("Please enter correct value!");
           input= in.nextInt();
       }
       input=input-1;
       playedCard= hand.get(input);
       hand.remove(input);
       tempHand.clear();
       return playedCard;
   }

   public Card randPlay()
   {
       if(check != 0)
       {
           check=0;
           if(tempHand.size() > 0)
           {
               playedCard= tempHand.get(rand.nextInt(tempHand.size()));
               tempHand.clear();
               return playedCard;
           }
           else
           {
               return null;
           }
       }
       else
       {
           playedCard= hand.get(rand.nextInt(hand.size()));
           hand.remove(playedCard);
           return playedCard;
       }
   }

   public void drawCard()
   {
       Card randC = deck.get(rand.nextInt(deck.size()));
       hand.add(randC);
       deck.remove(randC);
   }

   public Card getPlayedCard()
   {
       return playedCard;
   }

   public void removeHandCard(int i)
   {
       hand.remove(i);
   }

   public void removeDeckCard(int i)
   {
       deck.remove(i);
   }


   public void printDeck()
   {
       for(Card c : deck)
       {
           System.out.println(c.toString());
       }
   }

   public void printHand()
   {
       int i=0;
       for(Card c : hand)
       {
           i++;
           System.out.println(i + ". " + c.toString());
       }
   }

   public void printTempHand()
   {
       int i=0;
       for(Card c : hand)
       {
           i++;
           System.out.println(i + ". " + c.toString());
       }
   }

   public int getCheck()
   {
       return check;
   }

   public void setCheck(int newCheck)
   {
       check = newCheck;
   }



}

