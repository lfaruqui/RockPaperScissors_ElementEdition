public class FireBreath extends Fire implements Playable 
{
   public FireBreath()
   {
       super("Fire Breath");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target,attacker);
       int count = 0;
       for(int i = 0; i < attacker.getHand().size(); i++)
       {
           attacker.removeHandCard(i);
           count ++;
       }            
       target.setLives(target.getLives() - ((int)Math.floor(count/2)));
   }
}
