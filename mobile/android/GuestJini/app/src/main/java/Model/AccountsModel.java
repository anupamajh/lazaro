package Model;

public class AccountsModel {
    private String accountsTitle;
    private String accountsDescription;

    public AccountsModel(String accountsTitle, String accountsDescription) {
        this.accountsTitle = accountsTitle;
        this.accountsDescription = accountsDescription;
    }

    public String getAccountsTitle() {
        return accountsTitle;
    }

    public void setAccountsTitle(String accountsTitle) {
        this.accountsTitle = accountsTitle;
    }

    public String getAccountsDescription() {
        return accountsDescription;
    }

    public void setAccountsDescription(String accountsDescription) {
        this.accountsDescription = accountsDescription;
    }
}
