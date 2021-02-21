public class EarthWall extends Earth implements Playable
{
     public EarthWall()
   {
       super("Earth Wall");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target,attacker);
       attacker.setLives(attacker.getLives() + 2);       
   }
}

