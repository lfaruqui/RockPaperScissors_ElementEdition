public class Earth extends Card implements Playable
{
   public Earth(String name)
   {
       super(name);
   }

   @Override
   public void playCard(Player target, Player attacker) {       
      attacker.getHand().add(attacker.getPlayedCard());//since played card was removed previously it is added back here to keep card
   }
}

