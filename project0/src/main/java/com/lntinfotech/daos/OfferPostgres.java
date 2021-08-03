package com.lntinfotech.daos;

import java.awt.print.Printable;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;
import com.lntinfotech.models.User;
import com.lntinfotech.util.ConnectionUtil;

import org.postgresql.core.SqlCommand;

public class OfferPostgres implements OfferDao {

    @Override
    public Offer getOffer(int offerId) {
        String sql = "select * from offers where offerId = ?";
        Offer offer = null;
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, offerId);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                int customerId = result.getInt("customerId");
                int itemId = result.getInt("itemId");
                double offerAmount = result.getDouble("offerAmount");

                offer = new Offer(customerId, itemId, offerAmount);
            }

            return offer;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean makeOffer(Offer offer) {
        String sql = "insert into offers(customerId, itemId, offerAmount) values (?, ?, ?) returning offerId";
        try(Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, offer.getCustomerId());
            statement.setInt(2, offer.getItemId());
            statement.setDouble(3, offer.getOfferAmount());

            statement.executeQuery();

            return true;
        } catch(SQLException e) {
            return false;
        }
    }

    @Override
    public List<Offer> getAllOffers() {
        String sql = "select * from offers join items on offers.itemId = items.itemId join users on offers.customerId = users.userId";
        List<Offer> offers = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                int offerId = result.getInt("offerId");
                int customerId = result.getInt("customerId");
                int itemId = result.getInt("itemId");
                double offerAmount = result.getDouble("offerAmount");
                boolean isAccepted = result.getBoolean("isAccepted");
                Item item = new Item(result.getInt("itemId"), result.getString("itemName"), result.getDouble("minimumOffer"), result.getBoolean("isPurchased"));
                User user = new User(result.getInt("userId"), result.getString("username"), result.getString("password"), result.getString("userType"));

                Offer offer = new Offer(offerId, customerId, itemId, offerAmount, isAccepted, item, user);

                offers.add(offer);
            }

            return offers;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean acceptOffer(int offerId, Offer offer) {
        String sql1 = "update offers set isAccepted = true where offerId = ? returning offerId";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql1);
            statement.setInt(1, offerId);
            

            statement.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean rejectOffer(int offer) {
        String sql1 = "delete from offers where offerId = ? returning offerId";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql1);
            statement.setInt(1, offer);

            statement.executeQuery();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Offer> getAcceptedOffersByCustomer(int customerId) {
        String sql = "select * from items join offers on offers.itemId = items.itemId where items.isPurchased = false and offers.isAccepted = true and offers.customerId = ?";
        List<Offer> offers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int offerId = result.getInt("offerId");
                int userId = result.getInt("customerId");
                int itemId = result.getInt("itemId");
                double offerAmount = result.getDouble("offerAmount");
                boolean isAccepted = result.getBoolean("isAccepted");
                Item item = new Item(result.getInt("itemId"), result.getString("itemName"), result.getDouble("minimumOffer"), result.getBoolean("isPurchased"));

                offers.add(new Offer(offerId, userId, itemId, offerAmount, isAccepted, item, null));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public List<Offer> getAllPaymentsReceived() {
        String sql = "select * from offers join items on offers.itemId = items.itemId join users on offers.customerId = users.userId where items.ispurchased = true";
        List<Offer> offers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int offerId = result.getInt("offerId");
                int customerId = result.getInt("customerId");
                int itemId = result.getInt("itemId");
                double offerAmount = result.getDouble("offerAmount");
                boolean isAccepted = result.getBoolean("isAccepted");
                Item item = new Item(result.getInt("itemId"), result.getString("itemName"), result.getDouble("minimumOffer"), result.getBoolean("isPurchased"));
                User user = new User(result.getString("username"));
                
                offers.add(new Offer(offerId, customerId, itemId, offerAmount, isAccepted, item, user));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offers;
    }
}
