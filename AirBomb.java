public class AirBomb extends Air implements Playable
{
   public AirBomb()
   {
       super("Air Bomb");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target,attacker);
       target.setLives(target.getLives()-1);
       attacker.drawCard();
       target.setCheck(1);
       target.buildTempHand();
   }
}
