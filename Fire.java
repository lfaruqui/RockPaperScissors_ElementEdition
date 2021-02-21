public class Fire extends Card implements Playable
{
   public Fire(String name)
   {
       super(name);
   }

   @Override
   public void playCard(Player targetPlayer, Player attacker) {
     targetPlayer.setLives(targetPlayer.getLives()-1);
   }
}
