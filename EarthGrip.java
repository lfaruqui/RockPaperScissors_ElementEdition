public class EarthGrip extends Earth implements Playable 
{
   public EarthGrip()
   {
       super("Earth Grip");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target,attacker);
       attacker.getHand().add(target.getPlayedCard());
       
   }
}

