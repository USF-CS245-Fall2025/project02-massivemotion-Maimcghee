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

    /**
     * Constructs a Celestial body with the specified starting position, size, and velocity.
     *
     * @param startingPosX the starting X coordinate of the celestial body
     * @param startingPosY the starting Y coordinate of the celestial body
     * @param size the size (diameter) of the celestial body
     * @param velX the velocity in the X direction
     * @param velY the velocity in the Y direction
     */
    public Celestial(int startingPosX,int startingPosY, int size, int velX, int velY){
        //initializing innitial positions
        this.startPosX = startingPosX;
        this.startPosY = startingPosY;
        this.currX = startingPosX;
        this.currY = startingPosY;
        this.bodySize = size;
        this.velX = velX;
        this.velY = velY;
    }

    /**
     * Updates the current position of the celestial body by applying its velocity.
     * The X and Y coordinates are incremented by their respective velocities.
     */
    public void move(){
        this.currX += + velX;
        this.currY += velY;
    }

     /**
     * Creates and returns a list of the specified type.
     *
     * @param listType the type of list to create ("arraylist", "double", "single", or "dummyhead")
     * @return a new list instance of the specified type
     * @throws IllegalArgumentException if the listType is not one of the supported types
     */
    public static <T> List<T> makeList(String listType){

        if(listType.equals("arraylist")){
            ArrayList list = new ArrayList<>();
            return list;
        }else if (listType.equals("double")){
            DoublyLinkedList list = new DoublyLinkedList<>();
            return list;
        }else if (listType.equals("single")){
            SingleLinkedList list = new SingleLinkedList<>();
            return list;
        }else if(listType.equals("dummyhead")){
            DummyHeadLinkedList list = new DummyHeadLinkedList<>();
            return list;
        }else{
            throw new IllegalArgumentException("Unknown list");
        }

    }

}