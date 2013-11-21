package rtrevino.dtex;

public class DTexDrink {

	public long _Id;
	public long Bar_Id;
	public String Bar;
	public String Summary;
	public String Day;
	public String Special;
	public String Display;
	public Double Price;
	public int Rating;
	public int DrinkNumber;
	
	
	public DTexDrink() {
		_Id = 0;
		Bar_Id = 0;
		Bar = null;
		Summary = null;
		Day = null;
		Special = null;
		Display = null;
		Price = null;
		Rating = 0;
		DrinkNumber = 0;
	}
	
	public DTexDrink(long id, long barid, String bar, String summary, String day, String special, String display, Double price, int rating, int drink_num) {
		_Id = id;
		Bar_Id = barid;
		Bar = bar;
		Summary = summary;
		Day = day;
		Special = special;
		Display = display;
		Price = price;
		Rating = rating;
		DrinkNumber = drink_num;
		
	}
	
	public long getId() {
		return _Id;
	}
	public long getBarId() {
		return Bar_Id;
	}
	public String getBar() {
		return Bar;
	}
	public String getSummary() {
		return Summary;
	}
	public String getDay() {
		return Day;
	}
	public String getSpecial() {
		return Special;
	}
	public String getDisplay() {
		return Display;
	}
	public Double getPrice() {
		return Price;
	}
	public int getRating() {
		return Rating;
	}
	public int getDrinkNumber() {
		return DrinkNumber;
	}
	
	public void setId(long id) {
		_Id = id;
	}
	public void setBarId(long barid) {
		Bar_Id = barid;
	}
	public void setBar(String bar) {
		Bar = bar;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public void setDay(String day) {
		Day = day;
	}
	public void setSpecial(String special) {
		Special = special;
	}
	public void setDisplay(String display) {
		Display = display;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	public void setDrinkNumber(int drinknum) {
		DrinkNumber = drinknum;
	}
	
	@Override
	public String toString() {
		return this.getBar() + "  --  " + this.getDay() + "\n" + this.getSummary() + "\n" + this.getSpecial();
	}
	
}
