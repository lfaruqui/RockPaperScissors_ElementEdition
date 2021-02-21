public abstract class Card implements Playable
{
   private String cardName;

   public Card(String newName)
   {
       cardName = newName;
   }

   public String getCardName()
   {
       return cardName;
   }

   @Override
   public String toString()
   {
       return cardName;
   }
  
}
