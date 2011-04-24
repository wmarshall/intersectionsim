package sim.intersect.common;
import java.util.Random;
public class Spawner {

	/**
	 * @param args
	 */
	
		// TODO Auto-generated method stub
		public Value setval(){
			Value settileval = null;
			int endval = new Random().nextInt(7);
			switch(endval){
				case 0:
					settileval = Value.Empty;
					break;
				case 1:
					settileval = Value.Carval1;
					break;
				case 2:
					settileval = Value.Carval2;
					break;
				case 3:
					settileval = Value.PubTrans;
					break;
				case 4:
					settileval = Value.Commercial;
					break;
				case 5:
					settileval = Value.Emergencyveh;
					break;
				case 6:
					settileval = Value.GovtVeh;
					break;
			
		}
			return settileval;
		

	}
}
