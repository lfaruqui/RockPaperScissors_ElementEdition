import java.util.Random;
public class EarthColumn extends Earth implements Playable 
{
   public EarthColumn()
   {
       super("Earth Column");
   }

   @Override
   public void playCard(Player target, Player attacker)
   {
       super.playCard(target,attacker);
       Random rand = new Random();
       target.removeHandCard(rand.nextInt(target.getHand().size()));
       
   }
}

