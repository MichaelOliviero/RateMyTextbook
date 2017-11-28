package msn.ratemytextbook;

/**
 * Created by Pallavi Singh
 */

public class MarketBook {

        public String bookTitle;
        public String bookAuthor;
        public String bookCourse;
        public int bookCCode;
        public String email;
        public String number;
        public String price;

        protected MarketBook() {
            bookTitle = "";
            bookAuthor = "";
            bookCourse = "";
            bookCCode = 0;
            email = "";
            number = "";
            price="";
        }

        protected MarketBook(String t, String a, String c,int code, String e, String num, String p) {
            bookTitle = t;
            bookAuthor = a;
            bookCourse = c;
            bookCCode = code;
            email = e;
            number = num;
            price = p;
        }

        public String getBookTitle() { return bookTitle; }
        public String getBookAuthor() { return bookAuthor; }
        public String getBookCourse() { return bookCourse; }
        public int getBookCCode(){ return bookCCode; }
        public String getSellerEmail() { return email; }
        public String getSellerNumber() { return number; }
        public String getPrice(){ return price; }

        public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
        public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
        public void setBookCourse(String bookCourse) { this.bookCourse = bookCourse; }
        public void setBookCCodeCourse(int bookCCode) { this.bookCCode = bookCCode; }
        public void setSellerEmail(String email) { this.email = email; }
        public void setSellerNumber(String number) { this.number = number; }
        public void setPrice(String price){ this.price = price; }
    }
