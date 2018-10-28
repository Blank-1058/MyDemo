package ThinkInJava.chapter8.codeTest;

/**
 * 向上转型
 */
public class Upwards {

    public static void main(String[] args){
        Wind wind=new Wind();
        tune(wind);
    }

    public static void tune(Instrument instrument){
        instrument.play(Note.C_SHARP);
        instrument.newMethod();
    }

    private static class Instrument{

        public void play(Note n){
            System.out.println("Instrument.play()");
        }

        public void newMethod(){
            System.out.println("Instrument.newMethod()");
        }
    }

    private static class Wind extends Instrument{

        public void play(Note t){
            System.out.println("Wind.play()    "+t);
        }
    }

    private enum Note{
        MIDDLE_C,C_SHARP,B_FLAG;
    }
}
