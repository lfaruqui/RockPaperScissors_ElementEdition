public class FireStream extends Fire implements Playable 
{
   public FireStream()
   {
       super("Fire Stream");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target,attacker);
       target.setLives(target.getLives()-1);
       attacker.drawCard();
       
   }
}
