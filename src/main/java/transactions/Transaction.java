package transactions;

//makes data type called transaction
//get and set variables into object transaction
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private String description;
    private String vendor;
    private double amount;
    private LocalDate date;
    private LocalTime time;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
    }

        public LocalTime getTime () {
            return time;
        }

        public void setTime (LocalTime time){
            this.time = time;
        }

        public LocalDate getDate () {
            return date;
        }

        public void setDate (LocalDate date){
            this.date = date;
        }


        public double getAmount () {
            return amount;
        }

        public void setAmount ( double amount){
            this.amount = amount;
        }

        public String getVendor () {
            return vendor;
        }

        public void setVendor (String vendor){
            this.vendor = vendor;
        }

        public String getDescription () {
            return description;
        }

        public void setDescription (String description){
            this.description = description;
        }


    }

// object
