package com.lntinfotech.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;
import com.lntinfotech.util.ConnectionUtil;

public class ItemPostgres implements ItemDao {

    @Override
    public int addItem(Item item) {
        int id = -1;
        String sql = "insert into items (itemName, minimumOffer) values (?, ?) returning itemId";
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, item.getItemName());
            statement.setDouble(2, item.getMinimumOffer());

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                id = result.getInt("itemId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Item> getAllItems() {
        String sql = "select * from items";
        List<Item> items = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                int itemId = result.getInt("itemId");
                String itemName = result.getString("itemName");
                double minimumOffer = result.getDouble("minimumOffer");
                boolean isPurchased = result.getBoolean("isPurchased");
                Item item = new Item(itemId, itemName, minimumOffer, isPurchased);
                items.add(item);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Item getItemById(int itemId) {
        String sql = "select * from items where itemId = ?";
        Item item = null;
        try(Connection connection = ConnectionUtil.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int id = result.getInt("itemId");
                String itemName = result.getString("itemName");
                double minimumOffer = result.getDouble("minimumOffer");
                boolean isPurchased = result.getBoolean("isPurchased");
                item = new Item(id, itemName, minimumOffer, isPurchased);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public int updateItem(Item item) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String deleteItem(int itemId) {
        String sql1 = "delete from offers where itemId = ? returning offerId";
        String sql = "delete from items where itemId = ? returning itemName";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement firstStatement = connection.prepareStatement(sql1);
            firstStatement.setInt(1, itemId);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, itemId);

            firstStatement.executeQuery();

            ResultSet result = statement.executeQuery();

            String itemName = null;

            if(result.next()) {
                itemName = result.getString("itemName");
            }
            return itemName;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Offer> getItemsByOwner(int ownerId) {
        String sql = "select * from items join offers on offers.itemId = items.itemId where isAccepted = true and customerId = ?";
        List<Offer> offers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
        	PreparedStatement statement = connection.prepareStatement(sql);
        	statement.setInt(1, ownerId);
        	
        	ResultSet result = statement.executeQuery();
        	
        	while(result.next()) {
        		int offerId = result.getInt("offerId");
        		int customerId = result.getInt("customerId");
        		int itemId = result.getInt("itemId");
        		double offerAmount = result.getDouble("offerAmount");
        		boolean isAccepted = result.getBoolean("isAccepted");
        		Item item = new Item(result.getInt("itemId"), result.getString("itemName"), result.getDouble("minimumOffer"), result.getBoolean("isPurchased"));
        		
        		offers.add(new Offer(offerId, customerId, itemId, offerAmount, isAccepted, item, null));
        	}
        	
        } catch(SQLException e) {
        	e.printStackTrace();
        	return null;
        }
        
        return offers;
    }
    
}
