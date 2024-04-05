package de.beispiele.StockTradingSimulator;


import java.util.ArrayList;
import java.util.List;

class StockTrader {
    private double balance;
    private List<Stock> portfolio;

    public StockTrader(double initialBalance) {
        this.balance = initialBalance;
        this.portfolio = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost <= balance) {
            balance -= cost;
            for (int i = 0; i < quantity; i++) {
                portfolio.add(stock);
            }
            System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Insufficient funds to buy " + quantity + " shares of " + stock.getSymbol());
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int count = 0;
        for (int i = 0; i < portfolio.size(); i++) {
            if (portfolio.get(i).getSymbol().equals(stock.getSymbol())) {
                portfolio.remove(i);
                balance += stock.getPrice();
                count++;
                if (count == quantity)
                    break;
            }
        }
        System.out.println("Sold " + quantity + " shares of " + stock.getSymbol());
    }

    public double getBalance() {
        return balance;
    }

    public List<Stock> getPortfolio() {
        return portfolio;
    }
}