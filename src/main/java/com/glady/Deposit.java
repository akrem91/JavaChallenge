package main.java.com.glady;

import main.java.com.glady.models.Company;
import main.java.com.glady.models.User;

public interface Deposit {

    Deposit distribute(User user, Company company);
}
