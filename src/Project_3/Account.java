package Project_3;

  class Account {
    private String accountNo;
    private   int accountMoney;

      public Account(String accountNo, int accountMoney) {
          setAccountNo(accountNo);
          setAccountMoney(accountMoney);
      }

      public String getAccountNo() {
          return accountNo;
      }

      public void setAccountNo(String accountNo) {
          this.accountNo = accountNo;
      }

      public int getAccountMoney() {
          return accountMoney;
      }

      public void setAccountMoney(int accountMoney) {
          this.accountMoney = accountMoney;
      }
      public void moneyDepozit(int money){
          if (money>0 && money<10000) {
              accountMoney = accountMoney + money;
              System.out.println("The money has been uploaded successfully )");
          }else
              System.out.println("Yu did the wrong choice, you max can upload 9999");
      }
  }
