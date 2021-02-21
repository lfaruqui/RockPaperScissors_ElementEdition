public class Air extends Card implements Playable
{
   public Air(String name)
   {
       super(name);
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       target.setLives(target.getLives()-1);
   }
}
