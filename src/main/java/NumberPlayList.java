import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NumberPlayList {
    public static void main(String[] args) {
        //creating sample Collection
        List<Integer> myNumberList = new ArrayList<Integer>();
        for (int i=0; i<5; i++) myNumberList.add(i);

        //Method 1: traversing using Iterator
        Iterator<Integer> it = myNumberList.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println("Mth1: Iterator Value::"+i);
        }
        //Method 2: Traversing with Explicit Consumer interface implementation
        class MyConsumer implements Consumer<Integer>{
            public void accept(Integer t){
                System.out.println("Mth2: Consumer impl Value::"+t);
            }
        }
        MyConsumer action = new MyConsumer();
        myNumberList.forEach(action);

        //Method 3: Traversing with Anonymous Consumer interface implementation
        myNumberList.forEach(new Consumer<Integer>() {
            public void accept(Integer t) {
                System.out.println("Mth3: forEach anonymous class Value::"+t);

            }
        });
        //Method 4: Explicit lambda Function
        Consumer<Integer> myListAction = n-> {
            System.out.println("Mth4: forEach Lambda impl Value::" + n);
        };
        myNumberList.forEach(myListAction);

        //Method 5: Implicit Lambda Function
        myNumberList.forEach(n-> {
            System.out.println("Mth5: forEach Lambda impl Value::"+ n);
        });

        //Method 6: Implicit Lambda Function to print double value

        Function<Integer,Double> toDoubleFunction = Integer::doubleValue;
        Predicate<Integer> isEvenFunction = n -> n > 0 && n%2 ==0;
        List<Double> doubleArrayList = new ArrayList<Double>();
        List<Double> evenList = new ArrayList<Double>();
        myNumberList.forEach(n -> {
            doubleArrayList.add(toDoubleFunction.apply(n));
            doubleArrayList.forEach(n1 -> {
                if (isEvenFunction.test(n1.intValue()))
                    if (isEvenFunction.test(n1.intValue()))
                        evenList.add(n1);
            });
            doubleArrayList.forEach(n1 -> {
                if (isEvenFunction.test(n1.intValue()))
                    evenList.add(n1);
            });
            System.out.println("Even List" +evenList);

        });

        //Method 7: Implicit Lambda Function to check even

        myNumberList.forEach(n -> {
            System.out.println("Mth7: forEach value of: "+n+
                    " check for Even: " + isEvenFunction.test(n));
        });
        //Method 8: Processing the Stream
        myNumberList.stream().forEach( n ->{
            System.out.println("Mth8: Stream forEach Value::" + n);
        });
        //Method 9: Process the Stream, Apply Operations on the Stream and then
        //Store the Result
        List<Double> streamList = myNumberList.stream()
                                    .filter(isEvenFunction)
                                    .peek(n -> System.out.println("Peak Even Number: "+n))
                                    .map(toDoubleFunction)
                                  .collect(Collectors.toList());
        System.out.println("Mth9: Printing Double List: " + streamList);

        // Method 10: Listing the first Even
        Integer first = myNumberList.stream()
                .filter(isEvenFunction)
                .peek(n -> System.out.println("Peak Even Number: "+n))
                .findFirst()
                .orElse(null);
        System.out.println("Mth10: First Even: "+first);

        // Method 11: Minimum Even Numbers
        Integer min = myNumberList.stream().filter(isEvenFunction)
                .min((n1, n2) -> n1-n2)
                .orElse(null);
        System.out.println("Mth11: Min Even: "+min);

        // Method 12: Minimum Even Numbers
        Integer max = myNumberList.stream().filter(isEvenFunction)
                .max(Comparator.comparing(Integer::intValue))
                .orElse(null);
        System.out.println("Mth12: Max Even: "+max);

        // Method 13 Sum, Count and Average of numbers
        Integer sum = myNumberList.stream()
                .reduce(0, Integer::sum);
        long count = myNumberList.stream().count();
        System.out.println("Mth13: Avg of "+sum+"/"+count+" = "+sum/count);

        // Method 14 Checking all even, single even or none are divisible by 6
        boolean allEven = myNumberList.stream().allMatch(isEvenFunction);
        boolean oneEven = myNumberList.stream().anyMatch(isEvenFunction);
        boolean noneMultOfSix = myNumberList.stream().noneMatch(i -> i > 0 && i % 6 == 0);
        System.out.println("allEven: "+allEven+" oneEven: "+oneEven+
                " noneMultofSix: "+noneMultOfSix);
    }
}



