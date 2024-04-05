package de.beispiele.StockTradingSimulatorV2;

import de.beispiele.Utilities.ReadConfig;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

class StockTrader {
    private double balance;
    private List<Stock> portfolio;

    // TODO add a trader name

    public StockTrader(double initialBalance) {
        this.balance = initialBalance;
        this.portfolio = new ArrayList<>();
    }

    public void buyStock(String symbol, int quantity) {
        double price = getStockPrice(symbol);
        // TODO if stock price -1 --> Error
        double cost = price * quantity;
        if (cost <= balance) {
            balance -= cost;
            for (int i = 0; i < quantity; i++) {
                portfolio.add(new Stock(symbol, price));
            }
            System.out.println("Bought " + quantity + " shares of " + symbol + " at $" + price);
        } else {
            System.out.println("Insufficient funds to buy " + quantity + " shares of " + symbol);
        }
    }

    public void sellStock(String symbol, int quantity) {
        int count = 0;
        Iterator<Stock> iterator = portfolio.iterator();
        while (iterator.hasNext() && count < quantity) {
            Stock stock = iterator.next();
            if (stock.getSymbol().equals(symbol)) {
                iterator.remove();
                balance += stock.getPrice();
                count++;
            }
        }
        System.out.println("Sold " + quantity + " shares of " + symbol);
    }

    public double getBalance() {
        return balance;
    }

    public List<Stock> getPortfolio() {
        return portfolio;
    }

    private double getStockPrice(String symbol) {
        Properties config = ReadConfig.readPropertiesFile("config.properties");
        String apiKey = config.getProperty("alphavantageApiKey");
        String apiUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;

        try {
            String priceString = getPriceStringFromAPI(apiUrl);
            return Double.parseDouble(priceString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return -1; // Return -1 if there's an error fetching the price
        }
    }

    private static String getPriceStringFromAPI(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        System.out.println(jsonResponse);
        JSONObject globalQuote = jsonResponse.getJSONObject("Global Quote");
        String priceString = globalQuote.getString("05. price");
        return priceString;
    }
}