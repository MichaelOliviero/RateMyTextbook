package msn.ratemytextbook;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by Pallavi Singh
 * */
public class MarketBookList implements Serializable {

    public ArrayList<MarketBook> marketArrayList;

    protected MarketBookList() {
        this.marketArrayList = new ArrayList<MarketBook>();
    }

    public void addBook(MarketBook b) { this.marketArrayList.add(b); }

    public ArrayList<MarketBook> getBookArrayList() {
        return marketArrayList;
    }
    public MarketBook getBook(int i) { return marketArrayList.get(i); }
}
