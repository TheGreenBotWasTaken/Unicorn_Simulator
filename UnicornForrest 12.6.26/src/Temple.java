public class Temple {

    double luck;
    double spawnRate;
    int remainTime;

    Temple(){

        luck = 1.0;
        spawnRate = 1.0;
        remainTime = 10;

    }

    double returnLuck(){
        return luck;
    }

    double returnSpawnRate(){
        return spawnRate;
    }

    int returnRemainTime(){
        return remainTime;
    }

    void setLuck(double luckNew){
        luck = luckNew;
    }

    void setSpawnRate(double spawnRateNew){
        spawnRate = spawnRateNew;
    }

    void setRemainTime(int remainTimeNew){
        remainTime = remainTimeNew;
    }

    void luckUp(){
        setLuck(luck+0.1);
    }

    void spawnRateUp() {
        setSpawnRate(spawnRate+0.1);
    }

    void remainTimeUp() {
        setRemainTime(remainTime + 1);
    }}
//Preis muss noch hinzugefügt werden
