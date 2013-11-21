package rtrevino.dtex;


public class DTexBar {

	public long _Id;
	public String Name;
	public String Address;
	public String City;
	public String Phone;
	public String Website;
	public String Area;
	
	public double Latitude;
	public double Longitude;
	
	public int Rating;
	
	public String Open;
	public String Close;
	
	public DTexBar() {
		_Id = 0;
		Name = null;
		Address = null;
		City = null;
		Phone = null;
		Website = null;
		Area = null;
		
		Latitude = 0;
		Longitude = 0;
		
		Rating = 0;
	}
	
	public DTexBar(long id, String name, String addr, String city, String phone, String site, String area, double latitude, double longitude, int rating) {
		_Id = id;
		Name = name;
		Address = addr;
		City = city;
		Phone = phone;
		Website = site;
		Area = area;
		
		Latitude = latitude;
		Longitude = longitude;
		Rating = rating;
	}
	
	public long getId() {
		return _Id;
	}
	public void setId(long id) {
		_Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String addr) {
		Address = addr;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String site) {
		Website = site;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double lat) {
		Latitude = lat;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double lon) {
		Longitude = lon;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	
}
