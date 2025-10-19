public class Celestial{
    
    int startPosX; // starting position for CB X axis
    int startPosY; // starting position for CB Y axis 
    int velX;
    int velY;
    int genX;//probability of generating on x axis
    int genY; // probability of generating on Y axis
    int bodySize;
    
    //current x and y location of this celestial body
    int currX;
    int currY;

    //if we are reading star data we only want to make a star-> method will be called when we determine type of CB
    public Celestial(int startingPosX,int startingPosY, int size, int velX, int velY){
        //initializing innitial positions
        this.startPosX = startingPosX;
        this.startPosY = startingPosX;
        this.currX = startingPosX;
        this.currY = startingPosY;
        this.bodySize = size;
        this.velX = velX;
        this.velY = velY;
    }

    //move method to update location of CB
    public void move(){
        this.currX = currX * velX;
        this.currY = currY * velY;
    }

    public static <T> List<T> makeList(String listType){

        if(listType.equals("arraylist")){
            ArrayList list = new ArrayList<>();
            return list;
        }else if (listType.equals("doublylinkedlist")){
            DoublyLinkedList list = new DoublyLinkedList<>();
            return list;
        }else if (listType.equals("singlelinkedlist")){
            SingleLinkedList list = new SingleLinkedList<>();
            return list;
        }else if(listType.equals("dummyheadlinkedlist")){
            DummyHeadLinkedList list = new DummyHeadLinkedList<>();
            return list;
        }else{
            throw new IllegalArgumentException("Unknown list");
        }

    }


}