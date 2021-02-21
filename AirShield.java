public class AirShield extends Air implements Playable
{
   public AirShield()
   {
       super("Air Shield");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target, attacker);
       attacker.setLives(attacker.getLives() + 2);
       target.setCheck(2);
       target.buildTempHand();
   }
}


