package Exception;

public class Account {
    protected double balance;

    public Account(double init){
        balance=init;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amt){
        balance+=amt;
    }

    public void withdraw(double amt) throws overdraftException{
        if(this.balance<amt){
            throw new overdraftException("余额不足",amt-balance);
        }
        balance-=amt;
    }

    class overdraftException extends Exception{
        private double deficit;

        public overdraftException(String msg,double deficit){
            super(msg);
            this.deficit=deficit;
        }

        public double getDeficit(){
            return deficit;
        }
    }

    static class CheckingAccount extends Account{
        private double overdraftProtection;

        public CheckingAccount(double balance){
            super(balance);
        }

        public CheckingAccount(double balance,double protect){
            super(balance);
            overdraftProtection=protect;
        }

        public void withdraw(double amt) throws overdraftException{
            if((balance+overdraftProtection)<amt){
                throw new overdraftException("账户额度不足",amt-balance-overdraftProtection);
            }
            balance-=amt;
        }

    }

    public static void main(String[] args) {
        Account a=new Account(1000.8);
        a.deposit(300);
        try{
            a.withdraw(2000);
        }catch(overdraftException e){
            System.out.println("异常原因为："+e.getMessage());
            System.out.println("透支金额："+e.getDeficit());
            e.printStackTrace();
        }

        CheckingAccount ca= new CheckingAccount(1000, 500);
        try{
            ca.withdraw(2000);
        }catch(overdraftException e){
            System.out.println("异常类型为："+e.getMessage());

        }
    }



}
