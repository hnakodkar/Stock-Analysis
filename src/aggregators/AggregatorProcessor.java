package aggregators;


import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.List;

public class AggregatorProcessor<M extends Aggregator> {

    M agg;
   String file;

    public AggregatorProcessor(M agg, String file) {

        this.agg = agg;
        this.file = file;
    }

    public double runAggregator(int collomX) throws IOException {
        StockFileReader fileReader = new StockFileReader(file);
        List<String> lines = fileReader.readFileData();
        collomX--;
        for(String line : lines){
           String [] numbers =  line.split(",");
           Double value = Double.parseDouble(numbers[collomX]);
           agg.add(value);
        }
        List<Double> values = agg.getValues();
        double number = agg.calculate();
        return number;
    }
}
