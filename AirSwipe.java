public class AirSwipe extends Air implements Playable
{
   public AirSwipe()
   {
       super("Air Swipe");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target,attacker);
       System.out.println("Enemy hand:");
       target.printHand();
       target.setCheck(3);
       target.buildTempHand();
   }
}
