import stanford.karel.SuperKarel;

public class Homework extends SuperKarel {
    private int xAxis; private int yAxis; private int karelMoves;

    public void run(){
        karelMoves = 0; xAxis = 1; yAxis = 1;
        setBeepersInBag(1000);
        getMapDimensions();
        System.out.println("Map Dimensions are: "+xAxis+" x "+yAxis);

        if(xAxis == yAxis && xAxis >= 3)
                splitMapDiagonally4By4();
        else if (yAxis >= 3 && xAxis > 2) {
            if (yAxis % 2 != 0 && xAxis % 2 != 0)
                splitMapSingleBeeperLine(xAxis, yAxis);
            else if (yAxis % 2 != 0)
                setDoubleLineOfBeepersVertically(xAxis, yAxis);
            if (yAxis % 2 == 0 && xAxis % 2 != 0)
                setDoubleLineOfBeepersHorizontally(xAxis, yAxis);
            else if (yAxis % 2 == 0)
                setDoubleLineOfBeepersHorizontallyAndVertically(xAxis, yAxis);
        }

        else if(xAxis == 2 && yAxis == 2)
            splitMapDiagonally2by2();
        else if (yAxis < 3 && xAxis > 2) {
            if (xAxis == 3 || xAxis == 4) {
                splitIfX3Or4(xAxis);
            }
            else if (xAxis == 5 || xAxis == 6)
                splitInto3Equals(xAxis, yAxis);
            else
                splitInto4Equals(xAxis);
        }
        else if (xAxis < 3 && yAxis > 2) {
            if (yAxis == 3 || yAxis == 4) {
                splitIfX3Or4ForY(yAxis);
            }
            else if (yAxis == 5 || yAxis == 6)
                splitInto3EqualsForY(yAxis, xAxis);
            else
                splitInto4EqualsForY(yAxis);
        }
        System.out.println("Number of steps: "+karelMoves);
    }
    private void splitInto4EqualsForY(int y) {
        turnLeft();
        int newY = (y / 2);
        if (y % 2 != 0)
            moveKarelToTheMiddle(newY);
        else {
            if (xAxis == 1)
                moveKarelToTheMiddle(newY - 1);
            else moveKarelToTheMiddle(newY);
        }
        putBeeper();
        turnRight();
        if(frontIsClear()) {
            moveKarelAndPutOneBeeper();
            turnRight();
            if (y % 2 == 0)
                moveKarelAndPutOneBeeper();
        }else {
            turnLeft();
            if (y % 2 == 0)
                moveKarelOne();
        }
        splitInto2EqualsForY(newY + 1, y);
        if (yAxis % 2 != 0) {
            if ((newY - 1) % 2 == 0 && xAxis > 0)
                moveKarelToTheMiddle(newY / 2 + 1);
            else if ((newY - 1) % 2 != 0 && xAxis == 2)
                moveKarelToTheMiddle(newY / 2);
            else moveKarelToTheMiddle(newY / 2 + 1);
            splitInto2EqualsForY(newY + 1, y);
        }
        else {
            if ((newY - 1) % 2 != 0 && xAxis > 0){
                moveKarelToTheMiddle(newY / 2);
                putBeeper();
                moveKarelOne();
            }
            else if ((newY - 1) % 2 == 0 && xAxis > 0) {
                moveKarelToTheMiddle(newY / 2);
                putBeeper();
                moveKarelOne();
            }
            splitInto2EqualsForY(newY + 1, y);
        }
    }
    private void splitInto3EqualsForY(int y, int x) {
        if (y == 5){
            if (x == 1){
                turnLeft();
                moveKarelAndPutOneBeeper();
                moveKarelOne();
                moveKarelAndPutOneBeeper();
            }
            else {
                turnLeft();moveKarelAndPutOneBeeper();moveKarelOne();moveKarelAndPutOneBeeper();turnRight();moveKarelAndPutOneBeeper();
                turnRight();moveKarelOne();moveKarelAndPutOneBeeper();
            }
        }
        else{
            if(x == 1){
                turnLeft();
                moveKarelAndPutOneBeeper();
                moveKarelAndPutOneBeeper();
                moveKarelOne();moveKarelAndPutOneBeeper();
            }
            else{
                turnLeft();moveKarelAndPutOneBeeper();turnRight();moveKarelAndPutOneBeeper();turnLeft();moveKarelAndPutOneBeeper();
                turnLeft();moveKarelAndPutOneBeeper();turnRight();moveKarelOne();moveKarelAndPutOneBeeper();turnRight();
                moveKarelAndPutOneBeeper();
            }
        }
    }
    private void splitIfX3Or4ForY(int y) {
        turnLeft();
        if (y == 3) {
            if (xAxis == 1)
                moveKarelAndPutOneBeeper();
            else {
                moveKarelAndPutOneBeeper();
                turnRight();
                moveKarelAndPutOneBeeper();
            }
        }
        else {
            if (xAxis == 1){
                moveKarelAndPutOneBeeper();
                moveKarelAndPutOneBeeper();
            }
            else {
                moveKarelAndPutOneBeeper();
                moveKarelAndPutOneBeeper();
                turnRight();
                moveKarelAndPutOneBeeper();
                turnRight();
                moveKarelAndPutOneBeeper();
            }
        }
    }
    private void splitInto2EqualsForY(int newY,int y) {
        if (y % 2 != 0) {
            if (newY % 2 == 0) {
                moveKarelToTheMiddle(newY / 2);
                putBeeper();
                turnRight();
                if (frontIsClear()) {
                    moveKarelAndPutOneBeeper();
                    turnRight();
                } else turnRight();
            }else { // y odd and newY odd
                moveKarelToTheMiddle(newY / 2);
                putBeeper();
                moveKarelAndPutOneBeeper();
                turnRight();
                if (frontIsClear()){
                    moveKarelAndPutOneBeeper();
                    turnRight();
                    moveKarelAndPutOneBeeper();
                } else turnRight();
            }
        }else { // y even
            if (newY % 2 == 0) { // newY even
                moveKarelToTheMiddle(newY / 2 - 1);
                putBeeper();
                moveKarelAndPutOneBeeper();
                turnRight();
                if (frontIsClear()) {
                    moveKarelAndPutOneBeeper();
                    turnRight();
                    moveKarelAndPutOneBeeper();
                } else {turnRight();
                    moveKarelOne();
                }
            }else { // newY odd
                moveKarelToTheMiddle(newY / 2 );
                putBeeper();
                turnRight();
                if (frontIsClear()) {
                    moveKarelAndPutOneBeeper();
                    turnRight();
                } else turnRight();
            }
        }
    }
    private void splitIfX3Or4(int x) {
       if (x == 3) {
           if (yAxis == 1)
               moveKarelAndPutOneBeeper();
           else {
               moveKarelAndPutOneBeeper();
               turnLeft();
               moveKarelAndPutOneBeeper();
           }
       }
       else {
            if (yAxis == 1){
                moveKarelAndPutOneBeeper();
                moveKarelAndPutOneBeeper();
            }
            else {
                moveKarelAndPutOneBeeper();
                moveKarelAndPutOneBeeper();
                turnLeft();
                moveKarelAndPutOneBeeper();
                turnLeft();
                moveKarelAndPutOneBeeper();
            }
       }
    }
    private void splitInto2Equals(int newX,int x) {
        if (x % 2 != 0) {
            if (newX % 2 == 0) {
                moveKarelToTheMiddle(newX / 2);
                putBeeper();
                turnRight();
                if (frontIsClear()) {
                    moveKarelAndPutOneBeeper();
                    turnRight();
                } else turnRight();
            }else { // x odd and newX odd
                moveKarelToTheMiddle(newX / 2);
                putBeeper();
                moveKarelAndPutOneBeeper();
                turnRight();
                if (frontIsClear()){
                    moveKarelAndPutOneBeeper();
                    turnRight();
                    moveKarelAndPutOneBeeper();
                } else turnRight();
            }
        }else { // x even
            if (newX % 2 == 0) { // newX even
                moveKarelToTheMiddle(newX / 2 - 1);
                putBeeper();
                moveKarelAndPutOneBeeper();
                turnRight();
                if (frontIsClear()) {
                    moveKarelAndPutOneBeeper();
                    turnRight();
                    moveKarelAndPutOneBeeper();
                } else {turnRight();
                    moveKarelOne();
                }
            }else { // newX odd
                moveKarelToTheMiddle(newX / 2 );
                putBeeper();
                turnRight();
                if (frontIsClear()) {
                    moveKarelAndPutOneBeeper();
                    turnRight();
                } else turnRight();
            }
        }
    }
    private void splitInto3Equals(int x, int y) {
        if (x == 5){
            if (y == 1){
                moveKarelAndPutOneBeeper();
                moveKarelOne();
                moveKarelAndPutOneBeeper();
            }
            else {
                moveKarelAndPutOneBeeper();turnLeft();
                moveKarelAndPutOneBeeper();
                turnRight();moveKarelOne();
                moveKarelAndPutOneBeeper();
                turnRight();moveKarelAndPutOneBeeper();
            }
        }
        else{
            if(y == 1){
                moveKarelAndPutOneBeeper();
                moveKarelAndPutOneBeeper();
                moveKarelOne();moveKarelAndPutOneBeeper();
            }
            else{
                moveKarelAndPutOneBeeper();
                turnLeft();moveKarelAndPutOneBeeper();turnRight();
                moveKarelAndPutOneBeeper();
                turnRight();moveKarelAndPutOneBeeper();turnLeft();
                moveKarelOne();moveKarelAndPutOneBeeper();
                turnLeft();moveKarelAndPutOneBeeper();
            }
        }
    }
    private void splitInto4Equals(int x) {
            int newX = (x / 2);
            if (x % 2 != 0)
                moveKarelToTheMiddle(newX);
            else moveKarelToTheMiddle(newX - 1);
            putBeeper();
            turnLeft();
            if(frontIsClear()) {
                    moveKarelAndPutOneBeeper();
                    turnRight();
                if (x % 2 == 0){
                    moveKarelAndPutOneBeeper();
                }
            }else {
                turnRight();
                if (x % 2 == 0)
                    moveKarelOne();
            }
            splitInto2Equals(newX + 1, x);
            if (xAxis % 2 != 0) {
                if ((newX - 1) % 2 == 0 && yAxis > 0)
                    moveKarelToTheMiddle(newX / 2 + 1);
                else if ((newX - 1) % 2 != 0 && yAxis == 2)
                    moveKarelToTheMiddle(newX / 2);
                else moveKarelToTheMiddle(newX / 2 + 1);
                splitInto2Equals(newX + 1, x);
            }
            else {
                if ((newX - 1) % 2 != 0 && yAxis > 0){
                    moveKarelToTheMiddle(newX / 2);
                    putBeeper();
                    moveKarelOne();
                }
                else if ((newX - 1) % 2 == 0 && yAxis > 0) {
                    moveKarelToTheMiddle(newX / 2);
                    putBeeper();
                    moveKarelOne();
                }
                splitInto2Equals(newX + 1, x);
            }
    }
    private void getMapDimensions(){
        while(frontIsClear()){
            moveKarelOne();
            xAxis++;
        }
        turnLeft();
        while (frontIsClear()){
            moveKarelOne();
            yAxis++;
        }
        turnLeft();
    }
    private void moveKarelOne(){
        move();
        karelMoves++;
    }
    private void moveKarelIfPossible(){
        while(frontIsClear()){
            moveKarelOne();
        }
    }
    private void moveKarelAndPutOneBeeper(){
        moveKarelOne();
        putBeeper();
        karelMoves++;
    }
    private void putManyBeepers(){
        while (frontIsClear()){
            moveKarelAndPutOneBeeper();
        }
    }
    private void fillAndSkipBeepers(){
        while (frontIsClear()){
            moveKarelOne();
            if(noBeepersPresent())
                putBeeper();
        }
    }
    private void moveKarelToTheMiddle(int steps){
        for (int i = 0; i < steps; i++)
            moveKarelOne();
    }
    private void splitMapDiagonally4By4(){
        putBeeper();
        while(frontIsClear()) {
            moveKarelOne();
            turnLeft();
            moveKarelAndPutOneBeeper();
            turnRight();
        }
        turnRight();
        moveKarelIfPossible();
        putBeeper();
        turnRight();
        while (frontIsClear()){
            moveKarelOne();
            turnRight();
            moveKarelOne();
            if(beepersPresent()){
                turnLeft();
                continue;
            }
            putBeeper();
            turnLeft();
        }
    }
    private void splitMapDiagonally2by2() {
        putBeeper();
        moveKarelOne();
        turnLeft();
        moveKarelAndPutOneBeeper();
    }
    private void splitMapSingleBeeperLine(int x, int y){
        moveKarelToTheMiddle(x / 2);
        turnLeft();
        putBeeper();
        putManyBeepers();
        turnRight();
        moveKarelIfPossible();
        turnRight();
        moveKarelToTheMiddle(y / 2);
        turnRight();
        putBeeper();
        fillAndSkipBeepers();
    }
   private void setDoubleLineOfBeepersVertically(int x, int y){
        moveKarelToTheMiddle(x/2 - 1);
        turnLeft();
        putBeeper();
        putManyBeepers();
        turnRight();
        moveKarelAndPutOneBeeper();
        turnRight();
        putManyBeepers();
        turnLeft();
        moveKarelIfPossible();
        turnLeft();
        moveKarelToTheMiddle(y / 2);
        turnLeft();
        putBeeper();
        fillAndSkipBeepers();
   }
   private void setDoubleLineOfBeepersHorizontally(int x, int y){
       moveKarelToTheMiddle(x / 2);
       turnLeft();
       putBeeper();
       putManyBeepers();
       turnRight();
       moveKarelIfPossible();
       turnRight();
       moveKarelToTheMiddle(y/2 - 1);
       putBeeper();
       turnRight();
       fillAndSkipBeepers();
       turnLeft();
       moveKarelAndPutOneBeeper();
       turnLeft();
       fillAndSkipBeepers();
   }
   private void setDoubleLineOfBeepersHorizontallyAndVertically(int x, int y){
        moveKarelToTheMiddle(x/2 - 1);
        turnLeft();
        putBeeper();
        putManyBeepers();
        turnRight();
        moveKarelAndPutOneBeeper();
        turnRight();
        putManyBeepers();
        turnLeft();
        moveKarelIfPossible();
        turnLeft();
        moveKarelToTheMiddle(y/2 - 1);
        turnLeft();
        putBeeper();
        fillAndSkipBeepers();
        turnRight();
        moveKarelAndPutOneBeeper();
        turnRight();
        fillAndSkipBeepers();
   }
}
