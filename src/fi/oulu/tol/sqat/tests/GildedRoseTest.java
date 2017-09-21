package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
	
	@Test 
	public void testUpdateEndOfDay_brieLessThan10Days() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 9, 5));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(6, item.getQuality());
		
	}
	
	@Test 
	public void testUpdateEndOfDay_brieLessThan5Days() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 4, 5));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(6, item.getQuality());
		
	}
	
	@Test public void testUpdateEndOfDay_brie100Days() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 200, 0));
		
		for (int i=0; i<100; ++i) {
			store.updateEndOfDay();
		}
		
		Item item = store.getItems().get(0);
		assertEquals(50, item.getQuality());
		
	}
	
	@Test 
	public void testUpdateEndOfDay_spoiledBrie() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 0, 5));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(6, item.getQuality());
		
	}
    
	@Test
	public void testUpdateEndOfDay_spoiled5DayQuality() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Item", 0, 20));
		
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(10, item.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_negativeQuality() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Item", 0, 2));
		
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(0, item.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_over50QualityStart() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Item", 5, 60));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(50, item.getQuality());
		
	}
	
	
	@Test
	public void testUpdateEndOfDay_normalFiveDayQuality() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Item", 20, 20));
		
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(15, item.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_spoilAfter2Days_FiveDayQuality() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Item", 2, 20));
		
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(12, item.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_normalSellInDegration() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Item", 7, 20));
		
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(2, item.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_wothlessTicket() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 5));
		
		for (int i=0; i<21; ++i) {
			store.updateEndOfDay();
		}
		
		Item item = store.getItems().get(0);
		assertEquals(0, item.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_ticketQualitySellin10() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(7, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_ticketQualitySellin5() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(8, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_ticketQualityOver50() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(50, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_brieQualityOver50() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(50, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_itemQualityOver50() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50));
		
		store.updateEndOfDay();
		
		Item item = store.getItems().get(0);
		assertEquals(50, item.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_1DayToConcert() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 5));
		
		for (int i=0; i<20; ++i) {
			store.updateEndOfDay();
		}
		
		Item item = store.getItems().get(0);
		assertEquals(40, item.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_sulfurasQuality() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 50));
		
		for (int i=0; i<20; ++i) {
			store.updateEndOfDay();
		}
		
		Item item = store.getItems().get(0);
		assertEquals(50, item.getQuality());
		
	}
	
	@Test
	public void testUpdateEndOfDay_sulfurasSellIn() {
		
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 50));
		
		for (int i=0; i<20; ++i) {
			store.updateEndOfDay();
		}
		
		Item item = store.getItems().get(0);
		assertEquals(2, item.getSellIn());
		
	}
}
