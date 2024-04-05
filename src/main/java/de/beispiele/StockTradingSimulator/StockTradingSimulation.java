package de.beispiele.StockTradingSimulator;

public class StockTradingSimulation {
    public static void main(String[] args) {
        Stock apple = new Stock("AAPL", 150.50);
        Stock microsoft = new Stock("MSFT", 300.75);

        // Example dataset
        StockTrader trader = new StockTrader(10000);
        trader.buyStock(apple, 5);
        trader.buyStock(microsoft, 3);

        // Selling some stocks
        trader.sellStock(apple, 2);

        // Print portfolio and balance
        System.out.println("Portfolio:");
        for (Stock stock : trader.getPortfolio()) {
            System.out.println(stock.getSymbol());
        }
        System.out.println("Balance: $" + trader.getBalance());
    }
}
