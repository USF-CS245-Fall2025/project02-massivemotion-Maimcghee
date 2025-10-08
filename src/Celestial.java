public class Celestial{
    //atributes for star
    int starPosX;
    int starPosY;
    int starSize;
    int starVelX;
    int starVelY;

    //atributes for commet
    int genX;
    int genY;
    int bodySize;
    int bodyVel;

    //current x and y location of this celestial body
    int currX;
    int currY;

    //if we are reading star data we only want to make a star-> method will be called when we determine type of CB
    public void makeStar(int posX, int posY, int size, int velX, int velY){
        this.starPosX = posX;
        this.starPosY = posY;
        this.starSize = size;
        this.starVelX = velX;
        this.starVelY = velY;

    }
//if we are reading comme data we only want to make a commet->method will be called when we determine type of CB
    public void makeCommet(int commetX, int commetY, int size, int vel ){
        this.genX = commetX;
        this.genY = commetY;
        this.bodySize = size;
        this.bodyVel = vel;

    }


    //Private methods to help with the move method -> changes the current X and Y posiiton
    private void changeX(int newXPosition){
        this.currX = newXPosition;
    }
    private void changeY(int newYPosition){
        this.currY = newYPosition;
    }

    public void move(int velocity){
        //1) calculate new position based off of velocity

        //2) Update current X and Y locations accordingly

    }


}