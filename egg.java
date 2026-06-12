import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOExeption;
import java.io.InputStream;

public class Egg{
	
	public enum Rarity {COMMON, UNUSUAL, RARE, EPIC, LEGENDARY, ULTRA};
	Rarity rarity;
	int level;		//wird festgelegt durch die Elterneinhörner
	int poptime;		//Zeit benötigt zum Schlüpfen
	boolean hatched;
	private ScheduledExecutorService scheduler;
	int firstCrack;		//Zeit wann neue Textur geladen wird
	int secondCrack;		//Zeit wann neue Textur geladen wird
	
	public egg(Rarity r, int pop, int l)
	{
		this.rarity = r;
		this.level = l;
		poptime = pop;
		hatched = false;
		firstCrack = poptime/2;
		secondCrack = poptime/4;
		setEggTexture(1);		//Grundtextur wird gesetzt
	}
	
	
	boolean getIsHatched()		//Gibt Info über Schlüpfen
	{
		return hatched;
		}
			
	public int getPoptime()		//Gibt Info über Schlüpfzeit
	{
		return poptime;
	
	
	public void startHatching()		//Startet Schlüpfen
	{
		scheduler = Executors.newSingleThreadScheduledExecutor();
		
		final Runnable counter = () -> {		//Counter fürs Schlüpfen
			if(poptime > 0){
				poptime --;
				
				if(poptime == firstCrack){		//Läd neue Textur
					setEggTexture(2);
					}
					
				else if(poptime == secondCrack){		//Läd neue Textur
					setEggTexture(3);
					}
				}
			else{
				scheduler.shutdown();		//Ende, wenn geschlüpft
				hatched = true;
				}
			};
		scheduler.scheduleAtFixedRate(counter, 0, 1, TimeUnit.SECONDS);
		}
	
			
	private void setEggTexture(int stage)		//Legt Texturen fest
	{
		String imagePath = "";
		
		if(Rarity == COMMON){		//Texturen für COMMON
			if(stage == 1){
			imagePath = "/egg_C_whole.png";
			}
			else if(stage == 2){
			imagePath = "/egg_C_crack1.png";
			}
			else if(stage == 3){
			imagePath = "/egg_C_crack2.png";
			}
		}
		else if(Rarity == UNUSUAL){ˆˆ//Texturen für UNUSUAL
			if(stage == 1){
			imagePath = "/egg_U_whole.png";
			}
			else if(stage == 2){
			imagePath = "/egg_U_crack1.png";
			}
			else if(stage == 3){
			imagePath = "/egg_U_crack2.png";
			}
		}
		else if(Rarity == RARE){		//Texturen für RARE
			if(stage == 1){
			imagePath = "/egg_R_whole.png";
			}
			else if(stage == 2){
			imagePath = "/egg_R_crack1.png";
			}
			else if(stage == 3){
			imagePath = "/egg_R_crack2.png";
			}
		}
		else if(Rarity == EPIC){		//Texturen für EPIC
			if(stage == 1){
			imagePath = "/egg_E_whole.png";
			}
			else if(stage == 2){
			imagePath = "/egg_E_crack1.png";
			}
			else if(stage == 3){
			imagePath = "/egg_E_crack2.png";
			}
		}
		else if(Rarity == LEGENDARY){		//Texturen für LEGENDARY
			if(stage == 1){
			imagePath = "/egg_L_whole.png";
			}
			else if(stage == 2){
			imagePath = "/egg_L_crack1.png";
			}
			else if(stage == 3){
			imagePath = "/egg_L_crack2.png";
			}
		}
			
		try{
			InputStream is = getClass().getResourceAsStream(imagePath);
			
			if(is != null){
				currentTexture = ImageIO.read(is);
				}
			}
		catch (IOException e){
			e.printStackTrace();
			}
		}
}