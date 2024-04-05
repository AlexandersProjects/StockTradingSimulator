package de.beispiele.StockTradingSimulatorV2;

public class RealisticStockTradingSimulation {
    public static void main(String[] args) {
        StockTrader trader = new StockTrader(10000);
        System.out.println("Stock Trader started with the balance: $" + trader.getBalance());

        // Example trades
        trader.buyStock("AAPL", 5);
        trader.buyStock("MSFT", 3);
        trader.sellStock("AAPL", 2);

        // Print portfolio and balance
//        System.out.println("Portfolio:");
//        for (Stock stock : trader.getPortfolio()) {
//            System.out.println(stock.getSymbol() + " - $" + stock.getPrice());
//        }
        Utils.summarizePortfolio(trader.getPortfolio());
        System.out.println("Balance: $" + trader.getBalance());
    }
}