package ThinkInJava.chapter5.Practice;

/**
 * 枚举类型enum的用法
 */
public class Practice21and22 {

    public static void main(String[] arg0){
        printEnum();
        print(Week.FRIDAY);
    }

    private enum Week{
        MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
    }

    private static void printEnum(){
        for(Week week:Week.values()){
            System.out.println("值:"+week+"   ordinal:"+week.ordinal());
        }
    }

    private static void print(Week week){
        switch (week){
            case MONDAY:
                System.out.println("MONDAY");
                break;
            case TUESDAY:
                System.out.println("TUESDAY");
                break;
            case WEDNESDAY:
                System.out.println("WEDNESDAY");
                break;
            case THURSDAY:
                System.out.println("THURSDAY");
                break;
            case FRIDAY:
                System.out.println("FRIDAY");
                break;
            case SATURDAY:
                System.out.println("SATURDAY");
                break;
            case SUNDAY:
                System.out.println("SUNDAY");
                break;
            default:
                System.out.println("other");
                break;
        }
    }
}
